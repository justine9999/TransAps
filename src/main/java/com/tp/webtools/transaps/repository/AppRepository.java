package com.tp.webtools.transaps.repository;


import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.channels.AsynchronousFileChannel;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ErrorCoded;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;
import com.microsoft.azure.documentdb.Document;
import com.microsoft.azure.documentdb.DocumentClient;
import com.microsoft.azure.documentdb.DocumentClientException;
import com.microsoft.azure.storage.blob.BlockBlobURL;
import com.microsoft.azure.storage.blob.ContainerURL;
import com.microsoft.azure.storage.blob.TransferManager;
import com.tp.webtools.transaps.dao.AppDao;
import com.tp.webtools.transaps.dao.AppIconStorageDao;
import com.tp.webtools.transaps.dao.DocumentClientFactory;
import com.tp.webtools.transaps.model.App;
import com.tp.webtools.transaps.model.Tag;

@Repository
public class AppRepository {
 
	public static final Logger logger = LoggerFactory.getLogger(AppRepository.class);
	
	//we convert long to string during serialization because it seems documentClient.createDocument doesn't support types other than string
	private static Gson gson = new GsonBuilder().setLongSerializationPolicy(LongSerializationPolicy.STRING).create();
	
	@Autowired
	private DocumentClientFactory documentClientFactory;
	
	@Autowired
    private AppDao appDao;
	
	@Autowired
    private AppIconStorageDao appIconStorageDao;
	
	private final static String[] sorter = new String[]{"rate","downloads","creation_time"};
    

    /**
     * Read all app documents
     */
    public List<App> readAllApps(Tag[] tags, int sort) {

    	DocumentClient documentClient = documentClientFactory.getDocumentClient();
    	List<App> apps = new ArrayList<App>();
    	
    	String[] array_to_search = new String[]{"purposes","languages","source_file_types","app_types"};
    	
    	String[] field_to_search = new String[]{"title","description","content","author","division"};
    	
    	String search_condition_clause = buildArrayFuzzySearchConditionClauseString(array_to_search, field_to_search, tags);
	
        final String query = "SELECT * FROM root r"
        		+ search_condition_clause
        		+ " ORDER BY r." + sorter[sort] + " DESC";
        
        System.out.println("query string: " + query);

        List<Document> documentList = documentClient.queryDocuments(appDao.getDocumentCollection().getSelfLink(), query, null).getQueryIterable().toList();
        for (Document appDocument : documentList) {
        	apps.add(gson.fromJson(appDocument.toString(), App.class));
        }
        
        logger.info(apps.size() + " App(s) read");
        
        return apps;
    }
    
    private String buildArrayFuzzySearchConditionClauseString(String[] array_to_search, String[] field_to_search, Tag[] tags){
    	StringBuilder sb = new StringBuilder();
    	for(Tag tag : tags){
    		if(sb.length()!=0) sb.append(" AND ");
    		for(int i = 0; i < array_to_search.length; i++){
    			String array = array_to_search[i];
    			sb.append((i == 0?"":" OR") + " ARRAY_CONTAINS(r." + array + ", '" + tag.getText() + "', true)");
    		}
    		
    		for(int i = 0; i < field_to_search.length; i++){
    			String field = field_to_search[i];
    			sb.append((i == 0&&array_to_search.length==0?"":" OR") + " CONTAINS(r." + field + ", '" + tag.getText() + "')");
    		}
    	}
    	
    	return sb.length()==0?"":" WHERE"+sb.toString();
    }

    /**
     * Find an app document by its title
     *
     * @param id
     */
    public App findAppByTitle(String title) {

    	DocumentClient documentClient = documentClientFactory.getDocumentClient();
    	App app = null;
        final String query = "SELECT * FROM root r WHERE r.title='" + title + "'";

        List<Document> documentList = documentClient.queryDocuments(appDao.getDocumentCollection().getSelfLink(), query, null).getQueryIterable().toList();
        if (documentList.size() > 0) {
            app = gson.fromJson(documentList.get(0).toString(), App.class);
        }else{
        	return null;
        }
        
        logger.info("found App: " + app.toString());
        
        return app;
    }

    /**
     * Create an app
     */
    public App createApp(App app, String croppedImage) {
    	
    	if(croppedImage.length() != 0){
    		String[] imageDatas = croppedImage.split(",");
        	String fromat = imageDatas[0].split("/")[1].split(";")[0];
        	String base64Data = imageDatas[1];

        	upuloadAppIcon(base64Data, fromat, app);
    	}
    	
    	
    	DocumentClient documentClient = documentClientFactory.getDocumentClient();
    	Document appDocument = new Document(gson.toJson(app));
    	appDocument.set("entityType", "app");

    	try {
    		appDocument = documentClient.createDocument(appDao.getDocumentCollection().getSelfLink(), appDocument, null, false).getResource();
        } catch (DocumentClientException ex) {
            ex.printStackTrace();
            return null;
        }
        
    	App created_app = gson.fromJson(appDocument.toString(), App.class);
    	
    	logger.info("created App: " + created_app.toString());
    	
        return created_app;
    }
    
    /**
     * Delete an app
     * @throws DocumentClientException 
     */
    public App deleteApp(String title) throws DocumentClientException {

    	DocumentClient documentClient = documentClientFactory.getDocumentClient();
    	App app = null;
        final String query = "SELECT * FROM root r WHERE r.title='" + title + "'";

        try {
        	List<Document> documentList = documentClient.queryDocuments(appDao.getDocumentCollection().getSelfLink(), query, null).getQueryIterable().toList();
            if(documentList.size() == 0){
            	throw new DocumentClientException(404);
            }
        	app = gson.fromJson(documentList.get(0).toString(), App.class);
        	documentClient.deleteDocument(documentList.get(0).getSelfLink(), null);
        }catch(DocumentClientException ex) {
        	throw ex;
        }catch(Exception ex) {
        	ex.printStackTrace();
        	return null;
        }

    	logger.info("Deleted App: " + app.toString());
    	
    	return app;
    }
    
    private void upuloadAppIcon(String base64Data, String format, App app){
    	
    	byte[] decodedBytes = Base64.getDecoder().decode(base64Data);
    	ByteArrayInputStream bis = new ByteArrayInputStream(decodedBytes);
    	BufferedImage image;
    	File outputFile = null;
    	String fileName = app.getTitle() + "_icon." + format;
		try {
			
			image = ImageIO.read(bis);
	    	outputFile = new File(fileName);
	    	ImageIO.write(image, format, outputFile);
	    	
	    	ContainerURL containerURL = appIconStorageDao.getContainerURL();
	    	BlockBlobURL blobURL = containerURL.createBlockBlobURL(fileName);
	    	app.setProfile_picture(blobURL.toString());
	    	AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(outputFile.toPath());

	        TransferManager.uploadFileToBlockBlob(fileChannel, blobURL, 8*1024*1024, null)
	        .subscribe(response-> {
	            System.out.println("Completed upload image: " + fileName);
	            System.out.println(response.response().statusCode());
	        });
	    	
		} catch (IOException ex) {
			ex.printStackTrace();
		}finally{
			outputFile.delete();
		}
    }
}

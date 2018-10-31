package com.tp.webtools.transaps.repository;


import java.util.ArrayList;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;
import com.microsoft.azure.documentdb.Document;
import com.microsoft.azure.documentdb.DocumentClient;
import com.microsoft.azure.documentdb.DocumentClientException;
import com.tp.webtools.transaps.dao.AppDao;
import com.tp.webtools.transaps.dao.DocumentClientFactory;
import com.tp.webtools.transaps.model.App;

@Repository
public class AppRepository {
 
	public static final Logger logger = LoggerFactory.getLogger(AppRepository.class);
	
	//we convert long to string during serialization because it seems documentClient.createDocument doesn't support types other than string
	private static Gson gson = new GsonBuilder().setLongSerializationPolicy(LongSerializationPolicy.STRING).create();
	
	@Autowired
	private DocumentClientFactory documentClientFactory;
	
	@Autowired
    private AppDao appDao;
    

    /**
     * Read all app documents
     */
    public List<App> readAllApps() {

    	DocumentClient documentClient = documentClientFactory.getDocumentClient();
    	List<App> apps = new ArrayList<App>();
        final String query = "SELECT * FROM root r WHERE r.entityType = 'App'";

        List<Document> documentList = documentClient.queryDocuments(appDao.getDocumentCollection().getSelfLink(), query, null).getQueryIterable().toList();
        for (Document appDocument : documentList) {
        	apps.add(gson.fromJson(appDocument.toString(), App.class));
        }
        
        logger.info(apps.size() + " App(s) read");
        
        return apps;
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
    public String createApp(App app) {

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
    	
        return appDocument.getId();
    }
    
}

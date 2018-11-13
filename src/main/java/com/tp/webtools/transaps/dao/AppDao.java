package com.tp.webtools.transaps.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.microsoft.azure.documentdb.Database;
import com.microsoft.azure.documentdb.DocumentClient;
import com.microsoft.azure.documentdb.DocumentClientException;
import com.microsoft.azure.documentdb.DocumentCollection;

@Component
public class AppDao {

	@Autowired
	DocumentClientFactory documentClientFactory;
	
	@Value("${datasource.transaps.azure.cosmosdb.sql.appDB}")
    private String DATABASE_ID;
    
    @Value("${datasource.transaps.azure.cosmosdb.sql.appCollection}")
    private String COLLECTION_ID;
    
    private Database database = null;
    
    private DocumentCollection  dcumentCollection  = null;
    
    public Database getDatabase() {
        if (database == null) {
        	
        	DocumentClient  documentClient = documentClientFactory.getDocumentClient();
        	final String query = "SELECT * FROM root r WHERE r.id='" + DATABASE_ID + "'";
            List<Database> databaseList = documentClient.queryDatabases(query, null).getQueryIterable().toList();

            if (databaseList.size() > 0) {
            	database = databaseList.get(0);
            } else {
            	
                try {
                    Database databaseDefinition = new Database();
                    databaseDefinition.setId(DATABASE_ID);

                    database = documentClient.createDatabase(databaseDefinition, null).getResource();
                } catch (DocumentClientException ex) {
                    ex.printStackTrace();
                }
            }
        }

        return database;
    }
    
    public DocumentCollection getDocumentCollection() {
        if (dcumentCollection == null) {
        	
        	DocumentClient  documentClient = documentClientFactory.getDocumentClient();
        	final String query = "SELECT * FROM root r WHERE r.id='" + COLLECTION_ID + "'";
            List<DocumentCollection> collectionList = documentClient.queryCollections(getDatabase().getSelfLink(), query, null).getQueryIterable().toList();

            if (collectionList.size() > 0) {
            	dcumentCollection = collectionList.get(0);
            } else {
            	
                try {
                    DocumentCollection collectionDefinition = new DocumentCollection();
                    collectionDefinition.setId(COLLECTION_ID);

                    dcumentCollection = documentClient.createCollection(getDatabase().getSelfLink(), collectionDefinition, null).getResource();
                } catch (DocumentClientException ex) {
                    ex.printStackTrace();
                }
            }
        }

        return dcumentCollection;
    }
}

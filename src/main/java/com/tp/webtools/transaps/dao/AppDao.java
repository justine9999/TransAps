package com.tp.webtools.transaps.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.microsoft.azure.documentdb.DataType;
import com.microsoft.azure.documentdb.Database;
import com.microsoft.azure.documentdb.DocumentClient;
import com.microsoft.azure.documentdb.DocumentClientException;
import com.microsoft.azure.documentdb.DocumentCollection;
import com.microsoft.azure.documentdb.IndexingPolicy;
import com.microsoft.azure.documentdb.IncludedPath;
import com.microsoft.azure.documentdb.Index;

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
                    
                    IndexingPolicy indexingPolicy = new IndexingPolicy();
                    Collection<IncludedPath> includedPaths = new ArrayList<IncludedPath>();
                    IncludedPath includedPath = new IncludedPath();
                    includedPath.setPath("/*");
                    Collection<Index> indexes = new ArrayList<Index>();
                    Index stringIndex = Index.Range(DataType.String);
                    stringIndex.set("precision", -1);
                    indexes.add(stringIndex);

                    Index numberIndex = Index.Range(DataType.Number);
                    numberIndex.set("precision", -1);
                    indexes.add(numberIndex);
                    includedPath.setIndexes(indexes);
                    includedPaths.add(includedPath);
                    indexingPolicy.setIncludedPaths(includedPaths);
                    collectionDefinition.setIndexingPolicy(indexingPolicy);

                    dcumentCollection = documentClient.createCollection(getDatabase().getSelfLink(), collectionDefinition, null).getResource();
                } catch (DocumentClientException ex) {
                    ex.printStackTrace();
                }
            }
        }

        return dcumentCollection;
    }
}

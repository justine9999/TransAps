package com.tp.webtools.transaps.dao;


import javax.annotation.PreDestroy;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.microsoft.azure.documentdb.ConnectionPolicy;
import com.microsoft.azure.documentdb.ConsistencyLevel;
import com.microsoft.azure.documentdb.DocumentClient;


@Component
public class DocumentClientFactory {
	
	public static final Logger logger = LoggerFactory.getLogger(DocumentClientFactory.class);

	@Value("${datasource.transaps.azure.cosmosdb.sql.host}")
    private String host;
    
    @Value("${datasource.transaps.azure.cosmosdb.sql.key}")
    private String key;
    
    DocumentClient documentClient = null;
    
        
    public DocumentClientFactory() {
    	System.out.println("CassandraSessionFactory constructor loaded");
    }
    
    
    @PreDestroy
    public void destroy() {
        try {
            if(this.documentClient != null) {
            	this.documentClient.close();
            }
        } catch (Exception e) {
        	logger.error("Failed to clean up documentClient", e);
        }
    }
    
    public DocumentClient getDocumentClient() {

    	try {

            if(this.documentClient == null){
            	this.documentClient = new DocumentClient(host, key, ConnectionPolicy.GetDefault(), ConsistencyLevel.Session);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    	
        return this.documentClient;
    }
}

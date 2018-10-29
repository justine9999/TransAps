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
public class CosmosDbFactory {
	
	public static final Logger logger = LoggerFactory.getLogger(CosmosDbFactory.class);

	@Value("${datasource.transaps.azure.cosmosdb.uri}")
    private String uri;

    @Value("${datasource.transaps.azure.cosmosdb.key}")
    private String key;
    
    private DocumentClient documentClient;
    
    @PreDestroy
    public void destroy() {
        try {
            this.documentClient.close();
        } catch (Exception e) {
        	logger.error("Failed to clean up DocumentClient", e);
        }
    }

    public DocumentClient getDocumentClient() {
    	if(this.documentClient == null) {
    		this.documentClient = new DocumentClient(uri, key, ConnectionPolicy.GetDefault(), ConsistencyLevel.Session);
    	}
        return this.documentClient;
    }
}

package com.tp.webtools.transaps.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.microsoft.azure.storage.blob.ContainerURL;
import com.microsoft.azure.storage.blob.ServiceURL;
import com.microsoft.azure.storage.blob.models.ContainerCreateResponse;
import com.microsoft.azure.storage.blob.models.PublicAccessType;
import com.microsoft.rest.v2.RestException;

@Component
public class AppIconStorageDao {

	@Autowired
	StorageServiceFactory storageServiceFactory;
	
	@Value("${datasource.transaps.azure.storage.appIconContainer}")
    private String container;
        
    private ContainerURL containerURL = null;
    
    
    public ContainerURL getContainerURL() {

    	ServiceURL serviceURL = storageServiceFactory.getServiceURL();
    	this.containerURL = serviceURL.createContainerURL(this.container);

        try {
            ContainerCreateResponse response = containerURL.create(null, PublicAccessType.BLOB, null).blockingGet();
            System.out.println("Container Create Response was " + response.statusCode());
        } catch (RestException e){
            if (e instanceof RestException && ((RestException)e).response().statusCode() != 409) {
            	System.out.println("Service error: " + e.response().statusCode() );
            	throw e;
            } else {
                System.out.println("'" + this.container + "' container already exists, resuming...");
            }
        }
        
        return this.containerURL;
    }
}

package com.tp.webtools.transaps.dao;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.InvalidKeyException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.microsoft.azure.storage.blob.PipelineOptions;
import com.microsoft.azure.storage.blob.ServiceURL;
import com.microsoft.azure.storage.blob.SharedKeyCredentials;
import com.microsoft.azure.storage.blob.StorageURL;

@Component
public class StorageServiceFactory {
	
	public static final Logger logger = LoggerFactory.getLogger(DocumentClientFactory.class);

	@Value("${datasource.transaps.azure.storage.account}")
    private String accountName;
    
    @Value("${datasource.transaps.azure.storage.key}")
    private String accountKey;
    
    private ServiceURL serviceURL = null;
    
    
    public ServiceURL getServiceURL() {

    	try {

            if(this.serviceURL == null){
            	createServiceURL();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    	
        return this.serviceURL;
    }
    
    private void createServiceURL() {
    	
    	SharedKeyCredentials creds;
		try {
			creds = new SharedKeyCredentials(accountName, accountKey);
	        this.serviceURL = new ServiceURL(new URL("https://" + accountName + ".blob.core.windows.net"), StorageURL.createPipeline(creds, new PipelineOptions()));

		} catch (InvalidKeyException e) {
            System.out.println("Invalid Storage account name/key provided");
        } catch (MalformedURLException e) {
            System.out.println("Invalid URI provided");
        } catch (Exception ex){
        	ex.printStackTrace();
        }
    }
}

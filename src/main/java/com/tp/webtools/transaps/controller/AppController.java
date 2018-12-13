package com.tp.webtools.transaps.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.tp.webtools.transaps.service.AppService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.microsoft.azure.documentdb.DocumentClientException;
import com.tp.webtools.transaps.exception.AppConflictException;
import com.tp.webtools.transaps.exception.AppNotFoundException;
import com.tp.webtools.transaps.exception.InternalServerException;
import com.tp.webtools.transaps.model.App;
import com.tp.webtools.transaps.model.Tag;


@RestController
@RequestMapping("/api")
public class AppController {
	 public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	 
	    @Autowired
	    AppService appService;
	 
	    //Retrieve all Apps
		@RequestMapping(value = "/app/", method = RequestMethod.GET)
	    public ResponseEntity<List<App>> listAllApps(@RequestParam("tags") String _tags, @RequestParam("sort") String _sort) {
			
			System.out.println("all apps");
			
			logger.info("Retrieve all apps");
			logger.info("Filter info: " + "tags: "  + _tags + " sort: " + _sort);
			
			try {		
				Gson gson = new GsonBuilder().create();
				Tag[] tags = gson.fromJson(_tags, Tag[].class);
				int sort = Integer.parseInt(_sort);
		        List<App> apps = appService.findAllApps(tags, sort);
		        if (apps.isEmpty()) {
		        	throw new AppNotFoundException("retrieve all apps", "");
		        }
		        return new ResponseEntity<List<App>>(apps, HttpStatus.OK);
			}catch(Exception ex) {
				logger.error("Unable to retrieve all Apps. Internal Server Error:", ex);
				throw new InternalServerException("retrieve all apps");
			}
	    }
		
		//Retrieve all user Apps
		@RequestMapping(value = "/app/myapps", method = RequestMethod.GET)
	    public ResponseEntity<List<App>> listMyApps() {
			
			System.out.println("my apps");
			logger.info("Retrieve all user apps");
			
			try {
				List<App> myapps = appService.findAllApps(new Tag[]{}, 2);
		        if (myapps.isEmpty()) {
		        	throw new AppNotFoundException("retrieve user apps", "");
		        }
		        return new ResponseEntity<List<App>>(myapps, HttpStatus.OK);
			}catch(Exception ex) {
				logger.error("Unable to retrieve user Apps. Internal Server Error:", ex);
				throw new InternalServerException("retrieve user apps");
			}   
	    }
		
		//Create an App
	    @RequestMapping(value = "/app/", method = RequestMethod.POST)
	    public ResponseEntity<?> createApp(@RequestBody String appdata, UriComponentsBuilder ucBuilder) {
	    	
	    	System.out.println("create app");
	    	
	        logger.info("Parsing App Data");
	        
	        try {	      
	        	Thread.sleep(2000);
	        	
		    	Gson gson = new GsonBuilder().create();
		    	JsonParser parser = new JsonParser();
		    	JsonObject o = parser.parse(appdata).getAsJsonObject();
		    	App app = gson.fromJson(o.get("app"),  App.class);
		    	String croppedImage = gson.fromJson(o.get("croppedImage"),  String.class);
		    	
		    	logger.info("Creating App : {}", app.getTitle());
		    	
		        if (appService.isAppExist(app.getTitle())) {
		            logger.error("Unable to create. An App with title '{}' already exist", app.getTitle());
		            throw new AppConflictException("create", app.getTitle());
		        }
		        
		        App created_app = appService.createApp(app, croppedImage);
		        
		        HttpHeaders headers = new HttpHeaders();
		        headers.set("iconurl", created_app.getProfile_picture());
		        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	        }catch(Exception ex) {
	        	logger.error("Unable to create. Internal Server Error:", ex);
	        	throw new InternalServerException("create");
	        }  
	    }
	    
	    //Delete an App
	    @RequestMapping(value = "/app/{title}", method = RequestMethod.DELETE)
	    public ResponseEntity<App> deleteApp(@PathVariable("title") String title) {
	    	
	    	System.out.println("delete app");
	    		        
	        try {	      
	        	Thread.sleep(2000);

		    	logger.info("Deleting App : {}", title);
		    	
		        App deleted_app = appService.deleteApp(title);
		        
		        return new ResponseEntity<App>(deleted_app, HttpStatus.OK);
	        }catch(DocumentClientException ex){
	        	if(ex.getStatusCode() == 404){
	        		logger.error("Unable to delete. The App with title '{}' does not exist", title);
	        		throw new AppNotFoundException("delete", title);
	        	}else{
	        		logger.error("Unable to create. Internal Server Error:", ex);
	        		throw new InternalServerException("delete");
	        	} 	
	        }catch(Exception ex) {
	        	logger.error("Unable to create. Internal Server Error:", ex);
	        	throw new InternalServerException("delete");
	        }  
	    }
}

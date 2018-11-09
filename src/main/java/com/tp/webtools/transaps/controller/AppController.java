package com.tp.webtools.transaps.controller;


import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.tp.webtools.transaps.service.AppService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tp.webtools.transaps.exception.CustomError;
import com.tp.webtools.transaps.model.App;


@RestController
@RequestMapping("/api")
@SuppressWarnings({"unchecked","rawtypes"})
public class AppController {
	 public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	 
	    @Autowired
	    AppService appService;
	 
	    //Retrieve all Apps
		@RequestMapping(value = "/app/", method = RequestMethod.GET)
	    public ResponseEntity<List<App>> listAllApps() {
			
			System.out.println("all apps");
			logger.info("Retrieve all apps");
			
			try {				
		        List<App> apps = appService.findAllApps();
		        if (apps.isEmpty()) {
		            return new ResponseEntity(HttpStatus.NO_CONTENT);
		        }
		        return new ResponseEntity<List<App>>(apps, HttpStatus.OK);
			}catch(Exception ex) {
				logger.error("Unable to retrieve all Apps. Internal Server Error:", ex);
	        	return new ResponseEntity(new CustomError("Unable to retrieve all Apps. Internal server error."), HttpStatus.INTERNAL_SERVER_ERROR);
			}
	    }
		
		//Retrieve all user Apps
		@RequestMapping(value = "/app/myapps", method = RequestMethod.GET)
	    public ResponseEntity<List<App>> listMyApps() {
			
			System.out.println("my apps");
			logger.info("Retrieve all user apps");
			
			try {
				List<App> myapps = appService.findAllApps();
		        if (myapps.isEmpty()) {
		            return new ResponseEntity(HttpStatus.NO_CONTENT);
		        }
		        return new ResponseEntity<List<App>>(myapps, HttpStatus.OK);
			}catch(Exception ex) {
				logger.error("Unable to retrieve user Apps. Internal Server Error:", ex);
	        	return new ResponseEntity(new CustomError("Unable to retrieve user Apps. Internal server error."), HttpStatus.INTERNAL_SERVER_ERROR);
			}   
	    }
		
		//Create an App
	    @RequestMapping(value = "/app/", method = RequestMethod.POST)
	    public ResponseEntity<?> createApp(@RequestBody String appdata, UriComponentsBuilder ucBuilder) {
	    	
	    	System.out.println("create app");
	    	
	        logger.info("Parsing App Data");
	        
	        try {	        	
		    	Gson gson = new GsonBuilder().create();
		    	JsonParser parser = new JsonParser();
		    	JsonObject o = parser.parse(appdata).getAsJsonObject();
		    	App app = gson.fromJson(o.get("app"),  App.class);
		    	String croppedImage = gson.fromJson(o.get("croppedImage"),  String.class);
		    	
		    	logger.info("Creating App : {}", app.getTitle());
		    	
		        if (appService.isAppExist(app)) {
		            logger.error("Unable to create. An App with title {} already exist", app.getTitle());
		            return new ResponseEntity(new CustomError("Unable to create. An App with title " + app.getTitle() + " already exist."), HttpStatus.CONFLICT);
		        }
		        
		        App created_app = appService.createApp(app, croppedImage);
		        
		        HttpHeaders headers = new HttpHeaders();
		        headers.set("iconurl", created_app.getProfile_picture());
		        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	        }catch(Exception ex) {
	        	logger.error("Unable to create. Internal Server Error:", ex);
	        	return new ResponseEntity(new CustomError("Unable to create. Internal server error."), HttpStatus.INTERNAL_SERVER_ERROR);
	        }  
	    }
}

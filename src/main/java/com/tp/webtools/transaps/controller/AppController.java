package com.tp.webtools.transaps.controller;


import java.util.List;

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
			
			try {				
				System.out.println("all apps");
		        List<App> apps = appService.findAllApps();
		        for(int i = 0; i < 20; i++){
		        	App app = new App("title"+i,"description"+i,"author"+i);
		        	apps.add(app);
		        }
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
	        List<App> myapps = appService.findAllApps();
	        if (myapps.isEmpty()) {
	            return new ResponseEntity(HttpStatus.NO_CONTENT);
	        }
	        return new ResponseEntity<List<App>>(myapps, HttpStatus.OK);
	    }
		
		//Create an App
	    @RequestMapping(value = "/app/", method = RequestMethod.POST)
	    public ResponseEntity<?> createApp(@RequestBody App app, UriComponentsBuilder ucBuilder) {
	        logger.info("Creating App : {}", app);
	        
	        try {
	        	Thread.sleep(5000);
		        if (appService.isAppExist(app)) {
		            logger.error("Unable to create. An App with title {} already exist", app.getTitle());
		            return new ResponseEntity(new CustomError("Unable to create. An App with title " + 
		            app.getTitle() + " already exist."), HttpStatus.CONFLICT);
		        }
		        
		        appService.saveApp(app);
		        
		        HttpHeaders headers = new HttpHeaders();
		        headers.setLocation(ucBuilder.path("/api/app/{id}").buildAndExpand(app.getId()).toUri());
		        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	        }catch(Exception ex) {
	        	logger.error("Unable to create. Internal Server Error:", ex);
	        	return new ResponseEntity(new CustomError("Unable to create. Internal server error."), HttpStatus.INTERNAL_SERVER_ERROR);
	        }  
	    }
}

package com.tp.webtools.transaps.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tp.webtools.transaps.service.AppService;
import com.tp.webtools.transaps.model.App;


@RestController
@RequestMapping("/api")
@SuppressWarnings({"unchecked","rawtypes"})
public class AppController {
	 public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	 
	    @Autowired
	    AppService appService;
	 
	    //Retrieve All Apps
		@RequestMapping(value = "/app/", method = RequestMethod.GET)
	    public ResponseEntity<List<App>> listAllApps() {
			System.out.println("all apps");
	        List<App> apps = appService.findAllApps();
	        if (apps.isEmpty()) {
	            return new ResponseEntity(HttpStatus.NO_CONTENT);
	        }
	        return new ResponseEntity<List<App>>(apps, HttpStatus.OK);
	    }
		
		@RequestMapping(value = "/app/myapps", method = RequestMethod.GET)
	    public ResponseEntity<List<App>> listMyApps() {
			System.out.println("my apps");
	        List<App> myapps = appService.findAllApps();
	        if (myapps.isEmpty()) {
	            return new ResponseEntity(HttpStatus.NO_CONTENT);
	        }
	        return new ResponseEntity<List<App>>(myapps, HttpStatus.OK);
	    }
}

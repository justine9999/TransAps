package com.tp.webtools.transaps.service;

import java.util.List;

import com.tp.webtools.transaps.model.App;

public interface AppService {
			
	List<App> findAllApps();
		
	App findByTitle(String title);
	
	String createApp(App app, String croppedImage);

	boolean isAppExist(App app);
}

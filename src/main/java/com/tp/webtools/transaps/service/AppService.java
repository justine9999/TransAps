package com.tp.webtools.transaps.service;

import java.util.List;

import com.tp.webtools.transaps.model.App;
import com.tp.webtools.transaps.model.Tag;

public interface AppService {
			
	List<App> findAllApps(Tag[] tags, int sort);
		
	App findByTitle(String title);
	
	App createApp(App app, String croppedImage);
	
	App deleteApp(String title);

	boolean isAppExist(String title);
}

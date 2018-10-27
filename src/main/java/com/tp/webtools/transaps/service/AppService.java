package com.tp.webtools.transaps.service;

import java.util.List;

import com.tp.webtools.transaps.model.App;
import com.tp.webtools.transaps.model.User;

public interface AppService {
	List<App> findAllApps();
	
	void saveApp(App app);
	
	App findByTitle(String title);
	
	boolean isAppExist(App app);
}

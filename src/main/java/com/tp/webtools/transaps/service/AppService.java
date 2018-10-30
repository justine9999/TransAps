package com.tp.webtools.transaps.service;

import java.util.List;

import com.tp.webtools.transaps.model.App;
import com.tp.webtools.transaps.model.User;

public interface AppService {
		
	void deleteTable();
	
	List<App> findAllApps();
	
	App findById(int id);
	
	App findByTitle(String title);
	
	void saveApp(App app);

	boolean isAppExist(App app);
}

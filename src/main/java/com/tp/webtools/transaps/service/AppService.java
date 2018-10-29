package com.tp.webtools.transaps.service;

import java.util.List;

import com.tp.webtools.transaps.model.App;
import com.tp.webtools.transaps.model.User;

public interface AppService {
	
	void createKeyspace(String keyspaceName);
	
	void createTable(String keyspaceName, String talbeName);
	
	void deleteTable(String keyspaceName, String talbeName);
	
	List<App> findAllApps(String keyspaceName, String talbeName);
	
	App findById(int id, String keyspaceName, String talbeName);
	
	App findByTitle(String title, String keyspaceName, String talbeName);
	
	void saveApp(App app, String keyspaceName, String talbeName);

	boolean isAppExist(App app, String keyspaceName, String talbeName);
}

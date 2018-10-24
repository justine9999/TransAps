package com.tp.webtools.transaps.service;

import java.util.List;

import com.tp.webtools.transaps.model.App;
import com.tp.webtools.transaps.model.User;

public interface AppService {
	List<App> findAllApps();
	
	void saveApp(App app);
	
	boolean isAppExist(App app);
}

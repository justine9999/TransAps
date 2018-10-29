package com.tp.webtools.transaps.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datastax.driver.core.PreparedStatement;
import com.tp.webtools.transaps.model.App;
import com.tp.webtools.transaps.repository.AppRepository;

@Service("appService")
@Transactional
public class AppServiceImpl implements AppService{
	
	@Autowired
    private AppRepository appRepository;
 
	public void createKeyspace(String keyspaceName) {
		appRepository.createKeyspace(keyspaceName);
	}
	
	public void createTable(String keyspaceName, String talbeName) {
		appRepository.createTable(keyspaceName, talbeName);
	}
	
	public void deleteTable(String keyspaceName, String talbeName) {
		appRepository.deleteTable(keyspaceName, talbeName);
	}
	
    public List<App> findAllApps(String keyspaceName, String talbeName){
    	List<App> apps = new ArrayList<App>();
    	for(App app : appRepository.selectAllApps(keyspaceName, talbeName)) {
    		apps.add(app);
    	}
    	for(int i = 0; i < 30; i++){
    		apps.add(new App("titile"+i,"desc"+i,"author"+i));
    	}
    	
    	return apps;
    }
    
    public void saveApp(App app, String keyspaceName, String talbeName) {
    	PreparedStatement statement = appRepository.prepareInsertStatement(keyspaceName, talbeName);
        appRepository.insertApp(statement, app);
    }
    
    public App findById(int id, String keyspaceName, String talbeName) {
    	return appRepository.selectAppById(id, keyspaceName, talbeName);
    }
    
    public App findByTitle(String title, String keyspaceName, String talbeName) {
    	return appRepository.selectAppByTitle(title, keyspaceName, talbeName);
    }
    
    public boolean isAppExist(App app, String keyspaceName, String talbeName) {
    	return findByTitle(app.getTitle(), keyspaceName, talbeName) != null;
    }

}

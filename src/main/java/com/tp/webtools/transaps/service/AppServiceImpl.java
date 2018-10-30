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
	
	public void deleteTable() {
		appRepository.deleteTable();
	}
	
    public List<App> findAllApps(){
    	List<App> apps = new ArrayList<App>();
    	for(App app : appRepository.selectAllApps()) {
    		apps.add(app);
    	}
    	for(int i = 0; i < 30; i++){
    		apps.add(new App("titile"+i,"desc"+i,"author"+i));
    	}
    	
    	return apps;
    }
    
    public void saveApp(App app) {
    	PreparedStatement statement = appRepository.prepareInsertStatement();
        appRepository.insertApp(statement, app);
    }
    
    public App findById(int id) {
    	return appRepository.selectAppById(id);
    }
    
    public App findByTitle(String title) {
    	return appRepository.selectAppByTitle(title);
    }
    
    public boolean isAppExist(App app) {
    	return findByTitle(app.getTitle()) != null;
    }

}

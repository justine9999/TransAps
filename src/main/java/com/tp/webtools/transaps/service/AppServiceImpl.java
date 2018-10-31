package com.tp.webtools.transaps.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tp.webtools.transaps.model.App;
import com.tp.webtools.transaps.repository.AppRepository;

@Service("appService")
@Transactional
public class AppServiceImpl implements AppService{
	
	@Autowired
    private AppRepository appRepository;
	
    public List<App> findAllApps(){
    	List<App> apps = appRepository.readAllApps();
    	for(int i = 0; i < 30; i++){
    		apps.add(new App("titile"+i,"desc"+i,"author"+i));
    	}
    	
    	return apps;
    }
    
    
    public App findByTitle(String title) {
    	return appRepository.findAppByTitle(title);
    }
    
    public String createApp(App app) {
        return appRepository.createApp(app);
    }

    
    public boolean isAppExist(App app) {
    	return findByTitle(app.getTitle()) != null;
    }

}

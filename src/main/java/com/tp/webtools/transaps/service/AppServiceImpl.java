package com.tp.webtools.transaps.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tp.webtools.transaps.model.App;
import com.tp.webtools.transaps.model.Tag;
import com.tp.webtools.transaps.repository.AppRepository;

@Service("appService")
@Transactional
public class AppServiceImpl implements AppService{
	
	@Autowired
    private AppRepository appRepository;
	
    public List<App> findAllApps(Tag[] tags, int sort){
    	List<App> apps = appRepository.readAllApps(tags, sort);
    	
    	return apps;
    }
    
    
    public App findByTitle(String title) {
    	return appRepository.findAppByTitle(title);
    }
    
    public App createApp(App app, String croppedImage) {
        return appRepository.createApp(app, croppedImage);
    }

    
    public boolean isAppExist(App app) {
    	return findByTitle(app.getTitle()) != null;
    }

}

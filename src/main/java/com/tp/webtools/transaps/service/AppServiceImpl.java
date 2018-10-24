package com.tp.webtools.transaps.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tp.webtools.transaps.model.App;
import com.tp.webtools.transaps.model.User;
import com.tp.webtools.transaps.repository.AppRepository;
import com.tp.webtools.transaps.repository.UserRepository;

@Service("appService")
@Transactional
public class AppServiceImpl implements AppService{
	
	@Autowired
    private AppRepository appRepository;
 
    public List<App> findAllApps(){
    	List<App> apps = new ArrayList<App>();
    	for(int i = 0; i < 30; i++){
    		apps.add(new App("titile"+i,"desc"+i,"author"+i));
    	}
    	
    	return apps;
    }
    
    public void saveApp(App app) {
        appRepository.save(app);
    }
    
    public boolean isAppExist(App app) {
        return false;
    }

}

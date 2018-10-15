package com.tp.webtools.transaps.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.tp.webtools.transaps.model.App;

@Service("appService")
@Transactional
public class AppServiceImpl implements AppService{
 
    public List<App> findAllApps(){
    	List<App> apps = new ArrayList<App>();
    	for(int i = 0; i < 30; i++){
    		apps.add(new App("titile"+i,"desc"+i,"author"+i));
    	}
    	
    	return apps;
    }

}

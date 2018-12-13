package com.tp.webtools.transaps.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tp.webtools.transaps.exception.AppConflictException;
import com.tp.webtools.transaps.model.App;


public class AppControllerLoggingAspect {
	
	public static final Logger logger = LoggerFactory.getLogger(AppControllerLoggingAspect.class);

	//listAllApps()
	@Pointcut("execution(* com.tp.webtools.transaps.controller.AppController.listAllApps())")
	public void listAllAppsPointcut(){}
	
	@Before("listAllAppsPointcut()")
	public void beforeListAllAppsAdvice(JoinPoint joinPoint) {	
		Object[] signatureArgs = joinPoint.getArgs();
		String tags = signatureArgs[0].toString();
		String sort = signatureArgs[1].toString();
		logger.info("Retrieve all Apps");
		logger.info("Filter info: " + "tags: "  + tags + " sort: " + sort);
	}
	
	@AfterReturning(pointcut = "listAllAppsPointcut()", returning = "retVal")
	public void afterListAllAppsReturning(Object retVal) {
		logger.info("Retrieved all {} Apps", ((List<App>) retVal).size());
	}

	@AfterThrowing(pointcut = "listAllAppsPointcut()", throwing = "ex")
	public void afterListAllAppsThrowing(Exception ex) {
		logger.error("Unable to retrieve all Apps. Internal Server Error:", ex);
	}
	
	
	//listMyApps()
	@Pointcut("execution(* com.tp.webtools.transaps.controller.AppController.listMyApps())")
	public void listMyAppsPointcut(){}
	
	@Before("listMyAppsPointcut()")
	public void beforeListMyAppsAdvice() {		
		logger.info("Retrieve user Apps");
	}
	
	@AfterReturning(pointcut = "listMyAppsPointcut()", returning = "retVal")
	public void afterListMyAppsReturning(Object retVal) {
		logger.info("Retrieved {} user Apps", ((List<App>) retVal).size());
	}

	@AfterThrowing(pointcut = "listMyAppsPointcut()", throwing = "ex")
	public void afterListMyAppsThrowing(Exception ex) {
		logger.error("Unable to retrieve user Apps. Internal Server Error:", ex);
	}
	
	
	//createApp()
	@Pointcut("execution(* com.tp.webtools.transaps.controller.AppController.createApp())")
	public void createAppPointcut(){}
	
	@Before("createAppPointcut()")
	public void beforeCreateAppAdvice() {		
		logger.info("Create App");
	}
	
	@AfterReturning(pointcut = "createAppPointcut()", returning = "retVal")
	public void afterCreateAppReturning() {
		logger.info("Created App");
	}

	@AfterThrowing(pointcut = "createAppPointcut()", throwing = "ex")
	public void afterCreateAppThrowing(Exception ex) {
		if(ex instanceof AppConflictException) {
			logger.error("Unable to create. App already exist");
		}else {
			logger.error("Unable to create App. Internal Server Error:", ex);
		}	
	}

	
	//deleteApp()
	@Pointcut("execution(* com.tp.webtools.transaps.controller.AppController.deleteApp())")
	public void deleteAppPointcut(){}
	
	@Before("deleteAppPointcut()")
	public void beforeDeleteAppAdvice(JoinPoint joinPoint) {
		Object[] signatureArgs = joinPoint.getArgs();
		String title = signatureArgs[0].toString();
		logger.info("Delete App : {}", title);
	}
	
	@AfterReturning(pointcut = "deleteAppPointcut()", returning = "retVal")
	public void afterDeleteAppReturning(JoinPoint joinPoint) {
		Object[] signatureArgs = joinPoint.getArgs();
		String title = signatureArgs[0].toString();
		logger.info("Deleted App : {}", title);
	}

	@AfterThrowing(pointcut = "deleteAppPointcut()", throwing = "ex")
	public void afterDeleteAppThrowing(Exception ex) {
		if(ex instanceof AppConflictException) {
			logger.error("Unable to delete App. The App with title '{}' does not exist", ((AppConflictException) ex).getApp_title());
		}else {
			logger.error("Unable to delete App. Internal Server Error:", ex);
		}	
	}
}

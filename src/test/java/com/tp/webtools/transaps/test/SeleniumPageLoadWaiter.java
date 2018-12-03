package com.tp.webtools.transaps.test;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumPageLoadWaiter {
	
	private static WebDriver jsWaitDriver;
    private static WebDriverWait jsWait;
    private static JavascriptExecutor jsExec;
 
    public void setDriver (WebDriver driver) {
        jsWaitDriver = driver;
        jsWait = new WebDriverWait(jsWaitDriver, 10);
        jsExec = (JavascriptExecutor) jsWaitDriver;
    }
 
    //Wait for JQuery Load
    private void waitForJQueryLoad() {
        ExpectedCondition<Boolean> jQueryLoad = driver -> ((Long) ((JavascriptExecutor) jsWaitDriver)
                .executeScript("return jQuery.active") == 0);
 
        boolean jqueryReady = (Boolean) jsExec.executeScript("return jQuery.active==0");
 
        if(!jqueryReady) {
            System.out.println("JQuery is NOT Ready!");
            jsWait.until(jQueryLoad);
        } else {
            System.out.println("JQuery is Ready!");
        }
    }
 
 
    //Wait for Angular Load
    private void waitForAngularLoad() {
        WebDriverWait wait = new WebDriverWait(jsWaitDriver,15);
        JavascriptExecutor jsExec = (JavascriptExecutor) jsWaitDriver;
 
        String angularReadyScript = "return angular.element(document).injector().get('$http').pendingRequests.length === 0";
 
        ExpectedCondition<Boolean> angularLoad = driver -> Boolean.valueOf(((JavascriptExecutor) driver)
                .executeScript(angularReadyScript).toString());
 
        boolean angularReady = Boolean.valueOf(jsExec.executeScript(angularReadyScript).toString());
 
        if(!angularReady) {
            System.out.println("ANGULAR is NOT Ready!");
            wait.until(angularLoad);
        } else {
            System.out.println("ANGULAR is Ready!");
        }
    }
 
    //Wait Until JS Ready
    private void waitUntilJSReady() {
        WebDriverWait wait = new WebDriverWait(jsWaitDriver,15);
        JavascriptExecutor jsExec = (JavascriptExecutor) jsWaitDriver;
 
        ExpectedCondition<Boolean> jsLoad = driver -> ((JavascriptExecutor) jsWaitDriver)
                .executeScript("return document.readyState").toString().equals("complete");
 
        boolean jsReady =  (Boolean) jsExec.executeScript("return document.readyState").toString().equals("complete");
 
        if(!jsReady) {
            System.out.println("JS in NOT Ready!");
            wait.until(jsLoad);
        } else {
            System.out.println("JS is Ready!");
        }
    }
 
    //Wait Until JQuery and JS Ready
    private void waitUntilJQueryReady() {
        JavascriptExecutor jsExec = (JavascriptExecutor) jsWaitDriver;
 
        Boolean jQueryDefined = (Boolean) jsExec.executeScript("return typeof jQuery != 'undefined'");
        if (jQueryDefined == true) {
            sleep(20);
 
            waitForJQueryLoad();
            waitUntilJSReady();
 
            sleep(20);
        }  else {
            System.out.println("jQuery is not defined on this site!");
        }
    }
 
    //Wait Until Angular and JS Ready
    private void waitUntilAngularReady() {
        JavascriptExecutor jsExec = (JavascriptExecutor) jsWaitDriver;
 
        Boolean angularUnDefined = (Boolean) jsExec.executeScript("return window.angular === undefined");
        if (!angularUnDefined) {
            Boolean angularInjectorUnDefined = (Boolean) jsExec.executeScript("return angular.element(document).injector() === undefined");
            if(!angularInjectorUnDefined) {
                sleep(20);
 
                waitForAngularLoad();
                waitUntilJSReady();
 
                sleep(20);
            } else {
                System.out.println("Angular injector is not defined on this site!");
            }
        }  else {
            System.out.println("Angular is not defined on this site!");
        }
    }
 
    //Wait Until JQuery Angular and JS is ready
    public void waitJQueryAngular() {
        waitUntilJQueryReady();
        waitUntilAngularReady();
    }
 
    private void sleep(Integer seconds) {
        long secondsLong = (long) seconds;
        try {
            Thread.sleep(secondsLong);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

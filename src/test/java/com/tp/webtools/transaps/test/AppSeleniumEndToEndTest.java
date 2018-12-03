package com.tp.webtools.transaps.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import java.util.List;


public class AppSeleniumEndToEndTest {
	
	private static WebDriver driver;
	private final static String EntryUrl = "http://localhost:8080/TransAps/";
	private static SeleniumPageLoadWaiter seleniumPageLoadWaiter;
	
	@BeforeClass
	public static void setUp(){
		System.out.println("setup driver");
		System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		seleniumPageLoadWaiter = new SeleniumPageLoadWaiter();
		seleniumPageLoadWaiter.setDriver(driver);
	}
	
	@AfterClass
	public static void tearDown(){
		System.out.println("close driver");
		driver.quit();
	}
	
	@Test
	public void whenMainPageLoad_thenTitleShouldBeCorrect() {
		driver.get(EntryUrl);
		String desired_title = "TransAps";
		System.out.println("page title: " + driver.getTitle());
		assertThat(driver.getTitle(),equalTo(desired_title));
	}
	
	@Test
	public void whenClickMyAppsLink_thenMyAppsDisplayed() {
		driver.get(EntryUrl);
		List<WebElement> webElementList = driver.findElements(By.cssSelector("tr[ui-sref='my-apps']"));
		WebElement myAppLink = webElementList.get(0);
		myAppLink.click();
		seleniumPageLoadWaiter.waitJQueryAngular();
		WebElement myAppTable = driver.findElements(By.id("my-apps-container")).get(0);
		assertThat(myAppTable.isDisplayed(),equalTo(true));
		
	}
	
	@Test
	public void whenClickCreateAppsButton_thenCreateAppsFormDisplayed() {
		driver.get(EntryUrl);
		List<WebElement> webElementList = driver.findElements(By.cssSelector("tr[ui-sref='my-apps']"));
		WebElement myAppLink = webElementList.get(0);
		myAppLink.click();
		seleniumPageLoadWaiter.waitJQueryAngular();
		WebElement crateAppButton = driver.findElements(By.id("create-app-button")).get(0);
		crateAppButton.click();
		seleniumPageLoadWaiter.waitJQueryAngular();
		WebElement crateAppForm = driver.findElements(By.cssSelector("form[name='appinfoform']")).get(0);
		assertThat(crateAppForm.isDisplayed(),equalTo(true));
		
	}
}

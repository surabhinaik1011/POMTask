package com.test;

import java.io.FileInputStream;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBase {
	
    public static WebDriver driver;
	Properties prop = null;
	FileInputStream fis = null;
	
	@BeforeSuite
	public void launchApplication() throws Throwable{
		
	 fis = new FileInputStream(System.getProperty("user.dir")+ "/src/main/resources/config.properties");
	 prop = new Properties();
	 prop.load(fis);
	 
     String URL = prop.getProperty("url");     
     System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
     driver = new ChromeDriver();
     driver.get(URL);
     driver.manage().window().maximize();
	}
	
	@AfterSuite
	public void CloseLaunchApplication() {
		driver.close();
	}
}


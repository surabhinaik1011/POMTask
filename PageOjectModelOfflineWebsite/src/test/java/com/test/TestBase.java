package com.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBase {

	public static WebDriver driver;
	Properties prop = null;
	FileInputStream fis = null;

	String readAnyProperty(String propFileName, String propName) {
		String val = null;
		try {
			fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/" + "/config.properties");
			prop = new Properties();
			prop.load(fis);

			val = prop.getProperty(propName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return val;
	}

	public void launchApplication() throws Throwable {
		String URL = readAnyProperty("config.properties", "url");
		System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(URL);
		driver.manage().window().maximize();
	}

	public void CloseLaunchApplication() {
		driver.close();
	}
}

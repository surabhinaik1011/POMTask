package com.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pages.DashBoardPage;
import com.pages.LoginPage;

public class DashBoardTestCases {
	public WebDriver driver;
	DashBoardPage dp;
	LoginPage lp;

	@BeforeMethod
	public void setup_Browser() throws Exception {
	
		System.setProperty("webdriver.chrome.driver", "driver/chromedriver81.exe");
		driver = new ChromeDriver();
		driver.get("file:///D:/jbk/Offline%20Website/index.html");
		driver.manage().window().maximize();
		// wait is taken from property file
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		lp=new LoginPage(driver);
		dp=lp.NavigateToDashboard();
	}

	@AfterMethod
	public void browserClose() {
		driver.close();
	}

	@Test(priority = 1)
	public void checkTitle() {
		Assert.assertTrue(dp.verifyTitleOfPage());
	}

	@Test(priority = 2)
	public void checkLogoutLabel() {
		Assert.assertTrue(dp.verifyLogoutLabel());
	}

	@Test(priority = 3)
	public void checkPageHeaderText() {
		Assert.assertTrue(dp.verifyHeader());
	}

	@Test(priority = 4)
	public void checkAllCoursesHeaderText() {
		Assert.assertTrue(dp.verifyCourseHeaders());
	}

	@Test(priority = 5)
	public void checkNumberOfCourses() {
		Assert.assertTrue(dp.verifyNumberOfCourses());
	}

	@Test(priority = 6)
	public void checkAllCourseslinksClickable() {
		Assert.assertTrue(dp.verifyAllCourseslinks());
	}

	@Test(priority = 7)
	public void checkAllCoursesNames() {
		Assert.assertTrue(dp.verifyCourseNames());
	}
}

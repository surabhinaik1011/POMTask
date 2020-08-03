package com.test;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.pages.DownloadPage;
import com.pages.LoginPage;

public class LoginPageTest extends TestBase {
	
	LoginPage lp = null;

	// Extent Report Variables
		String pageName = "Login PAGE TESTCASES";
		String extentReprtName = "LoginPageExtentReport";
		String hostName = "Offline Website";
		String environment = "Login Page Testing";
		String uName = "Surabhi";
		String docTitle = "LoginPage";
		String reportName = "LoginPageExtentReport ";

		// ExtentReport Methods
		@BeforeTest()
		public void beforeTest() {
			super.setReport(extentReprtName, hostName, environment, uName, docTitle, reportName);
		}

		@AfterTest
		public void afterTest() {
			super.endReport();
		}
		
		
		
	@BeforeMethod
	public void loadUrl() throws Throwable {

		driver=super.launchApplication();
		lp = new LoginPage(driver);
	}
	
	
	
	@Test(priority = 1)
	public void checkURL() {
		Assert.assertTrue(lp.checkURL());
	}

	@Test(priority = 2)
	public void checkTitle() {
		Assert.assertTrue(lp.checkTitle());
	}

	@Test(priority = 3)
	public void checkHeading() {
		Assert.assertTrue(lp.checkHeading());
	}

	@Test(priority = 4)
	public void checkLoginBoxMsg() {
		Assert.assertTrue(lp.checkLoginMsg());
	}

	@Test(priority = 5)
	public void checkNavigationBetRegPageAndLogin() {
		Assert.assertTrue(lp.navigateToLoginFromReg());
	}

	@Test(priority = 6)
	public void validateInvalidLoginCred() {
		lp.clickLoginButton();
		Assert.assertTrue(lp.checkErrorMessage());
	}

	@Test(priority = 7)
	public void validateValidLoginCred() {
		lp.NavigateToDashboard();
		Assert.assertTrue(lp.checkTitleOfDashBoard());
	}

	@Test(priority = 8)
	public void checkNavigationBetDashboardAndLogin() {
		lp.navigationBetDashboardAndLogin();
		Assert.assertTrue(lp.navigateToLoginFromDashboard());
	}
	@AfterMethod
	public void closeBrowser() {
		//testlogger.log(Status.INFO, "Browser Closed");
		super.CloseLaunchApplication();
	}

}
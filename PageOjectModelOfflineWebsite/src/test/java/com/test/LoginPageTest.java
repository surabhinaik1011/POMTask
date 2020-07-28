package com.test;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.pages.LoginPage;

public class LoginPageTest extends TestBase {
	
	LoginPage lp = null;

	@BeforeSuite
	public void launchApplication() throws Throwable {
		super.launchApplication();
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

	@AfterSuite
	public void closeApplication(){
		super.CloseLaunchApplication();
	}

}
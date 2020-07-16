package com.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.pages.LoginPage;

public class LoginPageTest {

	LoginPage lp;
	public WebDriver driver;
	public static Properties prop;
	public static FileInputStream fis;

	@BeforeSuite
	public void setEnv() {
		String propPath = System.getProperty("user.dir") + "/src/main/resources/config.properties";
		prop = new Properties();

		try {
			fis = new FileInputStream(propPath);
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.setProperty("webdriver.chrome.driver", "C:/chromedriver81.exe");
		driver = new ChromeDriver();
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		// wait is taken from property file
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(prop.getProperty("wait")), TimeUnit.SECONDS);
		lp = new LoginPage(driver);
		System.out.println("LoginTest inialize");
	}

	@AfterSuite
	public void exitEnv() {
		driver.close();
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

}

package com.test;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.pages.DownloadPage;

public class DownloadTest extends TestBase {

	WebDriver driver = null;
	DownloadPage dp = null;
	DownloadPage dwp=null;

	// Extent Report Variables
	String pageName = "DOWNLOAD PAGE TESTCASES";
	String extentReprtName = "DownloadPageExtentReport";
	String hostName = "Offline Website";
	String environment = "Download Page Testing";
	String uName = "AshwiniD";
	String docTitle = "DownloadPage";
	String reportName = "DownloadPageExtentReport ";

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
	/*String URL = readAnyProperty("config.properties", "url");
		System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(URL);
		driver.manage().window().maximize();*/
		driver=super.launchApplication();
		dp = new DownloadPage(driver);
		dwp = dp.navigateToDownloadPage();
	}

	@AfterMethod
	public void closeBrowser() {
		testlogger.log(Status.INFO, "Browser Closed");
		driver.close();
	}

	@Test(priority = 1)
	public void validateCounOfHeaderColumns() throws Exception {
		super.passTest("validateCounOfHeaderColumns", pageName);// ExtentReport
																// Test Create
		Assert.assertTrue(dp.validateHeaderCount(testlogger));
		Thread.sleep(3000);
	}

	@Test(priority = 2)
	public void validateNoOfRowColumn() throws Exception {
		super.passTest("validateNoOfRowColumn", pageName);
		Assert.assertTrue(dp.checkTotalRowColNum(testlogger));
		Thread.sleep(3000);
	}

	/*@Test(priority = 3)
	public void validateFollowVender() throws Exception {
		super.passTest("validateFollowVender", pageName);
		Assert.assertTrue(dp.checkFollowVender(testlogger));
		Thread.sleep(3000);
	}

	@Test(priority = 4)
	public void check32bitLink() throws Exception {
		super.passTest("check32bitLink", pageName);
		Assert.assertTrue(dp.checkLink32bitClickable(testlogger));
		Thread.sleep(3000);
	}

	@Test(priority = 5)
	public void check64bitLink() throws Exception {
		super.passTest("check64bitLink", pageName);
		Assert.assertTrue(dp.checkLink64bitClickable(testlogger));
		Thread.sleep(3000);
	}

	@Test(priority = 6)
	public void checkOfficialWebsite() {
		super.passTest("CheckOfficialWebsite", pageName);
		Assert.assertTrue(dp.checkOfficialWebsiteClickable(testlogger));
	}

	@Test(priority = 8)
	public void validateVendorsNumberListSequence() {
		super.passTest("ValidateVendorsNumberListSequence", pageName);
		Assert.assertTrue(dp.checkVendorListIsSort(testlogger));
	}

	@Test(priority = 9)
	public void validateSrNumberListSequence() {
		super.passTest("ValidateSrNumberListSequence", pageName);
		Assert.assertTrue(dp.checkSrNumberListInSort(testlogger));
	}

	@Test(priority = 10)
	public void validateStartWithVendorName() {
		super.passTest("ValidateStartWithVendorName", pageName);
		Assert.assertTrue(dp.checkVendorStartWith(testlogger));
	}*/
}

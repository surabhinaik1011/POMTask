package com.test;

import java.io.FileInputStream;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class TestBase {

	public static WebDriver driver;
	Properties prop = null;
	FileInputStream fis = null;

	// Globle variables for ExtentReport

	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest testlogger;
	ITestResult result;

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

	// Ashwini code started for extent report.
	// 28 july 2020

	public ExtentReports setReport(String extentReprtName, String hostName, String environment, String uName,
			String docTitle, String reportName) {

		htmlReporter = new ExtentHtmlReporter(
				System.getProperty("user.dir") + "/test-output/" + extentReprtName + ".html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", hostName);
		extent.setSystemInfo("Environment", environment);
		extent.setSystemInfo("User Name", uName);

		htmlReporter.config().setDocumentTitle(docTitle);
		htmlReporter.config().setReportName(reportName);
		htmlReporter.config().setTheme(Theme.STANDARD);
		return extent;
	}

	public void endReport() {
		extent.flush();
	}

	public void passTest(String testName, String pageName) {
		testlogger = extent.createTest(testName, pageName);
		Assert.assertTrue(true);
	}

	public void failTest(String testName, String pageName) {
		testlogger = extent.createTest(testName, pageName);
		Assert.assertFalse(false);
	}

	public void skipTest(String testName) {
		testlogger = extent.createTest(testName);
		throw new SkipException("Skipping this test with exception");
	}

	public void getResult() {
		if (result.getStatus() == ITestResult.FAILURE) {

			testlogger.log(Status.FAIL, "Test Case Failed is " + result.getName());
			testlogger.fail(result.getThrowable());

		} else if (result.getStatus() == ITestResult.SKIP) {

			testlogger.log(Status.SKIP, "Test Case Skipped is " + result.getName());

		} else if (result.getStatus() == ITestResult.SUCCESS) {

			testlogger.log(Status.PASS, "Test Case Passed is " + result.getName());
		}
		testlogger.log(Status.INFO, "Browser Closed");
		driver.close();
	}

}

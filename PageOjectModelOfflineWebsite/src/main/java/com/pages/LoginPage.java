package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	// All WebElements.
	@FindBy(id = "email")
	private WebElement username;

	@FindBy(id = "password")
	private WebElement password;

	@FindBy(xpath = "//button")
	private WebElement loginButton;

	@FindBy(xpath = "//a[text()='I already have a membership']")
	private WebElement alreadymember;

	@FindBy(xpath = "//a[text()='Register a new membership']")
	private WebElement regLink;

	@FindBy(tagName = "b")
	private WebElement websiteName;

	@FindBy(xpath = "//p[text()='Sign in to start your session']")
	private WebElement LoginMsg;

	@FindBy(xpath = "//a[text()='LOGOUT']")
	private WebElement logout;

	@FindBy(xpath = "//p[text()='Logout successfully']")
	private WebElement LogoutMsg;

	@FindBy(xpath = "//div[text()='Please enter email.']")
	private WebElement ErrorMsgForInvalidMail;

	public static WebDriver driver;

	
	public String geturl() {
		return driver.getCurrentUrl();
	}

	// constructor
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	public String typeUserName(String uName) {
		username.clear();
		username.sendKeys(uName);
		return uName;
	}

	public void typePassword(String pass) {
		password.clear();
		password.sendKeys(pass);

	}

	public void clickLoginButton() {
		loginButton.click();
	}

	public LoginPage clickLogoutButton() {
		logout.click();
		return new LoginPage(driver);
	}

	public DashBoardPage NavigateToDashboard() {
		typeUserName("kiran@gmail.com");
		typePassword("123456");
		loginButton.click();
		return new DashBoardPage(driver);
	}

	public DashBoardPage VerifyCorrectLoginCred() {
		typeUserName("kiran@gmail.com");
		typePassword("123456");
		loginButton.click();
		return new DashBoardPage(driver);
	}

	public RegistrationPage clickOnRegLink() {
		regLink.click();
		return new RegistrationPage(driver);
	}

	public LoginPage clickOnalreadymemberLink() {
		alreadymember.click();
		return new LoginPage(driver);
	}

	public boolean navigateToLoginFromReg() {
		clickOnRegLink();
		clickOnalreadymemberLink();

		if (driver.getTitle().equals("JavaByKiran | Log in")) {

			System.out.println("Navigated to login page from registration page.");
			return true;
		} else

			System.out.println("Should navigate to login page from registration page.");
		return false;
	}

	public boolean checkTitle() {
		if (driver.getTitle().equals("JavaByKiran | Log in")) {
			System.out.println("We are on Login Page!!");
			return true;
		} else {
			System.out.println("We should be on Login Page!!");
			return false;
		}
	}

	public boolean checkTitleOfDashBoard() {
		if (driver.getTitle().equals("JavaByKiran | Dashboard")) {
			System.out.println("Login successfully-----We are on Dashboard!!");
			return true;
		} else {
			System.out.println("We Should be on Dashboard!!");
			return false;
		}
	}

	public boolean checkURL() {
		if (geturl().equals("file:///C:/Users/user/Desktop/Offline%20Website/Offline%20Website/index.html")) {
			System.out.println("URL is correct");
			return true;
		} else {
			System.out.println("Please Enter Valid URL");
			return false;
		}
	}

	public boolean checkHeading() {
		String actHeading = websiteName.getText();
		if (actHeading.equals("Java By Kiran")) {

			System.out.println("Heading is -Java By Kiran ");
			return true;
		} else {

			System.out.println("Heading is invalid. It should be- Java By Kiran");
			return false;
		}

	}

	public boolean checkLoginMsg() {
		String loginmsg = LoginMsg.getText();
		if (loginmsg.equals("Sign in to start your session")) {
			System.out.println("Login msg- Sign in to start your session is displayed correctly.");
			return true;
		}
		System.out.println("Loginmsg should display as- 'Sign in to start your session'");
		return false;
	}

	public boolean navigateToLoginFromDashboard() {

		if (LogoutMsg.getText().equals("Logout successfully")) {

			System.out.println("Navigation between login and Dashboard page is proper!");
			return true;
		} else {
			System.out.println("check navigation between login and Dashboard page!!");
			return false;
		}

	}

	// NavigationBetDashboardAndLogin
	public void navigationBetDashboardAndLogin() {
		if (geturl().equals("file:///C:/Users/user/Desktop/Offline%20Website/Offline%20Website/index.html")) {
			typeUserName("kiran@gmail.com");
			typePassword("123456");
			loginButton.click();
			clickLogoutButton();

		} else if (geturl().equals("file:///C:/Users/user/Desktop/Offline%20Website/Offline%20Website/pages/examples/dashboard.html")) {
			clickLogoutButton();

		}

	}

	public boolean checkErrorMessage() {

		if (ErrorMsgForInvalidMail.getText().equals("Please enter email.")) {
			System.out.println("Invalid login Cred entered.");
			return true;
		} else {

			System.out.println("Error msg should be displayed as--'Please enter email.'");
			return false;
		}
	}

}

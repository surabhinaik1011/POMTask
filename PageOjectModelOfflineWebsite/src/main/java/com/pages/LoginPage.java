package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	// take out all web elements
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

			public String gettitle() {
				return driver.getTitle();
			}

			public String geturl() {
				return driver.getCurrentUrl();
			}

			// constructor
			public LoginPage(WebDriver driver) {
				PageFactory.initElements(driver, this);
				this.driver = driver;
			}

			// all actions related to webelements

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

				if (gettitle().equals("JavaByKiran | Log in")) {
					System.out.println("Navigated to login page from registration page.");
					return true;
				} else
					System.out.println("Should navigate to login page from registration page.");
				return false;
			}

			public boolean checkTitle() {
				if (gettitle().equals("JavaByKiran | Log in")) {
					System.out.println("We are on Login Page!!");
					return true;
				} else {
					System.out.println("We should be on Login Page!!");
					return false;
				}
			}

			public boolean checkTitleOfDashBoard() {
				if (gettitle().equals("JavaByKiran | Dashboard")) {
					System.out.println("Login succesfully-----We are on Dashboard!!");
					return true;
				} else {
					System.out.println("We Should be on Dashboard!!");
					return false;
				}
			}

			public boolean checkURL() {
				if (geturl().equals("file:///D:/jbk/Offline%20Website/index.html")) {
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

					return true;
				}
				return false;
			}

			public boolean navigateToLoginFromDashboard() {

				if (LogoutMsg.getText().equals("Logout successfully")) {
					System.out.println("Navigation between login and dashboard page is proper!");
					return true;
				} else {
					System.out.println("check navigation between loginand dashboard page!!");
					return false;
				}

			}

			// NavigationBetDashboardAndLogin
			public void navigationBetDashboardAndLogin() {
				if (geturl().equals("file:///D:/jbk/Offline%20Website/index.html")) {
					typeUserName("kiran@gmail.com");
					typePassword("123456");
					loginButton.click();
					clickLogoutButton();

				} else if (geturl().equals("file:///D:/jbk/Offline%20Website/pages/examples/dashboard.html")) {
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

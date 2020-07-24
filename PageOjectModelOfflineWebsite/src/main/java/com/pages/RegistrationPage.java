package com.pages;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {
	WebDriver driver;
	
	@FindBy(linkText = "Register a new membership")
	private WebElement registerMember;

	@FindBy(id = "name")
	private WebElement name;

	@FindBy(id = "mobile")
	private WebElement mobile;

	@FindBy(id = "email")
	private WebElement email;

	@FindBy(id = "password")
	private WebElement password;

	@FindBy(xpath = "//button")
	public WebElement signinButton;

	@FindBy(xpath = "//b")
	private WebElement heading;

	@FindBy(linkText = "I already have a membership")
	private WebElement alreadyMember;

	public RegistrationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	
	public boolean validateRegistraionLink(WebDriver driver) {
		try {
			registerMember.click();
			System.out.println("Registration Clickable");
			return true;
		} catch (Throwable e) {
			System.out.println("Registration UnClickable");
			return false;
		}
	}

	public void EnterName() {
		name.sendKeys("ketaki");
	}

	public void EnterMobile() {
		mobile.sendKeys("9864376239");
	}

	public void EnterEmail() {
		email.sendKeys("ketaki@gmail.com");
	}

	public void EnterPassword() {
		password.sendKeys("12345");
	}

	public boolean validateAlertMessage(WebDriver driver) {
		try {
			signinButton.click();
			Alert al = driver.switchTo().alert();
			al.accept();
			System.out.println("Signin successfully and accept alert");
			return true;
		} catch (Throwable e) {
			System.out.println("Failed to SignIn");
			return false;
		}
	}

	public boolean getTitleOfRegistrationPage(WebDriver driver) {
		if (driver.getTitle().equals("JavaByKiran | Registration Page")) {
			System.out.println("Title match");
			return true;
		} else {
			System.out.println("Title mismatch");
			return false;
		}
	}

	public boolean getTextOfHeading(WebDriver driver) {
		if (heading.getText().equals("Java By Kiran")) {
			System.out.println("Heading match");
			return true;
		} else {
			System.out.println("Heading mismatch");
			return false;
		}
	}

	public boolean validateNoOfTextBoxes(WebDriver driver) {
		List<WebElement> textboxList = driver.findElements(By.tagName("input"));
		if (textboxList.size() == 4) {
			System.out.println("Total no of text box is correct");
			return true;
		} else {
			System.out.println("Total no of text box is Incorrect");
			return false;
		}
	}

	public boolean validationOfAlreadyMemberLink(WebDriver driver) {
		try {
			alreadyMember.click();
			System.out.println("Click successfully to I already have a membership");
			return true;
		} catch (Throwable t) {
			System.out.println("Unclickable to I already have a membership ");
			return false;
		}
	}

	public boolean validationOfMembership(WebDriver driver) {
		if (validationOfAlreadyMemberLink(driver) == true) {
			return driver.getTitle().equals("JavaByKiran | Log in");
		} else {
			System.out.println("Not return to Login page");
			return false;
		}
	}
}

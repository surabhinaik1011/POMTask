package com.pages;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import jxl.Sheet;
import jxl.Workbook;

public class DashBoardPage {
	WebDriver driver;
	
	public DashBoardPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	// all web element //section[@class='content']
	// all actions
	// all varifications
	// all navigations

	@FindBy(linkText = "LOGOUT")
	private WebElement logoutLable;

	@FindBy(xpath = "//div//child::h1//small[contains(text(),'Courses Offered')]")
	private WebElement dashboardHeader;

	@FindBy(xpath = "//section[@class='content']//a")
	private List<WebElement> courselinks;

	@FindBy(xpath = "//div//h3")
	private List<WebElement> courseHeaders; 

	@FindBy(xpath ="//div//h3//following::p")
	private List<WebElement> coursenames;
	
	public DashBoardPage navigateToDashBoardPage(WebDriver driver) {
		driver.findElement(By.id("email")).sendKeys("kiran@gmail.com");
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		return new DashBoardPage(driver);
	}

	public String getTitleofPage() {
		return driver.getTitle();

	}

	public boolean verifyTitleOfPage() {
		
		//---
		try {
			if (getTitleofPage().equals("JavaByKiran | Dashboard")) {
				return true;
			} else {
				return false;
			}
		} catch (Throwable t) {
			return false;
		}
	}

	public String getLabelLogout() {
		
		return logoutLable.getText();
	}

	public boolean verifyLogoutLabel() {
		
		//---
		try {
			if (getLabelLogout().equals("LOGOUT")) {
				return true;
			} else {
				return false;
			}
		} catch (Throwable t) {
			return false;
		}
	}

	public String getPageHeader() {
		return dashboardHeader.getText();

	}

	public boolean verifyHeader() {
		
		//---
		try {
			if (getPageHeader().equals("Courses Offered")) {
				return true;
			} else {
				return false;
			}
		} catch (Throwable t) {
			return false;
		}
	}

	public boolean verifyCourseHeaders() {
		
		//---
		boolean flag = true;
		// excel sheet
		try {
			ArrayList<String> expected = new ArrayList<String>();
			File file = new File(System.getProperty("user.dir")+"/src/com/jbk/data/ExpectedData.xls");
			Workbook workbook = Workbook.getWorkbook(file);
			Sheet sheet = workbook.getSheet(0);
			int rows=sheet.getRows();
			if (sheet.getCell(0, 0).getContents().equals("ExpectedHeaders")) {
				for (int i = 1; i < rows; i++) {
					expected.add((sheet.getCell(0, i).getContents()));
					// System.out.println(expected.get(i));
				}

				ArrayList<Boolean> flagList = new ArrayList<>();

				

				if (expected.size() == 0 && courseHeaders.size() > 0) {
					System.out.println("nothing is expected but seen some menu items");
					return false;
				}

				if (courseHeaders.size() != expected.size()) {
					System.out.println("count not matching..");
				}

				for (int i = 0; i < expected.size(); i++) {
					try {
						flag = courseHeaders.get(i).getText().equals(expected.get(i));/// false//false///false///true
						flagList.add(flag);
						if (!flag) {
							System.out.println("THis menu is wrong .. " + expected.get(i)
									+ " > instead of this it is showing " + courseHeaders.get(i).getText());// Selenium111
						} else {
							System.out.println("matching >> " + courseHeaders.get(i).getText());
						}
					} catch (Throwable t) {
						System.out.println("THis is missing from website .. " + expected.get(i));
					}
				}
				if (flagList.contains(false)) {
					return false;
				}
			}
		} catch (Throwable t) {
			return false;
		}
		return flag;
	}
	// return flag;

	public boolean verifyNumberOfCourses() {
	
		//---
		try {
			//List<WebElement> list = driver.findElements(By.xpath("//div//h3"));
			System.out.println(courseHeaders.size());
			if (courseHeaders.size() == 4) {
				return true;
			} else {
				return false;

			}
		} catch (Throwable t) {
			return false;
		}
	}

	public boolean verifyAllCourseslinks() {
		boolean flag = false;
		try {
			
			for (int i = 0; i < courselinks.size(); i++) {
				courselinks.get(i).click();
				flag = true;
			}
		} catch (Throwable t) {
			return false;
		}
		return flag;

	}

	public boolean verifyCourseNames() {
		
		boolean flag = true;
		try {
			ArrayList<String> expected = new ArrayList<String>();
			File file = new File(System.getProperty("user.dir")+"/src/com/jbk/data/ExpectedData.xls");
			Workbook workbook = Workbook.getWorkbook(file);
			Sheet sheet = workbook.getSheet(0);
			int rows=sheet.getRows();
			if (sheet.getCell(1, 0).getContents().equals("ExpectedCourseNames")) {
				for (int i = 1; i < rows; i++) {
					expected.add((sheet.getCell(1, i).getContents()));
				}
				ArrayList<Boolean> flagList = new ArrayList<>();

				if (expected.size() == 0 && coursenames.size() > 0) {
					System.out.println("nothing is expected but seen some menu items");
					return false;
				}

				if (coursenames.size()!=expected.size()) {
					System.out.println("count not matching..");
				}

				for (int i = 0; i < expected.size(); i++) {
					try {
						flag = coursenames.get(i).getText().equals(expected.get(i));/// f//false///false///true
						flagList.add(flag);
						if (!flag) {
							System.out.println("THis menu is wrong ..expected is " + expected.get(i)
									+ " > instead of this Actual is.. " + coursenames.get(i).getText());// Selenium111
						} else {
							System.out.println("matching >> " + coursenames.get(i).getText());
						}
					} catch (Throwable t) {
						System.out.println("THis is missing from website .. " + expected.get(i));
					}
				}
				if (flagList.contains(false)) {
					return false;
				}
			
		}
			}catch (Throwable t) {
			return false;
		}
		return flag;

	}
}

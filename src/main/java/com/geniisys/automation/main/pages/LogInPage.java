package com.geniisys.automation.main.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import com.geniisys.automation.common.BrowserDriver;


public class LogInPage {

	private BrowserDriver driver;
	private Logger log = LogManager.getLogger(LogInPage.class);

	private By usernameFldLocator = By.xpath("//input[@id='userId']");
	private By passwordFldLocator = By.xpath("//input[@id='password']");
	private By submitBtnLocator = By.xpath("//input[@id='loginButton']");


	public LogInPage(BrowserDriver driver) {
		this.driver = driver;
	}

	public HomePage logInAs(String username, String password) {
		try {
			WebElement usernameFld = driver.findElement(usernameFldLocator);
			usernameFld.click();
			usernameFld.sendKeys(username);
			log.info("Username field value set to '" + username + "'.");
		} catch (TimeoutException e) {
			log.error(e);
		}

		try {
			WebElement passwordFld = driver.findElement(passwordFldLocator);
			passwordFld.click();
			passwordFld.sendKeys(password);
			log.info("Password set.");
		} catch (TimeoutException e) {
			log.error(e);
		}

		try {
			driver.findElement(submitBtnLocator).click();
			log.info("Submit button clicked.");
		} catch (TimeoutException e) {
			log.error(e);
		}

		return new HomePage(driver);
	}
}

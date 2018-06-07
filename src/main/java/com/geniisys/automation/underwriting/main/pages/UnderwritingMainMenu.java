package com.geniisys.automation.underwriting.main.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import com.geniisys.automation.common.BrowserDriver;
import com.geniisys.automation.underwriting.policyissuance.parcreation.pages.PolicyParCreationPage;

public class UnderwritingMainMenu {

	private BrowserDriver driver;
	private Logger log = LogManager.getLogger(UnderwritingMainMenu.class.getName());

	private final By policyIssuanceMnuLocator = By.xpath("//a[@id='policyIssuance']");
	private final By parCreationMnuLocator = By.xpath("//a[@id='parCreation']");

	public UnderwritingMainMenu(BrowserDriver driver) {
		this.driver = driver;
	}

	public UnderwritingMainMenu goToPolicyIssuance() {
		try {
			driver.findElement(policyIssuanceMnuLocator).click();
		} catch (TimeoutException e) {
			log.error(e);
		}
		return new UnderwritingMainMenu(driver);
	}

	public PolicyParCreationPage goToParCreationPage() {
		try {
			driver.findClickableElement(parCreationMnuLocator).click();
			log.info("Go to Par Creation page.");
		} catch (TimeoutException e) {
			log.error(e);
		}
		return new PolicyParCreationPage(driver);
	}
}

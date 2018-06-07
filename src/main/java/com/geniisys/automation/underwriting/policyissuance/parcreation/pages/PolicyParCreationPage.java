package com.geniisys.automation.underwriting.policyissuance.parcreation.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.Select;

import com.geniisys.automation.common.BrowserDriver;
import com.geniisys.automation.common.MessageOverlay;
import com.geniisys.automation.common.ModalDialog;

public class PolicyParCreationPage {

	private BrowserDriver driver;

	private Logger log = LogManager.getLogger(PolicyParCreationPage.class.getName());

	private By lineLovLocator = By.xpath("//select[@id='linecd']");
	private By assuredSearchBtnLocator = By.xpath("//img[@id='btnSearchAssuredName']");
	private By saveBtnLocator = By.xpath("//input[@id='btnSave']");
	private By basicInformationMnuLocator = By.xpath("//a[@id='basicInformation']");

	public PolicyParCreationPage(BrowserDriver driver) {
		this.driver = driver;
	}

	public void setLine(String lineCode) {
		try {
			Select lineLov = new Select(driver.findElement(lineLovLocator));
			lineLov.selectByValue(lineCode);
			log.info("Line with code '" + lineCode + "' selected in the combo box.");
		} catch (TimeoutException e) {
			log.error(e);
		}
	}

	public void setAssured(String assuredNo) {
		openAssuredLov().searchAndSelect(assuredNo);
		log.info("Record with Assured No. " + assuredNo + " selected in the table grid.");
	}

	public void save() {
		try {
			driver.findClickableElement(saveBtnLocator).click();
			log.info("Save button clicked.");
		} catch (TimeoutException e) {
			log.error(e);
		}
		getMessageOvl().clickOk();
	}

	public BasicInformationPage goToBasicInformation() {
		try {
			driver.findClickableElement(basicInformationMnuLocator).click();
			log.info("'Basic Information' menu clicked.");
		} catch (TimeoutException e) {
			log.error(e);
		}
		return new BasicInformationPage(driver);
	}

	private ModalDialog openAssuredLov() {
		try {
			driver.findClickableElement(assuredSearchBtnLocator).click();
			log.info("Assured search button clicked.");
		} catch (TimeoutException e) {
			log.error(e);
		}
		return new ModalDialog(driver);
	}

	private MessageOverlay getMessageOvl() {
		return new MessageOverlay(driver);
	}
}

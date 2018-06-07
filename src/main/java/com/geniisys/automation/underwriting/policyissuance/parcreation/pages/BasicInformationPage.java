package com.geniisys.automation.underwriting.policyissuance.parcreation.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import com.geniisys.automation.common.BrowserDriver;
import com.geniisys.automation.common.MessageOverlay;
import com.geniisys.automation.underwriting.policyissuance.parcreation.blocks.BasicInformationBlock;
import com.geniisys.automation.underwriting.policyissuance.parcreation.blocks.ParCreationMenu;
import com.geniisys.automation.underwriting.policyissuance.parcreation.blocks.PeriodOfInsuranceBlock;

public class BasicInformationPage {

	private BrowserDriver driver;
	private Logger log = LogManager.getLogger(BasicInformationPage.class.getName());

	private final By saveBtnLocator = By.xpath("//input[@id='btnSave']");

	public BasicInformationPage(BrowserDriver driver) {
		this.driver = driver;
	}

	public BasicInformationBlock getBasicInfoBlk() {
		return new BasicInformationBlock(driver);
	}

	public PeriodOfInsuranceBlock getPeriodOfInsuranceBlk() {
		return new PeriodOfInsuranceBlock(driver);
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

	public ParCreationMenu getMenu() {
		return new ParCreationMenu(driver);
	}

	private MessageOverlay getMessageOvl() {
		return new MessageOverlay(driver);
	}

}

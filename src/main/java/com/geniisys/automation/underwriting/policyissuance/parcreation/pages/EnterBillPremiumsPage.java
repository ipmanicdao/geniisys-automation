package com.geniisys.automation.underwriting.policyissuance.parcreation.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import com.geniisys.automation.common.BrowserDriver;
import com.geniisys.automation.common.MessageOverlay;
import com.geniisys.automation.underwriting.policyissuance.parcreation.blocks.ParCreationMenu;

public class EnterBillPremiumsPage {

	private BrowserDriver driver;
	private Logger log = LogManager.getLogger(EnterBillPremiumsPage.class.getName());

	private By saveBtnLocator = By.xpath("//input[@id='btnSave']");

	public EnterBillPremiumsPage(BrowserDriver driver) {
		this.driver = driver;
	}

	public void save() {
		getMessageOvl().waitToFinishLoading();
		try {
			driver.findClickableElement(saveBtnLocator).click();
			log.info("Save button clicked.");
		} catch (Exception e) {
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

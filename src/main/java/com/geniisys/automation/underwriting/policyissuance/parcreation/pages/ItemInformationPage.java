package com.geniisys.automation.underwriting.policyissuance.parcreation.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import com.geniisys.automation.common.BrowserDriver;
import com.geniisys.automation.common.MessageOverlay;
import com.geniisys.automation.underwriting.policyissuance.parcreation.blocks.ItemInformationBlock;
import com.geniisys.automation.underwriting.policyissuance.parcreation.blocks.ParCreationMenu;
import com.geniisys.automation.underwriting.policyissuance.parcreation.blocks.PerilInformationBlock;

public class ItemInformationPage {

	private BrowserDriver driver;
	private Logger log = LogManager.getLogger(ItemInformationPage.class.getName());

	private By saveButtonLocator = By.xpath("//input[@id='btnSave']");

	public ItemInformationPage(BrowserDriver driver) {
		this.driver = driver;
	}

	public ItemInformationBlock getItemInformationBlk() {
		return new ItemInformationBlock(driver);
	}

	public PerilInformationBlock getPerilInformationBlk() {
		return new PerilInformationBlock(driver);
	}

	public void save() {
		try {
			driver.findClickableElement(saveButtonLocator).click();
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

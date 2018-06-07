package com.geniisys.automation.underwriting.policyissuance.parcreation.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import com.geniisys.automation.common.BrowserDriver;
import com.geniisys.automation.common.MessageOverlay;
import com.geniisys.automation.underwriting.policyissuance.parcreation.blocks.InvoiceCommissionInformationBlock;
import com.geniisys.automation.underwriting.policyissuance.parcreation.blocks.ParCreationMenu;

public class EnterInvoiceCommissionPage {

	private BrowserDriver driver;
	private Logger log = LogManager.getLogger(EnterInvoiceCommissionPage.class.getName());
	private InvoiceCommissionInformationBlock invoiceCommInfoBlk;
	private MessageOverlay popupMsg;

	private final By saveBtnLocator = By.xpath("//input[@id='btnSave']");


	public EnterInvoiceCommissionPage(BrowserDriver driver) {
		this.driver = driver;
		invoiceCommInfoBlk = new InvoiceCommissionInformationBlock(driver);
		popupMsg = new MessageOverlay(driver);
	}


	/**
	 * Add invoice commission of a bill based on given parameters.
	 *
	 * @param intmNo Intermediary number from GIIS_INTERMEDIARY
	 * @param sharePercentage Share percentage of intermediary
	 */
	public void addInvoiceCommission(int intmNo, int sharePercentage) {
		invoiceCommInfoBlk.setIntermediary(intmNo);
		invoiceCommInfoBlk.setSharePercentage(100);
		invoiceCommInfoBlk.clickAdd();
	}

	public void save() {
		try {
			driver.findClickableElement(saveBtnLocator).click();
			log.info("Save button clicked.");
		} catch (TimeoutException e) {
			log.error(e);
		}
		popupMsg.clickOk();
	}

	public ParCreationMenu getMenu() {
		return new ParCreationMenu(driver);
	}
}

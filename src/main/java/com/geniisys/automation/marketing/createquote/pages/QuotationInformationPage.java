package com.geniisys.automation.marketing.createquote.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import com.geniisys.automation.common.BrowserDriver;
import com.geniisys.automation.common.MessageOverlay;
import com.geniisys.automation.marketing.createquote.blocks.QuoteItemInformationBlock;
import com.geniisys.automation.marketing.createquote.blocks.QuotePerilInformationBlock;

public class QuotationInformationPage {

	private BrowserDriver driver;
	private Logger log = LogManager.getLogger(QuotationInformationPage.class.getName());

	private By saveBtnLocator = By.xpath("//input[@id='btnSaveQuotation']");

	public QuotationInformationPage(BrowserDriver driver) {
		this.driver = driver;
	}

	public void save() {
		try {
			driver.findClickableElement(saveBtnLocator).click();
			log.info("'Save' button clicked.");
		} catch (TimeoutException e) {
			log.error(e);
		}
		getMsgOverlay().clickOk();
	}

	public QuoteItemInformationBlock getItemInfoBlk() {
		return new QuoteItemInformationBlock(driver);
	}

	public QuotePerilInformationBlock getPerilInfoBlk() {
		return new QuotePerilInformationBlock(driver);
	}

	private MessageOverlay getMsgOverlay() {
		return new MessageOverlay(driver);
	}

}

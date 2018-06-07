package com.geniisys.automation.marketing.home.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import com.geniisys.automation.common.BrowserDriver;
import com.geniisys.automation.marketing.createquote.blocks.QuotationProcessingSubmenu;

public class MarketingMenu {

	private BrowserDriver driver;
	private Logger log = LogManager.getLogger(MarketingMenu.class.getName());

	private By quotationProcessingMnuLocator = By.xpath("//a[@id='quotationProcessing']");

	private By inquiryMnuLocator = By.xpath("//a[@id='inquiry']");

	public MarketingMenu(BrowserDriver driver) {
		this.driver = driver;
	}

	public QuotationProcessingSubmenu goToQuotationProcessing() {
		try {
			driver.findClickableElement(quotationProcessingMnuLocator).click();
			log.info("'Quotation Processing' menu clicked.");
		} catch (TimeoutException e) {
			log.error(e);
		}
		return new QuotationProcessingSubmenu(driver);
	}

	public void goToInquiry() {
		try {
			driver.findClickableElement(inquiryMnuLocator).click();
			log.info("'Inquiry' menu clicked.");
		} catch (TimeoutException e) {
			log.error(e);
		}
	}

}

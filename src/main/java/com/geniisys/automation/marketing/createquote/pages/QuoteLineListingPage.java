package com.geniisys.automation.marketing.createquote.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import com.geniisys.automation.common.BrowserDriver;

public class QuoteLineListingPage {

	private BrowserDriver driver;
	private Logger log = LogManager.getLogger(QuoteLineListingPage.class.getName());


	private By createQuotationButton = By.xpath("//input[@id='btnCreateQuotationFromQuotationList']");

	private By lineLocator(String lineCode) {
		return By.xpath("//*[@lineCd='"
				+ lineCode
				+ "']");
	}

	public QuoteLineListingPage(BrowserDriver driver) {
		this.driver = driver;
	}

	public QuotationListingPage selectLine(String lineCode) {
		try {
			driver.findClickableElement(lineLocator(lineCode)).click();
			log.info(lineCode + " line selected.");
		} catch (Exception e) {
			log.error(e);
		}
		try {
			driver.findClickableElement(createQuotationButton).click();
			log.info("'Create Quotation' button clicked.");
		} catch (Exception e) {
			log.error(e);
		}

		return new QuotationListingPage(driver);
	}

}

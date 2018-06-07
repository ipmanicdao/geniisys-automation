package com.geniisys.automation.marketing.createquote.blocks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import com.geniisys.automation.common.BrowserDriver;
import com.geniisys.automation.marketing.createquote.pages.QuotationInformationPage;

public class CreateQuotationMenu {

	private BrowserDriver driver;
	private Logger log = LogManager.getLogger(CreateQuotationMenu.class.getName());


	private final By quotationInformation = By.xpath("//a[@id='quoteInformation']");

	public CreateQuotationMenu(BrowserDriver driver) {
		this.driver = driver;
	}

	public QuotationInformationPage goToQuotationInfomation() {
		try {
			driver.findClickableElement(quotationInformation).click();
			log.info("'Quotation Information' menu clicked.");
		} catch (TimeoutException e) {
			log.error(e);
		}
		return new QuotationInformationPage(driver);
	}

}

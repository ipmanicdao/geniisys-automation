package com.geniisys.automation.underwriting.policyissuance.parcreation.blocks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import com.geniisys.automation.common.BrowserDriver;
import com.geniisys.automation.underwriting.policyissuance.parcreation.pages.EnterBillPremiumsPage;
import com.geniisys.automation.underwriting.policyissuance.parcreation.pages.EnterInvoiceCommissionPage;

public class BillSubmenu {

	private BrowserDriver driver;
	private Logger log = LogManager.getLogger(BillSubmenu.class.getName());

	private By enterBillPremiumsLnkLocator = By.xpath("//a[@id='enterBillPremiums']");
	private By enterInvoiceCommissionLnkLocator = By.xpath("//a[@id='enterInvoiceCommission']");

	public BillSubmenu(BrowserDriver driver) {
		this.driver = driver;
	}

	public EnterBillPremiumsPage goToEnterBillPremium() {
		try {
			driver.findClickableElement(enterBillPremiumsLnkLocator).click();
			log.info("'Enter Bill Premiums' menu clicked.");
		} catch (TimeoutException e) {
			log.error(e);
		}
		return new EnterBillPremiumsPage(driver);
	}

	public EnterInvoiceCommissionPage goToInvoiceCommission() {
		try {
			driver.findClickableElement(enterInvoiceCommissionLnkLocator).click();
			log.info("'Enter Invoice Commission' menu clicked.");
		} catch (TimeoutException e) {
			log.error(e);
		}
		return new EnterInvoiceCommissionPage(driver);
	}
}

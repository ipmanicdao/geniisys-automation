package com.geniisys.automation.underwriting.policyissuance.parcreation.pages;

import org.openqa.selenium.By;

import com.geniisys.automation.common.BrowserDriver;

public class PreliminaryOneRiskDistributionPage {

	private BrowserDriver driver;

	private final By CREATE_ITEMS_BUTTON = By.xpath("//input[@id='btnCreateItems']");


	public PreliminaryOneRiskDistributionPage(BrowserDriver driver) {
		this.driver = driver;
	}

	public void createItems() {
		driver.findClickableElement(CREATE_ITEMS_BUTTON).click();
	}

}

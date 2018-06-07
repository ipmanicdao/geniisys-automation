package com.geniisys.automation.underwriting.policyissuance.parcreation.blocks;

import org.openqa.selenium.By;

import com.geniisys.automation.common.BrowserDriver;

public class DistributionSubmenu {

	private BrowserDriver driver;

	private final By prelimOneRiskDistLnkLocator = By.xpath("//a[@id='prelimOneRiskDistTsiPrem']");

	public DistributionSubmenu(BrowserDriver driver) {
		this.driver = driver;
	}

	public void goToPrelimOneRiskDistribution() {
		driver.findClickableElement(prelimOneRiskDistLnkLocator).click();
	}

}

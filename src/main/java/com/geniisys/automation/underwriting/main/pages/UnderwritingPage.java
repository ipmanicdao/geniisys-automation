package com.geniisys.automation.underwriting.main.pages;

import com.geniisys.automation.common.BrowserDriver;

public class UnderwritingPage {

	private BrowserDriver driver;

	public UnderwritingPage(BrowserDriver driver) {
		this.driver = driver;
	}

	public UnderwritingMainMenu getMenu() {
		return new UnderwritingMainMenu(driver);
	}

}

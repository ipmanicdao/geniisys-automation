package com.geniisys.automation.marketing.home.pages;

import com.geniisys.automation.common.BrowserDriver;

public class MarketingHomePage {

	private BrowserDriver driver;

	public MarketingHomePage(BrowserDriver driver) {
		this.driver = driver;
	}

	public MarketingMenu menu() {
		return new MarketingMenu(driver);
	}
}

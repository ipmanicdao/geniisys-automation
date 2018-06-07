package com.geniisys.automation.marketing.createquote.blocks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import com.geniisys.automation.common.BrowserDriver;
import com.geniisys.automation.common.ModalDialog;

public class QuotePerilInformationBlock {

	private BrowserDriver driver;
	private Logger log = LogManager.getLogger(QuotePerilInformationBlock.class.getName());

	private By showPerilTxtLocator = By.xpath("//label[@id='perilInfoAccordionLbl']");
	private By perilSearchBtnLocator = By.xpath("//img[@id='searchPerilName']");
	private By perilRateFldLocator = By.xpath("//input[@id='perilRate']");
	private By perilTsiFldLocator = By.xpath("//input[@id='perilTsiAmt']");
	private By perilPremAmtFldLocator = By.xpath("//input[@id='perilPremAmt']");
	private By addPerilBtnLocator = By.xpath("//input[@id='btnAddPeril']");



	public QuotePerilInformationBlock(BrowserDriver driver) {
		this.driver = driver;
	}

	public void show() {
		try {
			driver.findClickableElement(showPerilTxtLocator).click();
			log.info("Show Peril Information block.");
		} catch (TimeoutException e) {
			log.info(e);
		}
	}

	public void setPeril(String perilName) {
		ModalDialog perilLov = openPerilLov();

		if(perilLov.isDisplayed()) {
			log.info("Peril LOV overlay is displayed.");
			perilLov.searchAndSelect(perilName);
		}
	}

	public void setPerilRate(Double rate) {
		try {
			WebElement perilRateFld = driver.findElement(perilRateFldLocator);
			perilRateFld.click();
			perilRateFld.sendKeys(rate.toString());
			log.info("Peril rate field value set to '" + rate + "'.");
		} catch (TimeoutException e) {
			log.error(e);
		}
	}

	public void setPerilTsi(Double tsiAmt) {
		try {
			WebElement perilTsiFld = driver.findElement(perilTsiFldLocator);
			perilTsiFld.click();
			perilTsiFld.sendKeys(tsiAmt.toString());
			log.info("TSI Amt. field value set to '" + tsiAmt + "'.");
		} catch (TimeoutException e) {
			log.error(e);
		}
	}

	public double getPerilPrem() {
		WebElement perilPremAmtFld = driver.findElement(perilPremAmtFldLocator);
		perilPremAmtFld.click();
		return Double.valueOf(perilPremAmtFld.getAttribute("value"));
	}

	public void addPeril() {
		try {
			driver.findClickableElement(addPerilBtnLocator).click();
			log.info("'Add' button clicked.");
		} catch (TimeoutException e) {
			log.error(e);
		}
	}

	private ModalDialog openPerilLov() {
		try {
			driver.findClickableElement(perilSearchBtnLocator).click();
			log.info("Peril search button clicked.");
		} catch (TimeoutException e) {
			log.error(e);
		}
		return new ModalDialog(driver);
	}

}

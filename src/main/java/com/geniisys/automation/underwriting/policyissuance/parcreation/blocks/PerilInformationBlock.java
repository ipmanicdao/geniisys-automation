package com.geniisys.automation.underwriting.policyissuance.parcreation.blocks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import com.geniisys.automation.common.BrowserDriver;
import com.geniisys.automation.common.ModalDialog;

public class PerilInformationBlock {

	private BrowserDriver driver;
	private Logger log = LogManager.getLogger(PerilInformationBlock.class.getName());

	private By showPerilBlockTxtLocator = By.xpath("//label[@id='showPeril']");
	private By perilSearchBtnLocator = By.xpath("//img[@id='hrefPeril']");
	private By perilRateFldLocator = By.xpath("//input[@id='perilRate']");
	private By perilTsiFldLocator = By.xpath("//input[@id='perilTsiAmt']");
	private By perilPremiumFldLocator = By.xpath("//input[@id='premiumAmt']");
	private By addPerilBtnLocator = By.xpath("//input[@id='btnAddItemPeril' and @value='Add']");

	public PerilInformationBlock(BrowserDriver driver) {
		this.driver = driver;
	}

	public void addPeril(String perilName, double perilTsi, double perilRate) {
		showPerilBlock();
		ModalDialog perilLov = openPerilLov();

		if(perilLov.isDisplayed()) {
			perilLov.searchAndSelect(perilName);
		}

		setPerilRate(perilRate);
		setPerilTsiAmt(perilTsi);
		add();
	}

	public void showPerilBlock() {
		try {
			driver.findClickableElement(showPerilBlockTxtLocator).click();
			log.info("Peril block shown.");
		} catch (TimeoutException e) {
			log.error(e);
		}
	}

	public ModalDialog openPerilLov() {
		try {
			driver.findClickableElement(perilSearchBtnLocator).click();
			log.info("Peril search button clicked.");
		} catch (TimeoutException e) {
			log.error(e);
		}
		return new ModalDialog(driver);
	}

	public void setPerilRate(Double perilRate) {
		try {
			driver.findElement(perilRateFldLocator).click();
			driver.findElement(perilRateFldLocator).sendKeys(perilRate.toString());
			log.info("Peril Rate field value set to '" + perilRate + "'.");
		} catch (TimeoutException e) {
			log.error(e);
		}
	}

	public void setPerilTsiAmt(Double perilTsiAmt) {
		try {
			driver.findClickableElement(perilTsiFldLocator).click();
			driver.findClickableElement(perilTsiFldLocator).sendKeys(perilTsiAmt.toString());
			log.info("TSI Amt. field value set to '" + perilTsiAmt + "'.");
		} catch (TimeoutException e) {
			log.error(e);
		}

		driver.findClickableElement(perilPremiumFldLocator).click();
	}

	public void setPerilPremAmt(Double perilPremAmt) {
		try {
			driver.findClickableElement(perilPremiumFldLocator).click();
			driver.findClickableElement(perilPremiumFldLocator).sendKeys(perilPremAmt.toString());
			log.info("Premium Amt. field value set to '" + perilPremAmt + "'.");
		} catch (TimeoutException e) {
			log.error(e);
		}
	}

	public Double getPerilPremAmt() {
		return Double.valueOf(driver.findElement(perilPremiumFldLocator).getAttribute("value"));
	}

	public void add() {
		try {
			driver.findClickableElement(addPerilBtnLocator).click();
			log.info("'Add' button clicked.");
		} catch (TimeoutException e) {
			log.error(e);
		}
	}

}

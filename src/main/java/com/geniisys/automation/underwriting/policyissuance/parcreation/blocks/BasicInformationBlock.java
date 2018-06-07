package com.geniisys.automation.underwriting.policyissuance.parcreation.blocks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import com.geniisys.automation.common.BrowserDriver;

import ru.yandex.qatools.htmlelements.element.Select;

public class BasicInformationBlock {

	private BrowserDriver driver;
	private Logger log = LogManager.getLogger(BasicInformationBlock.class.getName());

	private By sublineLovLocator = By.xpath("//select[@id='sublineCd']");
	private By referencePolicyNoFldLocator = By.xpath("//input[@id='referencePolicyNo']");
	private By parNoFldLocator = By.xpath("//input[@id='parNo']");

	public BasicInformationBlock(BrowserDriver driver) {
		this.driver = driver;
	}

	public void setSubline(String sublineCode) {
		try {
			Select sublineLov = new Select(driver.findElement(sublineLovLocator));
			sublineLov.click();
			sublineLov.selectByValue(sublineCode);
			log.info("Subline with code '" + sublineCode + "' selected in the combo box.");
		} catch (TimeoutException e) {
			log.error(e);
		}
	}

	public void setRefPolNo(String refPolNo) {
		try {
			driver.findClickableElement(referencePolicyNoFldLocator).click();
			driver.findElement(referencePolicyNoFldLocator).sendKeys(refPolNo);
			log.info("Reference Policy No. field value set to '" + refPolNo + "'.");
		} catch (TimeoutException e) {
			log.error(e);
		}
	}

	public String getParNo() {
		return driver.findElement(parNoFldLocator).getAttribute("value");
	}

	/**
	 * Set PAR No. as Reference Policy No.
	 */
	public void setParNoAsRefPolNo() {
		setRefPolNo(getParNo());
	}

}

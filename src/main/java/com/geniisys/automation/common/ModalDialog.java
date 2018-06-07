package com.geniisys.automation.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;

public class ModalDialog {

	private BrowserDriver driver;
	private Logger log = LogManager.getLogger(ModalDialog.class.getName());

	private By modalDialogOvlLocator = By.xpath("//div[starts-with(@id,'modal_dialog')"
			+ " and @class='dialogOverlay']");
	private By findFldLocator = By.xpath("//input[starts-with(@id,'modal_dialog')"
			+ " and contains(@id,'txtLOVFindText')]");
	private By okBtnLocator = By.xpath("//input[starts-with(@id, 'modal_dialog')"
			+ " and contains (@id, 'btnOk')]");

	public ModalDialog(BrowserDriver driver) {
		this.driver = driver;
	}

	public boolean isDisplayed() {
		return driver.findElement(modalDialogOvlLocator).isDisplayed();
	}

	public void searchAndSelect(String keyword) {
		if (isDisplayed()) {
			try {
				driver.findElement(findFldLocator).click();
				log.info("Find field clicked.");
				driver.findElement(findFldLocator).clear();
				log.info("Find field cleared.");
				driver.findElement(findFldLocator).sendKeys(keyword, Keys.ENTER);
				log.info("Find field value set to '" + keyword + "'.");
			} catch (TimeoutException e) {
				log.error(e);
			}

			try {
				driver.findElement(By.xpath("//div[starts-with(@class,'mtgInnerCell') "
						+ "and contains(text(),"
						+ " '" + keyword + "')]")).click();
				log.info("Record that contains text '" + keyword + "' clicked.");
			} catch (TimeoutException e) {
				log.error(e);
			}
			try {
				driver.findClickableElement(okBtnLocator).click();
				log.info("'Ok' button clicked.");
			} catch (TimeoutException e) {
				log.error(e);
			}
		}
		log.info("Prompt closed.");
	}

}

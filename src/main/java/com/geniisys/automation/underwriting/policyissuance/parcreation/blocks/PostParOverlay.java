package com.geniisys.automation.underwriting.policyissuance.parcreation.blocks;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import com.geniisys.automation.common.BrowserDriver;

public class PostParOverlay {

	private BrowserDriver driver;
	private Logger log = LogManager.getLogger(PostParOverlay.class.getName());

	private long timeout = 10;
	private long pollingTime = 1;

	private By postOvlLocator = By.xpath("//div[starts-with(@id,'modal_dialog') and @class='dialogOverlay']");
	private By postBtnLocator = By.xpath("//input[@id='btnPost']");
	private By cancelBtnLocator = By.xpath("//input[@id='btnPostCancel']");
	private By okBtnLocator = By.xpath("//input[@id='btnPostOk']");
	private By loadingOvlLocator = By.xpath("//div[@id='noticeOverlay']");

	public PostParOverlay(BrowserDriver driver) {
		this.driver = driver;
	}

	public void clickPost() {
		waitForMessageToDisplay();
		try {
			driver.findClickableElement(postBtnLocator).click();
			log.info("Post button clicked.");
		} catch (Exception e) {
			log.error(e);
		}
	}

	public void clickCancel() {
		waitForMessageToDisplay();
		try {
			driver.findClickableElement(cancelBtnLocator).click();
			log.info("Cancel button clicked.");
		} catch (Exception e) {
			log.error(e);
		}
	}

	public void clickOk() {
		waitToFinishLoading();
		try {
			driver.findClickableElement(okBtnLocator).click();
			log.info("Ok button clicked.");
		} catch (Exception e) {
			log.error(e);
		}
		waitForMessageToClose();
	}

	private void waitForMessageToDisplay() {
		fluentlyWait()
		.until(ExpectedConditions.
				visibilityOfElementLocated(postOvlLocator));
	}

	private void waitForMessageToClose() {
		fluentlyWait()
		.until(ExpectedConditions.
				invisibilityOfElementLocated(postOvlLocator));
	}

	private void waitToFinishLoading() {
		fluentlyWait()
		.until(ExpectedConditions
				.invisibilityOfElementLocated(loadingOvlLocator));
	}

	private FluentWait<BrowserDriver> fluentlyWait() {
		FluentWait<BrowserDriver> wait = new FluentWait<>(driver);

		wait = wait.withTimeout(timeout, TimeUnit.SECONDS)
				.pollingEvery(pollingTime, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);
		return wait;
	}
}

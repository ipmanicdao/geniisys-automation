package com.geniisys.automation.common;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class MessageOverlay {

	private BrowserDriver driver;
	private Logger log = LogManager.getLogger(MessageOverlay.class.getName());


	private long TIMEOUT = 10;
	private long POLLING_TIME = 1;

	private By messageOvlLocator = By.xpath("//div[starts-with(@id,'modal_dialog') "
			+ "and @class='dialog']");

	private By messageLblLocator = By.xpath("//div[starts-with(@id,'modal_dialog') "
			+ "and contains(@id,'top')]");

	private By messageTxtLocator = By.xpath("//div[@class='alphacube_message']/div/label");

	private By okBtnLocator = By.xpath("//input[@id='btnMsgBoxOk']");

	private By loadingOvlLocator = By.xpath("//div[@id='noticeOverlay']");

	public MessageOverlay(BrowserDriver driver) {
		this.driver = driver;
	}

	public By getMessageOverlayLocator() {
		return messageOvlLocator;
	}

	public boolean isDisplayed() {
		try {
			return driver.findHiddenElement(messageOvlLocator).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public String getMessageType() {
		if (isDisplayed()) {
			return driver.findElement(messageLblLocator).getText();
		} else {
			return null;
		}
	}

	public String getMessageText() {
		return driver.findElement(messageTxtLocator).getText();
	}

	public void clickOk() {
		waitForMessageToDisplay();
		try {
			driver.findClickableElement(okBtnLocator).click();
			log.info("'Ok' button clicked in the prompt.");
		} catch (TimeoutException e) {
			log.error(e);
		}
		waitForMessageToClose();
	}

	public void waitToFinishLoading() {
		fluentlyWait()
		.until(ExpectedConditions
				.invisibilityOfElementLocated(loadingOvlLocator));
	}

	private void waitForMessageToDisplay() {
		fluentlyWait()
		.until(ExpectedConditions.
				visibilityOfElementLocated(messageOvlLocator));
	}

	private void waitForMessageToClose() {
		fluentlyWait()
		.until(ExpectedConditions.
				invisibilityOfElementLocated(messageOvlLocator));
		log.info("Message prompt closed.");
	}

	private FluentWait<BrowserDriver> fluentlyWait() {
		FluentWait<BrowserDriver> wait = new FluentWait<BrowserDriver>(driver);

		wait = wait.withTimeout(TIMEOUT, TimeUnit.SECONDS)
				.pollingEvery(POLLING_TIME, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);

		return wait;
	}

}

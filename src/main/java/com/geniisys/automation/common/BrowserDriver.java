package com.geniisys.automation.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public final class BrowserDriver implements WebDriver {

	private WebDriver driver;
	private Logger log = LogManager.getLogger(BrowserDriver.class.getName());

	private String driverPath;
	private int timeOut;

	public BrowserDriver(String browserName) {
		this.driver = createDriver(browserName);
	}

	private WebDriver createDriver(String browserName) {

		Properties prop  = new Properties();

		try {
			prop.load(new FileInputStream("C:/SELENIUM-AUTOMATION/CONFIG/webdriver.properties"));
		} catch (IOException e) {
			log.error(e);
		}

		driverPath = prop.getProperty("path");
		timeOut = Integer.valueOf(prop.getProperty("timeout"));

		if ("FIREFOX".equalsIgnoreCase(browserName)) {
			return firefoxDriver();
		} else if ("CHROME".equalsIgnoreCase(browserName)) {
			return chromeDriver();
		} else {
			return null;
		}
	}

	private WebDriver firefoxDriver() {
		String geckoDriverPath = driverPath + "geckodriver.exe";
		if (!new File(geckoDriverPath).exists()) {
			log.error("Driver file geckodriver.exe does not exist!");
			throw new RuntimeException
			("Driver file geckodriver.exe does not exist!");
		}
		try {
			System.setProperty("webdriver.chrome.driver", geckoDriverPath);
			return new FirefoxDriver();
		} catch (Exception e) {
			log.error("Could not create Firefox driver.");
			throw new RuntimeException("Could not create Firefox driver.");
		}
	}

	private WebDriver chromeDriver() {
		String chromeDriverPath = driverPath + "chromedriver.exe";
		if (!new File(chromeDriverPath).exists()) {
			throw new RuntimeException
			("Driver file chromedriver.exe does not exist!");
		}

		try {
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
			return new ChromeDriver();
		} catch (Exception e) {
			log.error("Could not create Chrome driver.");
			throw new RuntimeException("Could not create Chrome driver.");
		}
	}

	public WebDriver driver() {
		return this.driver;
	}

	@Override
	public String getCurrentUrl() {
		return driver().getCurrentUrl();
	}

	@Override
	public String getTitle() {
		return driver().getTitle();
	}

	@Override
	public List<WebElement> findElements(By locator) {
		return driver().findElements(locator);
	}

	@Override
	public WebElement findElement(By locator) {
		return new WebDriverWait(driver(), timeOut)
				.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}


	public WebElement findClickableElement(By locator) {
		WebElement element = new WebDriverWait(driver(), timeOut)
				.until(ExpectedConditions.elementToBeClickable(locator));

		scrollIntoView(element);

		return element;
	}

	public WebElement findHiddenElement(By locator) {
		WebElement element =new WebDriverWait(driver(), timeOut)
				.until(ExpectedConditions.presenceOfElementLocated(locator));

		scrollIntoView(element);

		return element;
	}

	@Override
	public String getPageSource() {
		return driver().getPageSource();
	}

	@Override
	public void close() {
		driver().close();
	}

	@Override
	public void quit() {
		driver().quit();
	}

	@Override
	public Set<String> getWindowHandles() {
		return driver().getWindowHandles();
	}

	@Override
	public String getWindowHandle() {
		return driver().getWindowHandle();
	}

	@Override
	public TargetLocator switchTo() {
		return driver().switchTo();
	}

	@Override
	public Navigation navigate() {
		return driver().navigate();
	}

	@Override
	public Options manage() {
		return driver().manage();
	}

	@Override
	public void get(String url) {
		driver().get(url);
	}

	public void scrollIntoView(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

}

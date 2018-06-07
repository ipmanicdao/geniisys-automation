package com.geniisys.automation.marketing.createquote.blocks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import com.geniisys.automation.common.BrowserDriver;

public class QuoteItemInformationBlock {

	private BrowserDriver driver;
	private Logger log = LogManager.getLogger(QuoteItemInformationBlock.class.getName());

	private final By itemNoField = By.xpath("//input[@id='txtItemNo']");
	private final By itemTitleField = By.xpath("//input[@id='txtItemTitle']");
	private final By itemDescField1 = By.xpath("//textarea[@id='txtItemDesc']");
	private final By itemDescField2 = By.xpath("//textarea[@id='txtItemDesc2']");
	/*	private final By currencyLov = By.xpath("//select[@id='selCurrency']");
	private final By rateField = By.xpath("//input[@id='txtCurrencyRate']");
	private final By coverageLov = By.xpath("//select[@id='selCoverage']");*/
	private final By addButton = By.xpath("//input[@id='btnAddItem']");

	public QuoteItemInformationBlock(BrowserDriver driver) {
		this.driver = driver;
	}

	public void addItem(int itemNo, String itemTitle, String itemDesc1, String itemDesc2) {
		if (itemNo != 0)
			setItemNumber(itemNo);

		setItemTitle(itemTitle);
		setItemDescription1(itemDesc1);
		setItemDescription1(itemDesc2);
		clickAdd();
	}

	public void setItemNumber(int itemNo) {
		WebElement itemNumber = driver.findClickableElement(itemNoField);
		try {
			itemNumber.clear();
			log.info("Item No. field cleared.");
			itemNumber.sendKeys(Integer.toString(itemNo));
			log.info("Item No. field value set to " + itemNo + ".");
		} catch (Exception e) {
			log.error(e);
		}
	}

	public void setItemTitle(String itemTitle) {
		try {
			driver.findElement(itemTitleField).sendKeys(itemTitle);
			log.info("Item Title field value set to '" + itemTitle + "'.");
		} catch (TimeoutException e) {
			log.error(e);
		}
	}

	public void setItemDescription1(String itemDesc1) {
		try {
			driver.findElement(itemDescField1).sendKeys(itemDesc1);
			log.info("Item Description (1) field value set to '" + itemDesc1 + "'.");
		} catch (TimeoutException e) {
			log.error(e);
		}
	}

	public void setItemDescription2(String itemDesc2) {
		driver.findElement(itemDescField2).sendKeys(itemDesc2);
	}

	public void clickAdd() {
		try {
			driver.findClickableElement(addButton).click();
			log.info("'Add' button clicked.");
		} catch (TimeoutException e) {
			log.error(e);
		}
	}

	public void selectItem(int itemNo) {
		try {
			driver.findClickableElement(
					By.xpath("//div[starts-with(@id,'mtgIC2')"
							+ " and contains(text(), '"
							+ Integer.valueOf(itemNo)
							+ "')]")).click();
			log.info("Item number " + itemNo + " selected in the table grid.");
		} catch (TimeoutException e) {
			log.error(e);
		}
	}
}

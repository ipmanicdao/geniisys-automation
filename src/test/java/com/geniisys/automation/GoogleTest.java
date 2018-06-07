package com.geniisys.automation;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GoogleTest {
	
	private WebDriver driver;
	
	@BeforeTest
	public void setUp() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@Test
	public void test() {
		driver.get("https://www.google.com");
		Assert.assertTrue(driver.getTitle().equals("Google"));
	}
	
	@AfterTest
	public void tearDown() {
		driver.close();
	}

}

package com.geniisys.automation;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.geniisys.automation.main.pages.HomePage;
import com.geniisys.automation.marketing.createquote.blocks.CreateQuotationMenu;
import com.geniisys.automation.marketing.createquote.pages.CreateQuotationPage;
import com.geniisys.automation.marketing.createquote.pages.QuotationInformationPage;
import com.geniisys.automation.marketing.createquote.pages.QuotationListingPage;
import com.geniisys.automation.marketing.createquote.pages.QuoteLineListingPage;
import com.geniisys.automation.marketing.home.pages.MarketingHomePage;

public class MarketingTest extends BaseTest {

	@Test(dataProvider = "getData")
	public void createQuotation(String lineCd, String sublineCd, String credBranch,
			String assdNo, String inceptDate, String expiryDate, String itemTitle, String perilName,
			String perilRate, String perilTsiAmt) {

		HomePage homePage = new HomePage(driver);
		MarketingHomePage mktgPage = homePage.goToMarketingPage();
		QuoteLineListingPage lineListing = mktgPage.menu().goToQuotationProcessing().goToQuoatationListing();
		QuotationListingPage quotListing = lineListing.selectLine(lineCd);
		CreateQuotationPage createQuotePage = quotListing.addNewRecord();
		createQuotePage.getQuotationInfoBlk().setSubline(sublineCd);
		createQuotePage.getQuotationInfoBlk().setCreditingBranch(credBranch);
		createQuotePage.getQuotationInfoBlk().setAssured(assdNo);
		createQuotePage.getPeriodOfInsuranceBlk().setInceptDate(inceptDate);
		createQuotePage.getPeriodOfInsuranceBlk().setExpiryDate(expiryDate);
		createQuotePage.save();

		CreateQuotationMenu createQuoteMenu = new CreateQuotationMenu(driver);
		QuotationInformationPage quoteInfoPage =  createQuoteMenu.goToQuotationInfomation();
		quoteInfoPage.getItemInfoBlk().setItemTitle(itemTitle);
		quoteInfoPage.getItemInfoBlk().clickAdd();
		quoteInfoPage.getItemInfoBlk().selectItem(1);

		quoteInfoPage.getPerilInfoBlk().show();
		quoteInfoPage.getPerilInfoBlk().setPeril(perilName);
		double dPerilRate = Double.valueOf(perilRate);
		double dPerilTsi = Double.valueOf(perilTsiAmt);
		double computedPremAmt = Math.round(dPerilRate * dPerilTsi) / 100.0;
		quoteInfoPage.getPerilInfoBlk().setPerilRate(dPerilRate);
		quoteInfoPage.getPerilInfoBlk().setPerilTsi(dPerilTsi);
		double actualPremAmt = quoteInfoPage.getPerilInfoBlk().getPerilPrem();
		assertThat(actualPremAmt).isEqualTo(computedPremAmt);
		quoteInfoPage.getPerilInfoBlk().addPeril();
		quoteInfoPage.save();
	}

	@DataProvider
	public Object[][] getData() throws IOException {

		FileInputStream fis = new FileInputStream("D:\\TestData\\Marketing_TestData.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheetAt(0);
		wb.close();

		int lastRowNum = sh.getLastRowNum();

		Object[][] data = new Object[1][lastRowNum];

		for (int i = 0; i < lastRowNum; i++)
			data[0][i] = sh.getRow(i+1).getCell(1).toString();

		return data;
	}
}

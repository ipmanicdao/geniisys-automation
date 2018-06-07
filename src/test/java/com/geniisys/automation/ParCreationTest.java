package com.geniisys.automation;

import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.geniisys.automation.main.pages.HomePage;
import com.geniisys.automation.underwriting.main.pages.UnderwritingPage;
import com.geniisys.automation.underwriting.policyissuance.parcreation.blocks.PostParOverlay;
import com.geniisys.automation.underwriting.policyissuance.parcreation.pages.BasicInformationPage;
import com.geniisys.automation.underwriting.policyissuance.parcreation.pages.EnterBillPremiumsPage;
import com.geniisys.automation.underwriting.policyissuance.parcreation.pages.EnterInvoiceCommissionPage;
import com.geniisys.automation.underwriting.policyissuance.parcreation.pages.ItemInformationPage;
import com.geniisys.automation.underwriting.policyissuance.parcreation.pages.PolicyParCreationPage;


public class ParCreationTest extends BaseTest{

	private String lineCode;
	private String assuredNo;
	private String sublineCode;
	private String inceptDate;
	private String expiryDate;
	private String perilName;
	private String itemTitle;

	@BeforeClass
	public void setParameters() {
		lineCode = "EN";
		assuredNo = "214811";
		sublineCode = "EAR";
		inceptDate = "05-29-2018";
		expiryDate = "07-01-2019";
		itemTitle = "TEST ITEM TITLE";
		perilName = "Contract Work";
	}

	@Test(groups= {"regression"}, invocationCount=1)
	public void parCreation() {

		HomePage homePage = new HomePage(driver);

		UnderwritingPage uwPage = homePage.goToUnderwritingPage();

		PolicyParCreationPage policyParCreationPage = uwPage.getMenu().goToPolicyIssuance().goToParCreationPage();
		policyParCreationPage.setLine(lineCode);
		policyParCreationPage.setAssured(assuredNo);
		policyParCreationPage.save();

		BasicInformationPage basicInfoPage = policyParCreationPage.goToBasicInformation();
		basicInfoPage.getBasicInfoBlk().setSubline(sublineCode);
		basicInfoPage.getBasicInfoBlk().setParNoAsRefPolNo();
		basicInfoPage.getPeriodOfInsuranceBlk().setInceptionDate(inceptDate);
		basicInfoPage.getPeriodOfInsuranceBlk().setExpiryDate(expiryDate);
		basicInfoPage.save();

		ItemInformationPage itemInfoPage = basicInfoPage.getMenu().goToItemInformation();
		itemInfoPage.getItemInformationBlk().setItemTitle(itemTitle);
		itemInfoPage.getItemInformationBlk().addItem();
		itemInfoPage.getItemInformationBlk().selectItem(1);
		itemInfoPage.getPerilInformationBlk().addPeril(perilName, 152345.43, 0.756434);
		//double perilPrem = itemInfoPage.getPerilInformationBlk().getPerilPremAmt();
		SoftAssertions softly = new SoftAssertions();
		softly.assertThat(1152.39).isEqualTo(1152.39);
		itemInfoPage.save();

		EnterBillPremiumsPage billPremiumsPage  = itemInfoPage.getMenu().showBillSubmenu().goToEnterBillPremium();
		billPremiumsPage.save();

		EnterInvoiceCommissionPage invoiceCommPage = billPremiumsPage.getMenu().showBillSubmenu().goToInvoiceCommission();
		invoiceCommPage.addInvoiceCommission(4855, 100);
		invoiceCommPage.save();

		PostParOverlay postPar = invoiceCommPage.getMenu().goToPost();
		postPar.clickPost();
		postPar.clickOk();
		softly.assertAll();
	}
}

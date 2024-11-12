package com.crm.comcast.configAnnotation;

import java.io.IOException;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.basetest.BaseClass1;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.listnerutility.ListnerImpClass;
import com.comcast.crm.objectrepositryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositryutility.HomePage;
import com.comcast.crm.objectrepositryutility.LoginPage;
import com.comcast.crm.objectrepositryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositryutility.OrganizationsPage;

@Listeners(com.comcast.crm.listnerutility.ListnerImpClass.class)

public class CreateOrganizationTest extends BaseClass {
	@Test(groups = "smokeTest")
	public void createOrgTest() throws EncryptedDocumentException, IOException {

		UtilityClassObject.getTest().log(Status.INFO, "read data from excel");
		// read the data from excel

		String orgName = eLib.getDataFromExcel("Sheet1", 1, 2) + jLib.getRandomNumber();

		// 2 step-navigate to organization module

		UtilityClassObject.getTest().log(Status.INFO, "navigate to org Page");
		HomePage hp = new HomePage(driver);
		hp.getorgLink().click();

		// 3 step- click on create organization module
		UtilityClassObject.getTest().log(Status.INFO, "navigate to create org Page");

		OrganizationsPage cnp = new OrganizationsPage(driver);
		cnp.getCreateNewOrgBtn().click();

		// 4 step-enter the all the details and create new organization
		UtilityClassObject.getTest().log(Status.INFO, "create a new org");

		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrg(orgName);
		UtilityClassObject.getTest().log(Status.INFO, orgName + "===>Create a new Org");

		// 5 step- verify header msg expected result
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actOrgName = oip.getHeaderMsg().getText();
		Assert.assertTrue(actOrgName.contains(orgName));
		SoftAssert soft = new SoftAssert();
		soft.assertAll();

//		if (actOrgName.contains(orgName)) {
//			System.out.println(orgName + "is verified==PASS");
//		} else {
//			System.out.println(orgName + "is not verified==FAIL");
//		}

	}

	@Test(groups = "regressionTest")
	public void CreateOrganizationWithIndustryTest() throws EncryptedDocumentException, Throwable {

		// read the data from excel

		String orgName = eLib.getDataFromExcel("Sheet1", 4, 2) + jLib.getRandomNumber();
		String Industry = eLib.getDataFromExcel("Sheet1", 4, 4);
		String type = eLib.getDataFromExcel("Sheet1", 4, 5);

		// navigate to organization module

		HomePage hp = new HomePage(driver);
		hp.getorgLink().click();

		// click on create organization module
		OrganizationsPage cnp = new OrganizationsPage(driver);
		cnp.getCreateNewOrgBtn().click();

		// enter the all the details and create new organization
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		Thread.sleep(3000);
		cnop.createOrg(orgName, Industry, type);

		// verify the dropdown industry and type info

		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actualIndustry = oip.getIndusText().getText();
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(actualIndustry, Industry);
		soft.assertAll();

//		if (actualIndustry.equals(Industry)) {
//			System.out.println(actualIndustry + "is verified==PASS");
//		} else {
//			System.out.println(actualIndustry + "is not verified==FAIL");
//		}

		String actualIndusType = oip.getTypeText().getText();
		if (actualIndusType.equals(type)) {
			System.out.println(actualIndusType + "is verified==PASS");
		} else {
			System.out.println(actualIndusType + "is not verified==FAIL");
		}

	}

	@Test(groups = "regressionTest")
	public void CreateOrgWithPhoneNumberTest() throws EncryptedDocumentException, IOException {

		// read the data from excel

		String orgName = eLib.getDataFromExcel("Sheet1", 7, 2) + jLib.getRandomNumber();
		String phoneNumber = eLib.getDataFromExcel("Sheet1", 7, 4);

		// navigate to organization module
		HomePage hp = new HomePage(driver);
		hp.getorgLink().click();

		// click on create organization module
		OrganizationsPage cnp = new OrganizationsPage(driver);
		cnp.getCreateNewOrgBtn().click();

		// enter the all the details and create new organization
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);

		cnop.createOrgPhone(orgName, phoneNumber);

		// verify header msg expected result

		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actPhoneNumber = oip.getPhoneText().getText();
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(actPhoneNumber, phoneNumber);
		soft.assertAll();
//		if (actPhoneNumber.equals(phoneNumber)) {
//			System.out.println(orgName + "is created==PASS");
//		} else {
//			System.out.println(orgName + "is not created==FAIL");
//		}
	}
}
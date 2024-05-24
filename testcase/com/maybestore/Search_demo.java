package com.maybestore;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import pageObjects.maybistore.HomeObject;
import pageObjects.maybistore.LoginObject;
import pageObjects.maybistore.OrderObject;
import pageObjects.maybistore.RegisterObject;
import pageObjects.maybistore.SearchObject;

public class Search_demo extends BaseTest {
	WebDriver driver;
//	String projectLocation = System.getProperty("user.dir");
	HomeObject homePage;
	LoginObject loginPage;
	RegisterObject registerPage;
	OrderObject orderPage;
	SearchObject searchPage;

	@Parameters({ "browser", "url" })
	@BeforeMethod
	public void beforeMethod(String browserName, String url) {
		driver = getBrowserDriver(browserName, url);
	
		homePage = new HomeObject(driver);
	}

	@Test
	public void TC_01_Dam_Link() {
//		log.info("Step 1: Click icon seacrh ");
//		homePage.clickSeachIcon();
//		log.info("Step 2: Click 'Dam' ");
//		homePage.clickDamPageLink();
		
		homePage.clickItemLink("dam");
		
		searchPage = new SearchObject(driver);
		log.info("Step 3: Dam page is Display ");
		verifyTrue(searchPage.isDisplayPageSearch());
		verifyEquals(searchPage.isDisplayTextPage(), "ĐẦM");
	}

	@Test
	public void TC_02_AoSoMi_Link() {
//		log.info("Step 1: Click icon seacrh ");
//		homePage.clickSeachIcon();
//		log.info("Step 2: Click 'AoSoMi' ");
//		homePage.clickAoSoMiPageLink();
		
		homePage.clickItemLink("ao-so-mi");
		
		searchPage = new SearchObject(driver);
		log.info("Step 3: AoSoMi page is Display ");
		verifyTrue(searchPage.isDisplayPageSearch());
		verifyEquals(searchPage.isDisplayTextPage(), "ÁO");
	} 
	@Test
	public void TC_03_DoBoi_Link() {
//		log.info("Step 1: Click icon seacrh ");
//		homePage.clickSeachIcon();
//		log.info("Step 2: Click 'DoBoi' ");
//		homePage.clickDoBoiPageLink();
//		
		homePage.clickItemLink("do-boi");
		log.info( "Step 3: DoBoi page is Display ");
		searchPage = new SearchObject(driver);
		verifyTrue(searchPage.isDisplayPageSearch());
		verifyEquals(searchPage.isDisplayTextPage(), "ĐỒ BƠI");
	}
	@Test
	public void TC_04_DoNgu_Link() {
//		log.info("Step 1: Click icon seacrh ");
//		homePage.clickSeachIcon();
//		log.info("Step 2: Click 'DoNgu' ");
//		homePage.clickDoNguPageLink();
		
		homePage.clickItemLink("do-boi");
		
		log.info("Step 3: AoSoMi page is Display ");
		searchPage = new SearchObject(driver);
		verifyTrue(searchPage.isDisplayPageSearch());
		verifyEquals(searchPage.isDisplayTextPage(), "ĐỒ NGỦ");
	}
	@Parameters({ "browser"})
	@Test
	public void TC_05_EmptySearchFieldValidation(String browser) {
		String nameTestCase = "TC_05_EmptySearchFieldValidation";
		log.info(nameTestCase + "Step 1: Click icon seacrh ");
		sleepInSecond(1);
		homePage.clickSeachIcon();
		log.info(nameTestCase + "Step 2: Sendkey Data ");
		homePage.sendKeyToSearch("");
		homePage.clickIconEnterSearch();

		log.info(nameTestCase + "Step 3: Validate field ");

		
		verifyTrue(homePage.isDisplayErrorSearch());
		String messageError = getHTML5ValidationMessageSearch();
		System.out.println("--- " + messageError);
		if(browser.equals("chrome"))
		{
			verifyEquals("Please fill out this field.", messageError);
		}
		else if(browser.equals("firefox"))
		{
			verifyEquals("Vui lòng điền vào trường này.", messageError);
		}
	}
	
	@Test
	public void TC_06_SearchFieldWithMissingInformation() {
		String nameTestCase = "TC_06_SearchFieldWithMissingInformation";
		log.info(nameTestCase + "Step 1: Click icon seacrh ");
		homePage.clickSeachIcon();
		log.info(nameTestCase + "Step 2: Sendkey Data ");
		homePage.sendKeyToSearch("ao");
		log.info(nameTestCase + "Spep 2: Click box seach");
		homePage.clickIconBoxSearch();
		log.info(nameTestCase + "Spep 3: Click submit seach");
		homePage.clickIconEnterSearch();
		
		log.info(nameTestCase + "Step 3: Verify ");
		searchPage = new SearchObject(driver);
		verifyTrue(searchPage.isDisplayResultSearch());
		verifyEquals(searchPage.isDisplayResultTextSearch(), "CÓ 248 KẾT QUẢ TÌM KIẾM PHÙ HỢP");
	}
	@Test
	public void TC_07_SearchFieldWithCorrectInformation() {
		String nameTestCase = "TC_06_SearchFieldWithMissingInformation";
		log.info(nameTestCase + "Step 1: Click icon seacrh ");
		homePage.clickSeachIcon();
		log.info(nameTestCase + "Step 2: Sendkey Data ");
		homePage.sendKeyToSearch("Áo jacket");
		log.info(nameTestCase + "Spep 2: Click box seach");
		homePage.clickIconBoxSearch();
		log.info(nameTestCase + "Spep 3: Click submit seach");
		homePage.clickIconEnterSearch();
		log.info(nameTestCase + "Step 3: Verify ");
		searchPage = new SearchObject(driver);
		verifyTrue(searchPage.isDisplayResultSearch());
		verifyEquals(searchPage.isDisplayResultTextSearch(), "CÓ 1 KẾT QUẢ TÌM KIẾM PHÙ HỢP");
	}
	@Test
	public void TC_08_SearchFieldWithEmptyQuery() {
		String nameTestCase = "TC_06_SearchFieldWithMissingInformation";
		log.info(nameTestCase + "Step 1: Click icon seacrh ");
		homePage.clickSeachIcon();
		log.info(nameTestCase + "Step 2: Sendkey Data ");
		homePage.sendKeyToSearch("             ");
		sleepInSecond(2);
		log.info(nameTestCase + "Spep 3: Click submit seach");
		homePage.clickIconEnterSearch();
		log.info(nameTestCase + "Step 4: Verify ");
		sleepInSecond(2);
		searchPage = new SearchObject(driver);
		verifyTrue(searchPage.isDisplayResultSearch());
		verifyEquals(searchPage.isDisplayResultTextSearch(), "CÓ 0 KẾT QUẢ TÌM KIẾM PHÙ HỢP");
	}
	@Test
	public void TC_09_SearchFieldWithNumericQuery() {
		String nameTestCase = "TC_06_SearchFieldWithMissingInformation";
		log.info(nameTestCase + "Step 1: Click icon seacrh ");
		homePage.clickSeachIcon();
		log.info(nameTestCase + "Step 2: Sendkey Data ");
		homePage.sendKeyToSearch("!!@!@");
		log.info(nameTestCase + "Spep 2: Click box seach");
		homePage.clickIconBoxSearch();
		log.info(nameTestCase + "Spep 3: Click submit seach");
		homePage.clickIconEnterSearch();
		log.info(nameTestCase + "Step 3: Verify ");
		searchPage = new SearchObject(driver);
		verifyTrue(searchPage.isDisplayResultSearch());
		verifyEquals(searchPage.isDisplayResultTextSearch(), "CÓ 0 KẾT QUẢ TÌM KIẾM PHÙ HỢP");
	}
	@Test
	public void TC_10_SearchFieldWithSpecialCharactersQuery() {
		String nameTestCase = "TC_06_SearchFieldWithMissingInformation";
		log.info(nameTestCase + "Step 1: Click icon seacrh ");
		homePage.clickSeachIcon();
		log.info(nameTestCase + "Step 2: Sendkey Data ");
		homePage.sendKeyToSearch("@@@#@!");
		log.info(nameTestCase + "Spep 2: Click box seach");
		homePage.clickIconBoxSearch();
		log.info(nameTestCase + "Spep 3: Click submit seach");
		homePage.clickIconEnterSearch();
		log.info(nameTestCase + "Step 3: Verify ");
		searchPage = new SearchObject(driver);
		verifyTrue(searchPage.isDisplayResultSearch());
		verifyEquals(searchPage.isDisplayResultTextSearch(), "CÓ 0 KẾT QUẢ TÌM KIẾM PHÙ HỢP");
	}
	@Test
	public void TC_11_RefreshAfterEnteringSearchInformation() {
		String nameTestCase = "TC_06_SearchFieldWithMissingInformation";
		log.info(nameTestCase + "Step 1: Click icon seacrh ");
		homePage.clickSeachIcon();
		log.info(nameTestCase + "Step 2: Sendkey Data ");
		homePage.sendKeyToSearch("áo dài");
		homePage.refreshCurrentPage(driver);
		verifyEquals(homePage.getPageURL(driver), "https://maybi.com/");

	}

	@Test
	public void TC_12_SearchForNonexistentItem() {
		String nameTestCase = "TC_06_SearchFieldWithMissingInformation";
		log.info(nameTestCase + "Step 1: Click icon seacrh ");
		homePage.clickSeachIcon();
		log.info(nameTestCase + "Step 2: Sendkey Data ");
		homePage.sendKeyToSearch("lap top ");
		log.info(nameTestCase + "Spep 2: Click box seach");
		homePage.clickIconBoxSearch();
		log.info(nameTestCase + "Spep 3: Click submit seach");
		homePage.clickIconEnterSearch();
		log.info(nameTestCase + "Step 3: Verify ");
		searchPage = new SearchObject(driver);
		verifyTrue(searchPage.isDisplayResultSearch());
		verifyEquals(searchPage.isDisplayResultTextSearch(), "CÓ 0 KẾT QUẢ TÌM KIẾM PHÙ HỢP");

	}

	public void sleepInSecond(long sleepInsecond) {
		try {
			Thread.sleep(sleepInsecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	@AfterMethod
	public void AfterMethod() {
		log.info("CLose browser");
		driver.quit();
	}

}

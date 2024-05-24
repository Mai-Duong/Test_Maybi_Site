package com.maybestore;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.maybistore.HomeObject;
import pageObjects.maybistore.LoginObject;
import pageObjects.maybistore.OrderObject;
import pageObjects.maybistore.RegisterObject;
import pageObjects.maybistore.SearchObject;

public class Testcase_Search extends BaseTest {
	WebDriver driver;
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
		log.info("Step 1: Click icon seacrh");
		log.info("Step 2: Click item link dam");
		homePage.clickItemLink("dam");
		searchPage = new SearchObject(driver);
		log.info("Step 3: Dam page is Display ");
		verifyTrue(searchPage.isDisplayPageSearch());
		verifyEquals(searchPage.isDisplayTextPage(), "ĐẦM");
	}

	@Test(groups = "TC_02_AoSoMi_Link")
	public void TC_02_AoSoMi_Link() {
		log.info("Step 1: Click icon seacrh");
		log.info("Step 2: Click item link ao so mi");
		homePage.clickItemLink("ao-so-mi");
		searchPage = new SearchObject(driver);
		log.info("Step 3: AoSoMi page is Display ");
		verifyTrue(searchPage.isDisplayPageSearch());
		verifyEquals(searchPage.isDisplayTextPage(), "ÁO");
	}

	@Test
	public void TC_03_DoBoi_Link() {
		log.info("Step 1: Click icon seacrh");
		log.info("Step 2: Click item link do boi");
		homePage.clickItemLink("do-boi");
		log.info("Step 3: DoBoi page is Display ");
		searchPage = new SearchObject(driver);
		verifyTrue(searchPage.isDisplayPageSearch());
		verifyEquals(searchPage.isDisplayTextPage(), "ĐỒ BƠI");
	}

	@Test
	public void TC_04_DoNgu_Link() {
		log.info("Step 1: Click icon seacrh");
		log.info("Step 2: Click item link do ngu");

		homePage.clickItemLink("do-boi");

		log.info("Step 3: AoSoMi page is Display ");
		searchPage = new SearchObject(driver);
		verifyTrue(searchPage.isDisplayPageSearch());
		verifyEquals(searchPage.isDisplayTextPage(), "ĐỒ NGỦ");
	}

	@Parameters({ "browser" })
	@Test
	public void TC_05_EmptySearchFieldValidation(String browser) {
		log.info("Step 1: Click icon seacrh ");
		sleepInSecond(1);
		homePage.clickSeachIcon();
		log.info("Step 2: Sendkey Data ");
		homePage.sendKeyToSearch("");
		homePage.clickIconEnterSearch();

		log.info("Step 3: Validate field ");
		verifyTrue(homePage.isDisplayErrorSearch());
		String messageError = getHTML5ValidationMessageSearch();
		System.out.println("--- " + messageError);
		if (browser.equals("chrome")) {
			verifyEquals("Please fill out this field.", messageError);
		} else if (browser.equals("firefox")) {
			verifyEquals("Vui lòng điền vào trường này.", messageError);
		}
	}

	@Test
	public void TC_06_SearchFieldWithMissingInformation() {
		log.info("Step 1: Enter data seacrh ");
		homePage.seacrhProduct("ao");
		log.info("Step 2: Verify result search ");
		searchPage = new SearchObject(driver);
		verifyTrue(searchPage.isDisplayResultSearch());
		verifyEquals(searchPage.isDisplayResultTextSearch(), "CÓ 249 KẾT QUẢ TÌM KIẾM PHÙ HỢP");
	}

	@Test
	public void TC_07_SearchFieldWithCorrectInformation() {

		log.info("Step 1: Enter data seacrh ");
		homePage.seacrhProduct("Áo jacket");

		log.info("Step 2: Verify result search ");
		searchPage = new SearchObject(driver);
		verifyTrue(searchPage.isDisplayResultSearch());
		verifyEquals(searchPage.isDisplayResultTextSearch(), "CÓ 1 KẾT QUẢ TÌM KIẾM PHÙ HỢP");
	}

	@Test
	public void TC_08_SearchFieldWithEmptyQuery() {
		log.info("Step 1: Click icon seacrh ");
		homePage.clickSeachIcon();
		log.info("Step 2: Sendkey Data ");
		homePage.sendKeyToSearch("             ");
		sleepInSecond(2);
		log.info("Spep 3: Click submit seach");
		homePage.clickIconEnterSearch();
		log.info("Step 4: Verify result search ");
		sleepInSecond(2);
		searchPage = new SearchObject(driver);
		verifyTrue(searchPage.isDisplayResultSearch());
//		verifyEquals(searchPage.isDisplayResultTextSearch(), "CÓ 573 KẾT QUẢ TÌM KIẾM PHÙ HỢP");
	}

	@Test
	public void TC_09_SearchFieldWithNumericQuery() {

		log.info("Step 1: Enter data seacrh ");
		homePage.seacrhProduct("434534");

		log.info("Step 2: Verify result search ");
		searchPage = new SearchObject(driver);
		verifyTrue(searchPage.isDisplayResultSearch());
		verifyEquals(searchPage.isDisplayResultTextSearch(), "CÓ 0 KẾT QUẢ TÌM KIẾM PHÙ HỢP");
	}

	@Test
	public void TC_10_SearchFieldWithSpecialCharactersQuery() {
		log.info("Step 1: Enter data seacrh ");
		homePage.seacrhProduct("@#!#");
		log.info("Step 2: Verify result search ");
		searchPage = new SearchObject(driver);
		verifyTrue(searchPage.isDisplayResultSearch());
		verifyEquals(searchPage.isDisplayResultTextSearch(), "CÓ 0 KẾT QUẢ TÌM KIẾM PHÙ HỢP");
	}

	@Parameters({ "url" })
	@Test
	public void TC_11_RefreshAfterEnteringSearchInformation(String url) {
		String nameTestCase = "TC_06_SearchFieldWithMissingInformation";
		log.info(nameTestCase + "Step 1: Click icon seacrh ");
		homePage.clickSeachIcon();
		log.info(nameTestCase + "Step 2: Sendkey Data ");
		homePage.sendKeyToSearch("áo dài");
		homePage.refreshCurrentPage(driver);
		verifyEquals(homePage.getPageURL(driver), url);

	}

	@Test
	public void TC_12_SearchForNonexistentItem() {
		log.info("Step 1: Enter data seacrh ");
		homePage.seacrhProduct("lap top");
		log.info("Step 2: Verify result search ");
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

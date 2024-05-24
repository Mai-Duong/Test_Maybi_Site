package com.maybestore;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.maybistore.CartPageObject;
import pageObjects.maybistore.CheckountObject;
import pageObjects.maybistore.DetailProductObject;
import pageObjects.maybistore.HomeObject;
import pageObjects.maybistore.LoginObject;
import pageObjects.maybistore.OrderObject;
import pageObjects.maybistore.RegisterObject;
import pageObjects.maybistore.SearchObject;

public class Checkount extends BaseTest {
	WebDriver driver;
	HomeObject homePage;
	LoginObject loginPage;
	RegisterObject registerPage;
	OrderObject orderPage;
	SearchObject searchPage;
	DetailProductObject detailProduct;
	CheckountObject checkountPage;
	CartPageObject cartPage;
	String fullName;
	String email;
	String phone;
	String address;
	String city;
	String district;
	String ward;

	@Parameters({ "browser", "url" })
	@BeforeMethod
	public void beforeMethod(String browserName, String url) {
		driver = getBrowserDriver(browserName, url);
		homePage = new HomeObject(driver);
		homePage.hoverLogin();
		homePage.clickToLoginLink();
		loginPage = new LoginObject(driver);
		log.info("Step 4: Sendkey data Email Textbox ");
		loginPage.enterToEmailTextbox("duongmai060801@gmail.com");
		log.info("Step 5: Sendkey data Pass ");
		loginPage.enterToPasswordTextbox("Mai2001@");
		sleepInSecond(1);
		loginPage.clickToLoginButton();
		loginPage.clickToCartLink();
		cartPage = new CartPageObject(driver);
		cartPage.clickOrderButton();
		fullName = "Meii Dwg";
		email = RandomEmail();
		phone = "0344969345";
		address = "cntt";
		city = "Thái Nguyên";
		district = "Huyện Phú Bình";
		ward = "Xã Nhã Lộng";

	}
	@Test
	public void TC_01_Empty_All_Fields() {
		sleepInSecond(3);
		log.info("Step 1: Open Checkout page : ");
		checkountPage = new CheckountObject(driver);
		log.info("Step 2: Click submit : ");
		checkountPage.clickSubmitShopping();
		log.info("Step 2: Verify error message : ");
		verifyEquals(checkountPage.isDisPlayErrorPhone(), "Số điện thoại không được trống");
		verifyEquals(checkountPage.isDisPlayErrorAddress(), "Địa chỉ không được trống");
		verifyEquals(checkountPage.isDisPlayErrorCity(), "Vui lòng chọn tỉnh thành");
		verifyEquals(checkountPage.isDisPlayErrorDistrict(), "Vui lòng chọn quận huyện");
		verifyEquals(checkountPage.isDisPlayErrorWard(), "Vui lòng chọn phường xã");
	}
	@Test
	public void TC_02_Check_required_fields() {

		log.info("Step 1: Open Checkout page : ");
		checkountPage = new CheckountObject(driver);

		log.info("Step 2: Sendkey phone: " + phone);
		checkountPage.sendkeyPhone(phone);

		log.info("Step 3: Sendkey address: " + address);
		checkountPage.sendkeyAddress("");

		log.info("Step 4: Sendkey city: " + city);
		sleepInSecond(2);
		checkountPage.selectByVisibleTextCity(city);

		log.info("Step 5: Sendkey district: " + district);
		sleepInSecond(2);
		checkountPage.selectByVisibleTextDistrict(district);

		log.info("Step 6: Sendkey ward: " + ward);
		sleepInSecond(2);
		checkountPage.selectByVisibleTextWard(ward);

		sleepInSecond(2);
		log.info("Step 7: Click submit : ");
		checkountPage.clickSubmitShopping();
		sleepInSecond(2);
		log.info("Step 8: Show error message : ");

//		verifyEquals(checkountPage.isDisPlayErrorPhone(), "Số điện thoại không được trống");
		verifyEquals(checkountPage.isDisPlayErrorAddress(), "Địa chỉ không được trống");
//		verifyEquals(checkountPage.isDisPlayErrorCity(), "Vui lòng chọn tỉnh thành");
//		verifyEquals(checkountPage.isDisPlayErrorDistrict(), "Vui lòng chọn quận huyện");
//		verifyEquals(checkountPage.isDisPlayErrorWard(), "Vui lòng chọn phường xã");
	}

	@Test
	public void TC_03_ValidatFullName() {
		sleepInSecond(2);
		log.info("Step 1: Open Checkout page : ");
		checkountPage = new CheckountObject(driver);

		log.info("Step 2: Sendkey phone: " + "!@#$%");
		checkountPage.sendkeyFullName("!@#$%");

		
//		log.info("Step 4: Sendkey phone: " + phone);
//		checkountPage.sendkeyPhone(phone);
//
//		log.info("Step 5: Sendkey address: " + address);
//		sleepInSecond(2);
//		checkountPage.sendkeyAddress(address);
//
//		log.info("Step 6: Sendkey city: " + city);
//		sleepInSecond(2);
//		checkountPage.selectByVisibleTextCity(city);
//
//		log.info("Step 7: Sendkey district: " + district);
//		sleepInSecond(2);
//		checkountPage.selectByVisibleTextDistrict(district);
//
//		log.info("Step 8: Sendkey ward: " + ward);
//		sleepInSecond(2);
//		checkountPage.selectByVisibleTextWard(ward);

		sleepInSecond(2);
		log.info("Step 9: Click submit : ");
		checkountPage.clickSubmitShopping();
		checkountPage.clickSubmitShopping();
		sleepInSecond(2);
		log.info("Step 10: Show error message : ");
		sleepInSecond(1);
		
		verifyEquals(checkountPage.isDisPlayErrorName(),"Tên không bao gồm kí tự đặc biệt và số!!!");
//		verifyTrue(checkountPage.isSuccessfull());
	}
	
	@Test
	public void TC_04_ValidatPhone() {
		sleepInSecond(2);
		log.info("Step 1: Open Checkout page : ");
		checkountPage = new CheckountObject(driver);
		
		log.info("Step 4: Sendkey phone: " + "sodianthoai");
		checkountPage.sendkeyPhone("sodianthoai");
		
		log.info("Step 5: Sendkey address: " + address);
		sleepInSecond(2);
		checkountPage.sendkeyAddress(address);
		
		log.info("Step 6: Sendkey city: " + city);
		sleepInSecond(2);
		checkountPage.selectByVisibleTextCity(city);
		
		log.info("Step 7: Sendkey district: " + district);
		sleepInSecond(2);
		checkountPage.selectByVisibleTextDistrict(district);
		
		log.info("Step 8: Sendkey ward: " + ward);
		sleepInSecond(2);
		checkountPage.selectByVisibleTextWard(ward);
		
		sleepInSecond(2);
		log.info("Step 9: Click submit : ");
		checkountPage.clickSubmitShopping();
		sleepInSecond(2);
		log.info("Step 10: Show error message : ");
		sleepInSecond(1);
		verifyEquals(checkountPage.isDisPlayErrorPhone(),"Số điện thoại không hợp lệ (độ dài từ 8 - 15 ký tự, không chứa ký tự đặc biệt và khoảng trắng)");
//		verifyTrue(checkountPage.isSuccessfull());
	}


	@Test
	public void TC_05_ValidatAddress() {
		log.info("Step 1: Open Checkout page : ");
		checkountPage = new CheckountObject(driver);

		log.info("Step 3: Sendkey phone: " + phone);
		checkountPage.sendkeyPhone(phone);

		log.info("Step 4: Sendkey address: " + "@@!");
		sleepInSecond(2);
		checkountPage.sendkeyAddress("@@!");

//		log.info("Step 5: Sendkey city: " + city);
//		sleepInSecond(2);
//		checkountPage.selectByVisibleTextCity(city);
//
//		log.info("Step 6: Sendkey district: " + district);
//		sleepInSecond(2);
//		checkountPage.selectByVisibleTextDistrict(district);
//
//		log.info("Step 7: Sendkey ward: " + ward);
//		sleepInSecond(2);
//		checkountPage.selectByVisibleTextWard(ward);

		sleepInSecond(2);
		log.info("Step 8: Click submit : ");
		checkountPage.clickSubmitShopping();
		checkountPage.clickSubmitShopping();

		sleepInSecond(2);
		log.info("Step 9: Show error message : ");
		verifyEquals(checkountPage.isDisPlayErrorAddress(), "Địa chỉ không hợp lệ");
	}

	@Test
	public void TC_06_ValidatCity_District_Ward() {
		log.info("Step 1: Open Checkout page : ");
		checkountPage = new CheckountObject(driver);


		log.info("Step 3: Sendkey phone: " + phone);
		checkountPage.sendkeyPhone(phone);

		log.info("Step 4: Sendkey address: " + address);
		sleepInSecond(2);
		checkountPage.sendkeyAddress(address);

		log.info("Step 5: Sendkey city: " + city);
		sleepInSecond(2);
		checkountPage.selectByVisibleTextCity(city);

		log.info("Step 6: Check City field");
		verifyEquals(String.valueOf(checkountPage.getElementSizeCity()), "64");
		verifyFalse(checkountPage.isMultipleCity());
		sleepInSecond(2);
		verifyEquals(checkountPage.selectSelectedItemDropdownCity(), city);

		log.info("Step 7: Check District field " + district);
		sleepInSecond(2);
		checkountPage.selectByVisibleTextDistrict(district);
		verifyEquals(String.valueOf(checkountPage.getElementSizeDistrict()), "10");
		verifyFalse(checkountPage.isMultipleDistrict());
		sleepInSecond(2);
		verifyEquals(checkountPage.selectSelectedItemDropdownDistrict(), district);

		log.info("Step 8: Check Ward field " + ward);
		sleepInSecond(2);
		checkountPage.selectByVisibleTextWard(ward);
		verifyEquals(String.valueOf(checkountPage.getElementSizeWard()), "22");
		verifyFalse(checkountPage.isMultipleWard());
		sleepInSecond(2);
		verifyEquals(checkountPage.selectSelectedItemDropdownWard(), ward);
		
//		log.info("Step 8: Click submit : ");
//		checkountPage.clickSubmitShopping();
		
	}

	public void sleepInSecond(long sleepInsecond) {
		try {
			Thread.sleep(sleepInsecond * 1000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	@AfterMethod
	public void AfterMethod() {
		log.info("CLose browser");
		driver.quit();
	}

}

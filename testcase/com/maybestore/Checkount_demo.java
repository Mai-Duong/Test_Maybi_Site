package com.maybestore;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.maybistore.CheckountObject;
import pageObjects.maybistore.DetailProductObject;
import pageObjects.maybistore.HomeObject;
import pageObjects.maybistore.LoginObject;
import pageObjects.maybistore.OrderObject;
import pageObjects.maybistore.RegisterObject;
import pageObjects.maybistore.SearchObject;

public class Checkount_demo extends BaseTest {
	WebDriver driver;
	HomeObject homePage;
	LoginObject loginPage;
	RegisterObject registerPage;
	OrderObject orderPage;
	SearchObject searchPage;
	DetailProductObject detailProduct;
	CheckountObject checkountPage;
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
		driver.get(url + "products/ao-dai-om-voan-in-hoa-tiet-phoi-beo-vai");
		fullName = "Meii Dwg";
		email = RandomEmail();
		phone = "0987654321";
		address = "cntt";
		city = "Thái Nguyên";
		district = "Huyện Phú Bình";
		ward = "Xã Nhã Lộng";
	}
	@Test
	public void TC_01_Empty_All_Fields() {
		detailProduct = new DetailProductObject(driver);
		sleepInSecond(3);
		detailProduct.clickBuyNow();
		log.info("Step 1: Open Checkout page : ");
		checkountPage = new CheckountObject(driver);
		log.info("Step 2: Click submit : ");
		checkountPage.clickSubmitShopping();
		log.info("Step 2: Show error message : ");
		verifyEquals(checkountPage.isDisPlayErrorName(), "Vui lòng nhập họ tên");
		verifyEquals(checkountPage.isDisPlayErrorEmail(), "Địa chỉ email không được trống");
		verifyEquals(checkountPage.isDisPlayErrorPhone(), "Số điện thoại không được trống");
		verifyEquals(checkountPage.isDisPlayErrorAddress(), "Địa chỉ không được trống");
		verifyEquals(checkountPage.isDisPlayErrorCity(), "Vui lòng chọn tỉnh thành");
		verifyEquals(checkountPage.isDisPlayErrorDistrict(), "Vui lòng chọn quận huyện");
		verifyEquals(checkountPage.isDisPlayErrorWard(), "Vui lòng chọn phường xã");
	}

	@Test
	public void TC_02_Check_required_fields() {
		detailProduct = new DetailProductObject(driver);
		detailProduct.clickBuyNow();
		log.info("Step 1: Open Checkout page : ");
		checkountPage = new CheckountObject(driver);
		log.info("Step 2: Sendkey full name: " + "");
		checkountPage.sendkeyFullName("");

		log.info("Step 3: Sendkey email: " + email);
		checkountPage.sendkeyFEmail(email);

		log.info("Step 4: Sendkey phone: " + phone);
		checkountPage.sendkeyPhone(phone);

		log.info("Step 5: Sendkey address: " + address);
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
		verifyEquals(checkountPage.isDisPlayErrorName(), "Vui lòng nhập họ tên");
//		verifyEquals(checkountPage.isDisPlayErrorEmail(), "Địa chỉ email không được trống");
//		verifyEquals(checkountPage.isDisPlayErrorPhone(), "Số điện thoại không được trống");
//		verifyEquals(checkountPage.isDisPlayErrorAddress(), "Địa chỉ không được trống");
//		verifyEquals(checkountPage.isDisPlayErrorCity(), "Vui lòng chọn tỉnh thành");
//		verifyEquals(checkountPage.isDisPlayErrorDistrict(), "Vui lòng chọn quận huyện");
//		verifyEquals(checkountPage.isDisPlayErrorWard(), "Vui lòng chọn phường xã");
	}
	@Test
	public void TC_03_ValidatFullName() {
		detailProduct = new DetailProductObject(driver);
		detailProduct.clickBuyNow();
		log.info("Step 1: Open Checkout page : ");
		checkountPage = new CheckountObject(driver);

		log.info("Step 2: Sendkey fullname: " + "");
		checkountPage.sendkeyFullName("@!#@@");

		log.info("Step 3: Sendkey email: " + email);
		checkountPage.sendkeyFEmail(email);

		log.info("Step 4: Sendkey phone: " + phone);
		checkountPage.sendkeyPhone(phone);

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
		verifyEquals(checkountPage.isDisPlayErrorName(), "Tên không bao gồm kí tự đặc biệt và số!!!");
	}
	@Test
	public void TC_04_ValidatEmail() {
		detailProduct = new DetailProductObject(driver);
		sleepInSecond(2);
		detailProduct.clickBuyNow();
		log.info("Step 1: Open Checkout page : ");
		checkountPage = new CheckountObject(driver);

		log.info("Step 2: Sendkey fullname: " + fullName);
		checkountPage.sendkeyFullName(fullName);

		log.info("Step 3: Sendkey email: " + "duongmai@");
		checkountPage.sendkeyFEmail("duongmai@");

		log.info("Step 4: Sendkey phone: " + phone);
		checkountPage.sendkeyPhone(phone);

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
		verifyEquals(checkountPage.isDisPlayErrorEmail(), "Địa chỉ email không hợp lệ");
	}

	@Test
	public void TC_05_ValidatPhone() {
		detailProduct = new DetailProductObject(driver);
		sleepInSecond(2);
		detailProduct.clickBuyNow();
		log.info("Step 1: Open Checkout page : ");
		checkountPage = new CheckountObject(driver);

		log.info("Step 2: Sendkey fullname: " + fullName);
		checkountPage.sendkeyFullName(fullName);

		log.info("Step 3: Sendkey email: " + email);
		checkountPage.sendkeyFEmail(email);

		log.info("Step 4: Sendkey phone: " + "0344969321");
		checkountPage.sendkeyPhone("0344969321");

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
		verifyTrue(checkountPage.isSuccessfull());
	}
	@Test
	public void TC_06_ValidatAddress() {
		detailProduct = new DetailProductObject(driver);
		sleepInSecond(2);
		detailProduct.clickBuyNow();
		log.info("Step 1: Open Checkout page : ");
		checkountPage = new CheckountObject(driver);

		log.info("Step 2: Sendkey fullname: " + fullName);
		checkountPage.sendkeyFullName(fullName);

		log.info("Step 3: Sendkey email: " + email);
		checkountPage.sendkeyFEmail(email);

		log.info("Step 4: Sendkey phone: " + phone);
		checkountPage.sendkeyPhone(phone);

		log.info("Step 5: Sendkey address: " + "@@!");
		sleepInSecond(2);
		checkountPage.sendkeyAddress("@@!");

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
		verifyEquals(checkountPage.isDisPlayErrorAddress(), "Địa chỉ không hợp lệ");
	}

	@Test
	public void TC_07_ValidatCity_District_Ward() {
		detailProduct = new DetailProductObject(driver);
		sleepInSecond(2);
		detailProduct.clickBuyNow();
		log.info("Step 1: Open Checkout page : ");
		checkountPage = new CheckountObject(driver);

		log.info("Step 2: Sendkey fullname: " + fullName);
		checkountPage.sendkeyFullName(fullName);

		log.info("Step 3: Sendkey email: " + email);
		checkountPage.sendkeyFEmail(email);

		log.info("Step 4: Sendkey phone: " + phone);
		checkountPage.sendkeyPhone(phone);

		log.info("Step 5: Sendkey address: " + address);
		sleepInSecond(2);
		checkountPage.sendkeyAddress(address);

		log.info("Step 6: Sendkey city: " + city);
		sleepInSecond(2);
		checkountPage.selectByVisibleTextCity(city);

		log.info("Step 7: Check City field");
		verifyEquals(String.valueOf(checkountPage.getElementSizeCity()), "64");
		verifyFalse(checkountPage.isMultipleCity());
		sleepInSecond(2);
		verifyEquals(checkountPage.selectSelectedItemDropdownCity(), city);

		log.info("Step 8: Check District field " + district);
		sleepInSecond(2);
		checkountPage.selectByVisibleTextDistrict(district);
		verifyEquals(String.valueOf(checkountPage.getElementSizeDistrict()), "10");
		verifyFalse(checkountPage.isMultipleDistrict());
		sleepInSecond(2);
		verifyEquals(checkountPage.selectSelectedItemDropdownDistrict(), district);

		log.info("Step 9: Check Ward field " + ward);
		sleepInSecond(2);
		checkountPage.selectByVisibleTextWard(ward);
		verifyEquals(String.valueOf(checkountPage.getElementSizeWard()), "22");
		verifyFalse(checkountPage.isMultipleWard());
		sleepInSecond(2);
		verifyEquals(checkountPage.selectSelectedItemDropdownWard(), ward);
	}

	public void sleepInSecond(long sleepInsecond) {
		try {
			Thread.sleep(sleepInsecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String RandomEmail() {
		Random rand = new Random();
		return "test" + rand.nextInt(99999) + "@gmail.com";
	}

	@AfterMethod
	public void AfterMethod() {
		log.info("CLose browser");
		driver.quit();
	}


}

package com.maybestore;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.maybistore.CartPageObject;
import pageObjects.maybistore.DetailProductObject;
import pageObjects.maybistore.HomeObject;
import pageObjects.maybistore.LoginObject;
import pageObjects.maybistore.OrderObject;
import pageObjects.maybistore.RegisterObject;
import pageObjects.maybistore.SearchObject;

public class Testcase_Cart extends BaseTest {
	WebDriver driver;
	HomeObject homePage;
	LoginObject loginPage;
	RegisterObject registerPage;
	OrderObject orderPage;
	SearchObject searchPage;
	DetailProductObject detailProduct;
	CartPageObject cartPage;

	@Parameters({ "browser", "url" })
	@BeforeMethod
	public void beforeMethod(String browserName, String url) {
		driver = getBrowserDriver(browserName, url);	
		homePage = new HomeObject(driver);
	}
	@Parameters({"url" })
	@Test(groups="TC_01_AddDuplicateProductsToCartAndCheckCart")
	public void TC_01_AddDuplicateProductsToCartAndCheckCart(String url) {
		log.info("Step 1: Open detail page product");
		driver.get(url +"products/ao-dai-om-voan-in-hoa-tiet-phoi-beo-vai");
		String nameProduct = "Áo dài ôm voan in họa tiết phối bèo vai";		
		detailProduct = new DetailProductObject(driver);
		detailProduct.clickColorRadio();
		
		log.info("Step 2: Add the first product to cart");
		detailProduct.clickAddCartButton();
		log.info("Step 3: Valify product cart ");		
		verifyEquals(detailProduct.nameProduct(), nameProduct);
		verifyEquals(detailProduct.colorProduct(), "Kem phối, S");
		verifyEquals(detailProduct.price(), "299,400");
		verifyEquals(detailProduct.quantity(), "(1) sản phẩm");
		
		log.info("Step 4: Close box cart");		
		detailProduct.clickAddCartButton();		
		log.info("Step 7: Verify product detail");
		verifyEquals(detailProduct.nameProduct(), nameProduct);
		verifyEquals(detailProduct.colorProduct(), "Kem phối, S");
		verifyEquals(detailProduct.price(), "598,800");
		verifyEquals(detailProduct.quantity(), "(2) sản phẩm");

		log.info("Step 8: Show detail cart");		
		detailProduct.clickShowCart();
		log.info("Step 9: Verify in cart");
		cartPage = new CartPageObject(driver);
		verifyEquals(cartPage.firstProductAddToCart(), nameProduct);
		verifyEquals(cartPage.priceFirstProductAddToCart(), "598,800₫");
		verifyEquals(cartPage.quantityFirstProductAddToCart(), "2");

	}
	@Parameters({"url" })
	@Test(groups="TC_02_AddDifferentProductsToCart")
	public void TC_02_AddDifferentProductsToCart(String url) {
		log.info("Step 1: Open detail page product");
		driver.get(url +"products/ao-dai-om-voan-in-hoa-tiet-phoi-beo-vai");
		String firstProduct = "Áo dài ôm voan in họa tiết phối bèo vai";		
		detailProduct = new DetailProductObject(driver);
		detailProduct.clickColorRadio();
		
		log.info("Step 2: Add the first product to cart");
		detailProduct.clickAddCartButton();
		log.info("Step 3: Valify ");
		verifyEquals(detailProduct.nameProduct(), firstProduct);
		verifyEquals(detailProduct.colorProduct(), "Kem phối, S");
		verifyEquals(detailProduct.price(), "299,400");
		verifyEquals(detailProduct.quantity(), "(1) sản phẩm");
		log.info("Step 4: Close box cart");
		detailProduct.clickCloseAddCart();
		log.info("Step 5: Open detail page product");
		driver.get(url + "products/dam-midi-om-voan-cat-phoi-nhun-trang-tri");
		detailProduct = new DetailProductObject(driver);
		detailProduct.clickColorRadio();
		log.info("Step 6: Add the first product to cart");
		detailProduct.clickAddCartButton();
		log.info("Step 7: Valify ");
		String secondProduct = "Đầm midi ôm voan cát phối nhún trang trí";
		verifyEquals(detailProduct.nameProduct(), secondProduct);
		verifyEquals(detailProduct.colorProduct(), "Đen, M");
		verifyEquals(detailProduct.price(), "748,500");
		verifyEquals(detailProduct.quantity(), "(2) sản phẩm");
		log.info("Step 8: Close");
		log.info("Step 8: Show detail cart");
		detailProduct.clickShowCart();
		log.info("Step 9: Verify in cart");
		cartPage = new CartPageObject(driver);
		verifyEquals(cartPage.firstProductAddToCart(), firstProduct);
		verifyEquals(cartPage.priceFirstProductAddToCart(), "299,400₫");
		verifyEquals(cartPage.quantityFirstProductAddToCart(), "1");
		verifyEquals(cartPage.secondProductAddToCart(), secondProduct);
		verifyEquals(cartPage.priceSecondProductAddToCart(), "449,100₫");
		verifyEquals(cartPage.quantitySecondProductAddToCart(), "1");
	}
	@Test
	public void TC_03_EmptyCart() {
		log.info("Step 1 : Open cart");
		homePage.clickCart();
		cartPage = new CartPageObject(driver);
		log.info("Step 2: verify cart");
		verifyTrue(cartPage.isDisplayEmptyCart());
	}
	@Parameters({"url" })
	@Test(groups="TC_04_DeleteAllProductInCart")
	public void TC_04_DeleteAllProductInCart(String url) {
		log.info("Step 1: Open detail page product");
		
		driver.get(url + "products/ao-dai-om-voan-in-hoa-tiet-phoi-beo-vai");
		String firstProduct = "Áo dài ôm voan in họa tiết phối bèo vai";
		detailProduct = new DetailProductObject(driver);
		detailProduct.clickColorRadio();
		log.info("Step 2: Add to cart");
		detailProduct.clickAddCartButton();
		log.info("Step 3: Verify product box cart");
		verifyEquals(detailProduct.nameProduct(), firstProduct);
		verifyEquals(detailProduct.colorProduct(), "Kem phối, S");
		verifyEquals(detailProduct.price(), "299,400");
		verifyEquals(detailProduct.quantity(), "(1) sản phẩm");
		log.info("Step 4: Show detail cart");
		detailProduct.clickShowCart();
		cartPage = new CartPageObject(driver);
		log.info("Step 5: Delete product");
		sleepInSecond(1);
		cartPage.clickDeleteProduct();
		verifyTrue(cartPage.isDisplayEmptyCart());
	}
	@Parameters({"url" })
	@Test(groups="TC_05_IncreaseQuantity")
	public void TC_05_IncreaseQuantity(String url) {
		log.info("Step 1: Open detail page product");
		driver.get(url + "products/ao-dai-om-voan-in-hoa-tiet-phoi-beo-vai");		
		String firstProduct = "Áo dài ôm voan in họa tiết phối bèo vai";
		detailProduct = new DetailProductObject(driver);
		log.info("Step 2: Add to cart");
		detailProduct.clickColorRadio();	
		detailProduct.clickAddCartButton();
		log.info("Step 3: Verify product box cart");
		verifyEquals(detailProduct.nameProduct(), firstProduct);
		verifyEquals(detailProduct.colorProduct(), "Kem phối, S");
		verifyEquals(detailProduct.price(), "299,400");
		verifyEquals(detailProduct.quantity(), "(1) sản phẩm");
		log.info("Step 4: Show detail cart");
		detailProduct.clickShowCart();
		cartPage = new CartPageObject(driver);
		log.info("Step 5: Click Increase Quantity");
		cartPage.clickIncreaseQuantity();
		verifyEquals(cartPage.quantityFirstProductAddToCart(), "2");
		sleepInSecond(2);
		verifyEquals(cartPage.priceFirstProductAddToCart(), "598,800₫");
	}
	@Parameters({"url" })
	@Test(groups="TC_06_DecreaseQuantity")
	public void TC_06_DecreaseQuantity(String url) {
		log.info("Step 1: Open detail page product");
		driver.get(url + "products/ao-dai-om-voan-in-hoa-tiet-phoi-beo-vai");				
		String firstProduct = "Áo dài ôm voan in họa tiết phối bèo vai";
		detailProduct = new DetailProductObject(driver);
		detailProduct.clickColorRadio();		
		log.info("Step 2: Click add quantity");
		detailProduct.clickAddQuantity();	
		log.info("Step 3: Add to cart");
		detailProduct.clickAddCartButton();	
		log.info("Step 4: Verify");
		verifyEquals(detailProduct.nameProduct(), firstProduct);
		verifyEquals(detailProduct.colorProduct(), "Kem phối, S");
		verifyEquals(detailProduct.price(), "598,800");
		verifyEquals(detailProduct.quantity(), "(2) sản phẩm");
		log.info("Step 5: Click show cart");
		detailProduct.clickShowCart();
		cartPage = new CartPageObject(driver);
		log.info("Step 6: Click decrease product");
		cartPage.DecreaseQuantity();
		log.info("Step 7: Verify");
		verifyEquals(cartPage.quantityFirstProductAddToCart(), "1");
		sleepInSecond(2);
		verifyEquals(cartPage.priceFirstProductAddToCart(), "299,400₫");
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

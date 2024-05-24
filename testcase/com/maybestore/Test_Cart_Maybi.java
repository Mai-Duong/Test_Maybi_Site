package com.maybestore;

import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;
public class Test_Cart_Maybi extends BaseTest {

	WebDriver driver;
	By cartLink = By.xpath("//span[text()='Giỏ hàng']");
	By cartQuantityEmpty = By.xpath("//span[text()='Giỏ hàng (0)']");
	By cartEmpty = By.xpath("//h3[contains(text(),'“Hổng” có gì trong giỏ hết')]");
	By shoppingButton = By.xpath("//a[text()='Mua sắm ngay']");
	By searchIconSubmit = By.xpath("//button[@class='btn text-white icon-fallback-text h-100']");
	By searchIcon = By.xpath("//span[text()='Tìm kiếm']");
	By searchInput = By.xpath("//input[@class='input-group-field auto-search form-control ']");
	By resultSearchPage = By.xpath("//h1[@class='title_page']");
	By seacrhProduct = By.xpath("//span[text()='Sản phẩm ']");

	By nameProductFirst = By.xpath("//a[text()='Áo bra ren đen']");
	By nameProductSecond = By.xpath("//a[text()='Áo gile cotton chéo']");

	By buttonAddToCard = By.xpath("//button[@class='btn btn_add_cart btn-cart add_to_cart is-full']");


	@BeforeMethod
	public void beforeMethod() {
		driver = new ChromeDriver();
		driver.get("https://maybi.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public void TC_01_Cart_Empty() {
		driver.findElement(cartLink).click();
		Assert.assertTrue(driver.findElement(cartQuantityEmpty).isDisplayed());
		Assert.assertTrue(driver.findElement(cartEmpty).isDisplayed());
		Assert.assertTrue(driver.findElement(shoppingButton).isDisplayed());
	}

	public void TC_02_Adding_Two_Identical_Products() {
		driver.findElement(searchIcon).click();
		driver.findElement(searchInput).sendKeys("ao");
		driver.findElement(seacrhProduct).click();
		driver.findElement(searchIconSubmit).click();
//		/////
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(nameProductFirst);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);

		js = (JavascriptExecutor) driver;
		WebElement element1 = driver.findElement(By.xpath("//input[@id='swatch-0-den']"));
		js.executeScript("arguments[0].scrollIntoView(true);", element1);
		executor.executeScript("arguments[0].click();", element1);
//		driver.findElement(By.xpath("//input[@id='swatch-0-den']")).click();
//		driver.findElement(By.xpath("//input[@id='swatch-1-s']")).click();
//		driver.findElement(By.xpath("//input[@id='qtym']")).clear();
//		driver.findElement(By.xpath("//input[@id='qtym']")).sendKeys("1");
		driver.findElement(By.xpath("//button[@class='btn btn_add_cart btn-cart add_to_cart is-full']")).click();

		sleepInSecond(2);
		driver.findElement(By.xpath("//button[@class='close']/span[text()='×']")).click();
		sleepInSecond(2);
		driver.findElement(By.xpath("//button[@class='btn btn_add_cart btn-cart add_to_cart is-full']")).click();
		// click cart
		driver.findElement(By.xpath("//a[@class='btn btn-main checkout_button btn-full']")).click();

		// assert/equ
		Assert.assertEquals(driver.findElement(By.xpath("//a[@class='text2line link']")).getText(), "Áo bra ren đen");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@class='variant-title']")).getText(), "Đen / S");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@class='product-price price']")).getText(), "358,000₫");
	}

	public void TC_03_Adding_Two_Different_Products_To_Cart() {
		driver.findElement(searchIcon).click();
		driver.findElement(searchInput).sendKeys("ao");
		driver.findElement(seacrhProduct).click();
		driver.findElement(searchIconSubmit).click();
//		/////
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(nameProductFirst);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);

		js = (JavascriptExecutor) driver;
		WebElement element1 = driver.findElement(By.xpath("//input[@id='swatch-0-den']"));
		js.executeScript("arguments[0].scrollIntoView(true);", element1);
		executor.executeScript("arguments[0].click();", element1);
//		driver.findElement(By.xpath("//input[@id='swatch-0-den']")).click();
//		driver.findElement(By.xpath("//input[@id='swatch-1-s']")).click();
//		driver.findElement(By.xpath("//input[@id='qtym']")).clear();
//		driver.findElement(By.xpath("//input[@id='qtym']")).sendKeys("1");
		driver.findElement(By.xpath("//button[@class='btn btn_add_cart btn-cart add_to_cart is-full']")).click();

		sleepInSecond(2);
		driver.findElement(By.xpath("//button[@class='close']/span[text()='×']")).click();
		sleepInSecond(2);
		
		driver.findElement(searchIcon).click();
		driver.findElement(searchInput).sendKeys("ao");
		driver.findElement(seacrhProduct).click();
		driver.findElement(searchIconSubmit).click();
		
		WebElement element2 = driver.findElement(nameProductSecond);
		js.executeScript("arguments[0].scrollIntoView(true);", element2);
		executor.executeScript("arguments[0].click();", element2);
		sleepInSecond(2);
		js = (JavascriptExecutor) driver;
		WebElement element3 = driver.findElement(By.xpath("//input[@id='swatch-1-s']"));
		js.executeScript("arguments[0].scrollIntoView(true);", element3);
		executor.executeScript("arguments[0].click();", element3);
		driver.findElement(By.xpath("//button[@class='btn btn_add_cart btn-cart add_to_cart is-full']")).click();

		// assert
		driver.findElement(By.xpath("//a[@class='btn btn-main checkout_button btn-full']")).click();
		// assert/equ
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='active']")).getText(), "Giỏ hàng (2)");
		Assert.assertEquals(driver.findElement(By.xpath("//a[@class='text2line link']")).getText(), "Áo bra ren đen");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@class='variant-title']")).getText(), "Đen / S");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@class='product-price price']")).getText(), "179,000₫");
	}


	public void TC_04_Delete_Cart() {
			driver.findElement(searchIcon).click();
			driver.findElement(searchInput).sendKeys("ao");
			driver.findElement(seacrhProduct).click();
			driver.findElement(searchIconSubmit).click();
//			/////
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(nameProductFirst);
			js.executeScript("arguments[0].scrollIntoView(true);", element);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);

			js = (JavascriptExecutor) driver;
			WebElement element1 = driver.findElement(By.xpath("//input[@id='swatch-0-den']"));
			js.executeScript("arguments[0].scrollIntoView(true);", element1);
			executor.executeScript("arguments[0].click();", element1);
			driver.findElement(By.xpath("//button[@class='btn btn_add_cart btn-cart add_to_cart is-full']")).click();
			sleepInSecond(2);
			driver.findElement(By.xpath("//a[@class='btn btn-main checkout_button btn-full']")).click();
			driver.findElement(By.xpath("//a[@class='remove-itemx remove-item-cart ']")).click();
			Assert.assertTrue(driver.findElement(cartQuantityEmpty).isDisplayed());
			Assert.assertTrue(driver.findElement(cartEmpty).isDisplayed());
			Assert.assertTrue(driver.findElement(shoppingButton).isDisplayed());
	}

	

	public void TC_05_Increase_Quantity() {
			driver.findElement(searchIcon).click();
			driver.findElement(searchInput).sendKeys("ao");
			driver.findElement(seacrhProduct).click();
			driver.findElement(searchIconSubmit).click();
//			/////
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(nameProductFirst);
			js.executeScript("arguments[0].scrollIntoView(true);", element);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);

			js = (JavascriptExecutor) driver;
			WebElement element1 = driver.findElement(By.xpath("//input[@id='swatch-0-den']"));
			js.executeScript("arguments[0].scrollIntoView(true);", element1);
			executor.executeScript("arguments[0].click();", element1);
			driver.findElement(By.xpath("//*[@id=\"add-to-cart-form\"]/div[6]/div[2]/div/div[1]/div[1]/button[2]")).click();
			driver.findElement(By.xpath("//button[@class='btn btn_add_cart btn-cart add_to_cart is-full']")).click();
			sleepInSecond(2);
			// click xem gio hang
			driver.findElement(By.xpath("//a[@class='btn btn-main checkout_button btn-full']")).click();
			//click tang sp len 1 don vi
			driver.findElement(By.xpath("//button[@class='increase items-count btn-plus btn']")).click();
			assertEquals(driver.findElement(By.xpath("//input[@name='updates[]']")).getAttribute("value"), "3");
			sleepInSecond(2);
			assertEquals(driver.findElement(By.xpath("//span[@class='product-price price']")).getText(), "537,000₫");
						
	}
	

	public void TC_06_Reduce_Quantity() {
			driver.findElement(searchIcon).click();
			driver.findElement(searchInput).sendKeys("ao");
			driver.findElement(seacrhProduct).click();
			driver.findElement(searchIconSubmit).click();
//			/////
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(nameProductFirst);
			js.executeScript("arguments[0].scrollIntoView(true);", element);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);

			js = (JavascriptExecutor) driver;
			WebElement element1 = driver.findElement(By.xpath("//input[@id='swatch-0-den']"));
			js.executeScript("arguments[0].scrollIntoView(true);", element1);
			executor.executeScript("arguments[0].click();", element1);
			driver.findElement(By.xpath("//*[@id=\"add-to-cart-form\"]/div[6]/div[2]/div/div[1]/div[1]/button[2]")).click();
			driver.findElement(By.xpath("//button[@class='btn btn_add_cart btn-cart add_to_cart is-full']")).click();
			sleepInSecond(2);
			// click xem gio hang
			driver.findElement(By.xpath("//input[@name='updates[]']")).sendKeys("2");
			//click tang sp len 1 don vi
			driver.findElement(By.xpath("//button[@class='reduced items-count btn-minus btn']")).click();
			assertEquals(driver.findElement(By.xpath("//input[@name='updates[]']")).getAttribute("value"), "1");
			sleepInSecond(2);
			assertEquals(driver.findElement(By.xpath("//span[@class='product-price price']")).getText(), "179,000₫");
						
	}

	public void TC_07_Sendkey_Quantity() {
			driver.findElement(searchIcon).click();
			driver.findElement(searchInput).sendKeys("ao");
			driver.findElement(seacrhProduct).click();
			driver.findElement(searchIconSubmit).click();
//			/////
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(nameProductFirst);
			js.executeScript("arguments[0].scrollIntoView(true);", element);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);

			js = (JavascriptExecutor) driver;
			WebElement element1 = driver.findElement(By.xpath("//input[@id='swatch-0-den']"));
			js.executeScript("arguments[0].scrollIntoView(true);", element1);
			executor.executeScript("arguments[0].click();", element1);
			driver.findElement(By.xpath("//button[@class='btn btn_add_cart btn-cart add_to_cart is-full']")).click();
			sleepInSecond(2);
			// click xem gio hang
			driver.findElement(By.xpath("//a[@class='btn btn-main checkout_button btn-full']")).click();
	
//			driver.findElement(By.xpath("//button[@class='reduced items-count btn-minus btn']/following-sibling::input[@type='text']")).clear();
//			sleepInSecond(2);
			driver.findElement(By.xpath("//button[@class='reduced items-count btn-minus btn']/following-sibling::input[@type='text']")).sendKeys("2");
//			driver.findElement(By.xpath("//div[@class='cart_page_mobile content-product-list']")).click();
			sleepInSecond(5);
//			// Wait cho alert xuat hien trong vong xx giay
//			alert = explicitWait.until(ExpectedConditions.alertIsPresent());
//			sleepInSecond(5);
//			Assert.assertEquals(alert.getText(), "Sản phẩm đã vượt quá tồn kho");
			// nhu viec an OK
//			alert.accept();
//			alert.accept();
			
			// click 
			
			
//			 sp max 13 sp
			assertEquals(driver.findElement(By.xpath("//input[@name='updates[]']")).getAttribute("value"), "12");
			driver.findElement(By.xpath("//div[@class='cart_page_mobile content-product-list']")).click();
			sleepInSecond(2);
			assertEquals(driver.findElement(By.xpath("//span[@class='product-price price']")).getText(), "2,148,000₫");					
	}
	
	public String RandomEmail() {
		Random rand = new Random();
		return "test" + rand.nextInt(99999) + "@gmail.com";
	}

	public void sleepInSecond(long sleepInsecond) {
		try {
			Thread.sleep(sleepInsecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getHTML5ValidationMessage(WebElement element) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", element);
	}

	@AfterMethod
	public void afterMethod() {
//		driver.quit();
	}

}

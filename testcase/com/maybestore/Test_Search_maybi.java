package com.maybestore;

import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class Test_Search_maybi extends BaseTest {
	WebDriver driver;

	By searchIcon = By.xpath("//span[text()='Tìm kiếm']");
	By searchInput = By.xpath("//input[@class='input-group-field auto-search form-control ']");
	By dressLink = By.xpath("//a[@id='filter-search-dam']");
	
	By shirtLink = By.xpath("//a[@id='filter-search-ao-so-mi']");
	By swimwearLink = By.xpath("//a[@id='filter-search-do-boi']");
	By sleepwearLink = By.xpath("//a[@id='filter-search-do-ngu']");
	By resultSearchPage = By.xpath("//h1[@class='title_page']");
	By seacrhProduct = By.xpath("//span[text()='Sản phẩm ']");

	By searchIconSubmit = By.xpath("//button[@class='btn text-white icon-fallback-text h-100']");
	By dressPage = By.xpath("//h1[@class='title_page collection-title mb-0 pb-3']");

	@BeforeMethod
	public void beforeMethod() {
		driver = new ChromeDriver();
		driver.get("https://maybi.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Dam_Link() {
		driver.findElement(searchIcon).click();
		driver.findElement(dressLink).click();
//
//		Assert.assertTrue(driver.findElement(dressPage).isDisplayed());
		Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='title_page collection-title mb-0 pb-3']")).getText(),"Đầm");
		
//		Assert.assertEquals(getElementText(driver, "//h1[@class='title_page collection-title mb-0 pb-3']"), "ĐẦM");
	}

	public void TC_02_Shirt_Link() {
		driver.findElement(searchIcon).click();
		driver.findElement(dressLink).click();
		Assert.assertTrue(driver.findElement(dressPage).isDisplayed());
//		Assert.assertEquals(getElementText(driver, "//h1[@class='title_page collection-title mb-0 pb-3']"), "ĐẦM");
	}

	public void TC_03_Swimwear_Link() {
		driver.findElement(searchIcon).click();
		driver.findElement(dressLink).click();

		Assert.assertTrue(driver.findElement(dressPage).isDisplayed());
//		Assert.assertEquals(getElementText(driver, "//h1[@class='title_page collection-title mb-0 pb-3']"), "ĐẦM");
	}

	public void TC_04_Sleepwear_Link() {
		driver.findElement(searchIcon).click();
		driver.findElement(dressLink).click();

		Assert.assertTrue(driver.findElement(dressPage).isDisplayed());
//		Assert.assertEquals(getElementText(driver, "//h1[@class='title_page collection-title mb-0 pb-3']"), "ĐẦM");
	}

	public void TC_05_Search_Empty() {
		driver.findElement(searchIcon).click();
		driver.findElement(searchInput).sendKeys("");
		driver.findElement(searchIconSubmit).click();
		String message = getHTML5ValidationMessage(driver.findElement(searchInput));
		Assert.assertEquals("Please fill out this field.", message);
	}

	public void TC_10_SendKey_Special_Characters_Input_Seacrh() {
		driver.findElement(searchIcon).click();
		driver.findElement(searchInput).sendKeys("@!@#");
		driver.findElement(seacrhProduct).click();
		driver.findElement(searchIconSubmit).click();

//		Assert.assertEquals(getElementText(driver, "//h1[@class='title_page']"), "Nhập từ khóa để tìm kiếm");
//		Assert.assertEquals(getElementText(driver, "//h2[@class='title-head text-center mt-3']"),
//				"KHÔNG TÌM THẤY BẤT KỲ KẾT QUẢ NÀO VỚI TỪ KHÓA TRÊN.");
		String test = driver.findElement(By.xpath("//input[@class='input-group-field form-control ']"))
				.getAttribute("value");

		Assert.assertEquals(
				driver.findElement(By.xpath("//input[@class='input-group-field form-control ']")).getAttribute("value"),
				"");
//		Assert.assertTrue(driver.findElement(By.xpath("//input[@class='input-group-field form-control ']")).getAttribute("value").equals(""));

	}

	@Test
	public void TC_06_SendKey_ao() {
		driver.findElement(searchIcon).click();
		driver.findElement(searchInput).sendKeys("ao");
		driver.findElement(seacrhProduct).click();
		driver.findElement(searchIconSubmit).click();

		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Áo jacket']")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='title-head']")).getText(),"CÓ 264 KẾT QUẢ TÌM KIẾM PHÙ HỢP");
//		Assert.assertEquals(getElementText(driver, "//h1[@class='title-head']"), "CÓ 264 KẾT QUẢ TÌM KIẾM PHÙ HỢP");
	}


	public void TC_07_AO() {
		driver.findElement(searchIcon).click();
		driver.findElement(searchInput).sendKeys("AO");
		driver.findElement(seacrhProduct).click();
		driver.findElement(searchIconSubmit).click();
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Áo jacket']")).isDisplayed());
//		Assert.assertEquals(getElementText(driver, "//h1[@class='title-head']"), "CÓ 264 KẾT QUẢ TÌM KIẾM PHÙ HỢP");
	}


	public void TC_08_Ao_jacket() {
		driver.findElement(searchIcon).click();
		driver.findElement(searchInput).sendKeys("Áo jacket");
		driver.findElement(seacrhProduct).click();
		driver.findElement(searchIconSubmit).click();
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Áo jacket']")).isDisplayed());
//		Assert.assertEquals(getElementText(driver, "//h1[@class='title-head']"), "CÓ 1 KẾT QUẢ TÌM KIẾM PHÙ HỢP");
	}


	public void TC_08_AO_JACKET() {
		driver.findElement(searchIcon).click();
		driver.findElement(searchInput).sendKeys("ÁO JACKET");
		driver.findElement(seacrhProduct).click();
		driver.findElement(searchIconSubmit).click();
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Áo jacket']")).isDisplayed());
//		Assert.assertEquals(getElementText(driver, "//h1[@class='title-head']"), "CÓ 1 KẾT QUẢ TÌM KIẾM PHÙ HỢP");
	}


	public void TC_09_Asus() {
		driver.findElement(searchIcon).click();
		driver.findElement(searchInput).sendKeys("Asus");
		driver.findElement(seacrhProduct).click();
		driver.findElement(searchIconSubmit).click();
//		Assert.assertEquals(getElementText(driver, "//h1[@class='title_page']"), "Nhập từ khóa để tìm kiếm");
//		Assert.assertEquals(getElementText(driver, "//h2[@class='title-head text-center mt-3']"),
//				"KHÔNG TÌM THẤY BẤT KỲ KẾT QUẢ NÀO VỚI TỪ KHÓA TRÊN.");
		String test = driver.findElement(By.xpath("//input[@class='input-group-field form-control ']"))
				.getAttribute("value");

		Assert.assertEquals(
				driver.findElement(By.xpath("//input[@class='input-group-field form-control ']")).getAttribute("value"),
				"");
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

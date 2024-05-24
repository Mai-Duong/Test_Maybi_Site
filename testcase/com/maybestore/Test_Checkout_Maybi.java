package com.maybestore;

import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class Test_Checkout_Maybi extends BaseTest {

	WebDriver driver;
	Select select;
	
	By buttonSubmit = By.xpath("//span[text()='Hoàn tất đơn hàng']");
	By buttonBuynow = By.xpath("//button[@class='btn btn_base buynow ']");

	By fullName = By.xpath("//input[@id='billing_address_full_name']");
	By errorFullName = By.xpath("(//p[@class='field-message field-message-error'])[1]");

	By email = By.xpath("//input[@id='checkout_user_email']");
	By errorEmail = By.xpath("//input[@name='checkout_user[email]']/ancestor::div/following-sibling::p");

	By phone = By.xpath("//input[@id='billing_address_phone']");
	By errorPhone = By.xpath("//input[@name='billing_address[phone]']/ancestor::div/following-sibling::p");

	By address = By.xpath("//input[@id='billing_address_address1']");
	By errorAddress = By.xpath("//input[@name='billing_address[address1]']/ancestor::div/following-sibling::p");

	By city = By.xpath("//select[@id='customer_shipping_province']");
	By errorCity = By.xpath("//select[@id='customer_shipping_province']/ancestor::div/following-sibling::p");

	By district = By.xpath("//select[@id='customer_shipping_district']");
	By errorDistrict = By.xpath("//select[@id='customer_shipping_district']/ancestor::div/following-sibling::p");

	By ward = By.xpath("//select[@id='customer_shipping_ward']");
	By errorWard = By.xpath("//select[@id='customer_shipping_ward']/ancestor::div/following-sibling::p");

	
	By paymentDelivery = By.xpath("(//div[@class='radio-input payment-method-checkbox'])[1]");
	By paymentOnline = By.xpath("(//div[@class='radio-input payment-method-checkbox'])[2]");
	By paymentMomo = By.xpath("(//div[@class='radio-input payment-method-checkbox'])[3]");
	
	@BeforeMethod
	public void beforeMethod() {
		driver = new ChromeDriver();
		driver.get("https://maybi.com/products/ao-thun-gan-khong-tay-om-nhieu-mau");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public void TC_01_Empty_All() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(buttonBuynow);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);

		// scroll button Submit
		WebElement element1 = driver.findElement(buttonSubmit);
		js.executeScript("arguments[0].scrollIntoView(true);", element1);
		executor.executeScript("arguments[0].click();", element1);

		Assert.assertEquals(driver.findElement(errorFullName).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(errorEmail).getText(), "Địa chỉ email không được trống");
		Assert.assertEquals(driver.findElement(errorPhone).getText(), "Số điện thoại không được trống");
		Assert.assertEquals(driver.findElement(errorAddress).getText(), "Địa chỉ không được trống");
		Assert.assertEquals(driver.findElement(errorCity).getText(), "Vui lòng chọn tỉnh thành");
		Assert.assertEquals(driver.findElement(errorDistrict).getText(), "Vui lòng chọn quận huyện");
		Assert.assertEquals(driver.findElement(errorWard).getText(), "Vui lòng chọn phường xã");

	}

	public void TC_02_Empty_Fullname() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(buttonBuynow);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);

		driver.findElement(fullName).sendKeys("");
		driver.findElement(email).sendKeys("test@gmail.com");
		driver.findElement(phone).sendKeys("098765432");
		sleepInSecond(2);
		driver.findElement(address).sendKeys("cntt");
		
		sleepInSecond(2);
		select = new Select(driver.findElement(city));
		select.selectByVisibleText("Thái Nguyên");
		Assert.assertFalse(select.isMultiple());
		sleepInSecond(2);
		select = new Select(driver.findElement(city));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Thái Nguyên");
		sleepInSecond(2);	
		Assert.assertEquals(select.getOptions().size(), 64);
						
		select = new Select(driver.findElement(district));
		select.selectByVisibleText("Thành phố Thái Nguyên");
		Assert.assertFalse(select.isMultiple());
		sleepInSecond(2);
		select = new Select(driver.findElement(district));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Thành phố Thái Nguyên");
		sleepInSecond(2);	
		Assert.assertEquals(select.getOptions().size(), 10);
		Assert.assertTrue(driver.findElement(By.xpath("(//span[@class='radio-label-primary'])[1]")).isDisplayed());
		
		sleepInSecond(2);
		select = new Select(driver.findElement(ward));
		select.selectByVisibleText("Phường Tân Thịnh");
		Assert.assertFalse(select.isMultiple());
		sleepInSecond(2);
		select = new Select(driver.findElement(ward));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Phường Tân Thịnh");
		sleepInSecond(2);	
		Assert.assertEquals(select.getOptions().size(), 33);
		
		driver.findElement(paymentDelivery).click();
		// scroll button Submit
		WebElement element1 = driver.findElement(buttonSubmit);
		js.executeScript("arguments[0].scrollIntoView(true);", element1);
		executor.executeScript("arguments[0].click();", element1);

		Assert.assertEquals(driver.findElement(errorFullName).getText(), "Vui lòng nhập họ tên");
	}

	public void TC_03_Empty_Email() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(buttonBuynow);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);

		driver.findElement(fullName).sendKeys("Mai Dương");
		driver.findElement(email).sendKeys("");
		driver.findElement(phone).sendKeys("098765432");
		sleepInSecond(2);
		driver.findElement(address).sendKeys("cntt");
		
		sleepInSecond(2);
		select = new Select(driver.findElement(city));
		select.selectByVisibleText("Thái Nguyên");
		Assert.assertFalse(select.isMultiple());
		sleepInSecond(2);
		select = new Select(driver.findElement(city));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Thái Nguyên");
		sleepInSecond(2);	
		Assert.assertEquals(select.getOptions().size(), 64);
						
		select = new Select(driver.findElement(district));
		select.selectByVisibleText("Thành phố Thái Nguyên");
		Assert.assertFalse(select.isMultiple());
		sleepInSecond(2);
		select = new Select(driver.findElement(district));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Thành phố Thái Nguyên");
		sleepInSecond(2);	
		Assert.assertEquals(select.getOptions().size(), 10);
		Assert.assertTrue(driver.findElement(By.xpath("(//span[@class='radio-label-primary'])[1]")).isDisplayed());
		
		sleepInSecond(2);
		select = new Select(driver.findElement(ward));
		select.selectByVisibleText("Phường Tân Thịnh");
		Assert.assertFalse(select.isMultiple());
		sleepInSecond(2);
		select = new Select(driver.findElement(ward));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Phường Tân Thịnh");
		sleepInSecond(2);	
		Assert.assertEquals(select.getOptions().size(), 33);
		
		driver.findElement(paymentDelivery).click();
		// scroll button Submit
		WebElement element1 = driver.findElement(buttonSubmit);
		js.executeScript("arguments[0].scrollIntoView(true);", element1);
		executor.executeScript("arguments[0].click();", element1);

		sleepInSecond(1);
		Assert.assertEquals(driver.findElement(errorEmail).getText(), "Địa chỉ email không được trống");
	}

	public void TC_04_Enter_Invalid_Email() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(buttonBuynow);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);

		driver.findElement(fullName).sendKeys("Mai Dương");
		driver.findElement(email).sendKeys("duongmai");
		driver.findElement(phone).sendKeys("098765432");
		sleepInSecond(2);
		driver.findElement(address).sendKeys("cntt");
		
		sleepInSecond(2);
		select = new Select(driver.findElement(city));
		select.selectByVisibleText("Thái Nguyên");
		Assert.assertFalse(select.isMultiple());
		sleepInSecond(2);
		select = new Select(driver.findElement(city));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Thái Nguyên");
		sleepInSecond(2);	
		Assert.assertEquals(select.getOptions().size(), 64);
						
		select = new Select(driver.findElement(district));
		select.selectByVisibleText("Thành phố Thái Nguyên");
		Assert.assertFalse(select.isMultiple());
		sleepInSecond(2);
		select = new Select(driver.findElement(district));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Thành phố Thái Nguyên");
		sleepInSecond(2);	
		Assert.assertEquals(select.getOptions().size(), 10);
		Assert.assertTrue(driver.findElement(By.xpath("(//span[@class='radio-label-primary'])[1]")).isDisplayed());
		
		sleepInSecond(2);
		select = new Select(driver.findElement(ward));
		select.selectByVisibleText("Phường Tân Thịnh");
		Assert.assertFalse(select.isMultiple());
		sleepInSecond(2);
		select = new Select(driver.findElement(ward));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Phường Tân Thịnh");
		sleepInSecond(2);	
		Assert.assertEquals(select.getOptions().size(), 33);
		
		driver.findElement(paymentDelivery).click();
		// scroll button Submit
		WebElement element1 = driver.findElement(buttonSubmit);
		js.executeScript("arguments[0].scrollIntoView(true);", element1);
		executor.executeScript("arguments[0].click();", element1);

		sleepInSecond(1);
		Assert.assertEquals(driver.findElement(errorEmail).getText(), "Địa chỉ email không hợp lệ");
	}

	public void TC_05_Empty_Phone() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(buttonBuynow);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);

		driver.findElement(fullName).sendKeys("Mai Dương");
		driver.findElement(email).sendKeys("duongmai060801@gmail.com");
		driver.findElement(phone).sendKeys("");
		sleepInSecond(2);
		driver.findElement(address).sendKeys("cntt");
		
		sleepInSecond(2);
		select = new Select(driver.findElement(city));
		select.selectByVisibleText("Thái Nguyên");
		Assert.assertFalse(select.isMultiple());
		sleepInSecond(2);
		select = new Select(driver.findElement(city));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Thái Nguyên");
		sleepInSecond(2);	
		Assert.assertEquals(select.getOptions().size(), 64);
						
		select = new Select(driver.findElement(district));
		select.selectByVisibleText("Thành phố Thái Nguyên");
		Assert.assertFalse(select.isMultiple());
		sleepInSecond(2);
		select = new Select(driver.findElement(district));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Thành phố Thái Nguyên");
		sleepInSecond(2);	
		Assert.assertEquals(select.getOptions().size(), 10);
		Assert.assertTrue(driver.findElement(By.xpath("(//span[@class='radio-label-primary'])[1]")).isDisplayed());
		
		sleepInSecond(2);
		select = new Select(driver.findElement(ward));
		select.selectByVisibleText("Phường Tân Thịnh");
		Assert.assertFalse(select.isMultiple());
		sleepInSecond(2);
		select = new Select(driver.findElement(ward));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Phường Tân Thịnh");
		sleepInSecond(2);	
		Assert.assertEquals(select.getOptions().size(), 33);
		
		driver.findElement(paymentDelivery).click();
		// scroll button Submit
		WebElement element1 = driver.findElement(buttonSubmit);
		js.executeScript("arguments[0].scrollIntoView(true);", element1);
		executor.executeScript("arguments[0].click();", element1);

		sleepInSecond(1);
		Assert.assertEquals(driver.findElement(errorPhone).getText(), "Số điện thoại không được trống");
	}


	public void TC_05_Invalid_Phone() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(buttonBuynow);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);

		driver.findElement(fullName).sendKeys("Mai Dương");
		driver.findElement(email).sendKeys("duongmai060801@gmail.com");
		driver.findElement(phone).sendKeys("09098h");
		sleepInSecond(2);
		driver.findElement(address).sendKeys("cntt");
		
		sleepInSecond(2);
		select = new Select(driver.findElement(city));
		select.selectByVisibleText("Thái Nguyên");
		Assert.assertFalse(select.isMultiple());
		sleepInSecond(2);
		select = new Select(driver.findElement(city));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Thái Nguyên");
		sleepInSecond(2);	
		Assert.assertEquals(select.getOptions().size(), 64);
						
		select = new Select(driver.findElement(district));
		select.selectByVisibleText("Thành phố Thái Nguyên");
		Assert.assertFalse(select.isMultiple());
		sleepInSecond(2);
		select = new Select(driver.findElement(district));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Thành phố Thái Nguyên");
		sleepInSecond(2);	
		Assert.assertEquals(select.getOptions().size(), 10);
		Assert.assertTrue(driver.findElement(By.xpath("(//span[@class='radio-label-primary'])[1]")).isDisplayed());
		
		sleepInSecond(2);
		select = new Select(driver.findElement(ward));
		select.selectByVisibleText("Phường Tân Thịnh");
		Assert.assertFalse(select.isMultiple());
		sleepInSecond(2);
		select = new Select(driver.findElement(ward));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Phường Tân Thịnh");
		sleepInSecond(2);	
		Assert.assertEquals(select.getOptions().size(), 33);
		
		driver.findElement(paymentDelivery).click();
		// scroll button Submit
		WebElement element1 = driver.findElement(buttonSubmit);
		js.executeScript("arguments[0].scrollIntoView(true);", element1);
		executor.executeScript("arguments[0].click();", element1);

		sleepInSecond(1);
		Assert.assertEquals(driver.findElement(errorPhone).getText(), "Số điện thoại không hợp lệ (độ dài từ 8 - 15 ký tự, không chứa ký tự đặc biệt và khoảng trắng)");
	}
	

	public void TC_06_Empty_Address() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(buttonBuynow);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);

		driver.findElement(fullName).sendKeys("Mai Dương");
		driver.findElement(email).sendKeys("duongmai060801@gmail.com");
		driver.findElement(phone).sendKeys("0123456789");
		sleepInSecond(2);
		driver.findElement(address).sendKeys("");
		
		sleepInSecond(2);
		select = new Select(driver.findElement(city));
		select.selectByVisibleText("Thái Nguyên");
		Assert.assertFalse(select.isMultiple());
		sleepInSecond(2);
		select = new Select(driver.findElement(city));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Thái Nguyên");
		sleepInSecond(2);	
		Assert.assertEquals(select.getOptions().size(), 64);
						
		select = new Select(driver.findElement(district));
		select.selectByVisibleText("Thành phố Thái Nguyên");
		Assert.assertFalse(select.isMultiple());
		sleepInSecond(2);
		select = new Select(driver.findElement(district));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Thành phố Thái Nguyên");
		sleepInSecond(2);	
		Assert.assertEquals(select.getOptions().size(), 10);
		Assert.assertTrue(driver.findElement(By.xpath("(//span[@class='radio-label-primary'])[1]")).isDisplayed());
		
		sleepInSecond(2);
		select = new Select(driver.findElement(ward));
		select.selectByVisibleText("Phường Tân Thịnh");
		Assert.assertFalse(select.isMultiple());
		sleepInSecond(2);
		select = new Select(driver.findElement(ward));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Phường Tân Thịnh");
		sleepInSecond(2);	
		Assert.assertEquals(select.getOptions().size(), 33);
		
		driver.findElement(paymentDelivery).click();
		// scroll button Submit
		WebElement element1 = driver.findElement(buttonSubmit);
		js.executeScript("arguments[0].scrollIntoView(true);", element1);
		executor.executeScript("arguments[0].click();", element1);

		sleepInSecond(1);
		Assert.assertEquals(driver.findElement(errorAddress).getText(), "Địa chỉ không được trống");
	}
	

	public void TC_07_Empty_City() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(buttonBuynow);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);

		driver.findElement(fullName).sendKeys("Mai Dương");
		driver.findElement(email).sendKeys("duongmai060801@gmail.com");
		driver.findElement(phone).sendKeys("0123456789");
		sleepInSecond(2);
		driver.findElement(address).sendKeys("ĐHCNTT");
		
		sleepInSecond(2);
		select = new Select(driver.findElement(district));	
		Assert.assertEquals(select.getOptions().size(), 1);
		
		sleepInSecond(2);
		select = new Select(driver.findElement(ward));	
		Assert.assertEquals(select.getOptions().size(), 1);
		
		driver.findElement(paymentDelivery).click();
		// scroll button Submit
		WebElement element1 = driver.findElement(buttonSubmit);
		js.executeScript("arguments[0].scrollIntoView(true);", element1);
		executor.executeScript("arguments[0].click();", element1);

		sleepInSecond(1);
		Assert.assertEquals(driver.findElement(errorCity).getText(), "Vui lòng chọn tỉnh thành");
	}


	public void TC_08_Empty_Distric() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(buttonBuynow);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);

		driver.findElement(fullName).sendKeys("Mai Dương");
		driver.findElement(email).sendKeys("duongmai060801@gmail.com");
		driver.findElement(phone).sendKeys("0123456789");
		sleepInSecond(2);
		driver.findElement(address).sendKeys("ĐHCNTT");
		
		sleepInSecond(2);
		select = new Select(driver.findElement(city));
		select.selectByVisibleText("Thái Nguyên");
		Assert.assertFalse(select.isMultiple());
		sleepInSecond(2);
		select = new Select(driver.findElement(city));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Thái Nguyên");
		sleepInSecond(2);	
		Assert.assertEquals(select.getOptions().size(), 64);
		
		sleepInSecond(2);
		select = new Select(driver.findElement(district));	
		Assert.assertEquals(select.getOptions().size(), 10);
		
		sleepInSecond(2);
		select = new Select(driver.findElement(ward));	
		Assert.assertEquals(select.getOptions().size(), 1);
		
		driver.findElement(paymentDelivery).click();
		// scroll button Submit
		WebElement element1 = driver.findElement(buttonSubmit);
		js.executeScript("arguments[0].scrollIntoView(true);", element1);
		executor.executeScript("arguments[0].click();", element1);

		sleepInSecond(1);
		Assert.assertEquals(driver.findElement(errorDistrict).getText(), "Vui lòng chọn quận huyện");
		Assert.assertEquals(driver.findElement(errorWard).getText(), "Vui lòng chọn phường xã");
	}
	

	public void TC_09_Empty_Ward() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(buttonBuynow);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);

		driver.findElement(fullName).sendKeys("Mai Dương");
		driver.findElement(email).sendKeys("duongmai060801@gmail.com");
		driver.findElement(phone).sendKeys("0123456789");
		sleepInSecond(2);
		driver.findElement(address).sendKeys("ĐHCNTT");
		
		sleepInSecond(2);
		select = new Select(driver.findElement(city));
		select.selectByVisibleText("Thái Nguyên");
		Assert.assertFalse(select.isMultiple());
		sleepInSecond(2);
		select = new Select(driver.findElement(city));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Thái Nguyên");
		sleepInSecond(2);	
		Assert.assertEquals(select.getOptions().size(), 64);
		
		select = new Select(driver.findElement(district));
		select.selectByVisibleText("Thành phố Thái Nguyên");
		Assert.assertFalse(select.isMultiple());
		sleepInSecond(2);
		select = new Select(driver.findElement(district));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Thành phố Thái Nguyên");
		sleepInSecond(2);	
		Assert.assertEquals(select.getOptions().size(), 10);
		Assert.assertTrue(driver.findElement(By.xpath("(//span[@class='radio-label-primary'])[1]")).isDisplayed());
		
		sleepInSecond(2);
		select = new Select(driver.findElement(ward));	
		Assert.assertEquals(select.getOptions().size(), 33);
		
		driver.findElement(paymentDelivery).click();
		// scroll button Submit
		WebElement element1 = driver.findElement(buttonSubmit);
		js.executeScript("arguments[0].scrollIntoView(true);", element1);
		executor.executeScript("arguments[0].click();", element1);

		sleepInSecond(1);
		Assert.assertEquals(driver.findElement(errorWard).getText(), "Vui lòng chọn phường xã");
	}

	@Test
	public void TC_10_Check_Payment() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(buttonBuynow);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);

		driver.findElement(fullName).sendKeys("Mai Dương");
		driver.findElement(email).sendKeys("duongmai060801@gmail.com");
		driver.findElement(phone).sendKeys("0123456789");
		sleepInSecond(2);
		driver.findElement(address).sendKeys("");
		
		sleepInSecond(2);
		select = new Select(driver.findElement(city));
		select.selectByVisibleText("Thái Nguyên");
		Assert.assertFalse(select.isMultiple());
		sleepInSecond(2);
		select = new Select(driver.findElement(city));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Thái Nguyên");
		sleepInSecond(2);	
		Assert.assertEquals(select.getOptions().size(), 64);
						
		select = new Select(driver.findElement(district));
		select.selectByVisibleText("Thành phố Thái Nguyên");
		Assert.assertFalse(select.isMultiple());
		sleepInSecond(2);
		select = new Select(driver.findElement(district));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Thành phố Thái Nguyên");
		sleepInSecond(2);	
		Assert.assertEquals(select.getOptions().size(), 10);
		Assert.assertTrue(driver.findElement(By.xpath("(//span[@class='radio-label-primary'])[1]")).isDisplayed());
		
		sleepInSecond(2);
		select = new Select(driver.findElement(ward));
		select.selectByVisibleText("Phường Tân Thịnh");
		Assert.assertFalse(select.isMultiple());
		sleepInSecond(2);
		select = new Select(driver.findElement(ward));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Phường Tân Thịnh");
		sleepInSecond(2);	
		Assert.assertEquals(select.getOptions().size(), 33);
		
		driver.findElement(paymentDelivery).click();
		driver.findElement(paymentOnline).click();
		sleepInSecond(2);
		driver.findElement(paymentMomo).click();
		sleepInSecond(1);
		driver.findElement(paymentDelivery).click();
		Assert.assertFalse(driver.findElement(paymentMomo).isSelected());
		Assert.assertFalse(driver.findElement(paymentOnline).isSelected());
	}
	
	@Test
	public void TC_11_PaymentDelivery() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(buttonBuynow);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);

		driver.findElement(fullName).sendKeys("Mai Dương");
		driver.findElement(email).sendKeys("duongmai060801@gmail.com");
		driver.findElement(phone).sendKeys("0123456789");
		sleepInSecond(2);
		driver.findElement(address).sendKeys("");
		
		sleepInSecond(2);
		select = new Select(driver.findElement(city));
		select.selectByVisibleText("Thái Nguyên");
		Assert.assertFalse(select.isMultiple());
		sleepInSecond(2);
		select = new Select(driver.findElement(city));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Thái Nguyên");
		sleepInSecond(2);	
		Assert.assertEquals(select.getOptions().size(), 64);
						
		select = new Select(driver.findElement(district));
		select.selectByVisibleText("Thành phố Thái Nguyên");
		Assert.assertFalse(select.isMultiple());
		sleepInSecond(2);
		select = new Select(driver.findElement(district));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Thành phố Thái Nguyên");
		sleepInSecond(2);	
		Assert.assertEquals(select.getOptions().size(), 10);
		Assert.assertTrue(driver.findElement(By.xpath("(//span[@class='radio-label-primary'])[1]")).isDisplayed());
		
		sleepInSecond(2);
		select = new Select(driver.findElement(ward));
		select.selectByVisibleText("Phường Tân Thịnh");
		Assert.assertFalse(select.isMultiple());
		sleepInSecond(2);
		select = new Select(driver.findElement(ward));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Phường Tân Thịnh");
		sleepInSecond(2);	
		Assert.assertEquals(select.getOptions().size(), 33);
		
		driver.findElement(paymentDelivery).click();
		driver.findElement(paymentOnline).click();
		sleepInSecond(2);
		driver.findElement(paymentMomo).click();
		sleepInSecond(1);
		driver.findElement(paymentDelivery).click();
		Assert.assertFalse(driver.findElement(paymentMomo).isSelected());
		Assert.assertFalse(driver.findElement(paymentOnline).isSelected());
		
		Assert.assertTrue(driver.findElement(By.xpath("(//h1[text()='MAYBI- Thời trang thiết kế - Chất lượng vượt trội - Giá cả hợp lý'])[2]")).isDisplayed());
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

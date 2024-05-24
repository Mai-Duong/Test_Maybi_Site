package com.maybestore;
import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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


public class Test_Login_Maybi extends BaseTest{
	WebDriver driver;

	By emailTextbox = By.xpath("//input[@id='customer_email']");
	By passTextbox = By.xpath("//input[@id='customer_password']");	
	By errorMessage = By.xpath("//div[@class='form-signup margin-bottom-15']");	
	By buttonLogin = By.xpath("//button[@value='Đăng nhập']");
	By accountPage = By.xpath("//h5[@class='title-account']");
//	By errorMessage = By.xpath("//div[@class='errors']");
	
	Duration timeout = Duration.ofSeconds(30);
	@BeforeMethod
	public void beforeMethod() {
		driver = new ChromeDriver();
		driver.get("https://maybi.com/account/login");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_All_Empty() {
		driver.findElement(emailTextbox).sendKeys("");
		driver.findElement(passTextbox).sendKeys("");
		driver.findElement(buttonLogin).click();
		String nameMessage = getHTML5ValidationMessage(driver.findElement(emailTextbox));
		Assert.assertEquals("Please fill out this field.", nameMessage);
	} 
	
	
	public void TC_02_Email_Empty() {
		driver.findElement(emailTextbox).sendKeys("");
		driver.findElement(passTextbox).sendKeys("Mai2001");
		driver.findElement(buttonLogin).click();
		String nameMessage = getHTML5ValidationMessage(driver.findElement(emailTextbox));
		System.out.println(nameMessage);
		Assert.assertEquals("Please fill out this field.", nameMessage);
	} 


	public void TC_03_Pass_Empty() {
		driver.findElement(emailTextbox).sendKeys("duongmai060801@gmail.com");
		driver.findElement(passTextbox).sendKeys("");
		driver.findElement(buttonLogin).click();
		String nameMessage = getHTML5ValidationMessage(driver.findElement(passTextbox));
		Assert.assertEquals("Please fill out this field.", nameMessage);
	} 


	public void TC_04_Correct_Email_Incorrect_Pass_() {
		driver.findElement(emailTextbox).sendKeys("duongmai060801@gmail.com");
		driver.findElement(passTextbox).sendKeys("maimai");
		driver.findElement(buttonLogin).click();
		
		Assert.assertTrue(driver.findElement(errorMessage).isDisplayed());
		String showErrorMessage = driver.findElement(errorMessage).getText();
		Assert.assertEquals(showErrorMessage,"Thông tin đăng nhập không hợp lệ.");
	} 
	
	public void TC_05_InCorrect_Email_Pass_Correct() {
		driver.findElement(emailTextbox).sendKeys("duongmai0608011@gmail.com");
		driver.findElement(passTextbox).sendKeys("Mai2001@");
		sleepInSecond(5);
		driver.findElement(buttonLogin).click();
		
		Assert.assertTrue(driver.findElement(errorMessage).isDisplayed());
		String showErrorMessage = driver.findElement(errorMessage).getText();
		Assert.assertEquals(showErrorMessage,"Thông tin đăng nhập không hợp lệ.");
	} 
	

	public void TC_06_InCorrect_Email_Pass_Correct() {
		driver.findElement(emailTextbox).sendKeys("maiduong6801@gmail.com");
		driver.findElement(passTextbox).sendKeys("maimai");
		driver.findElement(buttonLogin).click();
		
		Assert.assertTrue(driver.findElement(errorMessage).isDisplayed());
		String showErrorMessage = driver.findElement(errorMessage).getText();
		Assert.assertEquals(showErrorMessage,"Thông tin đăng nhập không hợp lệ.");
	} 

	

	public void TC_07_Email_Pass_Deleted() {
		driver.findElement(emailTextbox).sendKeys("duongmai0608011@gmail.com");
		driver.findElement(passTextbox).sendKeys("Mai2001@");
		driver.findElement(buttonLogin).click();
		
		Assert.assertTrue(driver.findElement(errorMessage).isDisplayed());
		String showErrorMessage = driver.findElement(errorMessage).getText();
		Assert.assertEquals(showErrorMessage,"Thông tin đăng nhập không hợp lệ.");
	} 
	

	public void TC_08_Email_Disable() {
		driver.findElement(emailTextbox).sendKeys("maiduong6801@gmail.com");
		driver.findElement(passTextbox).sendKeys("maimai");
		driver.findElement(buttonLogin).click();
		
		Assert.assertTrue(driver.findElement(errorMessage).isDisplayed());
		String showErrorMessage = driver.findElement(errorMessage).getText();
		Assert.assertEquals(showErrorMessage,"Bạn cần kích hoạt lại tài khoản của mình để đăng nhập.");
	} 
	

	public void TC_09_Correct_Email_Pass() {
		driver.findElement(emailTextbox).sendKeys("duongmai060801@gmail.com");
		driver.findElement(passTextbox).sendKeys("Mai2001@");
		driver.findElement(buttonLogin).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//h5[@class='title-account']")).getText() ,"TRANG TÀI KHOẢN");
			
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

package com.maybestore;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import commons.BaseTest;


public class Test_Register_Maybi extends BaseTest {
	WebDriver driver;

	By lastNameTextbox = By.xpath("//input[@id='lastName']");
	By firstNameTextbox = By.xpath("//input[@id='firstName']");
	By phoneTextbox = By.xpath("//input[@id='Phone']");
	By emailTextbox = By.xpath("//input[@id='email']");
	By passTextbox = By.xpath("//input[@id='password']");
	By buttonRegister = By.xpath("//button[@class='btn  btn-style  btn_register btn-block']");
	By errorMessage = By.xpath("//div[@class='errors']");

	
	
	@BeforeMethod
	public void beforeMethod() {
		driver = new ChromeDriver();
		driver.get("https://maybi.com/account/register");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_All_Empty() {
		log.info(" TC_01_All_Empty Step 1 - Sendkey Lastname");
		driver.findElement(lastNameTextbox).sendKeys("");

		log.info("TC_01_All_Empty Step 2 - Sendkey Lastname");
		driver.findElement(firstNameTextbox).sendKeys("");

		log.info("TC_01_All_Empty Step 3 - Sendkey Lastname");
		driver.findElement(phoneTextbox).sendKeys("");

		log.info("TC_01_All_Empty Step 4 - Sendkey Lastname");
		driver.findElement(emailTextbox).sendKeys("");

		log.info("TC_01_All_Empty Step 5 - Sendkey Lastname");
		driver.findElement(passTextbox).sendKeys("");

		log.info("TC_01_All_Empty Step 6 - Sendkey Lastname");
		driver.findElement(buttonRegister).click();
		
		
		String nameMessage = getHTML5ValidationMessage(driver.findElement(lastNameTextbox));	
		log.info("TC_01_All_Empty Step 7 - Sendkey Lastname");
		verifyEquals("Please fill out this field..", nameMessage);

		// test
		log.info("TC_01_All_Empty Step 8 - Sendkey Lastname");
		verifyEquals("Please fill out this field..", nameMessage);

		// test
		log.info("TC_01_All_Empty  Step 9 - Sendkey Lastname");
		verifyEquals("Please fill out this field.", nameMessage);
	}
	
	@Test
	public void TC_001_All_Empty() {
		log.info("TC_001_All_Empty Step 1 - Sendkey Lastname");
		driver.findElement(lastNameTextbox).sendKeys("");

		log.info("TC_001_All_Empty  Step 2 - Sendkey Lastname");
		driver.findElement(firstNameTextbox).sendKeys("");

		log.info("TC_001_All_Empty  Step 3 - Sendkey Lastname");
		driver.findElement(phoneTextbox).sendKeys("");

		log.info("TC_001_All_Empty  Step 4 - Sendkey Lastname");
		driver.findElement(emailTextbox).sendKeys("");

		log.info("TC_001_All_Empty  Step 5 - Sendkey Lastname");
		driver.findElement(passTextbox).sendKeys("");

		log.info("TC_001_All_Empty  Step 6 - Sendkey Lastname");
		driver.findElement(buttonRegister).click();
		
		
		String nameMessage = getHTML5ValidationMessage(driver.findElement(lastNameTextbox));	
		// test
		log.info("TC_001_All_Empty Step 7 - Sendkey Lastname");
		verifyEquals("Please fill out this field.", nameMessage);
	}

	public void TC_02_Lastname_Empty() {
		driver.findElement(lastNameTextbox).sendKeys("");
		driver.findElement(firstNameTextbox).sendKeys("Mai");
		driver.findElement(phoneTextbox).sendKeys("098765432");
		driver.findElement(emailTextbox).sendKeys("test1211@gmail.com");
		driver.findElement(passTextbox).sendKeys("mai1211");
		driver.findElement(buttonRegister).click();
		String message = getHTML5ValidationMessage(driver.findElement(lastNameTextbox));
		Assert.assertEquals("Please fill out this field.", message);
	}

	public void TC_03_SendKey_Special_Characters_Lastname() {
//		tự cho
		driver.findElement(lastNameTextbox).sendKeys("Mai@@@");
		driver.findElement(firstNameTextbox).sendKeys("Mai");
		driver.findElement(phoneTextbox).sendKeys("098765432");
		driver.findElement(emailTextbox).sendKeys("test1211@gmail.com");
		driver.findElement(passTextbox).sendKeys("mai1211");
		driver.findElement(buttonRegister).click();
		String message = getHTML5ValidationMessage(driver.findElement(lastNameTextbox));
		Assert.assertEquals("Vui lòng nhập lại không chưa ký tự đặc biệt.", message);
	}

	public void TC_04_Firstname_Empty() {
		driver.findElement(lastNameTextbox).sendKeys("Dương");
		driver.findElement(firstNameTextbox).sendKeys("");
		driver.findElement(phoneTextbox).sendKeys("098765432");
		driver.findElement(emailTextbox).sendKeys("test1211@gmail.com");
		driver.findElement(passTextbox).sendKeys("mai1211");
		driver.findElement(buttonRegister).click();
		String message = getHTML5ValidationMessage(driver.findElement(firstNameTextbox));
		Assert.assertEquals("Vui lòng điền vào trường này.", message);
	}

	public void TC_05_Firstname_SendKey_Special_Characters() {
//		tu cho
		driver.findElement(lastNameTextbox).sendKeys("Dương");
		driver.findElement(firstNameTextbox).sendKeys("@@@");
		driver.findElement(phoneTextbox).sendKeys("098765432");
		driver.findElement(emailTextbox).sendKeys("test1211@gmail.com");
		driver.findElement(passTextbox).sendKeys("mai1211");
		driver.findElement(buttonRegister).click();
		String message = getHTML5ValidationMessage(driver.findElement(firstNameTextbox));
		Assert.assertEquals("Vui lòng nhập lại không chưa ký tự đặc biệt", message);
	}

	public void TC_06_Phone_Empty() {
		driver.findElement(lastNameTextbox).sendKeys("Dương");
		driver.findElement(firstNameTextbox).sendKeys("Mai");
		driver.findElement(phoneTextbox).sendKeys("");
		driver.findElement(emailTextbox).sendKeys("test1211@gmail.com");
		driver.findElement(passTextbox).sendKeys("mai1211");
		driver.findElement(buttonRegister).click();
		String message = getHTML5ValidationMessage(driver.findElement(phoneTextbox));
		Assert.assertEquals("Vui lòng điền vào trường này.", message);
	}

	public void TC_07_Phone_SendKeyText() {
		driver.findElement(lastNameTextbox).sendKeys("Dương");
		driver.findElement(firstNameTextbox).sendKeys("Mai");
		driver.findElement(phoneTextbox).sendKeys("sodienthoai");
		driver.findElement(emailTextbox).sendKeys("test1211@gmail.com");
		driver.findElement(passTextbox).sendKeys("mai1211");
		driver.findElement(buttonRegister).click();
		String message = getHTML5ValidationMessage(driver.findElement(phoneTextbox));
		Assert.assertEquals("Vui lòng khớp định dạng được yêu cầu.", message);
	}

	public void TC_08_Phone_SendKeySpace() {
		driver.findElement(lastNameTextbox).sendKeys("Dương");
		driver.findElement(firstNameTextbox).sendKeys("Mai");
		driver.findElement(phoneTextbox).sendKeys("098 765 431");
		driver.findElement(emailTextbox).sendKeys("test1211@gmail.com");
		driver.findElement(passTextbox).sendKeys("mai1211");
		driver.findElement(buttonRegister).click();
		String message = getHTML5ValidationMessage(driver.findElement(phoneTextbox));
		Assert.assertEquals("Vui lòng khớp định dạng được yêu cầu.", message);
	}

	public void TC_09_Phone_lenght_12() {
//		tu cho
		driver.findElement(lastNameTextbox).sendKeys("Dương");
		driver.findElement(firstNameTextbox).sendKeys("Mai");
		driver.findElement(phoneTextbox).sendKeys("123456789100");
		driver.findElement(emailTextbox).sendKeys("test1211@gmail.com");
		driver.findElement(passTextbox).sendKeys("mai1211");
		driver.findElement(buttonRegister).click();
		String message = getHTML5ValidationMessage(driver.findElement(phoneTextbox));
		Assert.assertEquals("Vui lòng kiểm tra lại số điện thoại.", message);
	}

	public void TC_10_11_Phone_lenght_5() {
//		tu cho
		driver.findElement(lastNameTextbox).sendKeys("Dương");
		driver.findElement(firstNameTextbox).sendKeys("Mai");
		driver.findElement(phoneTextbox).sendKeys("01231");
		driver.findElement(emailTextbox).sendKeys("test1211@gmail.com");
		driver.findElement(passTextbox).sendKeys("mai1211");
		driver.findElement(buttonRegister).click();
		String message = getHTML5ValidationMessage(driver.findElement(phoneTextbox));
		Assert.assertEquals("Vui lòng kiểm tra lại số định dạng", message);
	}

//	private boolean isValidPhoneNumber(String phoneNumber) {
//		// Biểu thức chính quy kiểm tra số điện thoại
//		String phonePattern = "^[0-9]{7,11}$"; // Số điện thoại từ 7 đến 11 chữ số
//		Pattern pattern = Pattern.compile(phonePattern);
//		Matcher matcher = pattern.matcher(phoneNumber);
//
//		// Kiểm tra số điện thoại không chứa khoảng trắng và không có kí tự đặc biệt
//		return !phoneNumber.contains(" ") && !matcher.find();
//	}

	public void TC_12_Email_Empty() {
		driver.findElement(lastNameTextbox).sendKeys("Dương");
		driver.findElement(firstNameTextbox).sendKeys("Mai");
		driver.findElement(phoneTextbox).sendKeys("0123");
		driver.findElement(emailTextbox).sendKeys("");
		driver.findElement(passTextbox).sendKeys("mai1211");
		driver.findElement(buttonRegister).click();
		String message = getHTML5ValidationMessage(driver.findElement(emailTextbox));
		Assert.assertEquals("Vui lòng điền vào trường này.", message);
	}

	public void TC_13_Email_Miss_Special() {
		driver.findElement(lastNameTextbox).sendKeys("Dương");
		driver.findElement(firstNameTextbox).sendKeys("Mai");
		driver.findElement(phoneTextbox).sendKeys("0123");
		driver.findElement(emailTextbox).sendKeys("duongmaigmail.com");
		driver.findElement(passTextbox).sendKeys("mai1211");
		driver.findElement(buttonRegister).click();
		String message = getHTML5ValidationMessage(driver.findElement(emailTextbox));
		Assert.assertEquals("Vui lòng điền một địa chỉ email.", message);
	}

	public void TC_14_Email_Miss_Back_Special() {
		driver.findElement(lastNameTextbox).sendKeys("Dương");
		driver.findElement(firstNameTextbox).sendKeys("Mai");
		driver.findElement(phoneTextbox).sendKeys("0123");
		driver.findElement(emailTextbox).sendKeys("duongmai@");
		driver.findElement(passTextbox).sendKeys("mai1211");
		driver.findElement(buttonRegister).click();
		String message = getHTML5ValidationMessage(driver.findElement(emailTextbox));
		Assert.assertEquals("Vui lòng điền một địa chỉ email.", message);
	}

	public void TC_15_Email_Miss_front_Special() {
		driver.findElement(lastNameTextbox).sendKeys("Dương");
		driver.findElement(firstNameTextbox).sendKeys("Mai");
		driver.findElement(phoneTextbox).sendKeys("0123");
		driver.findElement(emailTextbox).sendKeys("@gmail.com");
		driver.findElement(passTextbox).sendKeys("mai1211");
		driver.findElement(buttonRegister).click();
		String message = getHTML5ValidationMessage(driver.findElement(emailTextbox));
		Assert.assertEquals("Vui lòng điền một địa chỉ email.", message);
	}

	public void TC_16_Email_Miss_front_Special() {
		driver.findElement(lastNameTextbox).sendKeys("Dương");
		driver.findElement(firstNameTextbox).sendKeys("Mai");
		driver.findElement(phoneTextbox).sendKeys("01231516");
		driver.findElement(emailTextbox).sendKeys("duongmai060801@gmail.com");
		driver.findElement(passTextbox).sendKeys("mai1211");
		driver.findElement(buttonRegister).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='errors']")).isDisplayed());

	}

	public void TC_17_Password_Empty() {
		driver.findElement(lastNameTextbox).sendKeys("Dương");
		driver.findElement(firstNameTextbox).sendKeys("Mai");
		driver.findElement(phoneTextbox).sendKeys("0123456789");
		driver.findElement(emailTextbox).sendKeys("test@gmail.com");
		driver.findElement(passTextbox).sendKeys("");
		driver.findElement(buttonRegister).click();
		String message = getHTML5ValidationMessage(driver.findElement(passTextbox));
		Assert.assertEquals("Vui lòng điền vào trường này.", message);
	}

//	@Test
//	public void testPasswordEncryption() {
//		String password = ""; // Mật khẩu của bạn
//		String encryptedPassword = encryptPassword(password);
//		boolean isEncrypted = isPasswordEncrypted(encryptedPassword);
//		Assert.assertTrue(isEncrypted);
//	}

	private String encryptPassword(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] hashBytes = md.digest(password.getBytes());
			StringBuilder sb = new StringBuilder();
			for (byte b : hashBytes) {
				sb.append(String.format("%02x", b));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	private boolean isPasswordEncrypted(String encryptedPassword) {
		return encryptedPassword.contains("*"); // Kiểm tra xem mật khẩu đã được mã hóa chưa
	}

	public void TC_18_Password_Encrypted() {
		driver.findElement(passTextbox).sendKeys("mai1211");
		String encryptedPassword = encryptPassword("mai1211");
		boolean isEncrypted = isPasswordEncrypted(encryptedPassword);
		Assert.assertTrue(isEncrypted);
	}

	private boolean isValidPassword(String password) {
		// Biểu thức chính quy kiểm tra mật khẩu
		String passwordPattern = "^(?=.*[\\W]).{5,10}$"; // Mật khẩu chứa ít nhất một ký tự đặc biệt và có độ dài từ 5
															// đến 10 kí tự
		Pattern pattern = Pattern.compile(passwordPattern);
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();
	}

	
	public void TC_19_Password_4() {
		driver.findElement(lastNameTextbox).sendKeys("Dương");
		driver.findElement(firstNameTextbox).sendKeys("Mai");
		driver.findElement(phoneTextbox).sendKeys("098765432");
		driver.findElement(emailTextbox).sendKeys("test@gmail.com");
		driver.findElement(passTextbox).sendKeys("1211");
		driver.findElement(buttonRegister).click();

		// Kiểm tra xem lỗi có hiển thị không
		Assert.assertTrue(driver.findElement(errorMessage).isDisplayed());

		// Kiểm tra xem thông điệp lỗi có chứa chuỗi mong muốn không
		String actualErrorMessage = driver.findElement(errorMessage).getText();
		System.out.println(actualErrorMessage);
		Assert.assertTrue(actualErrorMessage.contains("Mật khẩu quá ngắn (tối thiểu 5 ký tự)."));
	}


	public void TC_24_Success() {
		driver.findElement(lastNameTextbox).sendKeys("Dương");
		driver.findElement(firstNameTextbox).sendKeys("Mai");
		driver.findElement(phoneTextbox).sendKeys("098765432");
		driver.findElement(emailTextbox).sendKeys("duongmai060801@gmail.com");
		driver.findElement(passTextbox).sendKeys("Mai2001");
		driver.findElement(buttonRegister).click();
		String actualErrorMessage = driver.findElement(errorMessage).getText();
		System.out.println(actualErrorMessage);
		Assert.assertTrue(actualErrorMessage.contains("ok"));
	}

	public String getHTML5ValidationMessage(WebElement element) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", element);
	}
	public WebDriver getWebdriver() {
		return this.driver;
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}
}
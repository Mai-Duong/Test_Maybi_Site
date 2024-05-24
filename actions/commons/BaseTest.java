package commons;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;

public class BaseTest {
	public WebDriver driver;
	private String projectLocation = System.getProperty("user.dir");
	protected final Log log;
	
	// khoi tao log
	protected BaseTest() {
		log = LogFactory.getLog(getClass());
	}
	public WebDriver getWebdriver() {
		return this.driver;
	}
	private enum BROWSER { CHROME, FIREFOX, SAFARI }
	private enum PLATFORM { IOS, ANDROID }
	
	public WebDriver getBrowserDriver(String browserName) {
		BROWSER browser = BROWSER.valueOf(browserName.toLowerCase());		
		if (browser==BROWSER.FIREFOX) {
			System.getProperty("webdriver.gecko.driver", projectLocation + "\\browserDrivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			System.out.println("driver: " + driver.toString());
		} else if (browser==BROWSER.CHROME) {
			System.getProperty("webdriver.chrome.driver", projectLocation + "\\browserDrivers\\chromedriver.exe");
			driver = new ChromeDriver();
		} else {
			throw new RuntimeException("Please enterr correct browser name!");
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		// full man
//		driver.manage().window().maximize();
		return driver;

	}
	
	public WebDriver getBrowserDriver(String browserName, String url) {
		BROWSER browser = BROWSER.valueOf(browserName.toUpperCase());
		if (browser==BROWSER.FIREFOX) {
			System.getProperty("webdriver.gecko.driver", projectLocation + "\\browserDrivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			System.out.println("driver: " + driver.toString());
		} else if (browser==BROWSER.CHROME) {
//			System.getProperty("webdriver.chrome.driver", projectLocation + "\\browserDrivers\\chromedriver.exe");
			driver = new ChromeDriver();
		} else {
			throw new RuntimeException("Please enterr correct browser name!");
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(url);
		// full man
//		driver.manage().window().maximize();
		return driver;

	}
	


	////////////////////////////
	protected boolean verifyTrue(boolean condition) {
		boolean status = true;
		try {
			Assert.assertTrue(condition);
			log.info("---------------------- PASSED -----------------------");
		} catch (Throwable e) {
			status = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
			log.info("---------------------- FAILED -----------------------");
		} 
		return status;
	}

	protected boolean verifyFalse(boolean condition) {
		boolean status = true;
		try {
			Assert.assertFalse(condition);
			log.info("---------------------- PASSED -----------------------");
		} catch (Throwable e) {
			status = false;
			// lấy  Failed
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			// add  Failed vào report
			Reporter.getCurrentTestResult().setThrowable(e);
			log.info("---------------------- FAILED -----------------------");
		}
		return status;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		boolean status = true;
		try {
			Assert.assertEquals(actual, expected);
			log.info("---------------------- PASSED -----------------------");
		} catch (Throwable e) {
			status = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
			log.info("---------------------- FAILED -----------------------");
		}
		return status;
	}
	
	
	/////////////////
	public String RandomEmail() {
		Random rand = new Random();
		return "test" + rand.nextInt(99999) + "@gmail.com";
	}


	
//
	public String getHTML5ValidationMessageEmail() {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return document.querySelector('#customer_email').validationMessage;");
	}
	public String getHTML5ValidationMessagePass() {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return document.querySelector('#customer_password').validationMessage;");
	}
	
	
	public String getHTML5ValidationMessageSearch() {
	    JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
	    return (String) jsExecutor.executeScript("return document.querySelector('.input-group-field.auto-search.form-control').validationMessage;");
	}
	
	// login
	public String getHTML5ValidationMessageLastNameRegister() {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return document.querySelector('#lastName').validationMessage;");
	}
	
	public String getHTML5ValidationMessageFirstNameRegister() {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return document.querySelector('#firstName').validationMessage;");
	}
	
	public String getHTML5ValidationMessagePhoneRegister() {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return document.querySelector('#Phone').validationMessage;");
	}
	public String getHTML5ValidationMessageEmailRegister() {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return document.querySelector('#email').validationMessage;");
	}
	public String getHTML5ValidationMessagePassRegister() {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return document.querySelector('#password').validationMessage;");
	}

	
	public boolean isValidPhoneNumber(String phoneNumber) {
	    // Kiểm tra xem số điện thoại có đúng 10 chữ số không
	    if (phoneNumber.length() != 10) {
	        return false;
	    }

	    // Kiểm tra xem số điện thoại có chứa ký tự không phải là số không
	    for (char c : phoneNumber.toCharArray()) {
	        if (!Character.isDigit(c)) {
	            return false;
	        }
	    }

	    // Kiểm tra xem số điện thoại có chứa khoảng cách không
	    if (phoneNumber.contains(" ")) {
	        return false;
	    }

	    // Nếu số điện thoại thỏa mãn các điều kiện trên, trả về true
	    return true;
	}

	public boolean isPasswordLengthValid(String password) {
	    // Kiểm tra xem mật khẩu có đủ dài từ 5 kí tự trở lên không
	    return password.length() >= 5;
	}


	
	
}

package com.maybestore;

import java.util.Random;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.maybistore.AccountPage;
import pageObjects.maybistore.HomeObject;
import pageObjects.maybistore.LoginObject;
import pageObjects.maybistore.OrderObject;
import pageObjects.maybistore.RegisterObject;
import pageObjects.maybistore.SearchObject;

public class Testcase_Login extends BaseTest {
	WebDriver driver;
	HomeObject homePage;
	LoginObject loginPage;
	RegisterObject registerPage;
	OrderObject orderPage;
	SearchObject searchPage;
	AccountPage accountPage;
	String email;
	String pass;

	@Parameters({ "browser", "url" })
	@BeforeMethod
	public void beforeMethod(String browserName, String url) {
		driver = getBrowserDriver(browserName, url);
		homePage = new HomeObject(driver);
		email = "duongmai060801@gmail.com";
		pass = "Mai2001@";
	}
	

	@Test
	public void TC_01_EmptyEmailAndPasswordFields() {
		homePage.openLoginPage();
		log.info("Step 1: Open Login Page ");
		loginPage = new LoginObject(driver);
		
		log.info("Step 2: Sendkey data");
		loginPage.enterEmailPass("", "");

		log.info("Step 3: Click Button Login");
		loginPage.clickToLoginButton();

		log.info("Step 4: Verify Message Email");
		verifyTrue(loginPage.isDisplayErroPass());
		verifyEquals("Please fill out this field.", getHTML5ValidationMessageEmail());
	}

	@Test
	public void TC_02_CorrectEmailFieldValidation() {		
		homePage.openLoginPage();
		log.info("Step 1: Open Login Page ");
		loginPage = new LoginObject(driver);
		
		log.info("Step 2: Sendkey data");
		loginPage.enterEmailPass(email, "");

		log.info("Step 3: Click Button Login");
		loginPage.clickToLoginButton();

		log.info("Step 4: Verify Message Email");
		verifyTrue(loginPage.isDisplayErroPass());
		verifyEquals("Please fill out this field.", getHTML5ValidationMessagePass());
	}

	@Test
	public void TC_03_CorrectPassFieldValidation() {	
		homePage.openLoginPage();
		log.info("Step 1: Open Login Page ");
		loginPage = new LoginObject(driver);
		
		log.info("Step 2: Sendkey data");
		loginPage.enterEmailPass("", pass);

		log.info("Step 3: Click Button Login");
		loginPage.clickToLoginButton();

		log.info("Step 4: Verify Message Email");
		verifyTrue(loginPage.isDisplayErroPass());
		verifyEquals("Please fill out this field.", getHTML5ValidationMessageEmail());
	}

	@Test
	public void TC_04_CorrectEmailAndIncorrectPass() {		
		homePage.openLoginPage();
		log.info("Step 1: Open Login Page ");
		loginPage = new LoginObject(driver);
		
		log.info("Step 2: Sendkey data");
		loginPage.enterEmailPass(email, "mai200001");

		log.info("Step 3: Click Button Login");
		loginPage.clickToLoginButton();

		log.info("Step 4: Verify Message Email");
		verifyTrue(loginPage.isDisplayError());
		verifyEquals(loginPage.isDisplayMessageError(), "Thông tin đăng nhập không hợp lệ.");
	}

	@Test
	public void TC_05_InCorrectEmailAndCorrectPass() {	
		homePage.openLoginPage();
		log.info("Step 1: Open Login Page ");
		loginPage = new LoginObject(driver);
		
		log.info("Step 2: Sendkey data");
		loginPage.enterEmailPass("duongmai001@gmail.com", pass);

		log.info("Step 3: Click Button Login");
		loginPage.clickToLoginButton();

		log.info("Step 4: Verify Message Email");
		verifyTrue(loginPage.isDisplayError());
		verifyEquals(loginPage.isDisplayMessageError(), "Thông tin đăng nhập không hợp lệ.");
	}

	@Test
	public void TC_06_InvalidEmailFormat() {		
		homePage.openLoginPage();
		log.info("Step 1: Open Login Page ");
		loginPage = new LoginObject(driver);
		
		log.info("Step 2: Sendkey data");
		loginPage.enterEmailPass("duongmai", pass);

		log.info("Step 3: Click Button Login");
		loginPage.clickToLoginButton();

		log.info("Step 4: Verify Message Email");
		verifyTrue(loginPage.isDisplayErroPass());
		String emailMessage = getHTML5ValidationMessageEmail();
		String expectedErrorMessage = String.format("Please include an '@' in the email address. '%s' is missing an '@'.", "duongmai");
		verifyEquals(expectedErrorMessage, emailMessage);
	}

	@Test
	public void TC_07_MissingDomainAfterAtSignInEmail() {		
		homePage.openLoginPage();
		log.info("Step 1: Open Login Page ");
		loginPage = new LoginObject(driver);
		
		log.info("Step 2: Sendkey data");
		loginPage.enterEmailPass("maiduong060801@", pass);

		log.info("Step 3: Click Button Login");
		loginPage.clickToLoginButton();

		log.info("Step 4: Verify Message Email");
		verifyTrue(loginPage.isDisplayErroPass());
		String emailMessage = getHTML5ValidationMessageEmail();
		log.info("Step 7 :" + emailMessage);
		String expectedErrorMessage = String.format("Please enter a part following '@'. '%s' is incomplete.", "maiduong060801@");
		verifyEquals(expectedErrorMessage, emailMessage);

	}

	@Test
	public void TC_08_IncorrectDomainAfterAtSignInEmail() {
		homePage.openLoginPage();
		log.info("Step 1: Open Login Page ");
		loginPage = new LoginObject(driver);
		
		log.info("Step 2: Sendkey data");
		loginPage.enterEmailPass("maiduong060801@com", pass);

		log.info("Step 3: Click Button Login");
		loginPage.clickToLoginButton();

		verifyTrue(loginPage.isDisplayErroPass());
		String emailMessage = getHTML5ValidationMessageEmail();
		log.info("Step 7: Verify");
		log.info("Step 7 :" + emailMessage);
		verifyTrue(loginPage.isDisplayError());
		sleepInSecond(3);
		verifyEquals(loginPage.isDisplayMessageError(), "Thông tin đăng nhập không hợp lệ.");

	}

	@Test
	public void TC_09_MissingCharactersBeforeAtSignInEmail() {		
		homePage.openLoginPage();
		log.info("Step 1: Open Login Page ");
		loginPage = new LoginObject(driver);
		
		log.info("Step 2: Sendkey data");
		loginPage.enterEmailPass("@gmail.com", pass);

		log.info("Step 3: Click Button Login");
		loginPage.clickToLoginButton();

		verifyTrue(loginPage.isDisplayErroPass());
		verifyTrue(loginPage.isDisplayErroPass());
		String emailMessage = getHTML5ValidationMessageEmail();
		log.info("Step 7 :" + emailMessage);
		String expectedErrorMessage = String.format("Please enter a part followed by '@'. '%s' is incomplete.", "@gmail.com");
		verifyEquals(expectedErrorMessage, emailMessage);
	}

	@Test
	public void TC_10_DisabledEmail_LoginWithPassword() {
		
		homePage.openLoginPage();
		log.info("Step 1: Open Login Page ");
		loginPage = new LoginObject(driver);
		
		log.info("Step 2: Sendkey data");
		loginPage.enterEmailPass("maiduong68011@gmail.com", "Mai1211");

		log.info("Step 3: Click Button Login");
		loginPage.clickToLoginButton();

		verifyTrue(loginPage.isDisplayErroPass());
		String emailMessage = getHTML5ValidationMessageEmail();
		log.info("Step 7 :" + emailMessage);
		log.info("Step 7 : verify");

		verifyTrue(loginPage.isDisplayError());

//		verifyEquals(loginPage.isDisplayError(),
//				"Your account has been disabled. Please contact support for further assistance.");

	}

	@Test(groups="TC_11_DeletedEmail_LoginWithPassword")
	public void TC_11_DeletedEmail_LoginWithPassword() {	
		homePage.openLoginPage();
		log.info("Step 1: Open Login Page ");
		loginPage = new LoginObject(driver);
		
		log.info("Step 2: Sendkey data");
		loginPage.enterEmailPass("maiduong1211@gmail.com", "Mai1995");

		log.info("Step 3: Click Button Login");
		loginPage.clickToLoginButton();

		sleepInSecond(2);
		verifyTrue(loginPage.isDisplayError());

		// 
//		verifyEquals(loginPage.isDisplayError(),
//				"Your email has been deleted. Please register again to continue accessing our services.");

	}

	@Test(groups="TC_12_ValidEmailAndPassword_Login")
	public void TC_12_ValidEmailAndPassword_Login() {	
		homePage.openLoginPage();
		log.info("Step 1: Open Login Page ");
		loginPage = new LoginObject(driver);
		
		log.info("Step 2: Sendkey data");
		loginPage.enterEmailPass(email,pass);

		log.info("Step 3: Click Button Login");
		loginPage.clickToLoginButton();

		accountPage = new AccountPage(driver);
		verifyTrue(accountPage.isDisplayedAccountPage());
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

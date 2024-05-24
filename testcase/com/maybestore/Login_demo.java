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

public class Login_demo extends BaseTest {
	WebDriver driver;
	HomeObject homePage;
	LoginObject loginPage;
	RegisterObject registerPage;
	OrderObject orderPage;
	SearchObject searchPage;
	AccountPage accountPage;
	String email = "duongmai060801@gmail.com";
	String pass = "Mai2001@";
	@Parameters({ "browser", "url" })
	@BeforeMethod
	public void beforeMethod(String browserName, String url) {
		driver = getBrowserDriver(browserName, url);
		homePage = new HomeObject(driver);

	}
	
	
	@Test(groups= {"TC_0001_EmptyEmailAndPasswordFields"})
	public void TC_0001_EmptyEmailAndPasswordFields() {
//		log.info("Step 1: Hover login span ");
//		homePage.hoverLogin();
//
//		log.info("Step 2: Click button login ");
//		homePage.clickToLoginLink();
		homePage.openLoginPage();

		log.info("Step 3: Open Login Page ");
		loginPage = new LoginObject(driver);

//		log.info("Step 4: Sendkey data Email Textbox ");
//		loginPage.enterToEmailTextbox("");
//
//		log.info("Step 5: Sendkey data Pass ");
//		loginPage.enterToPasswordTextbox("");
		loginPage.enterEmailPass("", "");

		log.info("Step 6: Click Button Login");
		loginPage.clickToLoginButton();

		log.info("Step 7: Verify");
		verifyTrue(loginPage.isDisplayErroPass());

		verifyEquals("Please fill out this field.", getHTML5ValidationMessageEmail());
	}

	@Test
	public void TC_01_EmptyEmailAndPasswordFields() {
		log.info("Step 1:Open login page ");
		homePage.openLoginPage();
		loginPage = new LoginObject(driver);

		log.info("Step 2: Sendkey data Email Textbox ");
		log.info("Step 3: Sendkey data Pass ");
		loginPage.enterEmailPass("", "");

		log.info("Step 4: Click Button Login");
		loginPage.clickToLoginButton();

		log.info("Step 5: Verify");
		verifyTrue(loginPage.isDisplayErroPass());
		verifyEquals("Please fill out this field.", getHTML5ValidationMessageEmail());
	}

	@Test
	public void TC_02_CorrectEmailFieldValidation() {	
		log.info("Step 1:Open login page ");
		homePage.openLoginPage();
		loginPage = new LoginObject(driver);

		log.info("Step 2: Sendkey data Email Textbox ");
		log.info("Step 3: Sendkey data Pass ");
		loginPage.enterEmailPass(email, "");

		log.info("Step 4: Click Button Login");
		loginPage.clickToLoginButton();

		log.info("Step 5: Verify");
		verifyTrue(loginPage.isDisplayErroPass());
		verifyEquals("Please fill out this field.", getHTML5ValidationMessagePass());
	}

	@Test
	public void TC_03_CorrectPassFieldValidation() {		
		log.info("Step 1:Open login page ");
		homePage.openLoginPage();
		loginPage = new LoginObject(driver);

		log.info("Step 2: Sendkey data Email Textbox ");
		log.info("Step 3: Sendkey data Pass ");
		loginPage.enterEmailPass("", pass);

		log.info("Step 4: Click Button Login");
		loginPage.clickToLoginButton();

		log.info("Step 5: Verify");
		verifyTrue(loginPage.isDisplayErroPass());
		verifyEquals("Please fill out this field.", getHTML5ValidationMessageEmail());
	}

	@Test
	public void TC_04_CorrectEmailAndIncorrectPass() {
	
		log.info("Step 1:Open login page ");
		homePage.openLoginPage();
		loginPage = new LoginObject(driver);

		log.info("Step 2: Sendkey data Email Textbox ");
		log.info("Step 3: Sendkey data Pass ");
		loginPage.enterEmailPass(email, "mai2001");

		log.info("Step 4: Click Button Login");
		loginPage.clickToLoginButton();

		log.info("Step 5: Verify");
		sleepInSecond(2);
		verifyTrue(loginPage.isDisplayError());
		verifyEquals(loginPage.isDisplayMessageError(), "Thông tin đăng nhập không hợp lệ.");
	}

	@Test
	public void TC_05_InCorrectEmailAndCorrectPass() {
		log.info("Step 1: Hover login span ");
		homePage.hoverLogin();

		log.info("Step 2: Click button login ");
		homePage.clickToLoginLink();

		log.info("Step 3: Open Login Page ");
		loginPage = new LoginObject(driver);

		log.info("Step 4: Sendkey data Email Textbox ");
		loginPage.enterToEmailTextbox("duongmai001@gmail.com");

		log.info("Step 5: Sendkey data Pass ");
		loginPage.enterToPasswordTextbox("Mai2001@");

		log.info("Step 6: Click Button Login");
		sleepInSecond(1);
		loginPage.clickToLoginButton();

		verifyTrue(loginPage.isDisplayError());
		verifyEquals(loginPage.isDisplayMessageError(), "Thông tin đăng nhập không hợp lệ.");

	}

	@Test
	public void TC_06_InvalidEmailFormat() {
		log.info("Step 1: Hover login span ");
		homePage.hoverLogin();

		log.info("Step 2: Click button login ");
		homePage.clickToLoginLink();

		log.info("Step 3: Open Login Page ");
		loginPage = new LoginObject(driver);

		String email = "duongmai";
		log.info("Step 4: Sendkey data Email Textbox ");
		loginPage.enterToEmailTextbox(email);

		log.info("Step 5: Sendkey data Pass ");
		loginPage.enterToPasswordTextbox("Mai2001@");

		log.info("Step 6: Click Button Login");
		loginPage.clickToLoginButton();

		verifyTrue(loginPage.isDisplayErroPass());
		String emailMessage = getHTML5ValidationMessageEmail();
		log.info("Step 7 :" + emailMessage);
		System.out.println(emailMessage);
		System.out.println("--- " + emailMessage);

		sleepInSecond(1);
		String expectedErrorMessage = String
				.format("Please include an '@' in the email address. '%s' is missing an '@'.", email);
		verifyEquals(expectedErrorMessage, emailMessage);
	}

	@Test
	public void TC_07_MissingDomainAfterAtSignInEmail() {
		log.info("Step 1: Hover login span ");
		homePage.hoverLogin();

		log.info("Step 2: Click button login ");
		sleepInSecond(1);
		homePage.clickToLoginLink();

		log.info("Step 3: Open Login Page ");
		loginPage = new LoginObject(driver);

		String email = "maiduong060801@";
		log.info("Step 4: Sendkey data Email Textbox ");
		loginPage.enterToEmailTextbox(email);

		log.info("Step 5: Sendkey data Pass ");
		loginPage.enterToPasswordTextbox("Mai2001@");

		log.info("Step 6: Click Button Login");
		loginPage.clickToLoginButton();

		verifyTrue(loginPage.isDisplayErroPass());
		String emailMessage = getHTML5ValidationMessageEmail();
		log.info("Step 7 :" + emailMessage);
		String expectedErrorMessage = String.format("Please enter a part following '@'. '%s' is incomplete.", email);
		verifyEquals(expectedErrorMessage, emailMessage);

	}

	@Test
	public void TC_08_IncorrectDomainAfterAtSignInEmail() {
		log.info("Step 1: Hover login span ");
		homePage.hoverLogin();

		log.info("Step 2: Click button login ");
		homePage.clickToLoginLink();

		log.info("Step 3: Open Login Page ");
		loginPage = new LoginObject(driver);

		String email = "maiduong060801@com";
		log.info("Step 4: Sendkey data Email Textbox ");
		loginPage.enterToEmailTextbox(email);

		log.info("Step 5: Sendkey data Pass ");
		loginPage.enterToPasswordTextbox("Mai2001@");

		log.info("Step 6: Click Button Login");
		sleepInSecond(1);
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
		String nameCase = "TC_09_MissingCharactersBeforeAtSignInEmail";
		log.info(nameCase + "Step 1: Hover login span ");
		homePage.hoverLogin();

		log.info(nameCase + "Step 2: Click button login ");
		sleepInSecond(2);
		homePage.clickToLoginLink();

		log.info(nameCase + "Step 3: Open Login Page ");
		loginPage = new LoginObject(driver);

		String email = "@gmail.com";
		log.info(nameCase + "Step 4: Sendkey data Email Textbox ");
		loginPage.enterToEmailTextbox(email);

		log.info(nameCase + "Step 5: Sendkey data Pass ");
		loginPage.enterToPasswordTextbox("Mai2001@");

		log.info(nameCase + "Step 6: Click Button Login");
		loginPage.clickToLoginButton();

		verifyTrue(loginPage.isDisplayErroPass());
		String emailMessage = getHTML5ValidationMessageEmail();
		log.info("Step 7 :" + emailMessage);
		String expectedErrorMessage = String.format("Please enter a part followed by '@'. '%s' is incomplete.", email);
		verifyEquals(expectedErrorMessage, emailMessage);

	}

	@Test
	public void TC_10_DisabledEmail_LoginWithPassword() {
		String nameCase = "TC_09_MissingCharactersBeforeAtSignInEmail";
		log.info(nameCase + "Step 1: Hover login span ");
		homePage.hoverLogin();

		log.info(nameCase + "Step 2: Click button login ");
		homePage.clickToLoginLink();

		log.info(nameCase + "Step 3: Open Login Page ");
		loginPage = new LoginObject(driver);

		String email = "maiduong68011@gmail.com";
		log.info(nameCase + "Step 4: Sendkey data Email Textbox ");
		loginPage.enterToEmailTextbox(email);

		log.info(nameCase + "Step 5: Sendkey data Pass ");
		loginPage.enterToPasswordTextbox("Mai6801@");

		log.info(nameCase + "Step 6: Click Button Login");
		sleepInSecond(1);
		loginPage.clickToLoginButton();

		verifyTrue(loginPage.isDisplayErroPass());
		String emailMessage = getHTML5ValidationMessageEmail();
		log.info("Step 7 :" + emailMessage);
		log.info("Step 7 : verify");

		sleepInSecond(2);
		verifyTrue(loginPage.isDisplayError());

//		verifyEquals(loginPage.isDisplayError(),
//				"Your account has been disabled. Please contact support for further assistance.");

	}

	@Test
	public void TC_11_DeletedEmail_LoginWithPassword() {
		String nameCase = "TC_09_MissingCharactersBeforeAtSignInEmail";
		log.info(nameCase + "Step 1: Hover login span ");
		homePage.hoverLogin();

		log.info(nameCase + "Step 2: Click button login ");
		homePage.clickToLoginLink();

		log.info(nameCase + "Step 3: Open Login Page ");
		loginPage = new LoginObject(driver);

		String email = "maiduong1211@gmail.com";
		log.info(nameCase + "Step 4: Sendkey data Email Textbox ");
		loginPage.enterToEmailTextbox(email);

		log.info(nameCase + "Step 5: Sendkey data Pass ");
		loginPage.enterToPasswordTextbox("Mai6801@");

		log.info(nameCase + "Step 6: Click Button Login");
		sleepInSecond(1);
		loginPage.clickToLoginButton();

		sleepInSecond(2);
		verifyTrue(loginPage.isDisplayError());

		// xoa
//		verifyEquals(loginPage.isDisplayError(),
//				"Your email has been deleted. Please register again to continue accessing our services.");

	}

	@Test
	public void TC_12_ValidEmailAndPassword_Login() {
		String nameCase = "TC_12_ValidEmailAndPassword_Login";
		log.info(nameCase + "Step 1: Hover login span ");
		homePage.hoverLogin();

		log.info(nameCase + "Step 2: Click button login ");
		homePage.clickToLoginLink();

		log.info(nameCase + "Step 3: Open Login Page ");
		loginPage = new LoginObject(driver);

		String email = "duongmai060801@gmail.com";
		log.info(nameCase + "Step 4: Sendkey data Email Textbox ");
		loginPage.enterToEmailTextbox(email);

		log.info(nameCase + "Step 5: Sendkey data Pass ");
		loginPage.enterToPasswordTextbox("Mai2001@");

		sleepInSecond(2);
		log.info(nameCase + "Step 6: Click Button Login");
		loginPage.clickToLoginButton();

		accountPage = new AccountPage(driver);
		sleepInSecond(2);
		verifyTrue(accountPage.isDisplayedAccountPage());

	}

	@Test
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

	@AfterMethod
	public void AfterMethod() {
		log.info("CLose browser");
		driver.quit();
	}

}

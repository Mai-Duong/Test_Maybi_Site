package com.maybestore;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
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

public class Register_demo extends BaseTest {
	WebDriver driver;
	HomeObject homePage;
	LoginObject loginPage;
	RegisterObject registerPage;
	OrderObject orderPage;
	SearchObject searchPage;
	AccountPage accountPage;

	String email;

	@Parameters({ "browser", "url" })
	@BeforeMethod
	public void beforeMethod(String browserName, String url) {
		driver = getBrowserDriver(browserName, url);
		homePage = new HomeObject(driver);
		email = RandomEmail();
	}

	@Parameters({ "browser" })
	@Test
	public void TC_01_Empty_All_Fields(String browser) {
		log.info("Step 1: Hover register link");
		homePage.hoverLogin();
		log.info("Step 2: Click register link");
		homePage.clickToRegister();
		log.info("Step 2: Click open Register page");
		registerPage = new RegisterObject(driver);

		log.info("Step 3: Sendkey Lastname");
		registerPage.enterToLastnameTextbox("");

		log.info("Step 4: Sendkey Firstname");
		registerPage.enterToFirstnameTextbox("");

		log.info("Step 5: Sendkey phone");
		registerPage.enterToPhoneTextbox("");

		log.info("Step 5: Sendkey email");
		registerPage.enterToEmailTextbox("");

		log.info("Step 5: Sendkey pass");
		registerPage.enterToPasswordTextbox("");

		log.info("Step 6: Click button Register");
		registerPage.clickToRegisterButton();
		log.info("Step 7: Validation Message");
		verifyTrue(registerPage.isDisplayErroLastName());
		String errorMessage = getHTML5ValidationMessageLastNameRegister();
		System.out.println("--- " + errorMessage);
		if (browser.equals("chrome")) {
			verifyEquals("Please fill out this field.", errorMessage);
		}
		if (browser.equals("firefox")) {
			verifyEquals("Vui lòng điền vào trường này.", errorMessage);
		}
	}

	@Parameters({ "browser" })
	@Test
	public void TC_02_RegisterWithWhitespaceLasName(String browser) {
		log.info("Step 1: Hover register link");
		homePage.hoverLogin();
		log.info("Step 2: Click register link");
		sleepInSecond(1);
		homePage.clickToRegister();
		log.info("Step 2: Click open Register page");
		registerPage = new RegisterObject(driver);

		log.info("Step 3: Sendkey Lastname");
		registerPage.enterToLastnameTextbox("        ");

		String firstName = "Mai";
		log.info("Step 4: Sendkey Firstname");
		registerPage.enterToFirstnameTextbox(firstName);

		String phone = "099834932";
		log.info("Step 5: Sendkey phone");
		registerPage.enterToPhoneTextbox(phone);

		log.info("Step 5: Sendkey email");
		registerPage.enterToEmailTextbox(email);

		String pass = "maii09";
		log.info("Step 5: Sendkey pass");
		registerPage.enterToPasswordTextbox(pass);

		log.info("Step 6: Click button Register");
		registerPage.clickToRegisterButton();
		log.info("Step 7: Validation Message");

		String errorMessage = getHTML5ValidationMessageLastNameRegister();
		if (browser.equals("chrome")) {
			sleepInSecond(2);
			verifyTrue(registerPage.isDisplayErroLastName());
			verifyEquals("Please fill out this field.", errorMessage);
		}
		if (browser.equals("firefox")) {
			sleepInSecond(2);
			verifyTrue(registerPage.isDisplayErroLastName());
			verifyEquals("Vui lòng điền vào trường này.", errorMessage);
		}
	}

	@Parameters({ "browser" })
	@Test
	public void TC_02_RegisterWithEmptyLastName(String browser) {
		log.info("Step 1: Hover register link");
		homePage.hoverLogin();
		log.info("Step 2: Click register link");
		homePage.clickToRegister();
		log.info("Step 2: Click open Register page");
		registerPage = new RegisterObject(driver);

		log.info("Step 3: Sendkey Lastname");
		registerPage.enterToLastnameTextbox("");

		log.info("Step 4: Sendkey Firstname");
		registerPage.enterToFirstnameTextbox("Mai");

		log.info("Step 5: Sendkey phone");
		registerPage.enterToPhoneTextbox("010120101");

		registerPage.enterToEmailTextbox(email);
		log.info("Step 5: Sendkey email " + email);

		log.info("Step 5: Sendkey pass");
		registerPage.enterToPasswordTextbox("mai1211");

		log.info("Step 6: Click button Register");
		registerPage.clickToRegisterButton();
		log.info("Step 7: Validation Message");
		verifyTrue(registerPage.isDisplayErroLastName());
		String errorMessage = getHTML5ValidationMessageLastNameRegister();
		System.out.println("--- " + errorMessage);
		if (browser.equals("chrome")) {
			verifyEquals("Please fill out this field.", errorMessage);
		}
		if (browser.equals("firefox")) {
			verifyEquals("Vui lòng điền vào trường này.", errorMessage);
		}
	}

	@Parameters({ "browser" })
	@Test
	public void TC_03_RegisterWithEmptyFirstName(String browser) {
		log.info("Step 1: Hover register link");
		homePage.hoverLogin();
		log.info("Step 2: Click register link");
		homePage.clickToRegister();
		log.info("Step 2: Click open Register page");
		registerPage = new RegisterObject(driver);

		log.info("Step 3: Sendkey Lastname");
		registerPage.enterToLastnameTextbox("Dương");

		log.info("Step 4: Sendkey Firstname");
		registerPage.enterToFirstnameTextbox("");

		log.info("Step 5: Sendkey phone");
		registerPage.enterToPhoneTextbox("010120101");

		log.info("Step 5: Sendkey email");
		registerPage.enterToEmailTextbox(email);

		log.info("Step 5: Sendkey pass");
		registerPage.enterToPasswordTextbox("mai1211");

		log.info("Step 6: Click button Register");
		registerPage.clickToRegisterButton();
		log.info("Step 7: Validation Message");
		verifyTrue(registerPage.isDisplayErroLastName());
		String errorMessage = getHTML5ValidationMessageFirstNameRegister();
		System.out.println("--- " + errorMessage);
		if (browser.equals("chrome")) {
			verifyEquals("Please fill out this field.", errorMessage);
		} else if (browser.equals("firefox")) {
			verifyEquals("Vui lòng điền vào trường này.", errorMessage);
		}
	}

	@Parameters({ "browser" })
	@Test
	public void TC_03_RegisterWithWhitespaceFirstName(String browser) {
		log.info("Step 1: Hover register link");
		homePage.hoverLogin();
		log.info("Step 2: Click register link");
		homePage.clickToRegister();
		log.info("Step 2: Click open Register page");
		registerPage = new RegisterObject(driver);

		log.info("Step 3: Sendkey Lastname");
		registerPage.enterToLastnameTextbox("Dương");

		log.info("Step 4: Sendkey Firstname");
		registerPage.enterToFirstnameTextbox("         ");

		log.info("Step 5: Sendkey phone");
		registerPage.enterToPhoneTextbox("010120101");

		log.info("Step 5: Sendkey email");
		registerPage.enterToEmailTextbox(email);

		log.info("Step 5: Sendkey pass");
		registerPage.enterToPasswordTextbox("mai1211");

		log.info("Step 6: Click button Register");
		registerPage.clickToRegisterButton();
		log.info("Step 7: Validation Message");
		sleepInSecond(2);
		String errorMessage = getHTML5ValidationMessageFirstNameRegister();
		if (browser.equals("chrome")) {
			verifyTrue(registerPage.isDisplayErroLastName());
			verifyEquals("Please fill out this field.", errorMessage);
		} else if (browser.equals("firefox")) {
			sleepInSecond(2);
			verifyTrue(registerPage.isDisplayErroLastName());
			verifyEquals("Vui lòng điền vào trường này.", errorMessage);
		}
	}

	@Parameters({ "browser" })
	@Test
	public void TC_04_RegisterWithEmptyPhone(String browser) {
		log.info("Step 1: Hover register link");
		homePage.hoverLogin();
		log.info("Step 2: Click register link");
		sleepInSecond(1);
		homePage.clickToRegister();
		log.info("Step 2: Click open Register page");
		registerPage = new RegisterObject(driver);

		log.info("Step 3: Sendkey Lastname");
		registerPage.enterToLastnameTextbox("Dương");

		log.info("Step 4: Sendkey Firstname");
		registerPage.enterToFirstnameTextbox("Mai");

		log.info("Step 5: Sendkey phone");
		registerPage.enterToPhoneTextbox("");

		registerPage.enterToEmailTextbox(email);
		log.info("Step 5: Sendkey email " + email);

		log.info("Step 5: Sendkey pass");
		registerPage.enterToPasswordTextbox("mai1211");

		log.info("Step 6: Click button Register");
		registerPage.clickToRegisterButton();
		log.info("Step 7: Validation Message");
		verifyTrue(registerPage.isDisplayErrorPhone());
		String errorMessage = getHTML5ValidationMessagePhoneRegister();
		System.out.println("--- " + errorMessage);
		if (browser.equals("chrome")) {
			verifyEquals("Please fill out this field.", errorMessage);
		} else if (browser.equals("firefox")) {
			verifyEquals("Vui lòng điền vào trường này.", errorMessage);
		}
	}

	@Parameters({ "browser" })
	@Test
	public void TC_04_RegisterWithInvalidPhoneNumber(String browser) {
		log.info("Step 1: Hover register link");
		homePage.hoverLogin();
		log.info("Step 2: Click register link");
		homePage.clickToRegister();
		log.info("Step 2: Click open Register page");
		registerPage = new RegisterObject(driver);

		log.info("Step 3: Sendkey Lastname");
		registerPage.enterToLastnameTextbox("Dương");

		log.info("Step 4: Sendkey Firstname");
		registerPage.enterToFirstnameTextbox("Mai");

		log.info("Step 5: Sendkey phone");
		registerPage.enterToPhoneTextbox("phonenume");

		registerPage.enterToEmailTextbox(email);
		log.info("Step 5: Sendkey email " + email);

		log.info("Step 5: Sendkey pass");
		registerPage.enterToPasswordTextbox("mai1211");

		log.info("Step 6: Click button Register");
		registerPage.clickToRegisterButton();
		log.info("Step 7: Validation Message");
		verifyTrue(registerPage.isDisplayErrorFristName());
		String errorMessage = getHTML5ValidationMessagePhoneRegister();
		System.out.println("--- " + errorMessage);
		if (browser.equals("chrome")) {
			verifyEquals("Please match the requested format.", errorMessage);
		} else if (browser.equals("firefox")) {
			verifyEquals("Vui lòng khớp định dạng được yêu cầu.", errorMessage);
		}
	}

	@Parameters({ "browser" })
	@Test
	public void TC_04_RegisterWithPhoneNumberContainingSpaces(String browser) {
		log.info("Step 1: Hover register link");
		homePage.hoverLogin();
		log.info("Step 2: Click register link");
		if (browser.equals("firefox")){
			sleepInSecond(2);
		}
		homePage.clickToRegister();
		log.info("Step 2: Click open Register page");
		registerPage = new RegisterObject(driver);

		log.info("Step 3: Sendkey Lastname");
		registerPage.enterToLastnameTextbox("Dương");

		log.info("Step 4: Sendkey Firstname");
		registerPage.enterToFirstnameTextbox("Mai");

		log.info("Step 5: Sendkey phone");
		registerPage.enterToPhoneTextbox("014 01 999");

		registerPage.enterToEmailTextbox(email);
		log.info("Step 5: Sendkey email :" + email);

		log.info("Step 5: Sendkey pass");
		registerPage.enterToPasswordTextbox("mai1211");

		log.info("Step 6: Click button Register");
		registerPage.clickToRegisterButton();
		log.info("Step 7: Validation Message");
		verifyTrue(registerPage.isDisplayErrorPhone());
		String errorMessage = getHTML5ValidationMessagePhoneRegister();
		System.out.println("--- " + errorMessage);
		if (browser.equals("chrome")) {
			verifyEquals("Please match the requested format.", errorMessage);
		} else if (browser.equals("firefox")) {
			verifyEquals("Vui lòng khớp định dạng được yêu cầu.", errorMessage);
		}
	}

	@Parameters({ "browser" })
	@Test
	public void TC_04_IsValidPhoneNumberLength(String browser) {
		log.info("Step 1: Hover register link");
		homePage.hoverLogin();
		log.info("Step 2: Click register link");
		sleepInSecond(2);
		homePage.clickToRegister();
		log.info("Step 2: Click open Register page");
		registerPage = new RegisterObject(driver);

		log.info("Step 3: Sendkey Lastname");
		registerPage.enterToLastnameTextbox("Dương");

		log.info("Step 4: Sendkey Firstname");
		registerPage.enterToFirstnameTextbox("Mai");

		log.info("Step 5: Sendkey phone");
		String phone = "11111111111111111111111111111";
		registerPage.enterToPhoneTextbox(phone);

		registerPage.enterToEmailTextbox(email);
		log.info("Step 5: Sendkey email :" + email);

		log.info("Step 5: Sendkey pass");
		registerPage.enterToPasswordTextbox("mai1211");

		log.info("Step 6: Click button Register");
		registerPage.clickToRegisterButton();
		log.info("Step 7: Validation Message");
		verifyTrue(registerPage.isDisplayErrorPhone());

		String errorMessage = getHTML5ValidationMessagePhoneRegister();
		if (browser.equals("chrome")) {
			sleepInSecond(1);
			verifyTrue(registerPage.isMessageErrorDisplayed());
			verifyEquals("Please fill out this field.", errorMessage);
		}

		else if (browser.equals("firefox")) {
			sleepInSecond(2);
			verifyTrue(registerPage.isMessageErrorDisplayed());
			verifyEquals("Vui lòng nhập thông tin vào trường này.", errorMessage);
		}
	}

	@Parameters({ "browser" })
	@Test
	public void TC_05_RegisterWithEmptyEmail(String browser) {
		log.info("Step 1: Hover register link");
		sleepInSecond(2);
		homePage.hoverLogin();
		log.info("Step 2: Click register link");
		sleepInSecond(2);
		homePage.clickToRegister();
		log.info("Step 2: Click open Register page");
		registerPage = new RegisterObject(driver);

		log.info("Step 3: Sendkey Lastname");
		registerPage.enterToLastnameTextbox("Dương");

		log.info("Step 4: Sendkey Firstname");
		registerPage.enterToFirstnameTextbox("Mai");

		log.info("Step 5: Sendkey phone");
		String phone = "0344567894";
		registerPage.enterToPhoneTextbox(phone);

		registerPage.enterToEmailTextbox("");
		log.info("Step 5: Sendkey email " + "");

		log.info("Step 5: Sendkey pass");
		registerPage.enterToPasswordTextbox("mai1211");

		log.info("Step 6: Click button Register");
		registerPage.clickToRegisterButton();
		log.info("Step 7: Validation Message");
		verifyTrue(registerPage.isDisplayErrorEmail());
		String errorMessage = getHTML5ValidationMessageEmailRegister();

		if (browser.equals("chrome")) {
			verifyEquals("Please fill out this field.", errorMessage);
		} else if (browser.equals("firefox")) {
			verifyEquals("Please fill out this field.", errorMessage);
		}
	}

	@Parameters({ "browser" })
	@Test
	public void TC_05_RegisterWithEmailMissingAtSymbol(String browser) {
		log.info("Step 1: Hover register link");
		homePage.hoverLogin();
		log.info("Step 2: Click register link");
		sleepInSecond(2);
		homePage.clickToRegister();
		log.info("Step 2: Click open Register page");
		registerPage = new RegisterObject(driver);

		log.info("Step 3: Sendkey Lastname");
		registerPage.enterToLastnameTextbox("Dương");

		log.info("Step 4: Sendkey Firstname");
		registerPage.enterToFirstnameTextbox("Mai");

		log.info("Step 5: Sendkey phone");
		String phone = "0344567894";
		registerPage.enterToPhoneTextbox(phone);

		log.info("Step 5: Sendkey email");
		String email = "duongmai060801gmail.com";
		registerPage.enterToEmailTextbox(email);

		log.info("Step 5: Sendkey pass");
		registerPage.enterToPasswordTextbox("mai1211");

		log.info("Step 6: Click button Register");
		registerPage.clickToRegisterButton();
		log.info("Step 7: Validation Message");
		verifyTrue(registerPage.isDisplayErrorEmail());
		String errorMessage = getHTML5ValidationMessageEmailRegister();
		String expectedErrorMessage = String
				.format("Please include an '@' in the email address. '%s' is missing an '@'.", email);
//		verifyEquals(expectedErrorMessage, errorMessage);

		if (browser.equals("chrome")) {
			verifyEquals(expectedErrorMessage, errorMessage);
		} else if (browser.equals("firefox")) {
			verifyEquals("Vui lòng điền một địa chỉ email.", errorMessage);
		}

	}

	@Parameters({ "browser" })
	@Test
	public void TC_05_RegisterWithEmailMissingDomain(String browser) {
		log.info("Step 1: Hover register link");
		homePage.hoverLogin();
		log.info("Step 2: Click register link");
		sleepInSecond(1);
		homePage.clickToRegister();
		log.info("Step 2: Click open Register page");
		registerPage = new RegisterObject(driver);

		log.info("Step 3: Sendkey Lastname");
		registerPage.enterToLastnameTextbox("Dương");

		log.info("Step 4: Sendkey Firstname");
		registerPage.enterToFirstnameTextbox("Mai");

		log.info("Step 5: Sendkey phone");
		String phone = "0344567894";
		registerPage.enterToPhoneTextbox(phone);

		log.info("Step 5: Sendkey email");
		String email = "duongmai060801@";
		registerPage.enterToEmailTextbox(email);

		log.info("Step 5: Sendkey pass");
		registerPage.enterToPasswordTextbox("mai1211");

		log.info("Step 6: Click button Register");
		registerPage.clickToRegisterButton();
		log.info("Step 7: Validation Message");
		verifyTrue(registerPage.isDisplayErrorEmail());
		String errorMessage = getHTML5ValidationMessageEmailRegister();
		if (browser.equals("chrome")) {
			String expectedErrorMessage = String.format("Please enter a part following '@'. '%s' is incomplete.",
					email);
			verifyEquals(expectedErrorMessage, errorMessage);
		} else if (browser.equals("firefox")) {
			verifyEquals("Vui lòng điền một địa chỉ email.", errorMessage);
		}
	}

	@Parameters({ "browser" })
	@Test
	public void TC_05_RegisterWithEmailMissingUsername(String browser) {
		log.info("Step 1: Hover register link");
		homePage.hoverLogin();
		log.info("Step 2: Click register link");
		sleepInSecond(1);
		homePage.clickToRegister();
		log.info("Step 2: Click open Register page");
		registerPage = new RegisterObject(driver);

		log.info("Step 3: Sendkey Lastname");
		registerPage.enterToLastnameTextbox("Dương");

		log.info("Step 4: Sendkey Firstname");
		registerPage.enterToFirstnameTextbox("Mai");

		log.info("Step 5: Sendkey phone");
		String phone = "0344567894";
		registerPage.enterToPhoneTextbox(phone);

		log.info("Step 5: Sendkey email");
		String email = "@gmail.com";
		registerPage.enterToEmailTextbox(email);

		log.info("Step 5: Sendkey pass");
		registerPage.enterToPasswordTextbox("mai1211");

		log.info("Step 6: Click button Register");
		registerPage.clickToRegisterButton();
		log.info("Step 7: Validation Message");
		verifyTrue(registerPage.isDisplayErrorEmail());
		String errorMessage = getHTML5ValidationMessageEmailRegister();

		if (browser.equals("chrome")) {
			String expectedErrorMessage = String.format("Please enter a part followed by '@'. '%s' is incomplete.",
					email);
			verifyEquals(expectedErrorMessage, errorMessage);
		} else if (browser.equals("firefox")) {
			sleepInSecond(2);
			verifyEquals("Vui lòng điền một địa chỉ email.", errorMessage);
		}
	}

	@Parameters({ "browser" })
	@Test
	public void TC_06_RegisterWithExistingEmail(String browser) {
		log.info("Step 1: Hover register link");
		homePage.hoverLogin();
		log.info("Step 2: Click register link");
		homePage.clickToRegister();
		log.info("Step 2: Click open Register page");
		registerPage = new RegisterObject(driver);

		log.info("Step 3: Sendkey Lastname");
		registerPage.enterToLastnameTextbox("Dương");

		log.info("Step 4: Sendkey Firstname");
		registerPage.enterToFirstnameTextbox("Mai");

		log.info("Step 5: Sendkey phone");
		String phone = "0987654321";
		registerPage.enterToPhoneTextbox(phone);

		log.info("Step 5: Sendkey email");
		String email = "duongmai060801@gmail.com";
		registerPage.enterToEmailTextbox(email);

		log.info("Step 5: Sendkey pass");
		registerPage.enterToPasswordTextbox("test1211");

		log.info("Step 6: Click button Register");
		registerPage.clickToRegisterButton();
		log.info("Step 7: Validation Message");
		sleepInSecond(2);
		verifyTrue(registerPage.isMessageErrorDisplayed());

		if (browser.equals("chrome")) {
			verifyEquals(registerPage.isMessageError(),
					"Email đã tồn tại. Nếu bạn quên mật khẩu, bạn có thể thiết lập lại mật khẩu tại đây.");
		} else if (browser.equals("firefox")) {
			sleepInSecond(2);
			verifyEquals("Vui lòng điền một địa chỉ email.", registerPage.isMessageError());
		}

	}

	@Parameters({ "browser" })
	@Test
	public void TC_07_RegisterWithEmptyPassword(String browser) {
		log.info("Step 1: Hover register link");
		homePage.hoverLogin();
		log.info("Step 2: Click register link");
		sleepInSecond(1);
		homePage.clickToRegister();
		log.info("Step 2: Click open Register page");
		registerPage = new RegisterObject(driver);

		log.info("Step 3: Sendkey Lastname");
		registerPage.enterToLastnameTextbox("Dương");

		log.info("Step 4: Sendkey Firstname");
		registerPage.enterToFirstnameTextbox("Mai");

		log.info("Step 5: Sendkey phone");
		String phone = "0987654321";
		registerPage.enterToPhoneTextbox(phone);

		log.info("Step 5: Sendkey email");
		String email = "duongmai060801@gmail.com";
		registerPage.enterToEmailTextbox(email);

		log.info("Step 5: Sendkey pass");
		registerPage.enterToPasswordTextbox("");

		log.info("Step 6: Click button Register");
		registerPage.clickToRegisterButton();
		log.info("Step 7: Validation Message");
		verifyTrue(registerPage.isDisplayErrorPass());
		String errorMessage = getHTML5ValidationMessagePassRegister();
		System.out.println("--- " + errorMessage);

		if (browser.equals("chrome")) {
			verifyEquals("Please fill out this field.", errorMessage);
		} else if (browser.equals("firefox")) {
			verifyEquals("Vui lòng nhập vào trường này.", errorMessage);
		}
	}

	@Test
	public void TC_07_RegisterWithWhitespacePassword() {
		log.info("Step 1: Hover register link");
		homePage.hoverLogin();
		log.info("Step 2: Click register link");
		sleepInSecond(1);
		homePage.clickToRegister();
		log.info("Step 2: Click open Register page");
		registerPage = new RegisterObject(driver);

		log.info("Step 3: Sendkey Lastname");
		registerPage.enterToLastnameTextbox("Dương");

		log.info("Step 4: Sendkey Firstname");
		registerPage.enterToFirstnameTextbox("Mai");

		log.info("Step 5: Sendkey phone");
		String phone = "0987654321";
		registerPage.enterToPhoneTextbox(phone);

		log.info("Step 5: Sendkey email");
		registerPage.enterToEmailTextbox(email);
		log.info("Step 5: Sendkey email :" + email);

		log.info("Step 5: Sendkey pass");
		registerPage.enterToPasswordTextbox("      ");

		log.info("Step 6: Click button Register");
		registerPage.clickToRegisterButton();
		log.info("Step 7: Validation Message");
		sleepInSecond(2);
		verifyTrue(registerPage.isMessageErrorDisplayed());
		verifyEquals(registerPage.isMessageError(), "Mật khẩu không được để trống.");
	}

	@Test
	public void TC_07_RegisterWithLenghtFourPassword() {
		log.info("Step 1: Hover register link");
		homePage.hoverLogin();
		log.info("Step 2: Click register link");

		homePage.clickToRegister();
		log.info("Step 2: Click open Register page");
		registerPage = new RegisterObject(driver);

		log.info("Step 3: Sendkey Lastname");
		registerPage.enterToLastnameTextbox("Dương");

		log.info("Step 4: Sendkey Firstname");
		registerPage.enterToFirstnameTextbox("Mai");

		log.info("Step 5: Sendkey phone");
		String phone = "0987654321";
		registerPage.enterToPhoneTextbox(phone);

		log.info("Step 5: Sendkey email");
		registerPage.enterToEmailTextbox(email);
		log.info("Step 5: Sendkey email :" + email);

		log.info("Step 5: Sendkey pass");
		registerPage.enterToPasswordTextbox("1234");

		log.info("Step 6: Click button Register");
		registerPage.clickToRegisterButton();
		log.info("Step 7: Validation Message");
		sleepInSecond(5);
		verifyTrue(registerPage.isMessageErrorDisplayed());
		verifyEquals(registerPage.isMessageError(), "Mật khẩu quá ngắn (tối thiểu 5 ký tự).");
	}

	@Test
	public void TC_07_RegisterWithLenghtFivePassword() {
		log.info("Step 1: Hover register link");
		homePage.hoverLogin();
		log.info("Step 2: Click register link");
		homePage.clickToRegister();
		log.info("Step 2: Click open Register page");
		registerPage = new RegisterObject(driver);

		log.info("Step 3: Sendkey Lastname");
		registerPage.enterToLastnameTextbox("Anna");

		log.info("Step 4: Sendkey Firstname");
		registerPage.enterToFirstnameTextbox("Anna");

		String phone = "0987654321";
		log.info("Step 5: Sendkey phone");
		registerPage.enterToPhoneTextbox(phone);

		registerPage.enterToEmailTextbox(email);
		log.info("Step 5: Sendkey email :" + email);

		String pass = "56789";
		log.info("Step 5: Sendkey pass : " + pass);
		registerPage.enterToPasswordTextbox(pass);

		log.info("Step 6: Click button Register");
		registerPage.clickToRegisterButton();
		sleepInSecond(2);
		log.info("Step 7: Validation Message");
		accountPage = new AccountPage(driver);
		verifyTrue(accountPage.isDisplayedAccountPage());
	}


	public void TC_07_RegisterWithLenghtSixPassword() {
		log.info("Step 1: Hover register link");
		homePage.hoverLogin();
		log.info("Step 2: Click register link");
		sleepInSecond(1);
		homePage.clickToRegister();
		log.info("Step 2: Click open Register page");
		registerPage = new RegisterObject(driver);

		log.info("Step 3: Sendkey Lastname");
		registerPage.enterToLastnameTextbox("Test");

		log.info("Step 4: Sendkey Firstname");
		registerPage.enterToFirstnameTextbox("Test");

		String phone = "0344969361";
		log.info("Step 5: Sendkey phone");
		registerPage.enterToPhoneTextbox(phone);

		log.info("Step 5: Sendkey email : " + email);
		registerPage.enterToEmailTextbox(email);

		String pass = "123456";
		log.info("Step 5: Sendkey pass : " + pass);
		registerPage.enterToPasswordTextbox(pass);
		
		sleepInSecond(1);
		log.info("Step 6: Click button Register");
		registerPage.clickToRegisterButton();
		log.info("Step 7: Validation Message");
		log.info("Step 7: Validation Message");
		accountPage = new AccountPage(driver);
		sleepInSecond(2);
		verifyTrue(accountPage.isDisplayedAccountPage());
	}

	public void sleepInSecond(long sleepInsecond) {
		try {
			Thread.sleep(sleepInsecond * 10000);
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

package pageObjects.maybistore;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.maybe.LoginPageUI;
import pageUIs.maybe.RegisterPageUI;

public class RegisterObject extends BasePage{
	private WebDriver driver;
	public RegisterObject(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}
	public void enterToLastnameTextbox(String lastName) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, RegisterPageUI.LAST_NAME_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.LAST_NAME_TEXTBOX, lastName);
	}
	public void enterToFirstnameTextbox(String firstName) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, RegisterPageUI.FIRST_NAME_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.FIRST_NAME_TEXTBOX, firstName);
	}
	public void enterToPhoneTextbox(String phone) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, RegisterPageUI.PHONE_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.PHONE_TEXTBOX, phone);
	}
	public void enterToEmailTextbox(String email) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, RegisterPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.EMAIL_TEXTBOX, email);
	}
	public void enterToPasswordTextbox(String pass) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, RegisterPageUI.PASS_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.PASS_TEXTBOX, pass);
	}
	public void clickToRegisterButton() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, RegisterPageUI.REGISTER_BUTTON);
		clickToElement(driver, RegisterPageUI.REGISTER_BUTTON);
	}
	public boolean isMessageErrorDisplayed() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, RegisterPageUI.ERROR_MESSAGE);
		return isElementDisplayed(driver, RegisterPageUI.ERROR_MESSAGE);
	}
	public String isMessageError() {
		waitForElementVisible(driver, RegisterPageUI.ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.ERROR_MESSAGE);
	}
	public boolean isDisplayErroLastName() {
		// TODO Auto-generated method stub
		return isElementDisplayed(driver, RegisterPageUI.LAST_NAME_TEXTBOX);
	}

	public boolean isDisplayErrorFristName() { 
		// TODO Auto-generated method stub
		return isElementDisplayed(driver, RegisterPageUI.FIRST_NAME_TEXTBOX);
	}
	public boolean isDisplayErrorPhone() {
		// TODO Auto-generated method stub
		return isElementDisplayed(driver, RegisterPageUI.PHONE_TEXTBOX);
	}
	public boolean isDisplayErrorEmail() {
		// TODO Auto-generated method stub
		return isElementDisplayed(driver, RegisterPageUI.EMAIL_TEXTBOX);
	}
	public boolean isDisplayErrorPass() {
		// TODO Auto-generated method stub
		return isElementDisplayed(driver, RegisterPageUI.PASS_TEXTBOX);
	}

}

package pageObjects.maybistore;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.maybe.LoginPageUI;
import pageUIs.maybe.RegisterPageUI;

public class LoginObject extends BasePage{
	private WebDriver driver;	
	public LoginObject(WebDriver driver) {
		this.driver = driver;
	}
	public void enterToEmailTextbox(String email) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, LoginPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.EMAIL_TEXTBOX, email);
	}
	public void enterToPasswordTextbox(String pass) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, LoginPageUI.PASS_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.PASS_TEXTBOX, pass);
	}
	public void clickToLoginButton() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
	}
	public String isDisplayErrorEmail() {
		return getElementText(driver, LoginPageUI.EMAIL_TEXTBOX);
	}
	public boolean isDisplayErroPass() {
        return isElementDisplayed(driver, LoginPageUI.PASS_TEXTBOX);
    }
	
	public boolean isDisplayError() {
		waitForElementVisible(driver, LoginPageUI.ERROR_MESSAGE_SPAN);
		return isElementDisplayed(driver, LoginPageUI.ERROR_MESSAGE_SPAN);
	}
	public String isDisplayMessageError() {
		return getElementText(driver, LoginPageUI.ERROR_MESSAGE_SPAN);
	}
	public void clickToCartLink() {
		waitForElementVisible(driver, LoginPageUI.CART_LINK);
		clickToElement(driver, LoginPageUI.CART_LINK);
	}

 
	public void enterEmailPass(String email, String pass) {
		enterToEmailTextbox(email);
		enterToPasswordTextbox(pass);	
		if(driver.toString().contains("chrome"))
		{	
			sleepInSecond(1);
		}
	}
	
	public void login(String email, String pass) {
		enterToEmailTextbox(email);
		enterToPasswordTextbox(pass);	
		
	}
	

}

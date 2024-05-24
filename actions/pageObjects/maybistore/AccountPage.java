package pageObjects.maybistore;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.maybe.AccountUI;
import pageUIs.maybe.CartUI;
import pageUIs.maybe.CheckountPageUI;

public class AccountPage extends BasePage{
	private WebDriver driver;
	public AccountPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}
	public boolean isDisplayedAccountPage() {
		waitForElementVisible(driver, AccountUI.ACOUNT_DISPLAY);
		return isElementDisplayed(driver, AccountUI.ACOUNT_DISPLAY);
	}


	
	
}

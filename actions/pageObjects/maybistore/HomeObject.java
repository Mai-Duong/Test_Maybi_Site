package pageObjects.maybistore;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.maybe.HomePageUI;

public class HomeObject extends BasePage{
	private WebDriver driver;
	public HomeObject(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}
	public void hoverLogin() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, HomePageUI.SPAN_LOGIN_LINK);
		hoverToElement(driver, HomePageUI.SPAN_LOGIN_LINK);
	}
	public void clickToRegister() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, HomePageUI.REGISTER_LINK);
//		clickToElement(driver, HomePageUI.REGISTER_LINK);
		clickToElementByJS(driver, HomePageUI.REGISTER_LINK);
		
	}
	public void clickToLoginLink() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, HomePageUI.LOGIN_LINK);
		clickToElement(driver, HomePageUI.LOGIN_LINK);
	}
	public void clickSeachIcon() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, HomePageUI.SEARCH_LINK);
		clickToElement(driver, HomePageUI.SEARCH_LINK);
	}
	public void clickDamPageLink() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, HomePageUI.DAM_LINK);
		clickToElement(driver, HomePageUI.DAM_LINK);
	}
	public void clickAoSoMiPageLink() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, HomePageUI.AOSOMI_LINK);
		clickToElement(driver, HomePageUI.AOSOMI_LINK);
	}
	public void clickDoBoiPageLink() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, HomePageUI.DOBOI_LINK);
		clickToElement(driver, HomePageUI.DOBOI_LINK);
	}
	public void clickDoNguPageLink() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, HomePageUI.DONGU_LINK);
		clickToElement(driver, HomePageUI.DONGU_LINK);
	}
	public void sendKeyToSearch(String searchInput) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, HomePageUI.SEARCH_INPUT);
		sendkeyToElement(driver, HomePageUI.SEARCH_INPUT,searchInput);
	}
	public void clickIconEnterSearch() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, HomePageUI.ICON_SUBMIT_SEARCH);
		clickToElement(driver, HomePageUI.ICON_SUBMIT_SEARCH);
	}
	public boolean isDisplayErrorSearch() {
		// TODO Auto-generated method stub
		return isElementDisplayed(driver, HomePageUI.SEARCH_INPUT);
	}
	public void clickIconBoxSearch() {
		// TODO Auto-generated method stub 
		waitForElementVisible(driver, HomePageUI.BOX_SEARCH);
		clickToElement(driver, HomePageUI.BOX_SEARCH);
	}
	public void clickCart() {
		waitForElementVisible(driver, HomePageUI.CART_LINK);
		clickToElement(driver, HomePageUI.CART_LINK);
	}	
	public void openLoginPage() {
		hoverLogin();
		clickToLoginLink();
	}	
	
	public void clickItemLink(String item) {
		clickSeachIcon();
		waitForElementVisible(driver,String.format(HomePageUI.LINK_ITEMS_SEARCH, item));
		clickToElement(driver,String.format(HomePageUI.LINK_ITEMS_SEARCH, item));
	}

	public void seacrhProduct(String nameProductSearch) {
		clickSeachIcon();
		sendKeyToSearch(nameProductSearch);
		clickIconBoxSearch();
		clickIconEnterSearch();
		sleepInSecond(1);
	}
}

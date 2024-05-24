package pageObjects.maybistore;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.maybe.AccountUI;
import pageUIs.maybe.CartUI;
import pageUIs.maybe.DetailPageUI;
import pageUIs.maybe.HomePageUI;

public class DetailProductObject extends BasePage{
	private WebDriver driver;
	public DetailProductObject(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}

	public void clickColorRadio() {
		scrollToElement(driver,DetailPageUI.COLOR_RADIO);
		clickToElement(driver, DetailPageUI.COLOR_RADIO);
	}
	public void clickQuantity() {
		// TODO Auto-generated method stub
		scrollToElement(driver, DetailPageUI.QUANTITY_BUTTON);
		clickToElement(driver, DetailPageUI.QUANTITY_BUTTON);
	}
	public void clickAddCartButton() {
		// TODO Auto-generated method stub
//		waitForElementVisible(driver, DetailPageUI.ADD_CART_BUTTON);
		scrollElement(driver, DetailPageUI.COLOR_RADIO);
//		clickToElement(driver, DetailPageUI.ADD_CART_BUTTON);
		waitForElementVisible(driver, DetailPageUI.ADD_CART_BUTTON);
		clickToElementByJS(driver, DetailPageUI.ADD_CART_BUTTON);
	}
	public String nameProduct() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, DetailPageUI.NAME_PRODUCT);
		return getElementText(driver, DetailPageUI.NAME_PRODUCT);
	}
	public String colorProduct() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, DetailPageUI.COLOR_PRODUCT);
		return getElementText(driver, DetailPageUI.COLOR_PRODUCT);
	}
	public String price() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, DetailPageUI.PRICE_PRODUCT);
		return getElementText(driver, DetailPageUI.PRICE_PRODUCT);
	}
	public String quantity() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, DetailPageUI.QUANTITY_PRODUCT);
		return getElementText(driver, DetailPageUI.QUANTITY_PRODUCT);
	}
	public void clickCloseAddCart() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, DetailPageUI.CLOSE_BOX_ADD_CART);
		clickToElement(driver, DetailPageUI.CLOSE_BOX_ADD_CART);
	}
	public void clickShowCart() {
		waitForElementVisible(driver, DetailPageUI.SHOWCART_BUTTON);
//		clickToElement(driver, DetailPageUI.SHOWCART_BUTTON);
		clickToElementByJS(driver, DetailPageUI.SHOWCART_BUTTON);
	}

	public void clickSize() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver,DetailPageUI.SIZE_RADIO);
		clickToElement(driver, DetailPageUI.SIZE_RADIO);
	}


	public void clickAddQuantity() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, DetailPageUI.INCREASE_QUANTITY);
		clickToElement(driver, DetailPageUI.INCREASE_QUANTITY);
	}

	public void clickBuyNow() {
//		waitForElementVisible(driver, DetailPageUI.BUY_NOW_LINK);
		scrollElement(driver, DetailPageUI.COLOR_RADIO);
//		clickToElement(driver, DetailPageUI.BUY_NOW_LINK);
//		sleepInSecond(1);
		waitForElementVisible(driver, DetailPageUI.BUY_NOW_LINK);
		clickToElement(driver, DetailPageUI.BUY_NOW_LINK);
				
	}
}

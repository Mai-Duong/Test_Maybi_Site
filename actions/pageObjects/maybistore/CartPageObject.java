package pageObjects.maybistore;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.maybe.AccountUI;
import pageUIs.maybe.CartUI;
import pageUIs.maybe.DetailPageUI;

public class CartPageObject extends BasePage{
	private WebDriver driver;
	public CartPageObject(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}
	public String nameProduct() {
		// TODO Auto-generated method stub
		waitForElementInvisible(driver, CartUI.NAME_FIRST_PRODUCT);
		return getElementText(driver, CartUI.NAME_FIRST_PRODUCT);
	}
	public boolean isDisplayEmptyCart() {
		waitForElementVisible(driver, CartUI.EMPTY_CART_TEXT);
		return isElementDisplayed(driver, CartUI.EMPTY_CART_TEXT);
	}
	public void clickDeleteProduct() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, CartUI.DEL_PRODUCT_ICON);
		clickToElement(driver, CartUI.DEL_PRODUCT_ICON);
	}

	public String firstProductAddToCart() {
		waitForElementVisible(driver, DetailPageUI.NAME_FIRST_PRODUCT);
		return getElementText(driver, DetailPageUI.NAME_FIRST_PRODUCT);
	}


	public String priceFirstProductAddToCart() {
		waitForElementVisible(driver, DetailPageUI.PRICE_FIRST_PRODUCT);
		return getElementText(driver, DetailPageUI.PRICE_FIRST_PRODUCT);
	}

	public String quantityFirstProductAddToCart() {
		waitForElementVisible(driver, DetailPageUI.QUANTITY_FIRST_PRODUCT);
		return getAttributeValue(driver, DetailPageUI.QUANTITY_FIRST_PRODUCT,"value");
	}

	public String secondProductAddToCart() {
		waitForElementVisible(driver, DetailPageUI.NAME_SECOND_PRODUCT);
		return getElementText(driver, DetailPageUI.NAME_SECOND_PRODUCT);
	}

	public String priceSecondProductAddToCart() {
		waitForElementVisible(driver, DetailPageUI.PRICE_SECOND_PRODUCT);
		return getElementText(driver, DetailPageUI.PRICE_SECOND_PRODUCT);
	}

	public String quantitySecondProductAddToCart() {
		waitForElementVisible(driver, DetailPageUI.QUANTITY_SECOND_PRODUCT);
		return getAttributeValue(driver, DetailPageUI.QUANTITY_SECOND_PRODUCT,"value");
	}
	public void clickIncreaseQuantity() {
		waitForElementVisible(driver, CartUI.INCREASE_QUANTITY);
		clickToElement(driver, CartUI.INCREASE_QUANTITY);
	}
	public void DecreaseQuantity() {
		waitForElementVisible(driver, CartUI.DECREASE_QUANTITY);
		clickToElement(driver, CartUI.DECREASE_QUANTITY);
	}
	public void sendkeyQuantity(String quantity) {
		waitForElementVisible(driver, CartUI.SENDKEY_QUANTITY);		
		sendkeyToElement(driver, CartUI.SENDKEY_QUANTITY,quantity);
	}
	public void clickspace() {
		waitForElementVisible(driver, CartUI.CLICK_SPACE);		
		clickToElement(driver, CartUI.CLICK_SPACE);
	}
	public void clickOrderButton() {
		waitForElementVisible(driver, CartUI.ORDER_BUTTON);
		clickToElement(driver, CartUI.ORDER_BUTTON);
	}
	
}

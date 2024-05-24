package pageObjects.maybistore;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.maybe.CartUI;
import pageUIs.maybe.CheckountPageUI;

public class CheckountObject extends BasePage {
	private WebDriver driver;

	public CheckountObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isDisplayPage() {
		waitForElementVisible(driver, CheckountPageUI.TITLE_PAGE_CHECKOUNT);
		return isElementDisplayed(driver, CheckountPageUI.TITLE_PAGE_CHECKOUNT);
	}

	public String isDisPlayErrorName() {
		waitForElementVisible(driver, CheckountPageUI.ERRO_FULL_NAME);
		return getElementText(driver, CheckountPageUI.ERRO_FULL_NAME);
	}

	public String isDisPlayErrorEmail() {
		waitForElementVisible(driver, CheckountPageUI.ERROR_EMAIL);
		return getElementText(driver, CheckountPageUI.ERROR_EMAIL);
	}

	public String isDisPlayErrorPhone() {
		waitForElementVisible(driver, CheckountPageUI.ERROR_PHONE);
		return getElementText(driver, CheckountPageUI.ERROR_PHONE);
	}

	public String isDisPlayErrorAddress() {
		waitForElementVisible(driver, CheckountPageUI.ERROR_ADDRESS);
		return getElementText(driver, CheckountPageUI.ERROR_ADDRESS);
	}

	public String isDisPlayErrorCity() {
		waitForElementVisible(driver, CheckountPageUI.ERROR_CITY);
		return getElementText(driver, CheckountPageUI.ERROR_CITY);
	}

	public String isDisPlayErrorDistrict() {
		waitForElementVisible(driver, CheckountPageUI.ERROR_DISTRICT);
		return getElementText(driver, CheckountPageUI.ERROR_DISTRICT);
	}

	public String isDisPlayErrorWard() {
		waitForElementVisible(driver, CheckountPageUI.ERROR_WARD);
		return getElementText(driver, CheckountPageUI.ERROR_WARD);
	}

	public void clickSubmitShopping() {
		scrollToElement(driver, CheckountPageUI.SUBMIT_SHOPPING_BUTTON);
		clickToElement(driver, CheckountPageUI.SUBMIT_SHOPPING_BUTTON);
	}

	public void sendkeyFullName(String fullName) {
		// TODO Auto-generated method stub
		scrollToElement(driver, CheckountPageUI.FULL_NAME_TEXTBOX);
		sendkeyToElement(driver, CheckountPageUI.FULL_NAME_TEXTBOX, fullName);
	}

	public void sendkeyFEmail(String email) {
		// TODO Auto-generated method stub
		scrollToElement(driver, CheckountPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, CheckountPageUI.EMAIL_TEXTBOX, email);
	}

	public void sendkeyPhone(String phone) {
		// TODO Auto-generated method stub
		scrollToElement(driver, CheckountPageUI.PHONE_TEXTBOX);
		sendkeyToElement(driver, CheckountPageUI.PHONE_TEXTBOX, phone);
	}

	public void sendkeyAddress(String address) {
		// TODO Auto-generated method stub
		scrollToElement(driver, CheckountPageUI.ADDRESS_TEXTBOX);
		sendkeyToElement(driver, CheckountPageUI.ADDRESS_TEXTBOX, address);
	}

	public void selectByVisibleTextCity(String city) {
		waitForElementVisible(driver, CheckountPageUI.CITY_COMBOBOX);
		selectDropdownByText(driver, CheckountPageUI.CITY_COMBOBOX,city);
	}

	public void selectByVisibleTextDistrict(String district) {
		waitForElementVisible(driver, CheckountPageUI.DISTRICT_COMBOBOX);
		selectDropdownByText(driver, CheckountPageUI.DISTRICT_COMBOBOX,district);
	}

	public void selectByVisibleTextWard(String ward) {
		waitForElementVisible(driver, CheckountPageUI.WARD_COMBOBOX);
		selectDropdownByText(driver, CheckountPageUI.WARD_COMBOBOX,ward);
	}

	public boolean isSuccessfull() {
		waitForElementVisible(driver, CheckountPageUI.SHOPING_SUCCESS);
		return isElementDisplayed(driver, CheckountPageUI.SHOPING_SUCCESS);
	}

	public boolean isMultipleCity() {
		waitForElementVisible(driver, CheckountPageUI.CITY_COMBOBOX);
		return isDropdownMultiple(driver, CheckountPageUI.CITY_COMBOBOX);
	}
	public String selectSelectedItemDropdownCity() {
		waitForElementVisible(driver, CheckountPageUI.CITY_COMBOBOX);
		return selectSelectedItemDropdown(driver, CheckountPageUI.CITY_COMBOBOX);
	}
	
	public boolean isMultipleDistrict() {
		waitForElementVisible(driver, CheckountPageUI.DISTRICT_COMBOBOX);
		return isDropdownMultiple(driver, CheckountPageUI.DISTRICT_COMBOBOX);
	}
	public String selectSelectedItemDropdownDistrict() {
		waitForElementVisible(driver, CheckountPageUI.DISTRICT_COMBOBOX);
		return selectSelectedItemDropdown(driver, CheckountPageUI.DISTRICT_COMBOBOX);
	}
	
	public boolean isMultipleWard() {
		waitForElementVisible(driver, CheckountPageUI.WARD_COMBOBOX);
		return isDropdownMultiple(driver, CheckountPageUI.WARD_COMBOBOX);
	}
	public String selectSelectedItemDropdownWard() {
		waitForElementVisible(driver, CheckountPageUI.WARD_COMBOBOX);
		return selectSelectedItemDropdown(driver, CheckountPageUI.WARD_COMBOBOX);
	}
	

	public int getElementSizeCity() {
		waitForElementVisible(driver, CheckountPageUI.CITY_COMBOBOX);
		return getDropdownSize(driver, CheckountPageUI.CITY_COMBOBOX);
	}

	public int getElementSizeDistrict() {
		waitForElementVisible(driver, CheckountPageUI.DISTRICT_COMBOBOX);
		return getDropdownSize(driver, CheckountPageUI.DISTRICT_COMBOBOX);
	}

	public int getElementSizeWard() {
		waitForElementVisible(driver, CheckountPageUI.WARD_COMBOBOX);
		return getDropdownSize(driver, CheckountPageUI.WARD_COMBOBOX);
	}

}

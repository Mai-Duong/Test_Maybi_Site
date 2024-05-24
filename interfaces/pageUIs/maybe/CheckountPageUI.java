package pageUIs.maybe;

import org.openqa.selenium.By;

public class CheckountPageUI {

	public static final String TITLE_PAGE_CHECKOUNT = "(//h1[@class='logo-text'])[2]";
	
	
	public static final String FULL_NAME_TEXTBOX = "//input[@id='billing_address_full_name']";
	public static final String ERRO_FULL_NAME = "(//p[@class='field-message field-message-error'])[1]";

	public static final String EMAIL_TEXTBOX = "//input[@id='checkout_user_email']";
	public static final String ERROR_EMAIL = "//input[@name='checkout_user[email]']/ancestor::div/following-sibling::p";

	public static final String PHONE_TEXTBOX = "//input[@id='billing_address_phone']";
	public static final String ERROR_PHONE = "//input[@name='billing_address[phone]']/ancestor::div/following-sibling::p";

	public static final String ADDRESS_TEXTBOX = "//input[@id='billing_address_address1']";
	public static final String ERROR_ADDRESS = "//input[@name='billing_address[address1]']/ancestor::div/following-sibling::p";

	public static final String CITY_COMBOBOX = "//select[@id='customer_shipping_province']";
	public static final String ERROR_CITY = "//select[@id='customer_shipping_province']/ancestor::div/following-sibling::p";

	public static final String DISTRICT_COMBOBOX = "//select[@id='customer_shipping_district']";
	public static final String ERROR_DISTRICT = "//select[@id='customer_shipping_district']/ancestor::div/following-sibling::p";

	public static final String WARD_COMBOBOX = "//select[@id='customer_shipping_ward']";
	public static final String ERROR_WARD = "//select[@id='customer_shipping_ward']/ancestor::div/following-sibling::p";


	public static final String SUBMIT_SHOPPING_BUTTON = "//button[@class='step-footer-continue-btn btn']";


	public static final String SHOPING_SUCCESS = "//h2[@class='os-header-title']";

}

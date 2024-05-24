package pageUIs.maybe;

import org.openqa.selenium.By;

public class HomePageUI {

	public static final String SPAN_LOGIN_LINK = "//span[contains(text(),'Đăng nhập')]";
	public static final String REGISTER_LINK = "(//a[@title='Đăng ký'])[1]";
	public static final String LOGIN_LINK = "(//a[@title='Đăng nhập'])[1]";
	public static final String SEARCH_LINK = "//span[text()='Tìm kiếm']";
	
	public static final String DAM_LINK = "//a[@id='filter-search-dam']";
	public static final String AOSOMI_LINK = "//a[@id='filter-search-ao-so-mi']";
	public static final String DOBOI_LINK = "//a[@id='filter-search-do-boi']";
	public static final String DONGU_LINK = "//a[@id='filter-search-do-ngu']";
	
	
	public static final String LINK_ITEMS_SEARCH = "//a[@id='filter-search-%s']";
	
	
	
	public static final String SEARCH_INPUT = "//input[@class='input-group-field auto-search form-control ']";
	public static final String ICON_SUBMIT_SEARCH = "//button[@class='btn text-white icon-fallback-text h-100']";
	public static final String BOX_SEARCH = "//span[@class='ega-sm-nav tw-px-4 tw-py-1.5 tw-rounded-3xl tw-cursor-pointer  ega-sm-is-active']";
	public static final String CART_LINK = "//span[text()='Giỏ hàng']";
	

}

package pageObjects.maybistore;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.maybe.SearchPageUI;

public class SearchObject extends BasePage{
	private WebDriver driver;	
	public SearchObject(WebDriver driver) {
		this.driver = driver;
	}
	public boolean isDisplayPageSearch() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, SearchPageUI.LINK_PAGE_SEARCH);
		return isElementDisplayed(driver, SearchPageUI.LINK_PAGE_SEARCH);
	}
	
	public String isDisplayTextPage() {
		return getElementText(driver, SearchPageUI.LINK_PAGE_SEARCH);
	}
	public boolean isDisplayResultSearch() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, SearchPageUI.RESULT_SEARCH_SPAN);
		return isElementDisplayed(driver, SearchPageUI.RESULT_SEARCH_SPAN);
	}
	public String isDisplayResultTextSearch() {
		// TODO Auto-generated method stub
		return getElementText(driver, SearchPageUI.RESULT_SEARCH_SPAN);
	}



 
}

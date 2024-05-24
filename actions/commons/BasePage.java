package commons;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	private Alert alert;
	private WebDriverWait explicitWait;
	Duration timeout = Duration.ofSeconds(30);
	private Select select;
	private Actions action;

	private JavascriptExecutor jsExecutor;

	public static BasePage getBasePage() {
		return new BasePage();
	}

	public void openPageURL(WebDriver driver, String url) {
		driver.get(url);
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getPageURL(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}

	public Alert waitForAlertPresent(WebDriver driver) {

		explicitWait = new WebDriverWait(driver, timeout);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	public void acceptAlert(WebDriver driver) {
		alert = waitForAlertPresent(driver);
		alert.accept();
	}

	public void canceltAlert(WebDriver driver) {
		alert = waitForAlertPresent(driver);
		alert.dismiss();
	}

	public void sendKeyToAlert(WebDriver driver, String value) {
		alert = waitForAlertPresent(driver);
		alert.sendKeys(value);
	}

	public String getAlertText(WebDriver driver) {
		alert = waitForAlertPresent(driver);
		return alert.getText();
	}
	public void sleepInSecond(long timeÍnecond) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(timeÍnecond * 10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void scrollElement(WebDriver driver, String locator) {
		WebElement element = driver.findElement(getByXpath(locator));
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public By getByXpath(String locator) {
		return By.xpath(locator);
	}

	public WebElement getElement(WebDriver driver, String locator) {
		return driver.findElement(getByXpath(locator));
	}

	public void clickToElement(WebDriver driver, String locator) {
		getElement(driver, locator).click();
	}

	public List<WebElement> getElenments(WebDriver driver, String locator) {
		return driver.findElements(getByXpath(locator));
	}

	public String getDynamicLocator(String locator, String... params) {
		return String.format(locator, (Object[]) params);
	}

	public void clickToElement(WebDriver driver, String locator, String... params) {
		getElement(driver, getDynamicLocator(locator, params)).click();
	}

//	public void clickToElement(WebDriver driver, String locator) {
//		getElement(driver,locator).click();
//	}

	public void sendkeyToElement(WebDriver driver, String locator, String value) {
		getElement(driver, locator).clear();
		getElement(driver, locator).sendKeys(value);
	}

	public int getElementSize(WebDriver driver, String locator) {
		// so nhieu
		return getElenments(driver, locator).size();
	}

	public void selectDropdownByText(WebDriver driver, String locator, String itemText) {
		select = new Select(getElement(driver, locator));
		select.selectByVisibleText(itemText);
	}

	public String selectSelectedItemDropdown(WebDriver driver, String locator) {
		select = new Select(getElement(driver, locator));
		return select.getFirstSelectedOption().getText();
	}

	public int getDropdownSize(WebDriver driver, String locator) {
	    // Tạo một đối tượng Select với dropdown được định vị bằng locator
	     select = new Select(getElement(driver, locator));
	    
	    // Lấy danh sách các lựa chọn
	    List<WebElement> options = select.getOptions();
	    
	    // Trả về số lượng lựa chọn
	    return options.size();
	}
	
	public boolean isDropdownMultiple(WebDriver driver, String locator) {
		select = new Select(getElement(driver, locator));
		return select.isMultiple();
	}


	public String getAttributeValue(WebDriver driver, String locator, String valueName) {
		return getElement(driver, locator).getAttribute(valueName);
	}


	public String getElementText(WebDriver driver, String locator) {
		return getElement(driver, locator).getText();
	}


	public void checkToCheckboxOrRadio(WebDriver driver, String locator) {
		if (!isElementSelected(driver, locator)) {
			getElement(driver, locator).click();
		}
	}

	public void unCheckToCheckboxOrRadio(WebDriver driver, String locator) {
		if (isElementSelected(driver, locator)) {
			getElement(driver, locator).click();
		}
	}

	public boolean isElementDisplayed(WebDriver driver, String locator) {
		try {
			// có trong UI + trong DOM
			// không trong UI + có trong DOM
			return getElement(driver, locator).isDisplayed();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			// khônh trong UI + không trong DOM
			return false;
		}
	}



	public boolean isElementEnabled(WebDriver driver, String locator) {
		return getElement(driver, locator).isEnabled();
	}

	public boolean isElementSelected(WebDriver driver, String locator) {
		return getElement(driver, locator).isSelected();
	}

	public WebDriver switchToIframeByElement(WebDriver driver, String locator) {
		return driver.switchTo().frame(getElement(driver, locator));
	}

	public WebDriver switchToDefaultContent(WebDriver driver, String locator) {
		return driver.switchTo().defaultContent();
	}

	public void hoverToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		// peform la bat buoc
		action.moveToElement(getElement(driver, locator)).perform();
	}

	public void doubleToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		// peform la bat buoc
		action.doubleClick(getElement(driver, locator)).perform();
	}

	public void rightClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		// peform la bat buoc
		action.contextClick(getElement(driver, locator)).perform();
	}


	public void clickToElementByJS(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getElement(driver, locator));
	}

//	public void scrollToElement(WebDriver driver, String locator) {
//		jsExecutor = (JavascriptExecutor) driver;
//		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(driver, locator));
//	}
	public void scrollToElement(WebDriver driver, String locator) {
	    WebElement element = getElement(driver, locator);
	    int yOffset = element.getLocation().getY();
	    int windowInnerHeight = Integer.parseInt(((JavascriptExecutor) driver).executeScript("return window.innerHeight").toString());
	    int currentScrollY = Integer.parseInt(((JavascriptExecutor) driver).executeScript("return window.scrollY").toString());
	    int scrollPosition = yOffset + currentScrollY - windowInnerHeight / 2;
	    ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, " + scrollPosition + ");");
	}

	public String getElementValidationMessage(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(driver, locator));
	}



	public void waitForElementVisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, timeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator)));

	}
	
	public void scrollAndClickToElementByJS(WebDriver driver, String locator) {
	    WebElement element = getElement(driver, locator);
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	    try {
	        Thread.sleep(500); // Chờ 0.5 giây 
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	    element.click();
	}



	public void waitForElementVisible(WebDriver driver, String locator, String... params) {
		explicitWait = new WebDriverWait(driver, timeout);
		explicitWait
				.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(getDynamicLocator(locator, params))));

	}

	public void waitForAllElementVisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, timeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(locator)));

	}

	public void waitForElementClickable(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, timeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(locator)));

	}

	public void waitForElementClickable(WebDriver driver, String locator, String... params) {
		explicitWait = new WebDriverWait(driver, timeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(getDynamicLocator(locator, params))));

	}

	public void waitForElementInvisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, timeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator)));

	}

	public void waitForElementInvisible(WebDriver driver, String locator, String... params) {
		explicitWait = new WebDriverWait(driver, timeout);
		explicitWait
				.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(getDynamicLocator(locator, params))));

	}

}

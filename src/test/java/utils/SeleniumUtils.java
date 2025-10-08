package utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Utility class providing reusable Selenium actions with explicit waits.
 */
public class SeleniumUtils {

	// ---------- BASIC WAIT HANDLERS ----------
	private static WebDriverWait getWait(WebDriver driver, int timeout) {
		return new WebDriverWait(driver, Duration.ofSeconds(timeout));
	}

	public static WebElement waitForVisibility(WebDriver driver, By locator, int timeout) {
		return getWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public static WebElement waitForClickable(WebDriver driver, By locator, int timeout) {
		return getWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(locator));
	}

	// ---------- CORE ACTIONS ----------
	public static void click(WebDriver driver, By locator, int timeout) {
		waitForClickable(driver, locator, timeout).click();
	}

	public static void type(WebDriver driver, By locator, String text, int timeout) {
		WebElement element = waitForVisibility(driver, locator, timeout);
		element.clear();
		element.sendKeys(text);
	}

	public static String getText(WebDriver driver, By locator, int timeout) {
		return waitForVisibility(driver, locator, timeout).getText();
	}

	public static boolean isDisplayed(WebDriver driver, By locator, int timeout) {
		try {
			return waitForVisibility(driver, locator, timeout).isDisplayed();
		} catch (TimeoutException e) {
			return false;
		}
	}

	// ---------- DROPDOWN HANDLERS ----------
	public static void selectByVisibleText(WebDriver driver, By locator, String visibleText, int timeout) {
		Select select = new Select(waitForVisibility(driver, locator, timeout));
		select.selectByVisibleText(visibleText);
	}

	public static void selectByValue(WebDriver driver, By locator, String value, int timeout) {
		Select select = new Select(waitForVisibility(driver, locator, timeout));
		select.selectByValue(value);
	}

	public static void selectByIndex(WebDriver driver, By locator, int index, int timeout) {
		Select select = new Select(waitForVisibility(driver, locator, timeout));
		select.selectByIndex(index);
	}

	// ---------- ADVANCED ACTIONS ----------
	public static void hoverOverElement(WebDriver driver, By locator, int timeout) {
		WebElement element = waitForVisibility(driver, locator, timeout);
		new Actions(driver).moveToElement(element).perform();
	}

	public static void doubleClick(WebDriver driver, By locator, int timeout) {
		WebElement element = waitForClickable(driver, locator, timeout);
		new Actions(driver).doubleClick(element).perform();
	}

	public static void rightClick(WebDriver driver, By locator, int timeout) {
		WebElement element = waitForClickable(driver, locator, timeout);
		new Actions(driver).contextClick(element).perform();
	}

	public static void dragAndDrop(WebDriver driver, By sourceLocator, By targetLocator, int timeout) {
		WebElement source = waitForVisibility(driver, sourceLocator, timeout);
		WebElement target = waitForVisibility(driver, targetLocator, timeout);
		new Actions(driver).dragAndDrop(source, target).perform();
	}

	// ---------- ALERT HANDLERS ----------
	public static void acceptAlert(WebDriver driver, int timeout) {
		getWait(driver, timeout).until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
	}

	public static void dismissAlert(WebDriver driver, int timeout) {
		getWait(driver, timeout).until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().dismiss();
	}

	public static String getAlertText(WebDriver driver, int timeout) {
		getWait(driver, timeout).until(ExpectedConditions.alertIsPresent());
		return driver.switchTo().alert().getText();
	}

	// ---------- FRAME HANDLERS ----------
	public static void switchToFrame(WebDriver driver, By locator, int timeout) {
		WebElement frameElement = waitForVisibility(driver, locator, timeout);
		driver.switchTo().frame(frameElement);
	}

	public static void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	// ---------- WINDOW HANDLERS ----------
	public static void switchToWindow(WebDriver driver, String partialTitle) {
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
			if (driver.getTitle().contains(partialTitle)) {
				break;
			}
		}
	}

	// ---------- MISC UTILS ----------
	public static void scrollToElement(WebDriver driver, By locator, int timeout) {
		WebElement element = waitForVisibility(driver, locator, timeout);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public static void scrollByPixels(WebDriver driver, int x, int y) {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(arguments[0], arguments[1]);", x, y);
	}

	public static List<WebElement> getElements(WebDriver driver, By locator, int timeout) {
		return getWait(driver, timeout).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	public static void clearText(WebDriver driver, By locator, int timeout) {
		WebElement element = waitForVisibility(driver, locator, timeout);
		element.clear();
	}
}

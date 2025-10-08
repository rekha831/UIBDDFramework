package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import config.ConfigManager;
import utils.SeleniumUtils;

/**
 * BasePage acts as a reusable parent for all page classes.
 * Delegates reusable UI interactions to SeleniumUtils.
 */
public abstract class BasePage {

    protected WebDriver driver;
    private static final int DEFAULT_TIMEOUT = ConfigManager.getInt("explicitWait");

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected void click(By locator) {
        SeleniumUtils.click(driver, locator, DEFAULT_TIMEOUT);
    }

    protected void type(By locator, String text) {
        SeleniumUtils.type(driver, locator, text, DEFAULT_TIMEOUT);
    }

    protected String getText(By locator) {
        return SeleniumUtils.getText(driver, locator, DEFAULT_TIMEOUT);
    }

    protected boolean isDisplayed(By locator) {
        return SeleniumUtils.isDisplayed(driver, locator, DEFAULT_TIMEOUT);
    }

    protected void waitForVisibility(By locator) {
        SeleniumUtils.waitForVisibility(driver, locator, DEFAULT_TIMEOUT);
    }

    protected void waitForClickable(By locator) {
        SeleniumUtils.waitForClickable(driver, locator, DEFAULT_TIMEOUT);
    }

    protected void selectByText(By locator, String value) {
        SeleniumUtils.selectByVisibleText(driver, locator, value, DEFAULT_TIMEOUT);
    }

    protected void scrollToElement(By locator) {
        SeleniumUtils.scrollToElement(driver, locator, DEFAULT_TIMEOUT);
    }

    protected void hover(By locator) {
        SeleniumUtils.hoverOverElement(driver, locator, DEFAULT_TIMEOUT);
    }

    protected void switchToFrame(By locator) {
        SeleniumUtils.switchToFrame(driver, locator, DEFAULT_TIMEOUT);
    }

    protected void acceptAlert() {
        SeleniumUtils.acceptAlert(driver, DEFAULT_TIMEOUT);
    }
}

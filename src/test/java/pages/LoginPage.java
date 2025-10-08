package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object representing the Login Page.
 * Extends BasePage to reuse common Selenium interactions.
 */
public class LoginPage extends BasePage {

    // Locators
    private final By txtUsername = By.id("username");
    private final By txtPassword = By.cssSelector("[type='password']");
    private final By btnLogin = By.id("signInBtn");
    private final By dashboardText = By.xpath("//a[text()='ProtoCommerce Home']");
    private final By errorText = By.xpath("//div[text()='Please enter valid mobile number']");

    // Constructor
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Page Actions
    public void enterUsername(String username) {
        type(txtUsername, username);
    }

    public void enterPassword(String password) {
        type(txtPassword, password);
    }

    public void clickLogin() {
        click(btnLogin);
    }

    public String getDashboardText() {
        return getText(dashboardText);
    }

    public String getLoginErrorText() {
        return getText(errorText);
    }

    public boolean isDashboardVisible() {
        return isDisplayed(dashboardText);
    }

    public boolean isErrorDisplayed() {
        return isDisplayed(errorText);
    }

    // Combined action (composite behavior)
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }
}

package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    private WebDriverWait wait;

    // Locators
    private By txtUsernameLocator = By.id("username");
    private By txtPasswordLocator = By.cssSelector("[type='password']");
    private By btnLoginLocator = By.id("signInBtn");
    private By loginButtonTextLocator = By.id("signInBtn");
    private By dashboardTextLocator = By.xpath("//a[text()='ProtoCommerce Home']");
    private By errorTextLocator = By.xpath("//div[text()='Please enter valid mobile number']");

    // Constructor
    public LoginPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // explicit wait
    }

    // Enter Username
    public void enterUsername(String username) {
        WebElement mobileInput = wait.until(ExpectedConditions.visibilityOfElementLocated(txtUsernameLocator));
        mobileInput.clear();
        mobileInput.sendKeys(username);
    }

    // Enter Password
    public void enterPassword(String password) {
        WebElement passInput = wait.until(ExpectedConditions.visibilityOfElementLocated(txtPasswordLocator));
        passInput.clear();
        passInput.sendKeys(password);
    }

    // Click Login Button
    public void clickLogin() {
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(btnLoginLocator));
        loginBtn.click();
    }

    // Click "Login" button text (alternative)
    public void clickLoginBtnText() {
        WebElement loginBtnText = wait.until(ExpectedConditions.elementToBeClickable(loginButtonTextLocator));
        loginBtnText.click();
    }

    // Get Dashboard Text
    public String getDashboardText() {
        WebElement dashText = wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardTextLocator));
        return dashText.getText();
    }

    // Get Login Error Text
    public String getLoginErrorText() {
        WebElement errText = wait.until(ExpectedConditions.visibilityOfElementLocated(errorTextLocator));
        return errText.getText();
    }
}

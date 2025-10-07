package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPage extends BasePage {


@FindBy(xpath = "//input[@data-testid='user-mobileno-input-box']")
private WebElement txtUsername;


@FindBy(xpath = "//input[@data-testid='password-input-box-cta']")
private WebElement txtPassword;


@FindBy(xpath = "//div[@data-testid='login-cta']")
private WebElement btnLogin;

@FindBy(xpath = "//div[text()='Login']")
private WebElement LogiinButton1;

@FindBy(xpath = "//div[text()='Hi jagadeesh']")
private WebElement dashboardText;

@FindBy(xpath = "//div[text()='Please enter valid mobile number']")
private WebElement errorText;


public LoginPage(WebDriver driver) {
super(driver);
}

/*
public void enterUsername(String user) {
//txtUsername.clear();
txtUsername.sendKeys(user);
}*/

public void enterUsername(String user) {
	//txtUsername.clear();
	//txtUsername.sendKeys(user);
	
WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
WebElement mobileInput = wait.until(
    ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@data-testid='user-mobileno-input-box']"))
);
mobileInput.sendKeys(user);

}
public void enterPassword(String pass) {
//txtPassword.clear();
txtPassword.sendKeys(pass);
}


public void clickLogin() {
btnLogin.click();
}

public void clickLoginBtn() {
	LogiinButton1.click();
}

public String getText() {
	return dashboardText.getText();
	
}

public String getloginErrorText() {
	return errorText.getText();
	
}
}

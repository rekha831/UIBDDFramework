package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPage extends BasePage {


@FindBy(xpath = "//input[@data-testid='user-mobileno-input-box']")
private WebElement txtUsername;


@FindBy(xpath = "//input[@data-testid='password-input-box-cta']")
private WebElement txtPassword;


@FindBy(xpath = "//div[@data-testid='login-cta']")
private WebElement btnLogin;

@FindBy(xpath = "(//div[@class='css-76zvg2 r-jwli3a r-ubezar'])[1]")
private WebElement LogiinButton1;

@FindBy(xpath = "//div[text()='Hi jagadeesh']")
private WebElement dashboardText;


public LoginPage(WebDriver driver) {
super(driver);
}


public void enterUsername(String user) {
//txtUsername.clear();
txtUsername.sendKeys(user);
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
}
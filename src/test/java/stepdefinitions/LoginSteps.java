package stepdefinitions;

import factory.DriverFactory;
import pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utils.JsonReader;

public class LoginSteps {
	private WebDriver driver;
	private LoginPage loginPage;

	@Given("I am on the login page")
	public void i_am_on_login_page() {
		driver = DriverFactory.getDriver();
		driver.get("https://www.spicejet.com/"); // replace with actual login URL
		loginPage = new LoginPage(driver);
		System.out.println("current TITLE is:"+driver.getTitle());
	}

	@When("I login with username {string} and password {string}")
	public void i_login_with_username_and_password(String username, String password) {
		loginPage.clickLoginBtn();
		loginPage.enterUsername(username);
		loginPage.enterPassword(password);
		loginPage.clickLogin();
	}

	
	@When("I login with {string} credentials")
	public void i_login_with_credentials(String userType) {
		String username = JsonReader.getUserData(userType, "username");
		String password = JsonReader.getUserData(userType, "password");
		System.out.println("user name is:"+username);
		System.out.println("passord is:"+password);
		driver.findElement(By.xpath("//div[text()='Login']")).click();
		driver.findElement(By.cssSelector("input[data-testid='user-mobileno-input-box']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@data-testid='password-input-box-cta']")).sendKeys(password);
		driver.findElement(By.xpath("//div[@data-testid='login-cta']")).click();
		/*loginPage.clickLoginBtn();
		loginPage.enterUsername(username);
		loginPage.enterPassword(password);
		loginPage.clickLogin();
		*/

	}
	
	@Then("I should see the {string} error on login page")
	public void i_should_see_the_error_on_login_page(String text) {
		String dashtext = JsonReader.getMessage(text);
		String getloginErrorText = loginPage.getloginErrorText();
		Assert.assertTrue(getloginErrorText.contains(getloginErrorText), "erroor is not displayed!");

	}


	@Then("I should see the {string} on dashboard")
	public void i_should_see_the_text_on_dashboard(String text) {
		String dashtext = JsonReader.getMessage(text);
		String dashboardText = loginPage.getText();
		Assert.assertTrue(dashboardText.contains(dashtext), "Dashboard page textnot displayed!");
	}
}

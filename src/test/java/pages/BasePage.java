package hooks;

import java.time.Duration;

import config.ConfigManager;
import factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.ScreenshotUtil;


public class Hooks {


	@Before
	public void beforeScenario() {
	    String browser = System.getProperty("browser", ConfigManager.get("browser"));
	    String implicitWaitDuration = System.getProperty("implicitWait", ConfigManager.get("implicitWait"));
	    System.out.println("wait is"+implicitWaitDuration);
	    DriverFactory.initDriver(browser, implicitWaitDuration);
	}

@After
public void afterScenario(Scenario scenario) {
    if (scenario.isFailed()) {
        ScreenshotUtil.captureScreenshot(scenario);
    }
    DriverFactory.quitDriver();
}
}

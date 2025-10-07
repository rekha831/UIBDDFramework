package factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;

/**
 * Factory class for managing WebDriver instances. Supports multi-threading and
 * CI environments (GitHub Actions).
 */
public class DriverFactory {

	private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

	public static void initDriver(String browser, String implicitWaitDuration) {
		if (tlDriver.get() != null) {
			return;
		}

		WebDriver driver;

		boolean isCI = System.getenv("CI") != null; // ✅ Detect GitHub Actions environment

		switch (browser.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOptions = new ChromeOptions();

			// Common options
			chromeOptions.addArguments("--disable-notifications");
			chromeOptions.addArguments("--window-size=1920,1080");

			ChromeOptions options = new ChromeOptions();
			if (System.getenv("CI") != null) { // GitHub Actions
				options.addArguments("--headless=new");
				options.addArguments("--disable-gpu");
				options.addArguments("--no-sandbox");
				options.addArguments("--disable-dev-shm-usage");
				options.addArguments("--window-size=1920,1080");
			}
			driver = new ChromeDriver(options);
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			if (isCI) {
				firefoxOptions.addArguments("--headless");
				firefoxOptions.addArguments("--width=1920");
				firefoxOptions.addArguments("--height=1080");
			}
			driver = new FirefoxDriver(firefoxOptions);
			break;

		default:
			throw new IllegalArgumentException("Unsupported browser: " + browser);
		}

		driver.manage().window().maximize();
		tlDriver.set(driver);

		// ✅ Use implicit wait for simple sync
		try {
			long waitTime = Long.parseLong(implicitWaitDuration);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(waitTime));
		} catch (NumberFormatException e) {
			System.err.println("⚠️ Invalid implicitWait value. Defaulting to 10 seconds.");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}

		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
	}

	public static WebDriver getDriver() {
		if (tlDriver.get() == null) {
			throw new IllegalStateException("❌ WebDriver not initialized. Call initDriver() first.");
		}
		return tlDriver.get();
	}

	public static void quitDriver() {
		WebDriver driver = tlDriver.get();
		if (driver != null) {
			driver.quit();
			tlDriver.remove();
		}
	}
}

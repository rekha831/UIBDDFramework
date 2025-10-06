package factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class DriverFactory {
	private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

	public static void initDriver(String browser, String implicitWaitDuration) {
		if (tlDriver.get() != null)
			return;

		switch (browser.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			ChromeOptions co = new ChromeOptions();
			if ("true".equals(System.getProperty("headless")))
				co.addArguments("--headless=new");
			tlDriver.set(new ChromeDriver(co));
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions fo = new FirefoxOptions();
			tlDriver.set(new FirefoxDriver(fo));
			break;
		default:
			throw new IllegalArgumentException("Unsupported browser: " + browser);
		}
		tlDriver.get().manage().window().maximize();

// ✅ Implicit Wait Defined Here
		tlDriver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(implicitWaitDuration)));
	}

	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	public static void quitDriver() {
		if (tlDriver.get() != null) {
			tlDriver.get().quit();
			tlDriver.remove();
		}
	}
}

package factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;
import java.util.UUID;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    /**
     * Initializes WebDriver instance for the given browser. Applies implicit wait
     * and maximizes the window.
     */
    public static void initDriver(String browser, String implicitWaitDuration) {
        if (tlDriver.get() != null) {
            return; // Avoid reinitialization for same thread
        }

        WebDriver driver;

        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();

                // ✅ Headless + CI-friendly options
                chromeOptions.addArguments("--headless=new");  // Selenium 4.36+ headless
                chromeOptions.addArguments("--no-sandbox");    // Linux CI requirement
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("--disable-gpu");
                chromeOptions.addArguments("--window-size=1920,1080");
                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.addArguments("--user-data-dir=/tmp/chrome-" + UUID.randomUUID());

                driver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();

                // Headless mode for CI
                firefoxOptions.addArguments("-headless");
                firefoxOptions.addArguments("--window-size=1920,1080");
                driver = new FirefoxDriver(firefoxOptions);
                break;

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        driver.manage().window().maximize();
        tlDriver.set(driver);

        // ✅ Implicit Wait
        try {
            long waitTime = Long.parseLong(implicitWaitDuration);
            tlDriver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(waitTime));
        } catch (NumberFormatException e) {
            tlDriver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
    }

    public static WebDriver getDriver() {
        if (tlDriver.get() == null) {
            throw new IllegalStateException("WebDriver not initialized. Call initDriver() first.");
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

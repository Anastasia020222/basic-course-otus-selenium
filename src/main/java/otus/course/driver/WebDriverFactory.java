package otus.course.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import otus.course.utils.TypeBrowser;

import java.util.List;

public class WebDriverFactory {

    private static final Logger logger = LogManager.getLogger(WebDriverFactory.class);

    public static WebDriver createWebDriver(TypeBrowser browser) {
        return createWebDriver(browser, List.of());
    }

    public static WebDriver createWebDriver(TypeBrowser browser, List<String> options) {
        switch (browser) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver((ChromeOptions) new BrowserOptions().getBrowserOptions(browser, options));
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver((FirefoxOptions) new BrowserOptions().getBrowserOptions(browser, options));
            default:
                logger.error("Указанный тип браузера не был найден. Возможные варианты: chrome, firefox");
                throw new RuntimeException("Указанный тип браузера не был найден.");
        }
    }
}

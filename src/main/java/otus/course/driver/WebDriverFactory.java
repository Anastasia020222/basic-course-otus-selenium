package otus.course.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import otus.course.utils.BrowserMode;
import otus.course.utils.TypeBrowser;

import static otus.course.utils.TypeBrowser.getTypeBrowser;

public class WebDriverFactory implements IWebDriver {

    private static final Logger logger = LogManager.getLogger(WebDriverFactory.class);

    @Override
    public WebDriver createWebDriver(BrowserMode browserMode) {
        TypeBrowser browser = getTypeBrowser();
        switch (browser) {
            case CHROME:
                return createChromeDriver(browserMode);
            case FIREFOX:
                return createFirefoxDriver(browserMode);
            default:
                logger.error("Указанный тип браузера не был найден. Возможные варианты: chrome, firefox");
                throw new RuntimeException("Указанный тип браузера не был найден.");
        }
    }

    private WebDriver createChromeDriver(BrowserMode browserMode) {
        WebDriverManager.chromedriver().setup();
        logger.info("Запуск браузера Chrome " + browserMode.getBrowserMode() + " режиме");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments(browserMode.getBrowserMode());
        return new ChromeDriver(options);
    }

    private WebDriver createFirefoxDriver(BrowserMode browserMode) {
        WebDriverManager.firefoxdriver().setup();
        logger.info("Запуск браузера FireFox " + browserMode.getBrowserMode() + " режиме");
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments(browserMode.getBrowserMode());
        return new FirefoxDriver();
    }
}

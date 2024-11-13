package otus.course.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import otus.course.utils.TypeBrowser;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

public class WebDriverFactory {

    private static final Logger logger = LogManager.getLogger(WebDriverFactory.class);
    private final static boolean remote = "true".equals(System.getProperty("remote"));
    private final static String remoteSelenoidUrl = System.getProperty("selenoid.url");
    private final static String versionBrowser = System.getProperty("versionBrowser");

    public static WebDriver createWebDriver(TypeBrowser browser) {
        return createWebDriver(browser, List.of());
    }

    public static WebDriver createWebDriver(TypeBrowser browser, List<String> options) {
        if (!remote) {
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
        } else {
            try {
                return new RemoteWebDriver(new URL(remoteSelenoidUrl), getCapabilitiesSelenoid(browser));
            } catch (MalformedURLException e) {
                throw new RuntimeException("Unable to create remote driver", e);
            }
        }
    }

    private static DesiredCapabilities getCapabilitiesSelenoid(TypeBrowser browser) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(String.valueOf(browser).toLowerCase());
        capabilities.setVersion(versionBrowser);
        capabilities.setCapability("enableVNC", true);

        capabilities.setCapability("selenoid:options", new HashMap<String, Object>() {{
            put("name", "Test badge...");
            put("env", List.of("LANG=ru_RU.UTF-8", "LANGUAGE=ru:en", "LC_ALL=ru_RU.UTF-8"));
            put("sessionTimeout", "15m");
            put("labels", new HashMap<String, Object>() {{
                put("manual", "true");
            }});
            //put("enableVideo", true);
        }});
        return capabilities;
    }
}

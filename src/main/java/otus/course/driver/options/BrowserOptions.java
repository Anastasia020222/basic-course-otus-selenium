package otus.course.driver.options;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import otus.course.utils.browser.TypeBrowser;

import java.util.List;

public class BrowserOptions implements IOptionsBrowser {

    private static final Logger logger = LogManager.getLogger(BrowserOptions.class);

    @Override
    public AbstractDriverOptions<?> getBrowserOptions(TypeBrowser browser, List<String> options) {
        switch (browser) {
            case CHROME:
                return chromeOptions(options);
            case FIREFOX:
                return firefoxOptions(options);
            default:
                throw new IllegalArgumentException("Неизвестный тип браузера: " + browser);
        }
    }

    private ChromeOptions chromeOptions(List<String> options) {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        for (String op : options) {
            chromeOptions.addArguments(op);
        }
        logger.info("Опции добавленные в драйвер : " + options);
        return chromeOptions;
    }

    private FirefoxOptions firefoxOptions(List<String> options) {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--remote-allow-origins=*");
        for (String op : options) {
            firefoxOptions.addArguments(op);
        }
        logger.info("Опции добавленные в драйвер : " + options);
        return firefoxOptions;
    }
}

package otus.course.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;
import otus.course.utils.browser.TypeBrowser;

import java.lang.reflect.Field;
import java.util.List;

import static otus.course.utils.browser.SettingsBrowser.getOptionsBrowser;
import static otus.course.utils.browser.TypeBrowser.getTypeBrowser;

public class DriverManagement implements BeforeEachCallback, AfterEachCallback {

    private static final Logger logger = LogManager.getLogger(DriverManagement.class);
    private WebDriver driver;

    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        TypeBrowser browser = getTypeBrowser();
        List<String> brOpt = getOptionsBrowser(extensionContext);
        driver = new WebDriverFactory().create(browser, brOpt);
        Object testInstance = extensionContext.getRequiredTestInstance();
        for (Field field : testInstance.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Driver.class) && field.getType().equals(WebDriver.class)) {
                field.setAccessible(true);
                field.set(testInstance, driver);
            }
        }
        logger.info("Запуск браузера " + browser.name().toUpperCase());
    }

    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
        logger.info("Завершение сеанса");
        if (driver != null) {
            driver.quit();
        }
    }
}

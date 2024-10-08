package otus.course.driver;

import org.openqa.selenium.WebDriver;
import otus.course.utils.BrowserMode;

public interface IWebDriver {
    WebDriver createWebDriver(BrowserMode browserMode);
}

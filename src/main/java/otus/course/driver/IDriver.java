package otus.course.driver;

import org.openqa.selenium.WebDriver;
import otus.course.utils.browser.TypeBrowser;

import java.util.List;

public interface IDriver {
    WebDriver create(TypeBrowser browser, List<String> options);
}

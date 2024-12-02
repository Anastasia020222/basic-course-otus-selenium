package otus.course.driver.options;

import org.openqa.selenium.remote.AbstractDriverOptions;
import otus.course.utils.browser.TypeBrowser;

import java.util.List;

public interface IOptionsBrowser {
    AbstractDriverOptions<?> getBrowserOptions(TypeBrowser browser, List<String> browserMode);
}

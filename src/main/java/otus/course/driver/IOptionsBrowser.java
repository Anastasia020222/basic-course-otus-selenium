package otus.course.driver;

import org.openqa.selenium.remote.AbstractDriverOptions;
import otus.course.utils.TypeBrowser;

import java.util.List;

public interface IOptionsBrowser {
    AbstractDriverOptions<?> getBrowserOptions(TypeBrowser browser, List<String> browserMode);
}

package otus.course.modules;

import com.google.inject.*;
import org.openqa.selenium.WebDriver;
import otus.course.driver.IWebDriver;
import otus.course.driver.WebDriverFactory;
import otus.course.utils.BrowserMode;

public class GuiceModule extends AbstractModule {

    private BrowserMode browserMode;

    public GuiceModule(BrowserMode browserMode) {
        this.browserMode = browserMode;
    }

    @Override
    protected void configure() {
        bind(IWebDriver.class).to(WebDriverFactory.class);
    }

    @Provides
    @Singleton
    public WebDriver provideWebDriver(IWebDriver webDriverFactory) {
        return webDriverFactory.createWebDriver(browserMode);
    }
}

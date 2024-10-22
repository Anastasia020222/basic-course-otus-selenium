package otus.course.driver;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.WebDriver;
import otus.course.modules.GuiceModule;

import static otus.course.utils.Annotation.getAnnotation;

public class DriverManagement implements BeforeEachCallback, AfterEachCallback {

    private static final ThreadLocal<Injector> threadLocal = new ThreadLocal<>();

    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        extensionContext.getTestInstance().ifPresent(instance -> {
            var injector = Guice.createInjector(new GuiceModule(getAnnotation(extensionContext)));
            injector.injectMembers(instance);
            threadLocal.set(injector);
        });
    }

    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
        extensionContext.getTestInstance().ifPresent(instance -> {
            if (threadLocal.get().getInstance(WebDriver.class) != null) {
                threadLocal.get().getInstance(WebDriver.class).quit();
            }
        });
    }
}

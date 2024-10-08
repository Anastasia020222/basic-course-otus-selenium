package otus.course.utils;

import org.junit.jupiter.api.extension.ExtensionContext;
import otus.course.utils.annotation.LabelBrowserMode;

public class Annotation {

    public static BrowserMode getAnnotation(ExtensionContext extensionContext) {
        return extensionContext.getRequiredTestMethod().getAnnotation(LabelBrowserMode.class).browserMode();
    }
}

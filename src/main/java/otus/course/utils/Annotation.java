package otus.course.utils;

import org.junit.jupiter.api.extension.ExtensionContext;
import otus.course.utils.annotation.LabelBrowserMode;

import static otus.course.utils.BrowserMode.FULLSCREEN;

public class Annotation {

    public static BrowserMode getAnnotation(ExtensionContext extensionContext) {
        if (extensionContext.getRequiredTestMethod().getAnnotation(LabelBrowserMode.class) != null) {
            return extensionContext.getRequiredTestMethod().getAnnotation(LabelBrowserMode.class).browserMode();
        } else {
            return FULLSCREEN; //например, берем по умолчанию, если не в тесте задано другое
        }
    }
}

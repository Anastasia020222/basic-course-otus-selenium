package otus.course.utils.annotations;

import otus.course.utils.browser.BrowserMode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface LabelBrowserMode {
    BrowserMode browserMode();
}

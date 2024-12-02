package otus.course.components;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import otus.course.AbsDriverObject;

public class AbsComponents extends AbsDriverObject {

    protected JavascriptExecutor js;

    public AbsComponents(WebDriver driver) {
        super(driver);
        this.js = (JavascriptExecutor) driver;
    }
}

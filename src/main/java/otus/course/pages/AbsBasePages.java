package otus.course.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import otus.course.AbsDriverObject;

import java.time.Duration;

public abstract class AbsBasePages<T> extends AbsDriverObject {

    private final String url = System.getProperty("base.url");
    protected JavascriptExecutor js;
    protected Actions actions;

    public AbsBasePages(WebDriver driver) {
        super(driver);
        this.actions = new Actions(driver);
        this.js = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    public T open(String path) {
        driver.get(url + path);
        return (T) this;
    }

    protected void customWait(By el) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(el));
        } catch (TimeoutException e) {
            throw new TimeoutException("Элемент " + el + " не отобразился на странице");
        }
    }
}

package otus.course.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class AbsBasePages {

    private final String url = System.getProperty("base.url");
    protected WebDriver driver;
    protected WebDriverWait wait;

    public AbsBasePages(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    protected void open(String path) {
        driver.get(url + path);
    }
}

package otus.course;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class AbsDriverObject {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public AbsDriverObject(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(20));
        PageFactory.initElements(this.driver, this);
    }
}

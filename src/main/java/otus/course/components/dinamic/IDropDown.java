package otus.course.components.dinamic;

import org.openqa.selenium.WebElement;

public interface IDropDown {
    void modalShouldBePresent(WebElement el);
    void modalShouldNotBePresent(WebElement el);
}

package otus.course.components.dinamic;

import org.openqa.selenium.*;
import otus.course.components.AbsComponents;

import static org.junit.jupiter.api.Assertions.*;

public class ComponentDropDown extends AbsComponents implements IDropDown {

    public ComponentDropDown(WebDriver driver) {
        super(driver);
    }

    @Override
    public void modalShouldBePresent(WebElement el) {
        String display = (String) js.executeScript("return window.getComputedStyle(arguments[0]).display;", el);
        assertNotEquals("none", display, "DropDown не расскрылся");
    }

    @Override
    public void modalShouldNotBePresent(WebElement el) {
        String display = (String) js.executeScript("return window.getComputedStyle(arguments[0]).display;", el);
        assertEquals(display, "none", "DropDown не скрылся");
    }
}

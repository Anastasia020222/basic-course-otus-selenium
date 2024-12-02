import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import otus.course.driver.Driver;
import otus.course.driver.DriverManagement;
import otus.course.pages.common.FormPages;

import static otus.course.utils.Path.FORM;
import static otus.course.utils.TypeLanguageLevel.INTERMEDIATE;

@ExtendWith(DriverManagement.class)
public class FormTest {

    @Driver
    private WebDriver driver;

    @Test
    @DisplayName("Проверка правильной отправки формы")
    public void checkForm() {
        new FormPages(driver)
                .open(FORM.getPath())
                .inputName()
                .inputEmail()
                .inputPassword()
                .inputConfirmPassword()
                .inputDate()
                .inputLanguageLevel(INTERMEDIATE.getTypeLanguageLevel())
                .sendingForm(INTERMEDIATE.name().toLowerCase());
    }
}

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import otus.course.driver.DriverManagement;
import otus.course.pages.FormPages;

import javax.inject.Inject;

import static otus.course.utils.TypeLanguageLevel.INTERMEDIATE;

@ExtendWith(DriverManagement.class)
public class FormTest {

    @Inject
    FormPages formPages;

    @Test
    @DisplayName("Проверка правильной отправки формы")
    public void checkForm() {
        formPages
                .inputName()
                .inputEmail()
                .inputPassword()
                .inputConfirmPassword()
                .inputDate()
                .inputLanguageLevel(INTERMEDIATE.getTypeLanguageLevel())
                .sendingForm(INTERMEDIATE.name().toLowerCase());
    }
}

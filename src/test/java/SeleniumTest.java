import com.google.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import otus.course.driver.DriverManagement;
import otus.course.pages.MainPages;
import otus.course.utils.annotation.LabelBrowserMode;

import static otus.course.utils.BrowserMode.*;

@ExtendWith(DriverManagement.class)
public class SeleniumTest {

    @Inject
    MainPages mainPages;

    @Test
    @LabelBrowserMode(browserMode = HEADLESS)
    @DisplayName("Проверка правильности введенного текста")
    public void checkInputText() {
        mainPages
                .inputText()
                .checkCorrectnessText();
    }

    @Test
    @LabelBrowserMode(browserMode = KIOSK)
    @DisplayName("Проверка открытия модального окна")
    public void openModelWindow() {
        mainPages
                .clickModeWindow()
                .checkVisibleModeWindow();
    }

    @Test
    @LabelBrowserMode(browserMode = FULLSCREEN)
    @DisplayName("Проверка отправки данных в форме")
    public void sendingForm() {
        mainPages.inputName()
                .inputEmail()
                .clickBtnSubmit()
                .checkMessageBox();
    }
}

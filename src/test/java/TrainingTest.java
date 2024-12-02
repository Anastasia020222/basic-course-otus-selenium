import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import otus.course.driver.Driver;
import otus.course.driver.DriverManagement;
import otus.course.pages.common.TrainingPages;
import otus.course.utils.annotations.LabelBrowserMode;

import static otus.course.utils.Path.TRAINING;
import static otus.course.utils.browser.BrowserMode.*;

@ExtendWith(DriverManagement.class)
public class TrainingTest {

    @Driver
    private WebDriver driver;

    @Test
    @LabelBrowserMode(browserMode = HEADLESS)
    @DisplayName("Проверка правильности введенного текста")
    public void checkInputText() {
        new TrainingPages(driver)
                .open(TRAINING.getPath())
                .inputText()
                .checkCorrectnessText();
    }

    @Test
    @LabelBrowserMode(browserMode = KIOSK)
    @DisplayName("Проверка открытия модального окна")
    public void openModelWindow() {
        new TrainingPages(driver)
                .open(TRAINING.getPath())
                .clickModeWindow()
                .checkVisibleModeWindow();
    }

    @Test
    @LabelBrowserMode(browserMode = FULLSCREEN)
    @DisplayName("Проверка отправки данных в форме")
    public void sendingForm() {
        new TrainingPages(driver)
                .open(TRAINING.getPath())
                .inputName()
                .inputEmail()
                .clickBtnSubmit()
                .checkMessageBox();
    }
}

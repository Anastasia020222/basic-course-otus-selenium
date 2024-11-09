import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import otus.course.driver.Driver;
import otus.course.driver.DriverManagement;
import otus.course.pages.MainPages;
import otus.course.utils.annotations.LabelBrowserMode;

import static otus.course.utils.BrowserMode.*;

@ExtendWith(DriverManagement.class)
public class SeleniumTest {

    @Driver
    private WebDriver driver;

    @Test
    @LabelBrowserMode(browserMode = HEADLESS)
    @DisplayName("Проверка правильности введенного текста")
    public void checkInputText() {
        MainPages mainPages = new MainPages(driver);
        mainPages
                .inputText()
                .checkCorrectnessText();
    }

    @Test
    @LabelBrowserMode(browserMode = KIOSK)
    @DisplayName("Проверка открытия модального окна")
    public void openModelWindow() {
        MainPages mainPages = new MainPages(driver);
        mainPages
                .clickModeWindow()
                .checkVisibleModeWindow();
    }

    @Test
    @LabelBrowserMode(browserMode = FULLSCREEN)
    @DisplayName("Проверка отправки данных в форме")
    public void sendingForm() {
        MainPages mainPages = new MainPages(driver);
        mainPages.inputName()
                .inputEmail()
                .clickBtnSubmit()
                .checkMessageBox();
    }
}

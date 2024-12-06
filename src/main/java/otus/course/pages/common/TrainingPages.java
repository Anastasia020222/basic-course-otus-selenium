package otus.course.pages.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import otus.course.pages.AbsBasePages;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static otus.course.utils.Constants.EXPECTED_TEXT_MAIN;
import static otus.course.utils.Constants.NAME;

public class TrainingPages extends AbsBasePages<TrainingPages> {

    private final String email = System.getProperty("email");
    private final String url = System.getProperty("url.training");

    public TrainingPages(WebDriver driver) {
        super(driver);
    }

    @Override
    public TrainingPages open(String path) {
        driver.get(url + path);
        return this;
    }

    @FindBy(id = "textInput")
    private WebElement inputText;

    @FindBy(id = "openModalBtn")
    private WebElement modalWindowBtn;

    @FindBy(id = "myModal")
    private WebElement visibleModal;

    @FindBy(id = "name")
    private WebElement inputFormName;

    @FindBy(id = "email")
    private WebElement inputFormEmail;

    @FindBy(css = "#sampleForm > button")
    private WebElement btnSubmit;

    @FindBy(id = "messageBox")
    private WebElement messageBox;

    public TrainingPages inputText() {
        inputText.sendKeys(NAME);
        return this;
    }

    public TrainingPages checkCorrectnessText() {
        String actualValue = inputText.getAttribute("value");
        assertEquals(actualValue, NAME, "Значение поля не соответствует ожидаемому.");
        return this;
    }

    public TrainingPages clickModeWindow() {
        modalWindowBtn.click();
        return this;
    }

    public TrainingPages checkVisibleModeWindow() {
        boolean visible = wait.until(ExpectedConditions.attributeToBe(visibleModal, "style", "display: block;"));
        assertTrue(visible, "Модальное окно не открылось.");
        return this;
    }

    public TrainingPages inputName() {
        inputFormName.sendKeys(NAME);
        String actualValue = inputFormName.getAttribute("value");
        assertEquals(actualValue, NAME, "Значение в поле 'Имя' не соответствует ожидаемому.");
        return this;
    }

    public TrainingPages inputEmail() {
        inputFormEmail.sendKeys(email);
        String actualValue = inputFormEmail.getAttribute("value");
        assertEquals(actualValue, email, "Значение в поле 'Email' не соответствует ожидаемому.");
        return this;
    }

    public TrainingPages clickBtnSubmit() {
        btnSubmit.click();
        return this;
    }

    public TrainingPages checkMessageBox() {
        String actualText = messageBox.getText();
        assertEquals(actualText, String.format(EXPECTED_TEXT_MAIN, NAME, email), "Сообщение не соответствует ожидаемому.");
        return this;
    }
}

package otus.course.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static otus.course.utils.Constants.EXPECTED_TEXT_MAIN;
import static otus.course.utils.Constants.NAME;
import static otus.course.utils.Path.MAIN;

public class MainPages extends AbsBasePages {

    private final String email = System.getProperty("email");

    public MainPages(WebDriver driver) {
        super(driver);
        super.open(MAIN.getPath());
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

    public MainPages inputText() {
        inputText.sendKeys(NAME);
        return this;
    }

    public MainPages checkCorrectnessText() {
        String actualValue = inputText.getAttribute("value");
        assertEquals(actualValue, NAME, "Значение поля не соответствует ожидаемому.");
        return this;
    }

    public MainPages clickModeWindow() {
        modalWindowBtn.click();
        return this;
    }

    public MainPages checkVisibleModeWindow() {
        boolean visible = wait.until(ExpectedConditions.attributeToBe(visibleModal, "style", "display: block;"));
        assertTrue(visible, "Модальное окно не открылось.");
        return this;
    }

    public MainPages inputName() {
        inputFormName.sendKeys(NAME);
        String actualValue = inputFormName.getAttribute("value");
        assertEquals(actualValue, NAME, "Значение в поле 'Имя' не соответствует ожидаемому.");
        return this;
    }

    public MainPages inputEmail() {
        inputFormEmail.sendKeys(email);
        String actualValue = inputFormEmail.getAttribute("value");
        assertEquals(actualValue, email, "Значение в поле 'Email' не соответствует ожидаемому.");
        return this;
    }

    public MainPages clickBtnSubmit() {
        btnSubmit.click();
        return this;
    }

    public MainPages checkMessageBox() {
        String actualText = messageBox.getText();
        assertEquals(actualText, String.format(EXPECTED_TEXT_MAIN, NAME, email), "Сообщение не соответствует ожидаемому.");
        return this;
    }
}

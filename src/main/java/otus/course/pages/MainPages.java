package otus.course.pages;

import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainPages extends BasePage {

    private final String expectedText = "Актуальный текст.";
    private final String name = "Настя";
    private final String email = "test@mail.ru";
    private final String expectedTextForm = "Форма отправлена с именем: %s и email: %s";
    private final WebDriverWait wait;

    @Inject
    public MainPages(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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

    public void inputText() {
        inputText.sendKeys(expectedText);
    }

    public void checkCorrectnessText() {
        String actualValue = inputText.getAttribute("value");
        assertEquals(actualValue, expectedText, "Значение поля не соответствует ожидаемому.");
    }

    public void clickModeWindow() {
        modalWindowBtn.click();
    }

    public void checkVisibleModeWindow() {
        boolean visible = wait.until(ExpectedConditions.attributeToBe(visibleModal, "style", "display: block;"));
        assertTrue(visible, "Модальное окно не открылось.");
    }

    public void inputName() {
        inputFormName.sendKeys(name);
        String actualValue = inputFormName.getAttribute("value");
        assertEquals(actualValue, name, "Значение в поле 'Имя' не соответствует ожидаемому.");
    }

    public void inputEmail() {
        inputFormEmail.sendKeys(email);
        String actualValue = inputFormEmail.getAttribute("value");
        assertEquals(actualValue, email, "Значение в поле 'Email' не соответствует ожидаемому.");
    }

    public void clickBtnSubmit() {
        btnSubmit.click();
    }

    public void checkMessageBox() {
        String actualText = messageBox.getText();
        assertEquals(actualText, String.format(expectedTextForm, name, email), "Сообщение не соответствует ожидаемому.");
    }
}

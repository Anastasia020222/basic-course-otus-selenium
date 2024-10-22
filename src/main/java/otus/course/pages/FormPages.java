package otus.course.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import javax.inject.Inject;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static otus.course.utils.Constants.*;
import static otus.course.utils.Date.convertDate;
import static otus.course.utils.Date.getTodayDate;
import static otus.course.utils.Path.FORM;
import static otus.course.utils.TypeLanguageLevel.INTERMEDIATE;

public class FormPages extends AbsBasePages {

    private final String email = System.getProperty("email");
    private final String password = System.getProperty("password");

    @Inject
    public FormPages(WebDriver driver) {
        super(driver);
        super.open(FORM.getPath());
    }

    @FindBy(id = "username")
    private WebElement username;

    @FindBy(id = "email")
    private WebElement fieldEmail;

    @FindBy(id = "password")
    private WebElement fieldPassword;

    @FindBy(id = "confirm_password")
    private WebElement fieldConfirm_password;

    @FindBy(id = "birthdate")
    private WebElement fieldDate;

    @FindBy(id = "language_level")
    private WebElement fieldLanguage_level;

    @FindBy(css = "#registrationForm > input[type=submit]")
    private WebElement submit;

    @FindBy(id = "output")
    private WebElement output;

    public FormPages inputName() {
        username.sendKeys(NAME);
        String actualText = username.getAttribute("value");
        assertEquals(NAME, actualText, "Введенный текст в поле 'name' не соответствует ожидаемому");
        return this;
    }

    public FormPages inputEmail() {
        fieldEmail.sendKeys(email);
        String actualText = fieldEmail.getAttribute("value");
        assertEquals(email, actualText, "Введенный текст в поле 'email' не соответствует ожидаемому");
        return this;
    }

    public FormPages inputPassword() {
        fieldPassword.sendKeys(password);
        String actualText = fieldPassword.getAttribute("value");
        assertEquals(password, actualText, "Введенный текст в поле 'password' не соответствует ожидаемому");
        return this;
    }

    public FormPages inputConfirmPassword() {
        fieldConfirm_password.sendKeys(password);
        String actualTextConfirmPassword = fieldConfirm_password.getAttribute("value");
        String actualTextPassword = fieldPassword.getAttribute("value");
        assertEquals(password, actualTextConfirmPassword, "Введенный текст в поле 'confirm_password' не соответствует ожидаемому");
        assertEquals(actualTextConfirmPassword, actualTextPassword, "Пароли в поле password и confirm-password не совпали");
        return this;
    }

    public FormPages inputDate() {
        fieldDate.sendKeys(getTodayDate());
        String actualText = fieldDate.getAttribute("value");
        String formattedDate = convertDate(getTodayDate());
        assertEquals(formattedDate, actualText, "Введенная дата не соответствует " + formattedDate);
        return this;
    }

    public FormPages inputLanguageLevel(String language) {
        fieldLanguage_level.click();

        if (getListOptions(language) != null) {
            Select select = new Select(fieldLanguage_level);
            select.selectByVisibleText(language);
        } else {
            throw new NoSuchElementException("Не удалось найти опцию с текстом " + language);
        }

        String actualLang = fieldLanguage_level.getAttribute("value");
        assertEquals(actualLang, INTERMEDIATE.name().toLowerCase(), "Выбранный язык в поле не соответствует: " + language);

        return this;
    }

    public FormPages sendingForm(String language) {
        submit.click();

        String actualValue = output.getText();
        assertEquals(actualValue, String.format(EXPECTED_TEXT_FORM, NAME, email, convertDate(getTodayDate()), language),
                "Текст после отправки формы не соответствует ожидаемому");

        assertFalse(checkAlert(), "Алерт отобразился");
        return this;
    }

    private WebElement getListOptions(String lang) {
        List<WebElement> options = fieldLanguage_level.findElements(By.tagName("option"));
        for (WebElement option : options) {
            if (lang.equals(option.getText())) {
                return option;
            }
        }
        return null;
    }

    private boolean checkAlert() {
        try {
            Alert alert = driver.switchTo().alert();
            String message = alert.getText();
            assertEquals(message, MESSAGE_FORM, "Текст в alert не соответствует " + MESSAGE_FORM);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

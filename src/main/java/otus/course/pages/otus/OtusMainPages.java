package otus.course.pages.otus;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import otus.course.components.dinamic.ComponentDropDown;
import otus.course.components.dinamic.IDropDown;
import otus.course.pages.AbsBasePages;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OtusMainPages extends AbsBasePages<OtusMainPages> {

    private final IDropDown iDropDown;

    public OtusMainPages(WebDriver driver) {
        super(driver);
        this.iDropDown = new ComponentDropDown(driver);
    }

    @FindBy(xpath = "//a[contains(@href, '/categories/testing') and contains(text(), 'Тестирование')]")
    private WebElement categoriesTestingDropDown;

    @FindBy(xpath = "//a[contains(@href, '/events/near') and contains(text(), 'Календарь мероприятий')]")
    private WebElement categoriesEventsDropDown;

    @FindBy(xpath = "(//div[contains(@class, 'sc-r03h0s-5 sc-1youhxc-2 cGgLky jMVjJA')])[1]")
    private WebElement openDropDown;

    @FindBy(xpath = "(//div[contains(@class, 'sc-piuiz2-0 fzHlJa')])[1]")
    private WebElement visibleDropDownBlock;

    @FindBy(xpath = "(//div[contains(@class, 'sc-piuiz2-0 iuiQgg')])[1]")
    private WebElement notVisibleDropDownMainPage;

    @FindBy(css = ".header3__nav-item-popup-wrapper.js-header3-popup")
    private WebElement notVisibleDropDownEventPages;

    private final By blockCatalogTesting = By.cssSelector(".sc-o4bnil-0.riKpM");
    private final By blockCatalogEvents = By.cssSelector(".dod_new-header__title");
    private final By dropDown = By.cssSelector(".sc-piuiz2-0.fzHlJa");

    public OtusMainPages openAndVisibleDropDown() {
        actions
                .moveToElement(openDropDown)
                .build().perform();
        customWait(dropDown);
        iDropDown
                .modalShouldBePresent(visibleDropDownBlock);
        return this;
    }

    public TestingPages clickCategoriesTesting() {
        actions
                .moveToElement(categoriesTestingDropDown)
                .click()
                .build().perform();

        customWait(blockCatalogTesting);

        iDropDown
                .modalShouldNotBePresent(notVisibleDropDownMainPage);
        return new TestingPages(driver);
    }

    public EventsPages clickCategoriesEvents() {
        actions
                .moveToElement(categoriesEventsDropDown)
                .click()
                .build().perform();

        customWait(blockCatalogEvents);

        WebElement el = driver.findElement(blockCatalogEvents);
        String actualTitleEvent = el.getText();
        assertEquals("Календарь мероприятий", actualTitleEvent, "На странице мероприятий отобразился неверный title");

        iDropDown
                .modalShouldNotBePresent(notVisibleDropDownEventPages);
        return new EventsPages(driver);
    }
}

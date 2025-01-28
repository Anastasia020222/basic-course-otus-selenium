package otus.course.pages.otus;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import otus.course.pages.AbsBasePages;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestingPages extends AbsBasePages<TestingPages> {

    public TestingPages(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".sc-zzdkm7-0")
    private List<WebElement> listCoursesTesting;

    @FindBy(css = ".sc-18q05a6-1.bwGwUO")
    private WebElement getBlockCatalogTesting;

    @FindBy(css = ".sc-o4bnil-0.riKpM")
    private WebElement blockCatalogTesting;

    @FindBy(css = ".sc-mrx253-0.enxKCy.sc-prqxfo-0.cXVWAS")
    private WebElement buttonShowMore;

    private By coursesPage = By.cssSelector(".sc-s2pydo-6.EOCgR.sc-x072mc-0.hOtCic");

    private WebElement getCourseLink(String nameCourses) {
        return driver.findElement(By.xpath(String.format("//a[contains(@href, '%s')]", nameCourses)));
    }

    public TestingPages clickButtonShowMore() {
        int count = getBlockCatalogTesting.findElements(By.cssSelector(".sc-zzdkm7-0")).size();

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});",
                buttonShowMore);

        buttonShowMore
                .click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".sc-mrx253-0.enxKCy.sc-prqxfo-0.cXVWAS")));

        int newCount = getBlockCatalogTesting.findElements(By.cssSelector(".sc-zzdkm7-0")).size();

        wait.until(driver -> newCount > count);

        assertTrue(newCount > count, "После попытки кликнуть по кнопке 'Показать еще' список курсов не увеличился");
        return this;
    }

    public TestingPages checkCountCoursesTesting() {
        List<WebElement> el = getBlockCatalogTesting.findElements(By.cssSelector(".sc-zzdkm7-0"));
        int countCourse = 11;
        assertEquals(countCourse, el.size(), "На странице Тестирование количество курсов не равно " + countCourse);
        return this;
    }

    private By getCourseLinkLocator(String nameCourses) {
        return By.xpath(String.format("//a[contains(@href, '%s')]", nameCourses));
    }

    public CoursesPages openCardCourse(String linkCourses) {

        WebElement element = driver.findElement(getCourseLinkLocator(linkCourses));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});",
                getCourseLink(linkCourses));

        actions
                .moveToElement(element)
                .doubleClick()
                .perform();

        customWait(coursesPage);
        return new CoursesPages(driver);
    }
}

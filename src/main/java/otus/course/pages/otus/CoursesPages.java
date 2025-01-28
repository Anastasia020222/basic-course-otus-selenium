package otus.course.pages.otus;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import otus.course.components.inactive.CourseInfoPanelComponent;
import otus.course.pages.AbsBasePages;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CoursesPages extends AbsBasePages<CoursesPages> {

    CourseInfoPanelComponent courseInfoPanelComponent;

    public CoursesPages(WebDriver driver) {
        super(driver);
        this.courseInfoPanelComponent = new CourseInfoPanelComponent(driver);
    }

    @FindBy(css = ".sc-1og4wiw-0.sc-s2pydo-1.iLVLDh.diGrSa")
    private WebElement getTitle;

    @FindBy(css = ".sc-1og4wiw-0.sc-s2pydo-3.jfNqTr.dZDxRw")
    private WebElement getDescription;

    public CoursesPages checkTitleCourse(String nameCourse) {
        String actualTitleCourse = getTitle.getText();
        assertEquals(nameCourse, actualTitleCourse, "На странице курса отобразилось неверное название курса");
        return this;
    }

    public CoursesPages checkDescriptionCourse(String description) {
        String actualDescription = getDescription.getText();
        assertEquals(description, actualDescription, "На странице курса отобразилосб неверное описание");
        return this;
    }

    public CoursesPages checkDurationCourse(String duration) {
        String actualDuration = courseInfoPanelComponent.getDurationCourse();
        assertEquals(duration, actualDuration, "На странице курса длительность обучения не равна " + duration);
        return this;
    }

    public CoursesPages checkFormatCourse(String format) {
        String actualFormat = courseInfoPanelComponent.getFormatCourse();
        assertEquals(format, actualFormat, "На странице формат обучения не равна " + format);
        return this;
    }
}

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import otus.course.driver.Driver;
import otus.course.driver.DriverManagement;
import otus.course.pages.otus.OtusMainPages;
import otus.course.pages.otus.TestingPages;
import otus.course.utils.CardCourses;

import static otus.course.utils.Path.OTUS;
import static otus.course.utils.Path.TESTING;

@ExtendWith(DriverManagement.class)
public class OtusTestingTest {

    @Driver
    private WebDriver driver;

    @Test
    @DisplayName("Проверка количества курсов в разделе тестирование")
    public void checkCountCourses() {
        new OtusMainPages(driver)
                .open("")
                .openAndVisibleDropDown()
                .clickCategoriesTesting()
                .clickButtonShowMore()
                .checkCountCoursesTesting();
    }

    @ParameterizedTest
    @MethodSource("otus.course.utils.CardCourses#listCardCoursesTesting")
    @DisplayName("Просмотр карточки курса")
    public void viewCourseCard(CardCourses cardCourses) {
        new TestingPages(driver)
                .open(OTUS.getPath() + TESTING.getPath())
                .openCardCourse(cardCourses.getLink())
                .checkTitleCourse(cardCourses.getName())
                .checkDescriptionCourse(cardCourses.getDescription())
                .checkDurationCourse(cardCourses.getDuration())
                .checkFormatCourse(cardCourses.getFormat());
    }
}

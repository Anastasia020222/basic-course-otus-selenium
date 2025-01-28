import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import otus.course.driver.Driver;
import otus.course.driver.DriverManagement;
import otus.course.pages.otus.OtusMainPages;

@ExtendWith(DriverManagement.class)
public class OtusEventsTest {

    @Driver
    private WebDriver driver;

    @Test
    @DisplayName("Валидация дат предстоящих мероприятий. Дата дожна быть не позже сегодняшней")
    public void checkDatesUpcomingEvents() {
        new OtusMainPages(driver)
                .open("")
                .openAndVisibleDropDown()
                .clickCategoriesEvents()
                .scrollEventsCard()
                .checkDataEvent();
    }

    @Test
    @DisplayName("Просмотр мероприятия по типу 'Открытый вебинар'")
    public void checkTypeOpenWebinar() {
        new OtusMainPages(driver)
                .open("")
                .openAndVisibleDropDown()
                .clickCategoriesEvents()
                .sortEventOpenWebinars()
                .scrollEventsCard()
                .checkOpenWebinarEventCard();
    }
}

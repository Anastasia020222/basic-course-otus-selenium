package otus.course.pages.otus;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import otus.course.pages.AbsBasePages;
import otus.course.utils.EventsCalendarInfo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static otus.course.utils.Date.*;

public class EventsPages extends AbsBasePages<EventsPages> {

    public EventsPages(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".dod_new-events__list.js-dod_new_events")
    private WebElement blockEventList;

    @FindBy(css = ".dod_new-events-dropdown.js-dod_new_events-dropdown")
    private WebElement eventsDropdown;

    @FindBy(css = ".dod_new-events-dropdown_opened .dod_new-events-dropdown__list")
    private WebElement listDropDown;

    @FindBy(css = "a.dod_new-events-dropdown__list-item[title='Открытый вебинар']")
    private WebElement eventListTitleOpenWebinar;

    @FindBy(css = ".dod_new-events-dropdown__input-selected")
    private WebElement inputSelectedEventsDropDown;

    @FindBy(css = ".dod_new-loader-wrapper.js-dod_new-loader-wrapper")
    private WebElement loaderEvents;

    public EventsPages checkDataEvent() {
        List<EventsCalendarInfo> list = getPageListEventsCalendarInfo();
        for (EventsCalendarInfo evPage : list) {
            assertTrue(checkActualData(evPage.getData()), "Дата на карточке " + evPage.getId() + " меньше или не равна текущей дате");
        }
        return this;
    }

    public EventsPages checkOpenWebinarEventCard() {
        List<String> result = new ArrayList<>();
        List<EventsCalendarInfo> list = getPageListEventsCalendarInfo();

        for (EventsCalendarInfo evPage : list) {
            if (!evPage.getTypeEvent().equals("Открытый вебинар")) {
                result.add(evPage.getTitleEvent());
            }
        }
        assertEquals(0, result.size(), "При фильтрации мероприятий по типу 'Открытые мероприятия' отобразились мероприятия " +
                "другого типа: " + result);
        return this;
    }

    public EventsPages sortEventOpenWebinars() {
        String actualTextSelectedDropDown = inputSelectedEventsDropDown.getText();
        assertEquals("Все мероприятия", actualTextSelectedDropDown, "В селекте выпадающего списка текст не соответствует 'Все мероприятия'");

        eventsDropdown
                .click();
        assertTrue(checkOpenDropDown(eventsDropdown), "Выпадающий список 'Все мероприятия' не открылся");

        eventListTitleOpenWebinar
                .click();

        customWait(By.cssSelector(".dod_new-header__title"));

        String actualTextSelectedOpenWebinar = inputSelectedEventsDropDown.getText();
        assertEquals("Открытые вебинары", actualTextSelectedOpenWebinar, "В селекте выпадающего списка текст не соответствует 'Открытые вебинары'");
        return this;
    }

    private boolean checkOpenDropDown(WebElement el) {
        String atr = el.getAttribute("class");
        if (atr.contains("_opened")) {
            return true;
        } else {
            return false;
        }
    }

    private List<EventsCalendarInfo> getPageListEventsCalendarInfo() {
        List<EventsCalendarInfo> listEventsCalendarInfo = new ArrayList<>();
        List<WebElement> el = blockEventList.findElements(By.xpath("//a[contains(@href,'#event')]"));

        int id;
        String type;
        String title;
        LocalDate data;
        LocalTime time;

        for (WebElement e : el) {
            id = Integer.parseInt(e.getAttribute("hash").replaceAll("\\D+", ""));
            type = e.findElement(By.cssSelector(".dod_new-event__type")).getText();
            title = e.findElement(By.cssSelector(".dod_new-event__title.js-dod-new-event-title")).getText();
            data = convertDataFormat(e.findElement(By.xpath(".//span[contains(@class, 'dod_new-event__calendar-icon')]/following-sibling::span")).getText() + " " + LocalDate.now().getYear());
            time = getLocaleTime(e.findElement(By.xpath(".//span[contains(@class, 'dod_new-event__clock-icon')]/following-sibling::span")).getText());

            listEventsCalendarInfo.add(new EventsCalendarInfo(id, type, title, data, time));

        }
        return listEventsCalendarInfo;
    }

    public EventsPages scrollEventsCard() {
        while (true) {
            int count = blockEventList.findElements(By.cssSelector(".dod_new-event")).size();

            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

            try {
                wait.until(driver ->
                        blockEventList.findElements(By.cssSelector(".dod_new-event")).size() > count);
            } catch (TimeoutException e) {
                String loader = (String) js.executeScript("return window.getComputedStyle(arguments[0]).display;",
                        loaderEvents);
                if (loader.equals("none")) {
                    break;
                }
            }
        }
        return this;
    }
}

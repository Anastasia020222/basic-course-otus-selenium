package otus.course.components.inactive;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import otus.course.components.AbsComponents;

import java.util.List;

public class CourseInfoPanelComponent extends AbsComponents {

    public CourseInfoPanelComponent(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".sc-x072mc-0.sc-3cb1l3-1.hOtCic.galmep")
    private WebElement courseInfoPanel;

    private CourseInfoPanel listCourseInfoPanel() {
        List<WebElement> listEl = courseInfoPanel.findElements(By.cssSelector(".sc-1og4wiw-0.sc-3cb1l3-0.gcChXs.dgWykw"));
        String data = "";
        String category = "";
        String duration = "";
        String format = "";
        String schedule = "";

        for (WebElement l : listEl) {
            String text = l.getText();

            if (category.isEmpty() && (text.equalsIgnoreCase("Professional") || text.equalsIgnoreCase("Basic"))) {
                category = text;
            } else if (duration.isEmpty() && (text.contains("месяц") || text.contains("недел"))) {
                duration = text;
            } else if (format.isEmpty() && (text.equalsIgnoreCase("Онлайн") || text.equalsIgnoreCase("Офлайн"))) {
                format = text;
            } else if (schedule.isEmpty() && text.contains(":")) {  // Например, "Пн/Ср 20:00 Мск"
                schedule = text;
            } else if (data.isEmpty()) {
                data = text;
            }
        }
        return new CourseInfoPanel(data, category, duration, format, schedule);
    }

    public String getDurationCourse() {
        return listCourseInfoPanel().getDuration();
    }

    public String getFormatCourse() {
        return listCourseInfoPanel().getFormat();
    }
}

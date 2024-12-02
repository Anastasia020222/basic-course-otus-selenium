package otus.course.components.inactive;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseInfoPanel {

    private String data;
    private String category;
    private String duration;
    private String format;
    private String schedule;

    public CourseInfoPanel(String data, String category, String duration, String format, String schedule) {
        this.data = data;
        this.category = category;
        this.duration = duration;
        this.format = format;
        this.schedule = schedule;
    }
}

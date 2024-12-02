package otus.course.utils;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Builder
public class EventsCalendarInfo {

    private int id;
    private String typeEvent;
    private String titleEvent;
    private LocalDate data;
    private LocalTime time;

    public EventsCalendarInfo(int id, String typeEvent, String titleEvent, LocalDate data, LocalTime time) {
        this.id = id;
        this.typeEvent = typeEvent;
        this.titleEvent = titleEvent;
        this.data = data;
        this.time = time;
    }

    @Override
    public String toString() {
        return "\n" + "typeEvent: '" + typeEvent +
                ", titleEvent: '" + titleEvent +
                ", data: '" + data +
                ", time: '" + time;
    }
}

package otus.course.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Date {
    public static String getTodayDate() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String convertDate(String date) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDate;

        try {
            localDate = LocalDate.parse(date, inputFormatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Неверный формат даты: " + date);
        }

        return localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}

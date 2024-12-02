package otus.course.utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

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

    public static boolean checkActualData(LocalDate localDate) {
        LocalDate localDateToday = LocalDate.now();
        if (localDate.equals(localDateToday) || localDate.isAfter(localDateToday)) {
            return true;
        } else {
            return false;
        }
    }

    public static LocalDate convertDataFormat(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", new Locale("ru"));
        LocalDate localDate;

        try {
            localDate = LocalDate.parse(data, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Неверный формат даты: " + data);
        }
        return localDate;
    }

    public static LocalTime getLocaleTime(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return LocalTime.parse(time, formatter);
    }
}

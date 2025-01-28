package otus.course.utils;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CardCourses {

    private String link;
    private String name;
    private String description;
    private String duration;
    private String format;

    public CardCourses(String link, String name, String description, String duration, String format) {
        this.link = link;
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.format = format;
    }

    public static List<CardCourses> listCardCoursesTesting() {
        List<CardCourses> list = new ArrayList<>();
        list.add(new CardCourses("/kotlin-qa-engineer", "Kotlin QA Engineer",
                "Научитесь разрабатывать тесты для всех платформ где используется Kotlin",
                "4 месяца", "Онлайн"));

        list.add(new CardCourses("/java-qa-pro", "Java QA Engineer. Professional",
                "Курс по автоматизированному тестированию на Java: продвинутые инструменты, новые карьерные возможности",
                "4 месяца", "Онлайн"));

        list.add(new CardCourses("/avtomatizaciya-web-testirovaniya", "Python QA Engineer",
                "Курс по автоматизации тестирования на Python: освойте фреймворк PyTest, автоматизируйте тесты UI и API",
                "5 месяцев", "Онлайн"));

        list.add(new CardCourses("/qa-game", "Game QA Engineer",
                "Научитесь с нуля тестировать игры на платформах:\n" +
                        "iOS, Android, PlayStation, Xbox, Switch и PC", "4 месяца", "Онлайн"));

        list.add(new CardCourses("/loadqa", "Нагрузочное тестирование",
                "Обучение затрагивает все аспекты нагрузочного тестирования: составляйте методику НТ, разрабатывайте скрипты, запускайте тесты и настраивайте мониторинг",
                "3 месяца", "Онлайн"));

        list.add(new CardCourses("/qajs", "JavaScript QA Engineer",
                "Первый на рынке курс по написанию автотестов на JavaScript", "4 месяца", "Онлайн"));

        list.add(new CardCourses("/java-qa-basic", "Java QA Engineer. Basic",
                "Курс по автоматизации тестирования на Java для начинающих: синтаксис Java, автотесты для UI и API, фреймворки",
                "5 месяцев", "Онлайн"));

        list.add(new CardCourses("/qa-auto-java-specialization", "QA Automation Engineer",
                "Идеальная точка входа в тестирование на Java", "10 месяцев", "Онлайн"));

        list.add(new CardCourses("/qa-lead", "QA Lead",
                "Best Practice по Soft и Hard Skills для эффективного управления командами тестирования",
                "5 месяцев", "Онлайн"));

        list.add(new CardCourses("/qa-engineer", "QA Engineer. Basic",
                "Ваш первый шаг в карьере инженера по тестированию: с нуля - до уровня младшего специалиста за 4 месяца",
                "4 месяца", "Онлайн"));
        return list;
    }

    @Override
    public String toString() {
        return "'" + name + "'";
    }
}

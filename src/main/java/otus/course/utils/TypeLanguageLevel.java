package otus.course.utils;

public enum TypeLanguageLevel {

    BEGINNER("Начальный"),
    INTERMEDIATE("Средний"),
    ADVANCED("Продвинутый"),
    NATIVE("Носитель языка");

    private final String typeLanguageLevel;

    TypeLanguageLevel(String typeLanguageLevel) {
        this.typeLanguageLevel = typeLanguageLevel;
    }

    public String getTypeLanguageLevel() {
        return typeLanguageLevel;
    }
}

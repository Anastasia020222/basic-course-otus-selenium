package otus.course.utils.browser;

public enum TypeBrowser {
    CHROME,
    FIREFOX;

    public static TypeBrowser getTypeBrowser() {
        try {
            return TypeBrowser.valueOf(System.getProperty("browser").trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Указанный тип браузера не был найден. Возможные варианты: chrome, firefox");
        }
    }
}

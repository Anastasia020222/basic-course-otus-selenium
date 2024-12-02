package otus.course.utils;

public enum Path {

    TRAINING("https://otus.home.kartushin.su/training.html"),
    OTUS("https://otus.ru"),
    TESTING("/catalog/courses?categories=testing"),
    FORM("https://otus.home.kartushin.su/form.html");

    private final String path;

    Path(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}

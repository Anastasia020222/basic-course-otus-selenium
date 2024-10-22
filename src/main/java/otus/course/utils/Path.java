package otus.course.utils;

public enum Path {

    MAIN("training.html"),
    FORM("form.html");

    private final String path;

    Path(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}

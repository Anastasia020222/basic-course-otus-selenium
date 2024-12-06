package otus.course.utils;

public enum Path {

    TRAINING("/training.html"),
    TESTING("/catalog/courses?categories=testing"),
    FORM("/form.html");

    private final String path;

    Path(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}

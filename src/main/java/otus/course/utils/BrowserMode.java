package otus.course.utils;

public enum BrowserMode {

    KIOSK("--kiosk"),
    FULLSCREEN("--start-maximized"),
    HEADLESS("--headless=old");

    private final String browserMode;

    BrowserMode(String browserMode) {
        this.browserMode = browserMode;
    }

    public String getBrowserMode() {
        return browserMode;
    }
}

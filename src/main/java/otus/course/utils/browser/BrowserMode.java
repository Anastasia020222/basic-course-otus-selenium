package otus.course.utils.browser;

import java.util.List;

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

    public static boolean getBrowserOptions(List<String> listOpt) {
        for (String op : listOpt) {
            for (BrowserMode browserMode : values()) {
                if (op.equals(browserMode.getBrowserMode())) {
                    return true;
                }
            }
        }
        return false;
    }
}

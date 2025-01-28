package otus.course.utils.browser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.extension.ExtensionContext;
import otus.course.utils.annotations.LabelBrowserMode;

import java.util.ArrayList;
import java.util.List;

public class SettingsBrowser {

    private static final Logger logger = LogManager.getLogger(SettingsBrowser.class);

    public static List<String> getOptionsBrowser(ExtensionContext extensionContext) {
        List<String> listOptions = new ArrayList<>();

        LabelBrowserMode label = extensionContext.getRequiredTestMethod().getAnnotation(LabelBrowserMode.class);
        if (label != null) {
            String labelbm = label.browserMode().getBrowserMode();
            listOptions.add(labelbm);
            logger.info("Получение опции для запуска теста в режиме " + labelbm);
        }

        String options = System.getProperty("options", "");
        if (!options.isEmpty()) {
            for (String op : options.split(" ")) { // Добавляем каждую опцию в список, уберая дублирования с `labelbm`
                if (!listOptions.contains(op) && !BrowserMode.getBrowserOptions(listOptions)) {
                    listOptions.add(op);
                }
            }
            logger.info("Добавлены опции из переданных параметров " + options);
        }

        return listOptions;
    }
}

package by.epam.finalTask.controller.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceManager {

    public final static String UPLOAD_DIRECTORY = "track\\";

    private final static String resourceFile = "locale";

    private ResourceManager() {
    }

    public static String getString(String key, Locale locale) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(resourceFile, locale);
        return resourceBundle.getString(key);
    }


}

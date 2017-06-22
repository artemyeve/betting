package by.artemyeu.betting.manager;

import java.util.Locale;
import java.util.ResourceBundle;


/**
 * Created by Acer on 16.05.2017.
 */
public enum ResourceBundleType {
    EN_US("en","US"),
    RU_RU("ru","RU");

    private ResourceBundle resourceBundle;

    ResourceBundleType(String curLocale, String country) {
        resourceBundle=ResourceBundle.getBundle("content", new Locale(curLocale,country));
    }

    public ResourceBundle getResourceBundle(){
        return resourceBundle;
    }
}

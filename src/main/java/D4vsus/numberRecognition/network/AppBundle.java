package D4vsus.numberRecognition.network;

import java.util.ResourceBundle;

/**
 * <h1>AppBundle</h1>
 * <p>Set-up the bundle for languages</p>
 *
 * @author D4vsus
 */
public class AppBundle {
    public static ResourceBundle resourceBundle;

    public static ResourceBundle getResourceBundle(){
        return resourceBundle;
    }

    public static void setResourceBundle(ResourceBundle resourceBundle){
        AppBundle.resourceBundle = resourceBundle;
    }
}

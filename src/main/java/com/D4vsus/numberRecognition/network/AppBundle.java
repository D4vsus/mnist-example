package com.D4vsus.numberRecognition.network;

import java.util.ResourceBundle;

/**
 * <h1>AppBundle</h1>
 * <p>Set-up the bundle for languages</p>
 *
 * @author D4vsus
 */
public class AppBundle {
    //variables and objects
    public static ResourceBundle resourceBundle;

    //methods

    public static ResourceBundle getResourceBundle(){
        return resourceBundle;
    }

    public static void setResourceBundle(ResourceBundle resourceBundle){
        AppBundle.resourceBundle = resourceBundle;
    }
}

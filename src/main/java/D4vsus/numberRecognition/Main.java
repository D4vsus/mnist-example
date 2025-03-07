package D4vsus.numberRecognition;

import D4vsus.numberRecognition.UI.MainWindow;
import D4vsus.numberRecognition.network.AppBundle;
import com.formdev.flatlaf.FlatIntelliJLaf;

import javax.swing.*;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * <h1>Main</h1>
 * <p>Initialize the application</p>
 *
 * @author D4vsus
 */
public class Main {
    public static void main(String[] args) {
        //set language
        try {
            AppBundle.setResourceBundle(ResourceBundle.getBundle("lang/lang", Locale.getDefault()));
        } catch ( MissingResourceException | NullPointerException ex){
            AppBundle.setResourceBundle(ResourceBundle.getBundle("lang/lang", Locale.of("en")));
            JOptionPane.showMessageDialog(null,
                    AppBundle.getResourceBundle().getString("language_not_supported"),
                    AppBundle.getResourceBundle().getString("error"),
                    JOptionPane.WARNING_MESSAGE
            );
        }
        //set look and feel
        try {
            UIManager.setLookAndFeel( new FlatIntelliJLaf());
        } catch( UnsupportedLookAndFeelException ex ) {
                try {
                    UIManager.setLookAndFeel(UIManager.getAuxiliaryLookAndFeels()[0]);
                } catch (UnsupportedLookAndFeelException unsupported) {
                    throw  new RuntimeException(unsupported);
                }
                JOptionPane.showMessageDialog(null,
                        AppBundle.getResourceBundle().getString("lf_not_set"),
                        AppBundle.getResourceBundle().getString("error"),
                        JOptionPane.WARNING_MESSAGE
                );
            }
        //initialize the app
        try {
            new MainWindow();
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null,ex.toString(),ex.getClass().getName(),JOptionPane.ERROR_MESSAGE,null);
        }
    }
}
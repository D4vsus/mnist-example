package D4vsus.numberRecognition;

import D4vsus.numberRecognition.UI.MainWindow;
import D4vsus.numberRecognition.network.AppBundle;
import com.formdev.flatlaf.FlatIntelliJLaf;

import javax.swing.*;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * <h1>Main</h1>
 * <p>Initialize the application</p>
 *
 * @author D4vsus
 */
public class Main {
    public static void main(String[] args) {
        try {
            AppBundle.setResourceBundle(ResourceBundle.getBundle("lang/lang", Locale.of("es")));
            UIManager.setLookAndFeel( new FlatIntelliJLaf());
            new MainWindow();
        } catch( UnsupportedLookAndFeelException ex ) {
            System.err.println( "Failed to initialize LaF" );
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null,ex.toString(),ex.getClass().getName(),JOptionPane.ERROR_MESSAGE,null);
        }
    }
}
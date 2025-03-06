package D4vsus.numberRecognition.UI;

import D4vsus.numberRecognition.network.AppBundle;
import D4vsus.numberRecognition.network.RetroFitClient;

import javax.swing.*;
import java.awt.event.*;
import java.util.regex.Pattern;

/**
 * <h1>IPWindow</h1>
 * <p>A dialog window to set the serverIP</p>
 *
 * @author D4vsus
 */
public class IPWindow extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textFieldIP;

    public IPWindow() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> {
            String socket = textFieldIP.getText();
            Pattern pattern = Pattern.compile("^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)(?::(0|[1-9][0-9]{0,3}|[1-5][0-9]{4}|6[0-4][0-9]{3}|65[0-4][0-9]{2}|655[0-2][0-9]|6553[0-5]))?$");
            if (pattern.matcher(socket.strip()).matches()){
                JOptionPane.showMessageDialog(null, AppBundle.getResourceBundle().getString("ip_format_error"),AppBundle.getResourceBundle().getString("error"),JOptionPane.ERROR_MESSAGE);
                return;
            }
            RetroFitClient.setURL("http://" + socket + "/");
            RetroFitClient.getRetrofitInstance();
            onOK();
        });

        buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        this.pack();
        this.setVisible(true);
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}

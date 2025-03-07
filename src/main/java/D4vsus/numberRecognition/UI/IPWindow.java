package D4vsus.numberRecognition.UI;

import D4vsus.numberRecognition.network.AppBundle;
import D4vsus.numberRecognition.network.RetroFitClient;

import javax.swing.*;
import java.awt.event.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <h1>IPWindow</h1>
 * <p>A dialog window to set the serverIP</p>
 *
 * @author D4vsus
 */
public class IPWindow extends JDialog {
    //variables and objects
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textFieldIP;
    private static final String ipPatter = "(?:(?:25[0-5]|2[0-4]\\d|[01]?\\d?\\d)\\.){3}"
            + "(?:25[0-5]|2[0-4]\\d|[01]?\\d?\\d)"
            + "(?::(0|[1-9]\\d{0,3}|[1-5]\\d{4}|6[0-4]\\d{3}|65[0-4]\\d{2}|655[0-2]\\d|6553[0-5]))?";

    //methods

    /**
     * <h1>Constructor</h1>
     * <p>Set up the window</p>
     */
    public IPWindow() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        Matcher matcher = Pattern.compile(ipPatter).matcher(RetroFitClient.getURL());
        if (matcher.find()) {
            String socket = matcher.group();
            textFieldIP.setText(socket);
        }

        buttonOK.addActionListener(e -> onOK());

        buttonCancel.addActionListener(e -> onCancel());

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        this.pack();
        this.setVisible(true);
    }

    /**
     * <h1>onOK()</h1>
     * <p>Try to put the new socket in the URL of the RESTapi. if the URL don't much, stop the execution</p>
     */
    private void onOK() {
        String socket = textFieldIP.getText();
        Pattern pattern = Pattern.compile(ipPatter);
        if (!pattern.matcher(socket.strip()).matches()){
            JOptionPane.showMessageDialog(null, AppBundle.getResourceBundle().getString("ip_format_error"),AppBundle.getResourceBundle().getString("error"),JOptionPane.ERROR_MESSAGE);
            return;
        }
        RetroFitClient.setURL("http://" + socket + "/");
        RetroFitClient.setRetrofitInstance();
        dispose();
    }

    /**
     * <h1>onCancel()</h1>
     * <p>Close the window without changing the URL</p>
     */
    private void onCancel() {
        dispose();
    }
}

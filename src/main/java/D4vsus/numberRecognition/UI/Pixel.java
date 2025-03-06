package D4vsus.numberRecognition.UI;

import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

/**
 * <h1>Pixel</h1>
 * <p>the minimum part of the model.image matrix</p>
 *
 * @author D4vsus
 */
public class Pixel {
    //variables and objects
    private JPanel pixel;
    private JLabel pixelColor;
    private int colorNumber;
    private final MainWindow mainWindow;

    //methods

    /**
     * <h1>Constructor</h1>
     * <p>Build the object</p>
     *
     * @param mainWindow: {@link MainWindow}
     */
    public Pixel(MainWindow mainWindow){
        this.mainWindow = mainWindow;
        this.pixelColor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.setBlack();
        this.pixelColor.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                whenClicking();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if(SwingUtilities.isLeftMouseButton(e)) whenClicking();
            }

        });
    }

    /**
     * <h1>SetWhite()</h1>
     * <p>Set the pixel in white and set it's number to one</p>
     */
    public void setWhite(){
        this.colorNumber = 1;
        pixelColor.setIcon(new ImageIcon(Objects.requireNonNull(Main.class.getClassLoader().getResource("white.png"))));
    }

    /**
     * <h1>setBlack()</h1>
     * <p>Set the pixel in black and set it's number to cero</p>
     */
    public void setBlack(){
        this.colorNumber = 0;
        pixelColor.setIcon(new ImageIcon(Objects.requireNonNull(Main.class.getClassLoader().getResource("black.png"))));
    }

    /**
     * <h1>getColorNumber()</h1>
     * <p>Get the color number</p>
     *
     * @return int
     */
    public int getColorNumber() {
        return colorNumber;
    }

    /**
     * <h1>whenClicking()</h1>
     * <p>Activates when it's clicked</p>
     * <p>switch the color of the pixel depending of the pencil or rubber</p>
     */
    public void whenClicking(){
        if (mainWindow.isPencil())setWhite();
        else setBlack();
    }

    /**
     * <h1>getPixelPanel()</h1>
     * <p>return the panel of the pixel</p>
     *
     * @return {@link  JPanel}
     */
    public JPanel getPixelPanel() {
        return pixel;
    }
}

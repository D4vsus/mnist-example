package D4vsus.numberRecognition.UI;

import D4vsus.numberRecognition.model.ImageToPredict;
import D4vsus.numberRecognition.network.AppBundle;
import D4vsus.numberRecognition.network.Client;
import D4vsus.numberRecognition.network.ServerListener;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * <h1>MainWindow</h1>
 * <p>Creates the window</p>
 *
 * @author D4vsus
 */
public class MainWindow extends JFrame{
    //variables and objects
    private JPanel mainWindow;
    private JPanel image;
    private JButton send;
    private JButton pencil;
    private JLabel number;
    private JButton restart;
    private JButton rubber;
    private JButton ip;
    private boolean isPencil;
    private Pixel[][] pixelBoard;
    private final Client client;

    //methods

    /**
     * <h1>Constructor</h1>
     * <p>Set up the window</p>
     */
    public MainWindow(){
        this.add(mainWindow);
        this.setTitle("MNIST");
        this.setBounds(50,50,1000,1000);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getClassLoader().getResource("icon.png")));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        setPencil();
        addImageBoard();

        restart.setMnemonic('r');
        send.setMnemonic('s');
        pencil.setMnemonic('p');
        rubber.setMnemonic('r');
        ip.setMnemonic('i');

        restart.addActionListener(e -> setAllBlack());
        send.addActionListener(e -> send());
        pencil.addActionListener(e -> setPencil());
        rubber.addActionListener(e -> setRubber());
        ip.addActionListener(e -> ip());

        client  = new Client(new ServerListener() {
            @Override
            public void onConnect(String result) {
                MainWindow.this.number.setText(result);
            }

            @Override
            public void onFail(String result) {
                MainWindow.this.number.setText(result);
            }
        });

        this.setVisible(true);
    }

    /**
     * <h1>addImageBoard()</h1>
     * <p>Set up the white board to write</p>
     */
    private void addImageBoard(){
        pixelBoard = new Pixel[28][28];
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        for (int y = 0;y < pixelBoard.length;y++){
            for (int x = 0;x < pixelBoard[y].length;x++){
                pixelBoard[y][x] = new Pixel(()->isPencil);
                image.add(pixelBoard[y][x].getPixelPanel(),gridBagConstraints);
                gridBagConstraints.gridx++;
            }
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy++;
        }
    }

    /**
     * <h1>setAllBlack()</h1>
     * <p>set all the pixels to black</p>
     */
    private void setAllBlack(){
        for (Pixel[] pixels : pixelBoard) {
            for (Pixel pixel : pixels) {
                pixel.setBlack();
            }
        }
    }

    /**
     * <h1>setPencil()</h1>
     * <p>Activate the pencil</p>
     */
    private void setPencil(){
        isPencil = true;
        pencil.setEnabled(false);
        rubber.setEnabled(true);
    }

    /**
     * <h1>setRubber()</h1>
     * <p>Activate the Rubber</p>
     */
    private void setRubber(){
        isPencil = false;
        pencil.setEnabled(true);
        rubber.setEnabled(false);
    }

    /**
     * <h1>send()</h1>
     * <p>send the message to the server</p>
     */
    private void send(){
        try {
            Float[][] image = new Float[28][28];
            int y = 0;
            int x = 0;
            for (Pixel[] pixels : pixelBoard){
                for (Pixel pixel:pixels)
                {
                    image[y][x] = (float)pixel.getColorNumber();
                    x++;
                }
                x = 0;
                y++;
            }

            ImageToPredict imageToPredict = new ImageToPredict();
            imageToPredict.setImage(image);

            client.send(imageToPredict);
        } catch (IOException e) {
            this.number.setText(AppBundle.getResourceBundle().getString("connection_error"));
        }
    }

    /**
     * <h1>ip()</h1>
     * <p>Open the dialog to set the Socket to comunicate</p>
     */
    private void ip(){
        new IPWindow();
        client.resetAPI();
    }
}

package D4vsus.numberRecognition;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * <h1>MainWindow</h1>
 * <p>Creates the window</p>
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
    private boolean isPencil;
    private Pixel[][] pixelBoard;

    //methods

    /**
     * <h1>Constructor</h1>
     * <p>Set up the window</p>
     */
    public MainWindow(){
        this.add(mainWindow);
        this.setTitle("MNIST");
        this.setBounds(50,50,1000,1000);
        this.setIconImage(new  ImageIcon(".\\resources\\icon.PNG").getImage());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        setPencil();
        addImageBoard();

        restart.setMnemonic('r');
        send.setMnemonic('s');
        pencil.setMnemonic('p');
        rubber.setMnemonic('r');

        restart.addActionListener(e -> setAllBlack());
        send.addActionListener(e -> send());
        pencil.addActionListener(e -> setPencil());
        rubber.addActionListener(e -> setRubber());

        this.setVisible(true);
    }

    /**
     * <h1>isPencil()</h1>
     * <p>return if it's pencil or rubber</p>
     *
     * @return boolean
     */
    public boolean isPencil(){
        return isPencil;
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
                pixelBoard[y][x] = new Pixel(this);
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
     * <h1>setAllWhite()</h1>
     * <p>set all the pixels to white</p>
     */
    private void setAllWhite(){
        for (Pixel[] pixels : pixelBoard) {
            for (Pixel pixel : pixels) {
                pixel.setWhite();
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
            Client client;
            client = new Client("localhost",9999,"localhost",9999);
            StringBuilder stringBuilder = new StringBuilder();
            for (Pixel[] pixels : pixelBoard){
                for (Pixel pixel:pixels)
                {
                    stringBuilder.append(pixel.getColorNumber()).append(" ");
                }
            }
            this.number.setText(client.send(stringBuilder.toString()));
            client.closeSocket();
        } catch (IOException e) {
            this.number.setText("ERROR: connexion not found, make sure the server is on");
        }
    }
}

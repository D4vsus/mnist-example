package D4vsus.numberRecognition;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * <h1>Client</h1>
 * <p>Send message to the server</p>
 */
public class Client {
    //variables and objects
    Socket socket;

    //methods

    /**
     * <h1>Constructor</h1>
     * <p>Set up the class</p>
     *
     * @param host{@link String}
     * @param port int
     * @param serverHost{@link String}
     * @param serverPort int
     */
    public Client(String host,int port,String serverHost,int serverPort) throws IOException{
            socket = new Socket(host,port);
            while (!socket.isConnected()) socket.connect(new Socket(serverHost,serverPort).getLocalSocketAddress());
    }

    /**
     * <h1>send()</h1>
     * <p>Send the message to the server</p>
     * @param toSend {@link String}
     * @return {@link String}
     */
    public String send(String toSend) throws IOException {
        BufferedOutputStream output  = new BufferedOutputStream(socket.getOutputStream());
        output.write(toSend.getBytes(StandardCharsets.UTF_8));
        output.flush();
        return get();
    }

    /**
     * <h1>get()</h1>
     * <p>return the output of the server</p>
     * @return {@link String}
     */
    public String get() throws IOException {
        BufferedInputStream input = new BufferedInputStream(socket.getInputStream());
        return new String(input.readAllBytes(), StandardCharsets.UTF_8);
    }

    public void closeSocket() throws IOException {
        socket.close();
    }
}

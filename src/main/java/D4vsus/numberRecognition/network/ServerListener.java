package D4vsus.numberRecognition.network;

/**
 * <h1>ServerListener</h1>
 * <p>execute an action when the connection to the server is success or a fail</p>
 *
 * @author D4vsus
 */
public interface ServerListener {
    void onConnect(String result);
    void onFail(String result);
}

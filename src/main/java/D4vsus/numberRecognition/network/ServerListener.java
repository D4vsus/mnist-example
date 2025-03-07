package D4vsus.numberRecognition.network;

/**
 * <h1>ServerListener</h1>
 * <p>execute an action when the connection to the server is success or a fail</p>
 *
 * @author D4vsus
 */
public interface ServerListener {
    /**
     * <h1>onConnect()</h1>
     * <p>Execute when a connection success posting in the end point</p>
     *
     * @param result @{@link String}
     */
    void onConnect(String result);

    /**
     * <h1>onFail()</h1>
     * <p>Execute when a connection fails posting in the end point</p>
     *
     * @param result @{@link String}
     */
    void onFail(String result);
}

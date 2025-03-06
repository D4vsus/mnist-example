package D4vsus.numberRecognition.model;

import java.io.Serializable;

/**
 * <h1>Prediction</h1>
 * <p>Represent the container to get the prediction</p>
 *
 * @author D4vsus
 */
public class Prediction implements Serializable {
    private String prediction;

    public Prediction() {
    }

    public String getContent() {
        return prediction;
    }
}

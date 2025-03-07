package D4vsus.numberRecognition.model;

import java.io.Serializable;

/**
 * <h1>Prediction</h1>
 * <p>Represent the container to get the prediction</p>
 *
 * @author D4vsus
 */
public class Prediction implements Serializable {
    //variables and objects
    private String prediction;

    //methods

    public Prediction() {
    }

    public void setPrediction(String prediction) {
        this.prediction = prediction;
    }

    public String getContent() {
        return prediction;
    }
}

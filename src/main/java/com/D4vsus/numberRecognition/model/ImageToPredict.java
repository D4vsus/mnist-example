package com.D4vsus.numberRecognition.model;

import java.io.Serializable;

/**
 * <h1>ImageToPredict</h1>
 * <p>Represent the container to get the image</p>
 *
 * @author D4vsus
 */
public class ImageToPredict implements Serializable {
    //variables and objects
    private Float[][] image;

    //methods

    public ImageToPredict() {
    }

    public void setImage(Float[][] image) {
        this.image = image;
    }

    public Float[][] getImage() {
        return image;
    }
}

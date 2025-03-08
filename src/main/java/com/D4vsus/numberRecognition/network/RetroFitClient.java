package com.D4vsus.numberRecognition.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * <h1>RetroFitClient</h1>
 * <p>Initialize the retrofit client</p>
 *
 * @author D4vsus
 */
public class RetroFitClient {
    //variables and objects
    private static String url = "http://127.0.0.1:9999/";
    private static Retrofit retrofit = null;

    //methods

    public static Retrofit getRetrofitInstance() {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    /**
     * <h1>setRetrofitInstance()</h1>
     * <p>Set a new RetroFit instance</p>
     */
    public static void setRetrofitInstance() {
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    /**
     * <h1>setURL()</h1>
     * <p>Set the URL for the RetroFit instance</p>
     *
     * @param url {@link String}
     */
    public static void setURL(String url) {
        RetroFitClient.url = url;
    }

    /**
     * <h1>getURL()</h1>
     * <p>Get the URL of the RetroFit instance</p>
     *
     * @return  url {@link String}
     */
    public static String getURL() {
        return url;
    }
}

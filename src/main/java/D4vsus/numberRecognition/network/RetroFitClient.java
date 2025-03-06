package D4vsus.numberRecognition.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * <h1>RetroFitClient</h1>
 * <p>Initialize the retrofit client</p>
 *
 * @author D4vsus
 */
public class RetroFitClient {
    private static String url = "http://127.0.0.1:9999/"; // Replace with your server IP
    private static Retrofit retrofit = null;

    public static Retrofit getRetrofitInstance() {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static void setURL(String url) {
        RetroFitClient.url = url;
    }
}

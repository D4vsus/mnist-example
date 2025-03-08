package com.D4vsus.numberRecognition.network;

import com.D4vsus.numberRecognition.model.ImageToPredict;
import com.D4vsus.numberRecognition.model.Prediction;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.*;

/**
 * <h1>Client</h1>
 * <p>Send message to the server</p>
 *
 * @author D4vid
 */
public class Client {
    //variables and objects
    private APIService service = RetroFitClient.getRetrofitInstance().create(APIService.class);
    private final ServerListener serverListener;

    //methods

    /**
     * <h1>Constructor</h1>
     * <p>Set up the class</p>
     *
     * @param serverListener{@link String}
     */
    public Client(ServerListener serverListener){
        this.serverListener = serverListener;
    }

    /**
     * <h1>send()</h1>
     * <p>Send the message to the server</p>
     *
     * @param toSend {@link String}
     */
    public void send(ImageToPredict toSend) throws IOException {
        service.makePrediction(toSend).enqueue(new Callback<>() {
            @Override
            public void onResponse(@NotNull Call<Prediction> call, @NotNull Response<Prediction> response) {
                if (!response.isSuccessful()){
                    try (ResponseBody reposeBody = response.errorBody()){
                        assert reposeBody != null;
                        serverListener.onConnect(reposeBody.toString());
                    }
                    return;
                }
                assert response.body() != null;
                serverListener.onConnect(response.body().getContent());
            }

            @Override
            public void onFailure(@NotNull Call<Prediction> call, @NotNull Throwable throwable) {
                serverListener.onFail(throwable.toString());
            }
        });
    }

    /**
     * <h1>resetAPI</h1>
     * <p>Reset the API, use for URL change</p>
     */
    public void resetAPI(){
        service = RetroFitClient.getRetrofitInstance().create(APIService.class);
    }
}

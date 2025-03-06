package D4vsus.numberRecognition.network;

import D4vsus.numberRecognition.model.ImageToPredict;
import D4vsus.numberRecognition.model.Prediction;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * <h1>APIService</h1>
 * <p>Create the endpoints to the server</p>
 *
 * @author D4vsus
 */
public interface APIService {
    @POST("api/predict")
    Call<Prediction> makePrediction(@Body ImageToPredict image);
}

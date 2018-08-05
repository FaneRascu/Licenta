package rascu.stefan.twitchanalysis.data.remote;

import rascu.stefan.twitchanalysis.data.models.TopGames;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public class GamesApiServices {
    public GamesApi gamesApi;

    private Call<TopGames> currentCall;

    @Inject
    public GamesApiServices(RetrofitClient retrofitClient){
        gamesApi = retrofitClient.create(GamesApi.class);
    }

    public void getGames(final ResponseHandler<TopGames> callBack, boolean isCancelable){
        Call<TopGames> call = gamesApi.getTopGames();
        if(isCancelable){
            if(currentCall != null){
                return;
            }
            else{
                currentCall = call;
            }
        }

        call.enqueue(new Callback<TopGames>() {
            @Override
            public void onResponse(Call<TopGames> call, Response<TopGames> response) {
                currentCall = null;
                if(response.isSuccessful()){
                    callBack.onSuccess(response.body());
                }
                else {
                    callBack.onError(response.code(), response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<TopGames> call, Throwable t) {
                currentCall = null;
                callBack.onError(-1, t.getMessage());
            }
        });
    }

    public void cancel(){
        if(currentCall != null && !currentCall.isCanceled()){
            currentCall.cancel();
            currentCall = null;
        }
    }

    public interface GamesApi{
        @Headers({"Accept: application/vnd.twitchtv.v5+json"})
        @GET("games/top?limit=50")
        Call<TopGames> getTopGames();
    }
}

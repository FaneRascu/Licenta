package rascu.stefan.twitchanalysis.data.remote;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import rascu.stefan.twitchanalysis.BuildConfig;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "https://api.twitch.tv/kraken";

    private final Retrofit retrofit;

    public RetrofitClient(){
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create());

        if(BuildConfig.DEBUG){
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();
            builder.client(client);
        }

        retrofit = builder.build();
    }

    public <T> T create(Class<T> apiRequestMethods) { return retrofit.create(apiRequestMethods);}
}

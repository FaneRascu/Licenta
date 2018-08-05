package rascu.stefan.twitchanalysis.injection;

import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rascu.stefan.twitchanalysis.TwitchGames;
import rascu.stefan.twitchanalysis.data.remote.RetrofitClient;

@Module
public class AppModule {

    private TwitchGames application;

    public AppModule(TwitchGames application) {this.application = application;}

    @Provides
    @Singleton
    public TwitchGames providesApplication() { return application; }

    @Provides
    @Singleton
    public Picasso providesPicasso(TwitchGames application){
        return new Picasso.Builder(application).downloader(new OkHttp3Downloader(application, Integer.MAX_VALUE))
                .build();
    }

    @Provides
    @Singleton
    public RetrofitClient providesRetrofit() { return new RetrofitClient(); }
}

package rascu.stefan.twitchanalysis.injection.data;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rascu.stefan.twitchanalysis.data.remote.GamesApiServices;
import rascu.stefan.twitchanalysis.data.remote.RetrofitClient;

@Module
public class GameApiModule {
    @Provides
    @Singleton

    public GamesApiServices providesGamesApiServicecs(RetrofitClient retrofitClient){
        return new GamesApiServices(retrofitClient);
    }
}

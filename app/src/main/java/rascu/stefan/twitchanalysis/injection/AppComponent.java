package rascu.stefan.twitchanalysis.injection;


import javax.inject.Singleton;

import dagger.Component;
import rascu.stefan.twitchanalysis.data.cache.GameCache;
import rascu.stefan.twitchanalysis.data.remote.GamesApiServices;
import rascu.stefan.twitchanalysis.data.remote.RetrofitClient;
import rascu.stefan.twitchanalysis.injection.data.CacheModule;
import rascu.stefan.twitchanalysis.injection.data.GameApiModule;
import rascu.stefan.twitchanalysis.ui.details.GameDetailsActivity;
import rascu.stefan.twitchanalysis.ui.lists.GameListAdapter;
import rascu.stefan.twitchanalysis.ui.lists.GameListPresenter;

import com.squareup.picasso.Picasso;

@Singleton
@Component(modules = {AppModule.class, CacheModule.class, GameApiModule.class})
public interface AppComponent {

    RetrofitClient getRetrofit();
    GameCache getCache();
    GamesApiServices getGameApiServices();
    Picasso getPicasso();

    void inject(GameDetailsActivity gameDetailsActivity);
    void inject(GameListAdapter adapter);
    void inject(GameListPresenter presenter);
}

package rascu.stefan.twitchanalysis.injection.data;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rascu.stefan.twitchanalysis.data.cache.GameCache;

@Module
public class CacheModule {
    @Provides
    @Singleton
    GameCache providesCache() { return  new GameCache();}
}

package rascu.stefan.twitchanalysis.injection.ui;


import javax.inject.Singleton;

import dagger.Component;
import rascu.stefan.twitchanalysis.injection.PerActivity;
import rascu.stefan.twitchanalysis.ui.lists.GameListActivity;

@PerActivity
@Singleton
@Component(modules = GameListPresenterModule.class)
public interface GameListPresenterComponent {
    void inject(GameListActivity gameListActivity);
}

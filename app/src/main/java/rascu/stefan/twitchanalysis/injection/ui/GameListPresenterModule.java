package rascu.stefan.twitchanalysis.injection.ui;

import dagger.Module;
import dagger.Provides;
import rascu.stefan.twitchanalysis.injection.PerActivity;
import rascu.stefan.twitchanalysis.ui.lists.GameListContract;
import rascu.stefan.twitchanalysis.ui.lists.GameListPresenter;

@Module
public class GameListPresenterModule {

    private GameListContract.View view;

    private GameListPresenter presenter;

    public GameListPresenterModule(GameListContract.View view) {
        this.view = view;
        presenter = new GameListPresenter(view);
    }

    @PerActivity
    @Provides
    GameListContract.View providesGameListView() { return view; }

    @PerActivity
    @Provides
    GameListPresenter providesGameListPresenter(GameListContract.View view) { return presenter; }

    @PerActivity
    @Provides
    GameListContract.Presenter providesPresenter() { return presenter; }
}

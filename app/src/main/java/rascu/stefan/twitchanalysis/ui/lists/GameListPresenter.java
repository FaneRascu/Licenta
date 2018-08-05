package rascu.stefan.twitchanalysis.ui.lists;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import javax.inject.Inject;

import rascu.stefan.twitchanalysis.R;
import rascu.stefan.twitchanalysis.TwitchGames;
import rascu.stefan.twitchanalysis.data.cache.GameCache;
import rascu.stefan.twitchanalysis.data.models.Game;
import rascu.stefan.twitchanalysis.data.models.TopGames;
import rascu.stefan.twitchanalysis.data.remote.GamesApiServices;
import rascu.stefan.twitchanalysis.data.remote.ResponseHandler;

public class GameListPresenter implements GameListContract.Presenter, ResponseHandler<TopGames> {

    GameListContract.View view;

    @Inject
    GameCache gamesCache;
    @Inject
    GamesApiServices apiServices;

    TopGames topGamesCache;

    @Inject
    public GameListPresenter(GameListContract.View view) {
        this.view = view;
        inject();
    }

    void inject() {
        if (TwitchGames.getInstance() != null) {
            TwitchGames.getInstance().getAppComponent().inject(this);
        }
    }

    @Override
    public void onSuccess(TopGames response) {
        if (view != null) {
            view.dismissRefreshing();
            if (response != null && response.games != null && !response.games.isEmpty()) {
                topGamesCache = response;
                gamesCache.save(response);
                view.updateTopGames(response.games);
            } else {
                //TODO: use cache here if response is malformatted.
                view.showEmptyResult();
            }
        }
    }

    @Override
    public void onError(int responseCode, String message) {
        view.dismissRefreshing();
        view.showError();
        if (isCacheEmpty(topGamesCache)) {
            view.showEmptyResult();
        }
    }

    @Override
    public void onCreate() {
        topGamesCache = retrieveCache();
        refreshTopGames();
    }

    TopGames retrieveCache() {
        TopGames topGames = gamesCache.retrieve();
        if (!isCacheEmpty(topGames)) {
            view.updateTopGames(topGames.games);
        } else {
            view.showLoadingFirst();
        }
        return topGames;
    }

    @Override
    public void refreshTopGames() {
        if (view != null) {
            view.showRefreshing();
            if (isCacheEmpty(topGamesCache)) {
                view.showLoadingFirst();
            }
            apiServices.getGames(this, true);
        }
    }

    boolean isCacheEmpty(TopGames topGamesCache) {
        return topGamesCache == null || topGamesCache.games.isEmpty();
    }

    @Override
    public void onDestroy() {
        apiServices.cancel();
    }

    @Override
    public void onGameSelected(Game game) {
        if (view != null) {
            view.showGameDetails(game);
        }
    }
}

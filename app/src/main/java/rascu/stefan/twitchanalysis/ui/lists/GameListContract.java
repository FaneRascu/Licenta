package rascu.stefan.twitchanalysis.ui.lists;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import rascu.stefan.twitchanalysis.R;
import rascu.stefan.twitchanalysis.data.models.Game;

public class GameListContract {

    public interface View {
        void showRefreshing();
        void dismissRefreshing();
        void showError();
        void updateTopGames(List<Game> games);
        void showGameDetails(Game game);
        void showEmptyResult();
        void showLoadingFirst();
    }

    public interface Presenter {
        void onCreate();
        void refreshTopGames();
        void onDestroy();
        void onGameSelected(Game game);
    }
}

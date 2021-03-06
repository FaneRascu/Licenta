package rascu.stefan.twitchanalysis.ui.details;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rascu.stefan.twitchanalysis.R;
import rascu.stefan.twitchanalysis.TwitchGames;
import rascu.stefan.twitchanalysis.data.models.Game;
import rascu.stefan.twitchanalysis.data.models.GameData;

public class GameDetailsActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.img_game_logo)
    ImageView imgGameLogo;

    @BindView(R.id.txt_game_title)
    TextView gameTitle;

    @BindView(R.id.txt_game_channels)
    TextView gameChannels;

    @BindView(R.id.txt_game_views)
    TextView gameViews;

    @BindView(R.id.img_game)
    ImageView imgGameBox;

    Game currentGame;

    @Inject
    Picasso picasso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_details);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        inject();

        retrieveGame();
        if (currentGame != null) {
            populate();
        }
    }

    void inject() {
        if (TwitchGames.getInstance() != null) {
            TwitchGames.getInstance().getAppComponent().inject(this);
        }
    }

    private void retrieveGame() {
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey(Game.GAME_TAG)) {
            currentGame = (Game) extras.getSerializable(Game.GAME_TAG);
        }
    }

    void populate() {
        GameData gameData = currentGame.gameData;
        if (gameData.logoImages != null && gameData.logoImages.large != null) {
            picasso.load(gameData.logoImages.large)
                    .placeholder(R.drawable.camera_loading_land)
                    .into(imgGameLogo);
        }

        if (gameData.boxImages != null && gameData.boxImages.large != null) {
            picasso.load(gameData.boxImages.large)
                    .placeholder(R.drawable.camera_loading)
                    .into(imgGameBox);
        }

        gameTitle.setText(gameData.name);
        gameChannels.setText(Integer.toString(currentGame.channels));
        gameViews.setText(Integer.toString(currentGame.viewers));
    }
}

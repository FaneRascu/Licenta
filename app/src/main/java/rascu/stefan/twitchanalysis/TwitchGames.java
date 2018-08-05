package rascu.stefan.twitchanalysis;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.paperdb.Paper;
import rascu.stefan.twitchanalysis.injection.AppComponent;
import rascu.stefan.twitchanalysis.injection.AppModule;
import rascu.stefan.twitchanalysis.injection.DaggerAppComponent;
import rascu.stefan.twitchanalysis.injection.ui.DaggerGameListPresenterComponent;

public class TwitchGames extends Application {

    private static TwitchGames twitchApp;

    public AppComponent component;

    public static TwitchGames getInstance() {
        return twitchApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        twitchApp = this;
        Paper.init(this);

        component = createAppComponent();
    }

    public AppComponent getAppComponent() {
        return component;
    }

    AppComponent createAppComponent() {
        return DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }
}

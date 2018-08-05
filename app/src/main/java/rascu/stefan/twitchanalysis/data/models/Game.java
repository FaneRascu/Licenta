package rascu.stefan.twitchanalysis.data.models;

import java.io.Serializable;
import com.squareup.moshi.Json;

public class Game implements Serializable{

    public static final String GAME_TAG = "game";
    public final int viewers;
    public final int channels;

    @Json(name = "game")
    public final GameData gameData;

    public Game(){ this ( 0, 0, null ); }

    public Game( int viewers, int channels, GameData gameData){
        this.viewers = viewers;
        this.channels = channels;
        this.gameData = gameData;
    }
}

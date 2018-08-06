package rascu.stefan.twitchanalysis.data.models;

import com.squareup.moshi.Json;

import java.io.Serializable;

public class GameData  implements Serializable{

    @Json(name = "name")
    public final String name;

    @Json(name = "id")
    public final int id;

    @Json(name = "giantbomb_id")
    public final int giantBombId;

    @Json(name = "box")
    public final Images boxImages;

    @Json(name = "logo")
    public final Images logoImages;

    @Json(name = "_links")
    public final GameLinks links;

    public GameData() { this(null, 0, 0,null, null, null); }

    public GameData( String name, int id, int giantBombId, Images boxImages, Images logoImages, GameLinks links) {
        this.name = name;
        this.id = id;
        this.giantBombId = giantBombId;
        this.boxImages = boxImages;
        this.logoImages = logoImages;
        this.links = links;
    }

}

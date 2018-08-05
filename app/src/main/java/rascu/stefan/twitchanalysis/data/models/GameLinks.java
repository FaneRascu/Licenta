package rascu.stefan.twitchanalysis.data.models;

import java.io.Serializable;

public class GameLinks implements Serializable{
    public final String self;
    public final String next;
    public GameLinks() { this(null, null ); }

    public GameLinks(String self, String next){

        this.self = self;
        this.next = next;
    }
}

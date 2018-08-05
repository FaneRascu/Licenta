package rascu.stefan.twitchanalysis.data.models;

import java.io.Serializable;

public class Images implements Serializable {

    public final String large;
    public final String medium;
    public final String small;
    public final String template;

    public Images() { this(null,null,null,null); }

    public Images( String large, String medium, String small, String template){
        this.large = large;
        this.medium = medium;
        this.small = small;
        this.template = template;
    }
}

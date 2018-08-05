package rascu.stefan.twitchanalysis.data.cache;

import rascu.stefan.twitchanalysis.data.models.TopGames;

import io.paperdb.Paper;

public class GameCache {

    public void save(TopGames topGames) { Paper.book().write(TopGames.TOPGAMES_TAG, topGames);}

    public TopGames retrieve() { return Paper.book().read(TopGames.TOPGAMES_TAG, null); }
}

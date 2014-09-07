package model;

/**
 * Singleton game monitor. The game model will control the passing of turns and
 * random events
 *
 * @author ngraves3
 *
 */
public class GameModel {

    private Player player;

    private static GameModel instance = new GameModel();

    private GameModel() { //private constructor for singleton
    }

    public static GameModel getInstance() {
        return instance;
    }

    public void setPlayer(Player data) {
        player = data;
    }

    public Player getPlayer() {
        return player;
    }

    public void endTurn() {
        //Do something to end the turn or whatever
    }
}

package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import view.Main;

import javax.naming.OperationNotSupportedException;

/**
 * @author Joshua on 9/30/2014.
 */
public class StartScreenController {

    /**
     * Ends the game when the quit button is pressed
     *
     * @param event
     */
    public void endGame(ActionEvent event) {
        Platform.exit();
    }

    /**
     * Loads the users old game and starts it
     *
     * TODO: Implement loading an old game
     *
     * @param actionEvent
     * @throws javax.naming.OperationNotSupportedException
     */
    public void loadSavedGame(ActionEvent actionEvent) throws OperationNotSupportedException {
        throw new OperationNotSupportedException("Loading a saved game is not yet implemented. \n Sorry :(");
    }

    /**
     * Starts a new game by sending the player to the character creation screen.
     *
     * @param actionEvent
     */
    public void startGame(ActionEvent actionEvent) {
        Main.setScene("screens/configscreen.fxml");
    }
}

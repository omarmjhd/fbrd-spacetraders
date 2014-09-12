package view;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javax.naming.OperationNotSupportedException;
import java.io.IOException;

public class Controller {

    private Main mainApp;

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

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
     * @throws OperationNotSupportedException
     */
    public void loadSavedGame(ActionEvent actionEvent) throws OperationNotSupportedException {
        throw new OperationNotSupportedException("Loading a saved game is not yet implemented. \n Sorry :(");
    }

    /**
     * Stats a new game by sending the player to the character creation screen.
     *
     * @param actionEvent
     */
    public void startGame(ActionEvent actionEvent) {
        System.out.println("yo");
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("screens/configscreen.fxml"));
            Parent root = loader.load();

            Scene newPane = new Scene(root);;
            mainApp.setScene(newPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

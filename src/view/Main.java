package view;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import model.core.GameInstance;

/**
 * Main application class.
 *
 * @author Joshua Winchester
 */
public class Main extends Application {

    /**
     * The main Stage for the game.
     */
    private static Stage primaryStage;
    /**
     * The GameInstance.
     */
    private static GameInstance game;

    /**
     * Starts the game.
     *
     * @param primaryStageArg
     *        the Primary window where the game take place.
     */
    @Override
    public void start(Stage primaryStageArg) {
        Main.primaryStage = primaryStageArg;
        primaryStage.setTitle("Space Traders!");
        primaryStage.setResizable(false);
        Main.game = GameInstance.getInstance();

        Main.setScene("screens/startscreen.fxml");
    }

    /**
     * Changes the scene currently displayed.
     *
     * @param fxmlUri
     *        the URI of the .fxml file representing the scene
     */
    public static void setScene(String fxmlUri) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource(fxmlUri));
            Parent root = loader.load();
            Scene newPane = new Scene(root);
            primaryStage.setScene(newPane);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Exits the game.
     */
    @Override
    public void stop() {
        Platform.exit();
    }

    /**
     * Why do I need to javadoc a main method...?
     *
     * @param args
     *        the (useless) args passed in
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Gets the primary window of the game.
     * 
     * @return the Games Stage
     */
    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Gets the Game model.
     *
     * @return The game model
     */
    public static GameInstance getGame() {
        return game;
    }

}

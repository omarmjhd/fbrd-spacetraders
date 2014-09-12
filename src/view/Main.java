package view;

import controller.Controller;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.GameModel;

import java.io.IOException;

public class Main extends Application {

    private static Stage primaryStage;
    private static Controller controller;
    private static GameModel game;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Main.primaryStage = primaryStage;
        primaryStage.setTitle("Space Traders!");
        primaryStage.setResizable(false);
        Main.game = GameModel.getInstance();

        setScene("screens/startscreen.fxml");
    }

    public static void setScene(String fxmlURI) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource(fxmlURI));
            Parent root = loader.load();

            Scene newPane = new Scene(root);
            primaryStage.setScene(newPane);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        Platform.exit();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static GameModel getGame() {
        return game;
    }

    public static Controller getController() {
        return controller;
    }
}

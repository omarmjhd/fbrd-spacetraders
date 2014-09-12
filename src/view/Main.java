package view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    protected Stage primaryStage;
    private Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Space Traders!");
        primaryStage.setResizable(false);

        initRootStage();
    }

    private void initRootStage() {
        try {
            // Loads the root layout from fxml file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("screens/startscreen.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();

            controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setScene(Scene scene) {
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() {
        Platform.exit();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}

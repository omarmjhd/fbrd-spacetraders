package controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import view.Main;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Renee on 12/3/2014.
 */
public class GameOverController implements Initializable{
    /**
     * Button to go to a menu.
     */
    public Button menuButton;
    /**
     * Button to continue to quit.
     */
    public Button quitButton;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void menuAction(ActionEvent actionEvent) {
        Main.setScene("screens/startscreen.fxml");
    }
    public void quitAction(ActionEvent actionEvent) {

        Main.setScene("Platform.exit();");
    }
}

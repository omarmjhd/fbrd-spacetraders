package controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;
import model.core.GameInstance;
import view.Main;



/**
 * @author Joshua on 9/30/2014.
 */
public class StartScreenController implements Initializable {
    /**
     * background image.
     */
    private Image background;
    /**
     * view of the astronaut.
     */
    public ImageView backgroundView;
    /**
     * the pane.
     */
    public Pane pane;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        background = new Image("file:assets/space.jpg");
        backgroundView = new ImageView(background);
        pane.getChildren().add(backgroundView);
        backgroundView.setPreserveRatio(true);
        backgroundView.setFitHeight(480);
        backgroundView.setX(0);
        backgroundView.setY(0);
        backgroundView.toBack();
    }

    /**
     * Ends the game when the quit button is pressed.
     *
     * @param event
     *        the trigger
     */
    public void endGame(ActionEvent event) {
        Platform.exit();
    }

    /**
     * Lets the user load an old .sav file and load it.
     *
     * @param actionEvent
     *        the trigger
     */
    public void loadSavedGame(ActionEvent actionEvent)  {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Save File to Load");
        fileChooser.getExtensionFilters().add(
                        new FileChooser.ExtensionFilter("Save Files", "*.sav"));
        File file = new File("game_saves");
        if (file.exists()) {
            fileChooser.setInitialDirectory(file);
        } else {
            Action response =
                    Dialogs.create()
                            .owner(Main.getPrimaryStage()).title("No Games Saves Detected")
                                            .message("No Game Saves found. "
                                                            + "\nDo you want to "
                                                            + "find a game save file yourself?")
                            .showConfirm();
            if (response == Dialog.Actions.NO || response == Dialog.Actions.CANCEL) {
                return;
            }
        }
        File saveFile = fileChooser.showOpenDialog(Main.getPrimaryStage());
        if (saveFile != null && saveFile.exists()) {
            if (GameInstance.getInstance().loadGameInstance(saveFile.getAbsolutePath())) {
                Main.setScene("screens/planetscreen.fxml");
            } else {
                Action response =
                        Dialogs.create()
                                .owner(Main.getPrimaryStage()).title("Save File Invalid")
                                                .message("The selected Save file is invalid."
                                                                + "\nPlease try another file.")
                                .showError();
            }
        }

    }

    /**
     * Starts a new game by sending the player to the character creation screen.
     *
     * @param actionEvent
     *        the trigger
     */
    public void startGame(ActionEvent actionEvent) {
        Main.setScene("screens/configscreen.fxml");
    }
}

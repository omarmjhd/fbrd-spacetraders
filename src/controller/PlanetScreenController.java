package controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import model.GameInstance;
import model.Planet;
import model.Player;
import org.controlsfx.dialog.Dialogs;
import view.Main;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Joshua on 10/6/2014.
 */
public class PlanetScreenController implements Initializable {
    public Text planetName;
    public Button enterMarket;
    public Button buyFuel;
    public Button travelButton;
    public Label planetText;
    public Pane planetPane;
    public ImageView astronaut;
    private GameInstance gi;
    private Planet curPlanet;
    private Player player;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gi = GameInstance.getInstance();
        curPlanet = gi.getCurrentPlanet();
        player = gi.getPlayer();
        planetPane.setBackground(new Background(new BackgroundFill(Paint.valueOf("black"), null, null)));

        planetName.setText(curPlanet.getName());
        planetText.setText(curPlanet.toString() + "\n\nFuel: " + player.getCurrentFuel()
                                                + "\nMoney: " + player.getMoney());
    }

    /**
     * Displays the marketplace scene
     *
     * @param actionEvent
     */
    public void goToMarket(ActionEvent actionEvent) {
        Main.setScene("screens/marketplacescreen.fxml");
    }

    /**
     * Fills the player's ship with fuel
     * TODO: add validation
     *
     * @param actionEvent
     */
    public void buyFuel(ActionEvent actionEvent) {
        player.buyFuel(player.getMaxFuel() - player.getCurrentFuel());
    }

    /**
     * displays the map screen
     *
     * @param actionEvent
     */
    public void leaveSystem(ActionEvent actionEvent) {
        Main.setScene("screens/mapscreen.fxml");
    }


    /**
     * saves the game
     *
     * @param actionEvent
     */
    public void saveEvent(ActionEvent actionEvent) {
        if (gi.saveGameInstance()){
            Dialogs.create().owner(Main.getPrimaryStage())
                    .title("File Saved")
                    .message("New Save File Created")
                    .lightweight().showInformation();
        } else {
            Dialogs.create().owner(Main.getPrimaryStage())
                    .title("Game Not Saved")
                    .message("No Save File Created")
                    .lightweight().showError();
        }
    }
}

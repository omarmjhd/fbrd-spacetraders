package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

import org.controlsfx.dialog.Dialogs;

import model.core.GameInstance;
import model.core.Planet;
import model.core.Player;
import model.core.TechLevel;

import view.Main;

/**
 * @author Joshua on 10/6/2014.
 */
public class PlanetScreenController implements Initializable {
    /**
     * planet name.
     */
    public Text planetName;
    /**
     * enter market button.
     */
    public Button enterMarket;
    /**
     * enter shipyard button.
     */
    public Button enterShipyard;
    /**
     * buy fuel button.
     */
    public Button buyFuel;
    /**
     * travel button.
     */
    public Button travelButton;
    /**
     * planet information.
     */
    public Label planetText;
    /**
     * planet information.
     */
    public Pane planetPane;
    /**
     * the astronaut view.
     */
    public ImageView astronautView;
    /**
     * the astronaut image.
     */
    public Image astronaut;
    /**
     * game instance.
     */
    private GameInstance gi;
    /**
     * current planet.
     */
    private Planet curPlanet;
    /**
     * the player.
     */
    private Player player;
    /**
     * string to make checkstyle happy.
     */
    private String fuelStr = "\n\nFuel: ";
    /**
     * string to make checkstyle happy.
     */
    private String refuel = "Refuel: ";
    /**
     * string to make checkstyle happy.
     */
    private String credits = " cr";
    /**
     * string to make checkstyle happy.
     */
    private String moneyStr = "\nMoney: ";


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        astronaut = new Image("file:assets/astronaut.png");
        astronautView.setImage(astronaut);
        gi = GameInstance.getInstance();
        curPlanet = gi.getCurrentPlanet();
        player = gi.getPlayer();
        int totalFuelCost = player.getRefuelCost();
        buyFuel.setText(refuel + totalFuelCost + credits);
        planetPane.setBackground(new Background(new BackgroundFill(Paint
                        .valueOf("black"), null, null)));

        planetName.setText(curPlanet.getName());
        planetText.setText(curPlanet.getName() + "\n Resources:  "
                        + curPlanet.getResource().toString() + fuelStr + player.getCurrentFuel()
                        + moneyStr
                        + player.getMoney());
        if (curPlanet.getTechLevel().equals(TechLevel.POST_INDUSTRIAL)) {
            enterShipyard.setVisible(true);
        } else if (curPlanet.getTechLevel().equals(TechLevel.HI_TECH)) {
            enterShipyard.setVisible(true);
        } else {
            enterShipyard.setVisible(false);
        }
        planetText.setText(curPlanet.toString() + fuelStr + player.getCurrentFuel() + moneyStr
                        + player.getMoney());
    }

    /**
     * Displays the marketplace scene.
     *
     * @param actionEvent
     *        the trigger
     */
    public void goToMarket(ActionEvent actionEvent) {
        Main.setScene("screens/marketplacescreen.fxml");
    }

    /**
     * Displays the shipyard scene.
     *
     * @param actionEvent
     *        the trigger
     */
    public void goToShipyard(ActionEvent actionEvent) {
        Main.setScene("screens/shipyardscreen.fxml");
    }

    /**
     * Fills the player's ship with fuel. fuel update after buying.
     *
     * @param actionEvent
     *        the trigger
     */
    public void buyFuel(ActionEvent actionEvent) {

        player.buyFuel();
        planetText.setText(curPlanet.toString() + fuelStr + player.getCurrentFuel() + moneyStr
                        + player.getMoney());
        buyFuel.setText(refuel + 0 + credits);
    }

    /**
     * Displays the map screen.
     *
     * @param actionEvent
     *        the trigger
     */
    public void leaveSystem(ActionEvent actionEvent) {
        Main.setScene("screens/mapscreen.fxml");
    }


    /**
     * Saves the game.
     *
     * @param actionEvent
     *        the trigger
     */
    public void saveEvent(ActionEvent actionEvent) {
        if (gi.saveGameInstance()) {
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

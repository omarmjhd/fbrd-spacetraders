package controller;

import java.net.URL;
import java.util.ResourceBundle;
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
import model.TechLevel;
import org.controlsfx.dialog.Dialogs;
import view.Main;

/**
 * @author Joshua on 10/6/2014.
 */
public class PlanetScreenController implements Initializable {
    public Text planetName;
    public Button enterMarket;
    public Button enterShipyard;
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
        int totalFuelCost = calculateFuelQuantity() * player.getFuelCost();
        buyFuel.setText("Refuel: " + totalFuelCost + " cr");
        planetPane.setBackground(new Background(new BackgroundFill(Paint.valueOf("black"), null, null)));

        planetName.setText(curPlanet.getName());
        planetText.setText(curPlanet.getName() + "\n Resources:  " + curPlanet.getResource().toString()
                                + "\n\nFuel: " + player.getCurrentFuel() + "\nMoney: " + player.getMoney());
        if(curPlanet.getTechLevel().equals(TechLevel.POST_INDUSTRIAL)) {
            enterShipyard.setVisible(true);
        } else if(curPlanet.getTechLevel().equals(TechLevel.HI_TECH)) {
            enterShipyard.setVisible(true);
        } else {
            enterShipyard.setVisible(false);
        }
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
     * Displays the shipyard scene
     *
     * @param actionEvent
     */
    public void goToShipyard(ActionEvent actionEvent) {
        Main.setScene("screens/shipyardscreen.fxml");
    }

    /**
     * Ensures player doesnt over buy fuel
     *
     * @return amount of fuel player can buy
     */
    private int calculateFuelQuantity() {
        int fuelAmount = player.getMaxFuel() - player.getCurrentFuel();

        if ((fuelAmount * player.getFuelCost()) > player.getMoney()) {
            fuelAmount = player.getMoney() / player.getFuelCost();
        }

        return fuelAmount;
    }

    /**
     * Fills the player's ship with fuel TODO: add validation TODO: make the
     * fuel update after buying
     *
     * @param actionEvent
     */
    public void buyFuel(ActionEvent actionEvent) {

        player.buyFuel(calculateFuelQuantity());
        planetText.setText(curPlanet.toString() + "\n\nFuel: "
                        + player.getCurrentFuel() + "\nMoney: "
                        + player.getMoney());
        buyFuel.setText("Refuel: " + 0 + " cr");
    }

    /**
     * Displays the map screen
     *
     * @param actionEvent
     */
    public void leaveSystem(ActionEvent actionEvent) {
        Main.setScene("screens/mapscreen.fxml");
    }


    /**
     * Saves the game
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

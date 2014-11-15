package controller;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import model.commerce.Shipyard;
import model.core.GameInstance;
import model.core.Planet;
import model.core.Player;
import model.core.Ship;
import model.core.TechLevel;

import view.Main;

/**
 * Created by Renee on 10/21/2014.
 */
public class ShipyardScreenController implements Initializable {

    /**
     * for choosing a ship.
     */
    public ComboBox<String> shipComboBox;
    /**
     * player's money.
     */
    public Label playerMoney;
    /**
     * ship's specs.
     */
    public Label shipAttributes;
    /**
     * planet's shipyard.
     */
    public Text shipyardTitle;
    /**
     * cost of ship.
     */
    public Label shipCost;
    /**
     * trade button.
     */
    public Button tradeButton;
    /**
     * player's ship.
     */
    public Label playershipLabel;
    /**
     * current shipyard.
     */
    private Shipyard shipyard;
    /**
     * player's ship.
     */
    private Ship currentShip;
    /**
     * the player.
     */
    private Player player;
    /**
     * what ships the player can choose.
     */
    private ObservableList<String> options;
    /**
     * cost to buy the chosen ship.
     */
    private int costToBuy;
    /**
     * the ship that the player chose.
     */
    private Ship playership;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GameInstance gm = GameInstance.getInstance();
        this.player = gm.getPlayer();
        playership = player.getShip();
        playershipLabel.setText(
                playership.toString());

        Planet currentPlanet = gm.getCurrentPlanet();
        shipyard = currentPlanet.getShipyard();
        shipyardTitle.setText(currentPlanet.getName() + " Shipyard");

        //can't trade until have chosen a ship
        tradeButton.setDisable(true);

        //shows which are available on each planet
        if (currentPlanet.getTechLevel().equals(TechLevel.POST_INDUSTRIAL)) {
            options = FXCollections.observableArrayList(
                    Ship.flea().toString()
            );
        } else if (currentPlanet.getTechLevel().equals(TechLevel.HI_TECH)) {
            options = FXCollections.observableArrayList(
                Ship.flea().toString(),
                Ship.gnat().toString(),
                Ship.firefly().toString(),
                Ship.mosquito().toString(),
                Ship.bumblebee().toString()
        );
        }
        shipComboBox.setItems(options);
        if (shipComboBox.getValue() == null) {
            shipAttributes.setText("");
        }

        if (currentShip == null) {
            shipCost.setText("");
        }

        playerMoney.setText("" + player.getMoney());
    }

    /**
     * When a ship is chosen from the combo box, updates the label.
     *
     * @param actionEvent
     *        the trigger
     */
    public void chooseShip(ActionEvent actionEvent) {
        String cur = shipComboBox.getValue();

        currentShip = Ship.makeShip(cur);

        Map<String, Integer> specs = currentShip.specs();

        String newLine = "\n";
        String text = cur + newLine;
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> e : specs.entrySet()) {
            sb.append(e.getKey()).append(": ").append(e.getValue()).append(newLine);
        }
        text += sb;

        //ship cost label
        shipAttributes.setText(text);
        costToBuy = shipyard.costToBuy(currentShip);
        shipCost.setText("" + costToBuy);
        if (costToBuy <= 0) {
            shipCost.setTextFill(Color.GREEN);
        } else {
            shipCost.setTextFill(Color.RED);
        }

        if (costToBuy > player.getMoney()) {
            tradeButton.setDisable(true);
        } else {
            tradeButton.setDisable(false);
        }
    }

    /**
     * Goes to ship upgrade screen.
     *
     * @param actionEvent
     *        the trigger
     */
    public void goToUpgradeShip(ActionEvent actionEvent) {
        Main.setScene("screens/shipupgradescreen.fxml");
    }

    /**
     * Go to planet screen.
     *
     * @param actionEvent
     *        the trigger
     */
    public void goToPlanet(ActionEvent actionEvent) {
        Main.setScene("screens/planetscreen.fxml");
    }

    /**
     * Does the actual action of selling your ship.
     *
     * @param actionEvent
     *        the trigger
     */
    public void trade(ActionEvent actionEvent) {
        player.changeShip(currentShip);
        player.subtractMoney(costToBuy);
        playership = player.getShip();
        playershipLabel.setText(playership.toString());
        playerMoney.setText("" + player.getMoney());
        costToBuy = shipyard.costToBuy(currentShip);
        shipCost.setText("" + costToBuy);
    }

}

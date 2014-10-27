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
import model.GameInstance;
import model.Planet;
import model.Player;
import model.Ship;
import model.Shipyard;
import model.TechLevel;
import view.Main;

/**
 * Created by Renee on 10/21/2014.
 */
public class ShipyardScreenController implements Initializable{

    public ComboBox shipComboBox;
    public Label playerMoney;
    public Label shipAttributes;
    public Text shipyardTitle;
    public Label shipCost;
    public Button tradeButton;
    public Label playershipLabel;

    private Shipyard shipyard;
    private Ship currentShip;
    private Planet currentPlanet;
    private GameInstance gm;
    private Player player;
    private ObservableList<String> options;
    private int costToBuy;
    private Ship playership;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.gm = GameInstance.getInstance();
        this.player = gm.getPlayer();
        playership = player.getShip();
        playershipLabel.setText(
                playership.toString());
        currentPlanet = gm.getCurrentPlanet();
        shipyard = currentPlanet.getShipyard();
        shipyardTitle.setText(currentPlanet.getName() + " Shipyard");

        //can't trade until have chosen a ship
        tradeButton.setDisable(true);

        //shows which are available on each planet
        if(currentPlanet.getTechLevel().equals(TechLevel.POST_INDUSTRIAL)) {
            options =
                    FXCollections.observableArrayList(
                    Ship.flea().toString()
            );
        } else if(currentPlanet.getTechLevel().equals(TechLevel.HI_TECH)) {
            options = FXCollections.observableArrayList(
                Ship.flea().toString(),
                Ship.gnat().toString(),
                Ship.firefly().toString(),
                Ship.mosquito().toString(),
                Ship.bumblebee().toString()
        );
        }
        shipComboBox.setItems(options);
        if(shipComboBox.getValue() == null) {
            shipAttributes.setText("");
        }

        if(currentShip == null) {
            shipCost.setText("");
        }

        playerMoney.setText("" + player.getMoney());
    }

    /**
     * When a ship is chosen from the combo box, updates the label
     * @param actionEvent
     */
    public void chooseShip(ActionEvent actionEvent) {
        String cur = (String) shipComboBox.getValue();
        if (cur.equals("Flea")) {
            currentShip = Ship.flea();
        } else if(cur.equals("Gnat")) {
            currentShip = Ship.gnat();
        } else if(cur.equals("Firefly")) {
            currentShip = Ship.firefly();
        } else if(cur.equals("Mosquito")) {
            currentShip = Ship.mosquito();
        } else if(cur.equals("Bumblebee")) {
            currentShip = Ship.bumblebee();
        }
        Map<String, Integer> specs = currentShip.specs();
        String text = cur + "\n";
        for (Map.Entry<String, Integer> e : specs.entrySet()) {
            text += e.getKey() + ": " + e.getValue() + "\n";
        }

        //ship cost label
        shipAttributes.setText(text);
        costToBuy = shipyard.costToBuy(currentShip);
        shipCost.setText("" + costToBuy);
        if(costToBuy <= 0) {
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
     * Goes to ship upgrade screen
     * @param actionEvent
     */
    public void goToUpgradeShip(ActionEvent actionEvent) {
        Main.setScene("screens/shipupgradescreen.fxml");
    }

    /**
     * Go to planet screen
     * @param actionEvent
     */
    public void goToPlanet(ActionEvent actionEvent) {
        Main.setScene("screens/planetscreen.fxml");
    }

    /**
     * Does the actual action of selling your ship
     * @param actionEvent
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

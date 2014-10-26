package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import model.*;
import view.Main;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by Renee on 10/21/2014.
 */
public class ShipyardScreenController implements Initializable{

    public ComboBox shipComboBox;
    public Button buyButton;
    public Button sellButton;
    public Label playerMoney;
    public Label shipAttributes;
    public Text shipyardTitle;
    public Label shipCost;

    private Shipyard shipyard;
    private Ship currentShip;
    private Planet currentPlanet;
    private Marketplace marketplace;
    private GameInstance gm;
    private Player player;
    private ObservableList<String> options;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.gm = GameInstance.getInstance();
        this.player = gm.getPlayer();
        currentPlanet = gm.getCurrentPlanet();
        shipyard = currentPlanet.getShipyard();
        shipyardTitle.setText(currentPlanet.getName() + " Shipyard");

        options = FXCollections.observableArrayList(
                Ship.flea().toString(),
                Ship.gnat().toString(),
                Ship.firefly().toString(),
                Ship.mosquito().toString(),
                Ship.bumblebee().toString()
        );


//        if(currentPlanet.getTechLevel().equals(TechLevel.POST_INDUSTRIAL)) {
//            options =
//                    FXCollections.observableArrayList(
//                    Ship.flea().toString()
//            );
//        } else if(currentPlanet.getTechLevel().equals(TechLevel.HI_TECH)) {
//            options = FXCollections.observableArrayList(
//        Ship.flea().toString(),
//                Ship.gnat().toString(),
//                Ship.firefly().toString(),
//                Ship.mosquito().toString(),
//                Ship.bumblebee().toString()
//        );
//        }
        shipComboBox.setItems(options);
        if(shipComboBox.getValue() == null) {
            shipAttributes.setText("");
        }
    }

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
        shipAttributes.setText(text);
    }

    public void goToUpgradeShip(ActionEvent actionEvent) {
        Main.setScene("screens/shipupgradescreen.fxml");
    }

    public void goToPlanet(ActionEvent actionEvent) {
        Main.setScene("screens/planetscreen.fxml");
    }
}

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

import java.awt.*;
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
    public TextArea shipAttributes;
    public Text shipyardTitle;
    private Shipyard shipyard;
    private Ship currentShip;
    private Planet currentPlanet;
    private Marketplace marketplace;
    private GameInstance gm;
    private Player player;
    private ObservableList<Ship> options;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.gm = GameInstance.getInstance();
        this.player = gm.getPlayer();
        currentPlanet = gm.getCurrentPlanet();
        shipyard = currentPlanet.getShipyard();
        shipyardTitle.setText(currentPlanet.getName() + " Shipyard");


        if(currentPlanet.getTechLevel().equals(TechLevel.POST_INDUSTRIAL)) {
            options =
                    FXCollections.observableArrayList(
                    Ship.flea()
            );
        } else if(currentPlanet.getTechLevel().equals(TechLevel.HI_TECH)) {
            options =
                    FXCollections.observableArrayList(
                            Ship.flea(),
                            Ship.gnat(),
                            Ship.firefly(),
                            Ship.mosquito(),
                            Ship.bumblebee()
                    );
        }
        shipComboBox = new ComboBox(options);
    }

    public void chooseShip(ActionEvent actionEvent) {
        currentShip = (Ship) shipComboBox.getValue();
        Map<String, Integer> specs = currentShip.specs();
        String text = "";
        for(int i = 0; i < specs.size(); i++) {
            text += specs;
        }
        for (Map.Entry<String, Integer> e : specs.entrySet()) {
            text += e.getKey() + ": " + e.getValue() + "\n";
        }
        shipAttributes.setText(text);
    }

}

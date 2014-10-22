package controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import model.*;

import java.awt.*;
import java.net.URL;
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
    private Planet currentPlanet;
    private Marketplace marketplace;
    private GameInstance gm;
    private Player player;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Sets various instance variables
        this.gm = GameInstance.getInstance();
        this.player = gm.getPlayer();
        this.shipComboBox = new ComboBox();
        currentPlanet = gm.getCurrentPlanet();
        shipyardTitle.setText(currentPlanet.getName() + " Shipyard");

    }

    public Ship chooseShip(ActionEvent actionEvent) {
        return (Ship) shipComboBox.getValue();
    }

}

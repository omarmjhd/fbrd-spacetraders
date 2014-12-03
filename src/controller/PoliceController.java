package controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.text.Text;
import model.commerce.Goods;
import model.commerce.Marketplace;

import model.core.GameInstance;
import model.core.Player;
import model.core.Ship;
import model.core.TechLevel;
import model.events.Encounter;
import view.Main;


/**
 * Created by Renee on 12/3/2014.
 */

public class PoliceController implements Initializable{

    /**
     * Button to continue to the planet
     */
    public Button planetButton;
    /**
     * Text showing what's happening with the encounter
     */
    public Text encounterMessage;
    /**
     * Player affected by the encounter.
     */
    private Player player;

    /**
     * Use a marketplace for the trader.
     */
    private Marketplace marketplace;

    /**
     * The game instance.
     */
    private GameInstance gm;

    /**
     * player's ship.
     */
    private Ship ship;

    /**
     * encounter to use.
     */
    private Encounter encounter;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gm = GameInstance.getInstance();
        player = gm.getPlayer();
        encounter = new Encounter(player, "police");
        encounterMessage.setText(encounter.encounter());
    }

    public void planetAction(ActionEvent actionEvent) {

        Main.setScene("screens/planetscreen.fxml");
    }
}
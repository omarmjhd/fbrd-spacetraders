package controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import model.commerce.Goods;
import model.commerce.Marketplace;
import model.core.GameInstance;
import model.core.Player;
import model.core.Ship;
import model.core.TechLevel;
import model.encounters.Encounter;

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
     * the pane.
     */
    public Pane pane;
    /**
     * Text showing what's happening with the encounter
     */
    public Text encounterMessage;
    /**
     * background image.
     */
    private Image background;
    /**
     * view of the astronaut.
     */
    public ImageView backgroundView;
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
        background = new Image("file:assets/policeship.jpg");
        backgroundView = new ImageView(background);
        pane.getChildren().add(backgroundView);
        backgroundView.setPreserveRatio(true);
        backgroundView.setFitHeight(480);
        backgroundView.setX(0);
        backgroundView.setY(0);
        backgroundView.toBack();
    }

    public void planetAction(ActionEvent actionEvent) {

        Main.setScene("screens/planetscreen.fxml");
    }
}
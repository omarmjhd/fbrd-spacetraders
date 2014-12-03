package controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import model.core.GameInstance;
import model.core.Player;
import model.encounters.Encounter;

import view.Main;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Renee on 12/3/2014.
 */
public class TraderController implements Initializable{

    /**
     * Button to go to a trade screen.
     */
    public Button tradeButton;
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
     * The game instance.
     */
    private GameInstance gm;
    /**
     * encounter to use.
     */
    private Encounter encounter;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gm = GameInstance.getInstance();
        player = gm.getPlayer();
        encounter = new Encounter(player, "trader");
        encounterMessage.setText("");
    }

    public void planetAction(ActionEvent actionEvent) {
        Main.setScene("screens/planetscreen.fxml");
    }
    public void tradeAction(ActionEvent actionEvent) {

        Main.setScene("screens/marketplacescreen.fxml");
    }
}

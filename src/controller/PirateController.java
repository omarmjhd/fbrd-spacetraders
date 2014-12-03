package controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.text.Text;

import model.commerce.Marketplace;
import model.core.GameInstance;
import model.core.Player;
import model.encounters.Encounter;

import view.Main;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Renee on 12/3/2014.
 */
public class PirateController implements Initializable {
    /**
     * Pirate's health.
     */
    public ProgressBar pirateHealthBar;
    /**
     * Player's health.
     */
    public ProgressBar playerHealthBar;
    /**
     * To flee.
     */
    public Button fleeButton;
    /**
     * To fight.
     */
    public Button fightButton;
    /**
     * To surrender.
     */
    public Button surrenderButton;
    /**
     * Button that displays after the fight is over.
     */
    public Button toPlanet;
    /**
     * To talk to the player.
     */
    public Text encounterMessage;
    /**
     * The player.
     */
    private Player player;
     /**
     * The game instance.
     */
    private GameInstance gm;
    /*
     * Encounter to use.
     */
    private Encounter encounter;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gm = GameInstance.getInstance();
        player = gm.getPlayer();
        encounter = new Encounter(player, "pirate");
        encounterMessage.setText("");
        toPlanet.setVisible(false);
    }

    /**
     * Sends player to planet.
     * @param actionEvent
     */
    public void planetAction(ActionEvent actionEvent) {

        Main.setScene("screens/planetscreen.fxml");
    }

    /**
     * Flees.
     * @param actionEvent
     */
    public void fleeAction(ActionEvent actionEvent) {

        encounterMessage.setText(encounter.flee());
        toPlanet.setVisible(true);
    }

    /**
     * Surrenders.
     * @param actionEvent
     */
    public void surrenderAction(ActionEvent actionEvent) {

        encounterMessage.setText(encounter.surrender());
        toPlanet.setVisible(true);
    }

    /**
     * Fights.
     * @param actionEvent
     */
    public void fightAction(ActionEvent actionEvent) {
        encounterMessage.setText(encounter.fight());
        pirateHealthBar.setProgress(encounter.getEncounterHealth() / 10.0);
        playerHealthBar.setProgress(encounter.getPlayerHealth() / 10.0);
        if (encounter.getEncounterHealth() <= 0) {
            toPlanet.setVisible(true);
        }
        if (encounter.getPlayerHealth() <= 0) {
            //TODO: GAME OVER
            toPlanet.setVisible(true);
        }
    }
}

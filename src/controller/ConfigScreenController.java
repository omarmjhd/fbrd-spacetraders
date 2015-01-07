package controller;


import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import model.core.GameInstance;
import model.core.Player;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;
import view.Main;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class handles all button presses and handing of information from the
 * model to the view.
 *
 * @author Joshua Winchester
 */
public class ConfigScreenController implements Initializable {

    /**
     * engineering slider.
     */
    public Slider engSlide;
    /**
     * trade slider.
     */
    public Slider tradeSlide;
    /**
     * fight slider.
     */
    public Slider fightSlide;
    /**
     * invest slider.
     */
    public Slider investSlide;
    /**
     * pilot slider.
     */
    public Slider pilotSlide;
    /**
     * player name.
     */
    public TextField playerName;
    /**
     * skill points.
     */
    public Label skillPoints;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sliderListener();
    }



    /**
     * Listens for change in slider values and calls sliders().
     */
    public void sliderListener() {
        // Handle Slider value change events.
        engSlide.valueProperty().addListener((observable, oldValue, newValue) -> {
                sliders();
            });
        tradeSlide.valueProperty().addListener((observable, oldValue, newValue) -> {
                sliders();
            });
        fightSlide.valueProperty().addListener((observable, oldValue, newValue) -> {
                sliders();
            });
        investSlide.valueProperty().addListener((observable, oldValue, newValue) -> {
                sliders();
            });
        pilotSlide.valueProperty().addListener((observable, oldValue, newValue) -> {
                sliders();
            });
    }



    /**
     * Updates the skillPoints Label.
     *
     */
    public void sliders() {
        int currentInt = 0;
        int pilotSkill = (int) pilotSlide.getValue();
        int fightSkill = (int) fightSlide.getValue();
        int engSkill = (int) engSlide.getValue();
        int tradeSkill = (int) tradeSlide.getValue();
        int investSkill = (int) investSlide.getValue();

        int total = pilotSkill + fightSkill + engSkill + tradeSkill + investSkill;
        currentInt = 15 - total;

       //displays the skillPoints left
        skillPoints.setText("" + currentInt);
    }

    /**
     * Creates the Game from the slider values when the user presses the button.
     *
     * @param actionEvent
     *        the trigger
     */
    public void createGame(ActionEvent actionEvent) {
        int pilotSkill = (int) pilotSlide.getValue();
        int fightSkill = (int) fightSlide.getValue();
        int engSkill = (int) engSlide.getValue();
        int tradeSkill = (int) tradeSlide.getValue();
        int investSkill = (int) investSlide.getValue();
        String name = playerName.getText();

        int total = pilotSkill + fightSkill + engSkill + tradeSkill + investSkill;

        if (total <= 15 && !name.equals("")) {
            Player player =
                            new Player(name, pilotSkill, fightSkill, engSkill,
                                            tradeSkill, investSkill);
            Action response =
                    Dialogs.create().owner(Main.getPrimaryStage())
                    .title("Player Created!")
                    .message("Use this Character?: \n" + player.toString())
                    .lightweight().showConfirm();

            if (response == Dialog.Actions.YES) {
                Main.getGame().setPlayer(player);
                Main.getGame().getPlayer().addMoney(100000);
                System.out.println("Player Created");
                System.out.println(Main.getGame().getPlayer());
                GameInstance gm = GameInstance.getInstance();
                gm.createUniverse();
                Main.setScene("screens/mapscreen.fxml");
            }
        } else if (total > 15) {
            Dialogs.create()
                    .owner(Main.getPrimaryStage())
                    .title("Too Many Skill Points")
                    .message("You have used " + total + " skill points."
                            + " You are only allowed 15. \n Try again.")
                    .lightweight().showWarning();
        } else {
            Dialogs.create()
                    .owner(Main.getPrimaryStage())
                    .title("Invalid Name")
                    .message("You have not entered a name.")
                    .lightweight().showWarning();

        }


    }

    /**
     * Sends the user back to the main menu.
     *
     * @param actionEvent
     *        the trigger
     */
    public void returnToMainMenu(ActionEvent actionEvent) {
        Main.setScene("screens/startscreen.fxml");
    }
}

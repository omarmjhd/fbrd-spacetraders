package controller;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import model.GameInstance;
import model.Player;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;
import view.Main;

import javax.naming.OperationNotSupportedException;

/**
 * This class handles all button presses and handing of information from the
 * model to the view.
 *
 * @author Joshua Winchester
 */
public class Controller {

    public Slider engSlide;
    public Slider tradeSlide;
    public Slider fightSlide;
    public Slider investSlide;
    public Slider pilotSlide;
    public TextField playerName;
    public Label skillPoints;

    /**
     * Ends the game when the quit button is pressed
     *
     * @param event
     */
    public void endGame(ActionEvent event) {
        Platform.exit();
    }

    /**
     * Loads the users old game and starts it
     *
     * TODO: Implement loading an old game
     *
     * @param actionEvent
     * @throws OperationNotSupportedException
     */
    public void loadSavedGame(ActionEvent actionEvent) throws OperationNotSupportedException {
        throw new OperationNotSupportedException("Loading a saved game is not yet implemented. \n Sorry :(");
    }

    /**
     * Listens for change in slider values and calls sliders()
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
     * Starts a new game by sending the player to the character creation screen.
     *
     * @param actionEvent
     */
    public void startGame(ActionEvent actionEvent) {
        Main.setScene("screens/configscreen.fxml");
    }

    /**
     * Updates the skillPoints Label
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
     */
    public void createGame(ActionEvent actionEvent) {
        int pilotSkill = (int) pilotSlide.getValue();
        int fightSkill = (int) fightSlide.getValue();
        int engSkill = (int) engSlide.getValue();
        int tradeSkill = (int) tradeSlide.getValue();
        int investSkill = (int) investSlide.getValue();
        String name = playerName.getText();

        int total = pilotSkill + fightSkill + engSkill + tradeSkill + investSkill;

        if (total <= 30 && !name.equals("")) {
            Player player = new Player(name, pilotSkill, fightSkill, engSkill, tradeSkill, investSkill);
            Action response = Dialogs.create().owner(Main.getPrimaryStage()).title("Player Created!").message("Use this Character?: \n" + player.toString()).lightweight().showConfirm();
            if (response == Dialog.Actions.YES) {
                Main.getGame().setPlayer(player);
                Main.getGame().getPlayer().addMoney(1000);
                System.out.println("Player Created");
                System.out.println(Main.getGame().getPlayer());
                GameInstance gm = GameInstance.getInstance();
                gm.createUniverse();
                Main.setScene("screens/mapscreen.fxml");
            }
        } else if (total >= 15) {
            Action response = Dialogs.create().owner(Main.getPrimaryStage()).title("Too Many Skill Points").message("You have used " + total + " skill points. You are only allowed 15. \n Try again.").lightweight().showWarning();
        } else {
            Action response = Dialogs.create().owner(Main.getPrimaryStage()).title("Invalid Name").message("You have not entered a name.").lightweight().showWarning();
        }

    }

    /**
     * Sends the user back to the main menu
     *
     * @param actionEvent
     */
    public void returnToMainMenu(ActionEvent actionEvent) {
        Main.setScene("screens/startscreen.fxml");
    }
}

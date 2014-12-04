package controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import model.commerce.Goods;
import model.core.GameInstance;
import model.core.Player;
import model.core.TechLevel;
import model.encounters.Encounter;

import view.Main;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * Created by Renee on 12/3/2014.
 */
public class TraderController implements Initializable{

    /**
     * Button to buy.
     */
    public Button buyButton;
    /**
     * Sell.
     */
    public Button sellButton;
    /**
     * Button to continue to the planet
     */
    public Button planetButton;
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
     * the pane.
     */
    public Pane pane;
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
    /**
     * private good for the trader to sell/buy.
     */
    private Goods cargo;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gm = GameInstance.getInstance();
        player = gm.getPlayer();
        encounter = new Encounter(player, "trader");
        encounter.encounter();
        background = new Image("file:assets/tradership.png");
        backgroundView = new ImageView(background);
        pane.getChildren().add(backgroundView);
        backgroundView.setPreserveRatio(true);
        backgroundView.setFitHeight(480);
        backgroundView.setX(0);
        backgroundView.setY(0);
        backgroundView.toBack();
        Random ran = new Random();
        int goodindex = ran.nextInt(Goods.values().length);
        cargo = Goods.values()[goodindex];
        encounterMessage.setText("This trader is selling and buying " +
                cargo.toString() +" for " + encounter.getMarketplace().getPrice(cargo) + ".");
        if (player.cargoRoomLeft() < 1) {
            buyButton.setDisable(true);
        }
        if (encounter.getMarketplace().getPrice(cargo) > player.getMoney()) {
            buyButton.setDisable(true);
        }
        if (player.getCargo().contains(cargo) == false) {
            sellButton.setDisable(true);
        }
    }

    public void planetAction(ActionEvent actionEvent) {
        Main.setScene("screens/planetscreen.fxml");
    }
    public void sellAction(ActionEvent actionEvent) {
        encounter.sell(cargo);
        if (player.getCargo().contains(cargo) == false) {
            sellButton.setDisable(true);
        }
        if (encounter.getMarketplace().getMerchandise().contains(cargo) == true) {
            buyButton.setDisable(false);
        }
    }
    public void buyAction(ActionEvent actionEvent) {
        encounter.buy(cargo);
        if (player.cargoRoomLeft() < 1) {
            buyButton.setDisable(true);
        }
        if (encounter.getMarketplace().getPrice(cargo) > player.getMoney()) {
            buyButton.setDisable(true);
        }
        if (encounter.getMarketplace().getMerchandise().contains(cargo) == false) {
            buyButton.setDisable(true);
        }
        if (player.getCargo().contains(cargo) == true) {
            sellButton.setDisable(false);
        }
    }
}

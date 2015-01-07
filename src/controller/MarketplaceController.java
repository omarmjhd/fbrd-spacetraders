package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import model.GameInstance;
import model.Marketplace;
import model.Planet;
import model.Player;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author jwinchester6
 */
public class MarketplaceController implements Initializable{
    public Text marketTitle;
    public ListView shipView;
    public ListView marketView;
    public Button buyButton;
    public Button sellButton;
    public Label playerMoney;
    private Planet currentPlanet;
    private Marketplace marketplace;
    private GameInstance gm;
    private Player player;
    private ObservableList<String> marketGoods = FXCollections.observableArrayList();
    private ObservableList<String> shipGoods = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.gm = GameInstance.getInstance();
        System.out.println("INIT");
        this.player = gm.getPlayer();
        currentPlanet = gm.getCurrentPlanet();
        marketTitle.setText(currentPlanet.getName() + " Market");
        marketplace = currentPlanet.enterMarket(gm.getPlayer());
        for (int i = 0; i < marketplace.getSupply(); i++) {
            marketGoods.add(marketplace.getMerchandise().name());
        }
        marketView.setItems(marketGoods);
        playerMoney.setText(String.valueOf(player.getMoney()));
    }

    public void buy(ActionEvent actionEvent) {
        marketplace.playerBuys(1);
        marketGoods.remove(0);
        if (marketGoods.size() == 0) {
            buyButton.setDisable(true);
        }
        playerMoney.setText(String.valueOf(player.getMoney()));
    }
}

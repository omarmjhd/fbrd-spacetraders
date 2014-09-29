package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.util.Callback;
import model.*;

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
    private ObservableList<Goods> marketGoods = FXCollections.observableArrayList();
    private ObservableList<Goods> shipGoods = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        marketView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        shipView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        this.gm = GameInstance.getInstance();
        this.player = gm.getPlayer();
        currentPlanet = gm.getCurrentPlanet();
        marketTitle.setText(currentPlanet.getName() + " Market");
        marketplace = currentPlanet.enterMarket(gm.getPlayer());
        for (int i = 0; i < marketplace.getSupply(); i++) {
            marketGoods.add(marketplace.getMerchandise());
        }
        shipGoods.addAll(player.getCargo());

        marketView.setCellFactory(new Callback<ListView<Goods>, ListCell<Goods>>() {
            @Override
            public ListCell<Goods> call(ListView<Goods> param) {
                return new GoodsCell<Goods>();
            }
        });
        marketView.setItems(marketGoods);
        shipView.setItems(shipGoods);
        playerMoney.setText(String.valueOf(player.getMoney()));
    }

    public void buy(ActionEvent actionEvent) {

        marketplace.playerBuys(1);
        shipGoods.add(marketGoods.remove(marketView.getSelectionModel().getSelectedIndex()));
        if (marketGoods.size() == 0) {
            buyButton.setDisable(true);
        }
        if (shipGoods.size() > 0) {
            sellButton.setDisable(false);
        }
        playerMoney.setText(String.valueOf(player.getMoney()));
    }

    public void sell(ActionEvent actionEvent) {
        marketplace.playerSells(player.getCargo().get(shipView.getSelectionModel().getSelectedIndex()), 1);
        marketGoods.add(shipGoods.remove(shipView.getEditingIndex()));
        if (shipGoods.size() == 0) {
            sellButton.setDisable(true);
        }
        if (marketGoods.size() > 0) {
            buyButton.setDisable(false);
        }
        playerMoney.setText(String.valueOf(player.getMoney()));
    }

    static class GoodsCell<Goods> extends ListCell<Goods> {
        @Override
        public void updateItem(Goods item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null) {

            }
        }
    }
}


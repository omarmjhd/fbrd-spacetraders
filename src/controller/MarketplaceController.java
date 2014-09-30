package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.text.Text;
import javafx.util.Callback;
import model.GameInstance;
import model.Goods;
import model.Marketplace;
import model.Planet;
import model.Player;

/**
 * @author jwinchester6
 */
public class MarketplaceController implements Initializable{
    public Text marketTitle;
    public ListView<Goods> shipView;
    public ListView<Goods> marketView;
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
        //for (int i = 0; i < marketplace.getSupply(); i++) {
        //  marketGoods.add(marketplace.getMerchandise());
        //}
        marketGoods.addAll(marketplace.getMerchandise());
        shipGoods.addAll(player.getCargo());

        marketView.setCellFactory(new Callback<ListView<Goods>, ListCell<Goods>>() {
            @Override
            public ListCell<Goods> call(ListView<Goods> param) {
                return new GoodsCell(marketplace.getPrice());
            }
        });

        marketView.setItems(marketGoods);
        shipView.setItems(shipGoods);
        playerMoney.setText(String.valueOf(player.getMoney()));
    }

    public void buy(ActionEvent actionEvent) {
        boolean canBuy = marketplace.getPrice() <= player.getMoney() && player.cargoRoomLeft() >= 1;

        if (canBuy) {
            marketplace.playerBuys(1);

            shipGoods.add(marketGoods.remove(marketView.getSelectionModel().getSelectedIndex()));

            //marketGoods = marketView.getItems();
            //marketView.setItems(marketGoods);
            //marketView.edit(arg0);
        }

        canBuy = marketplace.getPrice() <= player.getMoney() && player.cargoRoomLeft() >= 1;

        if (marketGoods.size() == 0 || !canBuy) {
            buyButton.setDisable(true);
        }
        if (shipGoods.size() > 0 || canBuy) {
            sellButton.setDisable(false);
        }
        playerMoney.setText(String.valueOf(player.getMoney()));
    }

    public void sell(ActionEvent actionEvent) {
        //Need to validate the selection index
        //i.e. is something actually selected.
        //if you press sell with nothing selected we get bad Exceptions
        marketplace.playerSells(player.getCargo().get(
                        shipView.getSelectionModel().getSelectedIndex()));
        marketGoods.add(shipGoods.remove(shipView.getSelectionModel().getSelectedIndex()));
        //marketplace.playerSells(shipGoods.remove(shipView.getSelectionModel().getSelectedIndex()));
        if (shipGoods.size() == 0) {
            sellButton.setDisable(true);
        }
        if (marketGoods.size() > 0) {
            buyButton.setDisable(false);
        }
        playerMoney.setText(String.valueOf(player.getMoney()));
    }

    static class GoodsCell extends ListCell<Goods> {

        private int price;

        public GoodsCell(int price) {
            this.price = price;
        }
        @Override
        public void updateItem(Goods item, boolean empty) {
            super.updateItem(item, empty);

            if (item != null) {
                setText(item.toString() + " | " + price + " cr");
            } else {
                setText("");
            }
        }
    }
}


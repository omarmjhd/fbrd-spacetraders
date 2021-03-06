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

import model.commerce.Goods;
import model.commerce.Marketplace;
import model.core.GameInstance;
import model.core.Planet;
import model.core.Player;

import view.Main;

/**
 *
 * @author jwinchester6
 */
public class MarketplaceController implements Initializable {
    /**
     * market title.
     */
    public Text marketTitle;
    /**
     * ship's good.
     */
    public ListView<Goods> shipView;
    /**
     * market's good.
     */
    public ListView<Goods> marketView;
    /**
     * buy button.
     */
    public Button buyButton;
    /**
     * sell button.
     */
    public Button sellButton;
    /**
     * player's money.
     */
    public Label playerMoney;
    /**
     * current planet.
     */
    private Planet currentPlanet;
    /**
     * planet's marketplace.
     */
    private Marketplace marketplace;
    /**
     * game instance.
     */
    private GameInstance gm;
    /**
     * the player.
     */
    private Player player;
    /**
     * market's goods.
     */
    private ObservableList<Goods> marketGoods = FXCollections.observableArrayList();
    /**
     * shipy's goods.
     */
    private ObservableList<Goods> shipGoods = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Sets only one item allowed to be selected in each ListView for easier buying selling code
        marketView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        shipView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        // Sets various instance variables
        this.gm = GameInstance.getInstance();
        this.player = gm.getPlayer();
        currentPlanet = gm.getCurrentPlanet();
        marketTitle.setText(currentPlanet.getName() + " Market");
        marketplace = currentPlanet.getMarketplace();
        marketGoods.addAll(marketplace.getMerchandise());

        // Enables the sell button if the player has goods to sell
        if (player.getCargo().size() != 0) {
            sellButton.setDisable(false);
            shipGoods.addAll(player.getCargo());
        }

        if (player.cargoRoomLeft() < 1) {
            buyButton.setDisable(true);
        }

        // Allows us to set our ListCell's text to what we needs and bind them to a good
        marketView.setCellFactory(new Callback<ListView<Goods>, ListCell<Goods>>() {
            @Override
            public ListCell<Goods> call(ListView<Goods> param) {
                return new GoodsCell();
            }
        });
        shipView.setCellFactory(marketView.getCellFactory());

        // Loads the ListViews and displays the players cash
        marketView.setItems(marketGoods);
        shipView.setItems(shipGoods);
        marketView.getSelectionModel()
                        .selectedItemProperty()
                        .addListener((observable, oldValue, newValue) -> {
                                if (newValue == null) {
                                    return;
                                }
                                if (marketplace.getPrice(newValue) > player.getMoney()) {
                                    buyButton.setDisable(true);
                                } else if (marketplace.getPrice(newValue) <= player.getMoney()
                                                && player.cargoRoomLeft() != 0) {
                                    buyButton.setDisable(false);
                                }
                            });
        playerMoney.setText(String.valueOf(player.getMoney()));
    }

    /**
     * Buys the selected item and does some validation.
     *
     * @param actionEvent
     *        the trigger
     */
    public void buy(ActionEvent actionEvent) {
        Goods boughtGood = marketView.getSelectionModel().getSelectedItem();
        if (player.cargoRoomLeft() < 1) {
            buyButton.setDisable(true);
            return;
        }

        if (boughtGood == null) {
            return;
        }
        if (marketplace.getPrice(boughtGood) > player.getMoney()) {
            buyButton.setDisable(true);
            return;
        }

        marketplace.playerBuys(boughtGood);
        marketGoods.remove(boughtGood);
        shipGoods.add(boughtGood);


        if (marketGoods.size() == 0 || player.cargoRoomLeft() == 0) {
            buyButton.setDisable(true);
        }

        if (shipGoods.size() > 0) {
            sellButton.setDisable(false);
        }
        playerMoney.setText(String.valueOf(player.getMoney()));
    }

    /**
     * Sells the selected item and does some validation.
     *
     * @param actionEvent
     *        the trigger
     */
    public void sell(ActionEvent actionEvent) {
        if (shipView.getSelectionModel().getSelectedItem() == null) {
            return;
        }

        marketplace.playerSells(player.getCargo().get(
                        shipView.getSelectionModel().getSelectedIndex()));
        marketGoods.add(shipGoods.remove(shipView.getSelectionModel().getSelectedIndex()));
        if (shipGoods.size() == 0) {
            sellButton.setDisable(true);
        }
        if (marketGoods.size() > 0) {
            buyButton.setDisable(false);
        }
        playerMoney.setText(String.valueOf(player.getMoney()));
    }

    /**
     * List Cell subclass that displays items correctly.
     *
     */
    class GoodsCell extends ListCell<Goods> {

        @Override
        public void updateItem(Goods item, boolean empty) {
            super.updateItem(item, empty);

            //second null check an ugly way to stop marketplace from crashing
            if (item != null && marketplace.getPrice(item) != null) {
                setText(item.toString() + "  " + marketplace.getPrice(item));

            } else {
                setText("");
            }
        }
    }

    /**
     * goes back to the planet screen.
     *
     * @param actionEvent
     *        the trigger
     */
    public void goToPlanet(ActionEvent actionEvent) {
        Main.setScene("screens/planetscreen.fxml");
    }
}


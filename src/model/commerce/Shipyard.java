package model.commerce;

import java.io.Serializable;
import model.core.Player;
import model.core.Ship;
import model.upgrades.HasPrice;

public class Shipyard implements Serializable {

    /* Knows about market to get prices for goods */
    private Marketplace marketplace;

    /* Knows about player to get ship */
    private Player player;

    public Shipyard(Marketplace marketplace, Player player) {
        this.marketplace = marketplace;
        this.player = player;
    }

    /**
     * Returns the cost (or income) to buy the ship. If cost is negative, than
     * the player will receive money upon changing the ship. the cost of the
     * ship is decreased for each item in the cargo
     *
     * @param shipToBuy
     *        the ship to buy
     * @return the net cost of the ship to buy
     */
    public int costToBuy(Ship shipToBuy) {

        int total = shipToBuy.getPrice();

        for (Goods cargo : player.getCargo()) {
            total -= marketplace.getPrice(cargo);
        }

        for (HasPrice upgrade : player.getUpgrades()) {
            total -= upgrade.getPrice();
        }

        total -= player.getShipBasePrice();

        return total;
    }

    /**
     * The player buys a ship. This method removes the ship from the player and
     * assigns the new ship to the player. It also removes or adds the
     * appropriate amount of money from the player
     *
     * @param shipToBuy
     *        the ship the player wants to buy
     */
    public void buyShip(Ship shipToBuy) {
        player.subtractMoney(costToBuy(shipToBuy));
        player.changeShip(shipToBuy);
    }

}

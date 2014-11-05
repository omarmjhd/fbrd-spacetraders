package model.commerce;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import model.core.Planet;
import model.core.Player;
import model.core.TechLevel;

/**
 * Class to act as a service provider between Planet and Player for trading
 * items
 *
 * @author ngraves3
 *
 */
public class Marketplace implements Serializable {


    /*
     * Planet tech affects the price of a good in the market
     */
    private TechLevel planetTech;

    /*
     * Set of map of goods this planet can sell inherently (i.e. produce)
     */
    private Map<Goods, Integer> productionPrices;

    /*
     * Map of goods this planet can buy. Min tech to produce is always >= min
     * tech to use
     *
     * K(productionPrices) is a subset of K(purchasePrices)
     */
    private Map<Goods, Integer> purchasePrices;

    private List<Goods> supply;

    private Player player;

    private int tradeSkillModifier;

    /**
     * Instantiates a marketplace with the given planet's tech level
     *
     * @param tech
     *        the tech level of the planet
     * @param player
     *        the Player
     */
    public Marketplace(TechLevel tech, Player player) {
        this.planetTech = tech;
        this.player = player;
        productionPrices = new HashMap<Goods, Integer>();
        purchasePrices = new HashMap<Goods, Integer>();
        tradeSkillModifier = new Random().nextInt(2 * player.getTradeSkill() + 1);


        /*
         * Initialize goods the planet can produce
         */
        for (Goods item : Goods.values()) {
            /* Check if planetTech is higher than minTech for the good */
            if (planetTech.compareTo(item.minTechToProduce()) >= 0) {
                productionPrices.put(item, adjustPriceOnSkills(item));
            }
        }


        /*
         * Initialize goods the planet can buy. Copy over production goods and
         * add any other goods it can sell.
         */
        purchasePrices.putAll(productionPrices);

        for (Goods item : Goods.values()) {
            if (!productionPrices.containsKey(item)
                            && planetTech.compareTo(item.minTechToUse()) >= 0) {
                purchasePrices.put(item, adjustPriceOnSkills(item));
            }
        }

        Random rand = new Random();
        int quantity = rand.nextInt(9) + 10;

        supply = new ArrayList<Goods>(quantity);

        Goods[] usableGoods = productionPrices.keySet().toArray(new Goods[productionPrices.size()]);

        while (supply.size() < quantity) {
            supply.add(usableGoods[rand.nextInt(productionPrices.size())]);
        }

    }

    private int adjustPriceOnSkills(Goods good) {
        return Math.max(good.price(planetTech) - tradeSkillModifier, 1);
    }

    /**
     * Player buys {quantity} amount of goods from the planet. If the player
     * can't buy that many, don't let him/her.
     *
     * @param item
     *        the amount of Goods to buy
     * @return boolean whether the good was actually bought
     */
    public boolean playerBuys(Goods item) {
        if (player.cargoRoomLeft() >= 1 && supply.remove(item)) {
            player.addCargo(item);
            player.subtractMoney(purchasePrices.get(item));
            return true;
        }
        return false;
    }

    /**
     * Player sells goods to the market.
     *
     * @param cargo
     *        the item to sell
     * @return whether the good was sold or not
     */
    public boolean playerSells(Goods cargo) {
        // if cargo not in keys, can't sell to market
        if (!purchasePrices.containsKey(cargo)) {
            return false;
        }

        //check if cargo is in player's inventory
        Goods removed = player.removeCargo(cargo);
        if (removed != null) { //item exists in cargo
            player.addMoney(purchasePrices.get(removed));
            supply.add(removed);
        }

        return removed != null;

    }

    /**
     * Returns an List of the Merchandise.
     *
     * @return List of the Goods this Marketplace sells
     */
    public List<Goods> getMerchandise() {
        return supply;
    }

    /**
     * Gets the price for a specific type of Goods
     *
     * @param item
     *        a Good
     * @return price of the Good
     */
    public Integer getPrice(Goods item) {
        if (purchasePrices.containsKey(item)) {
            return purchasePrices.get(item);
        }

        return null;
    }
}

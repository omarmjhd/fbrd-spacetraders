package model.events;

import java.util.List;
import java.util.Map;
import java.util.Random;

import model.commerce.Goods;

import model.core.Player;
import model.core.Ship;
import model.core.TechLevel;

/**
 * A class representing encounters with traders, pirates, and police.
 */
public class Encounter {

    /**
     * Player affected by the encounter.
     */
    private Player player;

    /**
     * Player's cargo.
     */
    private List<Goods> cargo;

    /**
     * Set of map of goods this planet can sell inherently (i.e. produce).
     */
    private Map<Goods, Integer> purchasePrices;

    /**
     * Trader's cargo.
     */
    private List<Goods> traderCargo;

    /**
     * Random used for generating effect of player's skill levels.
     */
    private Random rand;

    /**
     * Starting health of encounter.
     */
    private int encounterHealth = 15;

    /**
     * Starting health of player.
     */
    private int playerHealth = 15;

    /**
     * Fight modifier based on player's fight skill level.
     */
    private int fightSkillModifier;

    /**
     * Trade modifier based on player's trade skill level.
     */
    private int tradeSkillModifier;

    /**
     * Array of encounters.
     */
    private String[] encounters;

    /**
     * Type of encounter.
     */
    private String encounterType;


    /**
     * Constructor for the encounters.
     *
     * @param player
     *        player affected by encounter
     */
    public Encounter(Player player) {

        this.player = player;
        rand = new Random();

    }

    /**
     * Encounter with a trader, pirate, or police.
     *
     * @return String notifying an encounter
     */
    public String encounter() {

        fightSkillModifier = player.getFightingSkill();
        tradeSkillModifier = player.getTradeSkill();
        cargo = player.getCargo();

        if (encounters == null) {
            encounters = new String[] {
                    "You encountered a trader!",
                    "You encountered a pirate!",
                    "You encountered the police!"};
        }

        if (rand.nextInt(10) == 0) {
            int type = rand.nextInt(encounters.length);
            if (type == 0) {
                encounterType = "trader";
                /* Generates random tech level for setting prices for the trader. */
                TechLevel tech = TechLevel.values()[new Random().nextInt(TechLevel.values().length)];
                for (Goods g : Goods.values()) {
                    purchasePrices.put(g, Math.max(g.price(tech) - tradeSkillModifier, 1));
                }
                for (Goods g : purchasePrices.keySet()) {
                    traderCargo.add(g);
                }
            } else if (type == 1) {
                encounterType = "pirate";
            } else if (type == 2) {

                Ship ship = player.getShip();
                boolean isVisible = ship.isVisible();

                if (!isVisible) {

                    return "You managed to pass the police without notice with your cloaking gadget!";

                } else {

                    if (!cargo.contains(Goods.FIREARMS) && !cargo.contains(Goods.NARCOTICS)) {
                        return "The police inspected your cargo and didn't find anything suspicious. " +
                                "They apologize for the inconvenience.";
                    }

                    if (cargo.contains(Goods.FIREARMS)) {
                        ship.removeCargo(Goods.FIREARMS);
                    }

                    if (cargo.contains(Goods.NARCOTICS)) {
                        ship.removeCargo(Goods.NARCOTICS);
                    }
                    return "FREEZE! The police inspected your cargo and found illegal goods! " +
                            "The items have been taken from your possession.";

                }
            }
            return encounters[type];
        }

        return "";
    }

    /**
     * Attack the encounter.
     *
     * @return String indicating if either the encounter or player was beaten
     */
    public String fight() {

        encounterHealth -= fightSkillModifier;
        playerHealth -= new Random().nextInt(2);

        if (encounterHealth <= 0) {
            return String.format("You beat the %s!", encounterType);
        }
        if (playerHealth <= 0) {
            return "You died.";
        }

        return "";

    }

    /**
     * Flee the encounter.
     *
     * @return String indicating that the player fled
     */
    public String flee() {

        return String.format("You managed to flee the %s!", encounterType);

    }

    /**
     * Surrenders all goods to the encounter.
     */
    public void surrender() {

        for (Goods g : cargo) {
            player.removeCargo(g);
        }

    }

    /**
     * Sells good to trader.
     *
     * @param item
     *        Good to be sold
     * @return boolean whether or not good was sold
     */
    public boolean sell(Goods item) {

        Goods removed = player.removeCargo(item);
        if (removed != null) {
            player.addMoney(purchasePrices.get(removed));
            return true;
        }
        return false;
    }

    /**
     * Buys good from trader.
     *
     * @param item
     *        Good to be bought
     * @return boolean whether or not good was bought
     */
    public boolean buy(Goods item) {

        if (player.cargoRoomLeft() >= 1 && traderCargo.remove(item)) {
            player.addCargo(item);
            player.subtractMoney(purchasePrices.get(item));
            return true;
        }
        return false;
    }

}
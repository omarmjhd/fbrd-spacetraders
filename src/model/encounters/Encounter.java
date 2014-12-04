package model.encounters;

import java.util.List;
import java.util.Random;

import model.commerce.Goods;
import model.commerce.Marketplace;
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
     * Random used for generating effect of player's skill levels.
     */
    private Random rand;

    /**
     * Starting health of encounter.
     */
    private int encounterHealth = 10;

    /**
     * Starting health of player.
     */
    private int playerHealth = 10;

    /**
     * Fight modifier based on player's fight skill level.
     */
    private int fightSkillModifier;

    /**
     * Use a marketplace for the trader.
     */
    private Marketplace marketplace;

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
        fightSkillModifier = player.getFightingSkill() + 1;
        cargo = player.getCargo();
    }

    public Encounter(Player player, String type) {
        this.player = player;
        encounterType = type;
        fightSkillModifier = player.getFightingSkill() + 1;
        cargo = player.getCargo();
    }

    /**
     * Encounter with a trader, pirate, or police.
     *
     * @return String notifying an encounter
     */
    public String encounter() {

        if (encounters == null) {

            encounters = new String[]{
                    "null encounter",
                    "You encountered a trader!",
                    "You encountered a pirate!",
                    "You encountered the police!"};

        }

        if (encounterType == null) {
            int type = rand.nextInt(encounters.length);
                if (type == 0) {

                    return null;

                } else if (type == 1) {

                    encounterType = "trader";

                } else if (type == 2) {

                    encounterType = "pirate";

                } else if (type == 3) {
                    encounterType = "police";
                }
            } else {
                // The below lines always throw a NPE because we are in the branch where
                // encounterType == null
                int type = 0;
                if (encounterType.equals("trader")) {
                    type = 1;
                    TechLevel tech = TechLevel.values()[new Random().nextInt(TechLevel.values().length)];
                    marketplace = new Marketplace(tech, player);
                } else if (encounterType.equals("pirate")) {
                    type = 2;
                } else if (encounterType.equals("police")) {
                    type = 3;
                    Ship ship = player.getShip();
                    boolean isVisible = ship.isVisible();

                    if (!isVisible) {

                        return "You managed to pass the police without notice because of your cloaking gadget!";

                    } else {

                        if (!cargo.contains(Goods.FIREARMS) && !cargo.contains(Goods.NARCOTICS)) {
                            return "The police inspected your cargo and didn't find anything suspicious. \n" +
                                    "They apologize for the inconvenience.";
                        }

                        while (cargo.contains(Goods.FIREARMS)) {
                            cargo.remove(Goods.FIREARMS);
                        }

                        while (cargo.contains(Goods.NARCOTICS)) {
                            cargo.remove(Goods.NARCOTICS);
                        }

                        return "FREEZE! The police inspected your cargo and found illegal goods! \n" +
                                "The items have been taken from your possession.";

                    }
                }
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
        playerHealth -= new Random().nextInt(4);

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
    public String surrender() {

        for (Goods g : cargo) {
            player.removeCargo(g);
        }

        return String.format("You surrendered your goods to the %s.", encounterType);

    }

    /**
     * Sells good to trader.
     *
     * @param item
     *        Good to be sold
     * @return boolean whether or not good was sold
     */
    public boolean sell(Goods item) {

        return marketplace.playerSells(item);

    }

    /**
     * Buys good from trader.
     *
     * @param item
     *        Good to be bought
     * @return boolean whether or not good was bought
     */
    public boolean buy(Goods item) {

        return marketplace.playerBuys(item);

    }

    public String getEncounterType() {
        return encounterType;
    }

    public int getEncounterHealth() {
        return encounterHealth;
    }

    public int getPlayerHealth() {
        return playerHealth;
    }

    public Marketplace getMarketplace() {
        return marketplace;
    }

}

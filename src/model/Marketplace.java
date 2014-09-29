package model;

import java.util.ArrayList;
import java.util.Random;

public class Marketplace {


    /*
     * Planet tech affects the price of a good in the market
     */
    private TechLevel planetTech;

    /*
     * Right now a planet can sell a finite amount of one type of Goods. We
     * may want to implement a limited number of goods per planet -Nick
     */
    private Goods merchandise;

    private int supply;

    private int price;

    private Player player;

    public Marketplace(Planet planet, Player player) {
        this.planetTech = planet.getTechLevel();
        this.supply = new Random().nextInt(9) + 1;
        this.merchandise = planet.getResource();
        this.player = player;
        price = merchandise.price(planetTech);
    }

    /**
     * Player buys {quantity} amount of goods from the planet. If the player
     * can't buy that many, don't let him/her.
     *
     * @param quantity
     *        the amount of Goods to buy
     */
    public void playerBuys(int quantity) {

        player.addCargo(merchandise);
        player.subtractMoney(price);
        supply--;

    }

    /**
     * Player sells goods to the market.
     *
     * @param cargo
     * @param quantity
     */
    public boolean playerSells(Goods cargo) {

        Goods removed = player.removeCargo(cargo);
        if (removed != null) { //item exists in cargo
            player.addMoney(removed.price(planetTech));
        }

        return removed != null;

    }

    /**
     * Returns an ArrayList of the Merchandise. I.e. supply * the item
     *
     * @return ArrayList of the Goods this Marketplace sells
     */
    public ArrayList<Goods> getMerchandise() {
        ArrayList<Goods> retval = new ArrayList<>(supply);
        for (int i = 0; i < supply; i++) {
            retval.add(merchandise);
        }

        return retval;
    }

    public int getPrice() {
        return price;
    }

    /**
     * Returns a representation of an object in the format: Water 1000cr
     *
     * @return String representation of the merchandise object
     */
    public String itemString() {
        String firstChar = merchandise.name().substring(0, 1).toUpperCase();
        String rest = merchandise.name().substring(1).toLowerCase();
        String name = firstChar + rest;
        return name + "   " + price + "cr";
    }

    public int getSupply() {
        return supply;
    }
}

package model;

import java.util.Random;

public class TradeInteraction {


    /*
     * Planet tech affects the price of a good in the market
     */
    private TechLevel planetTech;

    /*
     * Right now a planet can sell an infinite amount of one type of Goods. We
     * may want to implement a limited number of goods per planet -Nick
     */
    private Goods merchandise;

    private int supply;

    private int price;

    private Player player;

    public TradeInteraction(Planet planet, Player player) {
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
        if (quantity <= player.cargoRoomLeft() && (price * quantity) <= player.getMoney()
                        && quantity <= supply) {
            for (int i = 0; i < quantity; i++) {
                player.addCargo(merchandise);
            }
        } else if (quantity > player.cargoRoomLeft()) {
            System.out.println("Not enought room for cargo; only " + player.cargoRoomLeft()
                            + " available");
        } else if ((price * quantity) > player.getMoney()) {
            System.out.println("Not enough money to buy that many goods");
        } else if (quantity > supply) {
            System.out.println("The market doesn't have that many goods");
        }
    }

    /**
     * Player sells goods to the market.
     *
     * @param cargo
     * @param quantity
     */
    public void playerSells(Goods cargo, int quantity) {
        for (int i = 0; i < quantity; i++) {
            Goods removed = player.removeCargo(cargo);
            if (removed != null) { //item exists in cargo
                player.addMoney(removed.price(planetTech));
            }
        }
    }

}

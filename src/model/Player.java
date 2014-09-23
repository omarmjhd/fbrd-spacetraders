package model;

/**
 * This class represents the Player and his state.
 *
 * @author ngraves3
 *
 */
public class Player {

    private String name;
    private final int PILOT_SKILL;
    private final int FIGHT_SKILL;
    private final int ENG_SKILL;
    private final int TRADE_SKILL;
    private final int INVEST_SKILL;

    private int money = 0;

    private Ship ship;

    public Player(String name, int pilotSkill, int fightSkill, int engSkill, int tradeSkill, int investSkill) {
        this.name = name;
        this.ENG_SKILL = engSkill;
        this.FIGHT_SKILL = fightSkill;
        this.TRADE_SKILL = tradeSkill;
        this.INVEST_SKILL = investSkill;
        this.PILOT_SKILL = pilotSkill;
        this.ship = Ship.makeGnat();

    }

    /**
     * Adds money to the player's money
     *
     * @param income
     *        amount of money to add
     */
    public void addMoney(int income) {
        this.money += income;
    }

    public void subtractMoney(int money) {
        addMoney(-money);
    }

    public void buyShip(Ship otherShip) {
        /*
         * Need to transfer all items from current ship to new ship
         */
        while (ship.cargoRoomLeft() < ship.cargoSize() && otherShip.cargoRoomLeft() > 0) {
            otherShip.addCargo(ship.removeCargo());
        }

        /**
         * Need to the same thing for weapons, shields, crew, gadgets
         */
    }

    /**
     * Adds cargo to the player's ship
     *
     * @param item
     * @param quantity
     */
    public void buyCargo(Goods item, int quantity) {
        if (quantity <= ship.cargoRoomLeft() && (item.price() * quantity) <= money) {
            for (int i = 0; i < quantity; i++) {
                ship.addCargo(item);
            }
        } else if (quantity > ship.cargoRoomLeft()) {
            System.out.println("Not enought room for cargo; only " + ship.cargoRoomLeft() + " available");
        } else if ((item.price() * quantity) > money) {
            System.out.println("Not enough money to buy that many goods");
        }

    }

    public void sellCargo(Goods item, int quantity) {
        int income = 0;
        for (int i = 0; i < quantity; i++) {
            Goods removed = ship.removeCargo(item);
            if (removed != null) { //item exists in cargo
                income += removed.price();
            }
        }
        addMoney(income);
    }

    @Override
    public String toString() {
        String retval = "Name: " + name + "\n";
        retval += "Piloting skill: " + PILOT_SKILL + "\n";
        retval += "Fighting skill: " + FIGHT_SKILL + "\n";
        retval += "Engineering skill: " + ENG_SKILL + "\n";
        retval += "Trading Skill: " + TRADE_SKILL + "\n";
        retval += "Investing Skill: " + INVEST_SKILL + "\n";
        return retval;
    }
}

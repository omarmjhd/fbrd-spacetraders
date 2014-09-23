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
        this.ship = Ship.GNAT;

    }

    public int getMoney() {
        return money;
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
        ship.transferCargo(otherShip);
        ship = otherShip;
    }

    public int cargoRoomLeft() {
        return ship.cargoRoomLeft();
    }

    public void addCargo(Goods item) {
        ship.addCargo(item);
    }

    public Goods removeCargo(Goods item) {
        return ship.removeCargo(item);
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

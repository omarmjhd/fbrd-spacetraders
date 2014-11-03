package model.core;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import model.commerce.Goods;
import model.upgrades.Crew;
import model.upgrades.HasPrice;
/**
 * This class represents the Player and his state.
 *
 * @author ngraves3
 *
 */
public class Player implements HasSkills, Serializable {

    private String name;

    private SkillSet skills;

    private int money = 0;

    private Ship ship;

    public Player(String name, int pilotSkill, int fightSkill, int engSkill,
        int tradeSkill, int investSkill) {
        this.name = name;
        skills = new SkillSet(tradeSkill, fightSkill, engSkill,
                                        pilotSkill, investSkill);
        this.ship = Ship.gnat();

    }

    public int getMoney() {
        return money;
    }

    /**
     * Adds money to the player's money
     *
     * @param income amount of money to add
     */
    public void addMoney(int income) {
        this.money += income;
    }

    public void subtractMoney(int money) {
        addMoney(-money);
    }

    public Ship getShip() {
        return ship;
    }

    public void changeShip(Ship otherShip) {
        ship = otherShip;
    }

    public int cargoRoomLeft() {
        return ship.cargoRoomLeft();
    }

    public void addCargo(Goods item) {
        ship.addCargo(item);
    }

    public List<Goods> getCargo() {
        return ship.getCargo();
    }

    public List<HasPrice> getUpgrades() {
        List<HasPrice> upgrades = new LinkedList<HasPrice>();

        for (HasPrice weapon : ship.getWeapons()) {
            upgrades.add(weapon);
        }

        for (HasPrice shield : ship.getShields()) {
            upgrades.add(shield);
        }

        for (HasPrice crew : ship.getCrew()) {
            upgrades.add(crew);
        }

        for (HasPrice gadget : ship.getGadgets()) {
            upgrades.add(gadget);
        }

        return upgrades;
    }

    public Goods removeCargo(Goods item) {
        return ship.removeCargo(item);
    }

    /**
     * Travels the distance. Uses distance units of fuel
     *
     * @param distance distance to travel
     */
    public void travel(int distance) {
        ship.travel(distance);
    }

    public int getShipBasePrice() {
        return ship.getPrice();
    }

    /**
     * Gets maximum amount of fuel for the given Ship
     *
     * @return maximum amount of fuel in Ship
     */
    public int getMaxFuel() {
        return ship.getMaxFuel();
    }

    /**
     * Get the fuel cost of the Player's ship
     *
     * @return the cost of 1 unit of fuel
     */
    public int getFuelCost() {
        return ship.getFuelCost();
    }

    /**
     * Gets the current amount of fuel
     *
     * @return fuel left in Ship
     */
    public int getCurrentFuel() {
        return ship.getCurrentFuel();
    }

    /**
     * Adds fuel to the player's ship and removes the appropriate amount of
     * money from the player
     *
     * @param quantity
     *        the amount of fuel
     */
    public void buyFuel(int quantity) {
        ship.buyFuel(quantity);
        subtractMoney(quantity * getFuelCost());
    }

    @Override
    public String toString() {
        String retval = "Name: " + name + "\n";
        retval += "Piloting skill: " + getPilotSkill() + "\n";
        retval += "Fighting skill: " + getFightingSkill() + "\n";
        retval += "Engineering skill: " + getEngineeringSkill() + "\n";
        retval += "Trading Skill: " + getTradeSkill() + "\n";
        retval += "Investing Skill: " + getInvestingSkill() + "\n";
        retval += "Ship: " + ship.toString();
        return retval;
    }

    public String getName() {
        return name;
    }

    /*
     * The below methods need to account for a Crew members skill too
     */

    @Override
    public int getTradeSkill() {
        int total = skills.getTradeSkill();
        for (Crew member : ship.getCrew()) {
            total += member.getTradeSkill();
        }
        return total;
    }

    @Override
    public int getEngineeringSkill() {
        int total = skills.getEngineeringSkill();
        for (Crew member : ship.getCrew()) {
            total += member.getEngineeringSkill();
        }
        return total;
    }

    @Override
    public int getPilotSkill() {
        int total = skills.getPilotSkill();
        for (Crew member : ship.getCrew()) {
            total += member.getPilotSkill();
        }
        return total;
    }

    @Override
    public int getFightingSkill() {
        int total = skills.getFightingSkill();
        for (Crew member : ship.getCrew()) {
            total += member.getFightingSkill();
        }
        return total;
    }

    @Override
    public int getInvestingSkill() {
        int total = skills.getInvestingSkill();
        for (Crew member : ship.getCrew()) {
            total += member.getInvestingSkill();
        }
        return total;
    }
}

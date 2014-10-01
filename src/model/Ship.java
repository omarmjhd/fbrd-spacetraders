package model;

import java.util.AbstractList;

/**
 * This class represents a Ship object. A ship contains cargo, weapons, shields,
 * gadgets, and crew. It also has fuel, a purchasing price, and NPC disposition
 * modifiers.
 *
 *
 * It might be a good idea to use a custom linked list with a maximum size
 * rather than arrays to store cargo
 *
 * @author ngraves3
 *
 */
public enum Ship {

    FLEA(10, 0, 0, 0, 1, 500, 1, 2000, 5, 25, -1, -1, 0),

    GNAT(15, 1, 0, 1, 1, 140, 2, 10000, 50, 100, 0, 0, 1),

    FIREFLY(20, 1, 1, 1, 1, 170, 3, 25000, 75, 100, 0, 0, 1),

    MOSQUITO(15, 2, 1, 1, 1, 130, 5, 30000, 100, 100, 0, 1, 1),

    BUMBLEBEE(25, 1, 2, 2, 2, 150, 7, 60000, 125, 100, 0, 1, 2);

    private PresizedList<Goods> cargo;

    private PresizedList<Weapon> weapons;

    private PresizedList<Shield> shields;

    private PresizedList<Gadget> gadgets;

    private PresizedList<Crew> crew;

    private int maxFuel;

    private int currentFuel;

    private int minTechLevel; // use for validation purposes
    private int fuelCost;
    private int price;
    private int bounty;
    private int hullStrength;
    private int policeAggression;
    private int pirateAggression;
    private int size;

    private Ship(int cargoSize, int weaponSize, int shieldSize, int gadgetSize, int crewSize, int maxFuel, int fuelCost, int price, int bounty, int hullStrength, int police, int pirate, int size) {
        //cargo = new Goods[cargoSize];
        cargo = new PresizedList<Goods>(cargoSize);
        weapons = new PresizedList<Weapon>(weaponSize);
        shields = new PresizedList<Shield>(shieldSize);
        gadgets = new PresizedList<Gadget>(gadgetSize);
        crew = new PresizedList<Crew>(crewSize);
        this.maxFuel = maxFuel;
        this.fuelCost = fuelCost;
        this.price = price;
        this.bounty = bounty;
        this.hullStrength = hullStrength;
        policeAggression = police;
        pirateAggression = pirate;
        this.size = size;
        currentFuel = maxFuel;
    }

    /**
     * Returns the number of empty slots for cargo
     *
     * @return number of empty slots for cargo
     */
    public int cargoRoomLeft() {
        return cargo.maxSize() - cargo.size();
    }

    /**
     * Returns the list of cargo the Ship contains
     *
     * @return
     */
    public AbstractList<Goods> getCargo() {
        return cargo;
    }

    /**
     * Adds cargo to the ship
     *
     * @param item
     * @return true if item was added, false otherwise
     * @throws IllegalArgumentException
     *         if item is null
     */
    public boolean addCargo(Goods item) throws IllegalArgumentException {
        return cargo.add(item);
    }

    /**
     * Looks through the cargo to find the given item.
     *
     * @param item
     *        the item to look for. null if item not in cargo
     * @return the matching item if found or null if no items match
     * @throws IllegalArgumentException
     *         if item is null
     */
    public Goods removeCargo(Goods item) throws IllegalArgumentException {
        boolean wasRemoved = cargo.remove(item);
        if (wasRemoved) {
            return item;
        } else {
            return null;
        }
    }

    /**
     * Transfers the cargo from one ship to another. Might change this method to
     * include other items as we implement more
     *
     * @param other
     *        the Ship to transfer to
     */
    public void transferCargo(Ship other) {
        for (Goods item : cargo) {
            other.addCargo(item);
        }
    }

    /**
     * Remove [distance] units of fuel after travelling distance.
     *
     * @param distance
     *        the distance to travel
     */
    public void travel(int distance) {
        currentFuel -= distance;
    }

    /**
     * Returns the current amount of fuel
     *
     * @return current fuel
     */
    public int getCurrentFuel() {
        return currentFuel;
    }

    /**
     * Adds fuel to the ship.
     *
     * @param amount
     */
    public void buyFuel(int amount) {
        currentFuel += amount;
    }

    /**
     * Returns the maximum amount of fuel
     *
     * @return maxFuel
     */
    public int getMaxFuel() {
        return maxFuel;
    }

    /*
     * Getter methods below
     */

    public int cargoSize() {
        return cargo.maxSize();
    }

    public int weaponsSize() {
        return weapons.maxSize();
    }

    public int shieldsSize() {
        return shields.maxSize();
    }

    public int crewSize() {
        return crew.maxSize();
    }

    public int gadgetSize() {
        return gadgets.maxSize();
    }

    public AbstractList<Weapon> getWeapons() {
        return weapons;
    }

    public AbstractList<Shield> getShields() {
        return shields;
    }

    public AbstractList<Gadget> getGadgets() {
        return gadgets;
    }

    public AbstractList<Crew> getCrew() {
        return crew;
    }
}

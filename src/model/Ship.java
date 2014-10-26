package model;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.Map;
import java.util.TreeMap;

/**
 * This class represents a Ship object. A ship contains cargo, weapons, shields,
 * gadgets, and crew. It also has fuel, a purchasing price, and NPC disposition
 * modifiers. Converted from enum to class so object would be serialized
 * correctly
 *
 *
 * It might be a good idea to use a custom linked list with a maximum size
 * rather than arrays to store cargo
 *
 * @author ngraves3
 *
 */
public class Ship implements Serializable {

    public static Ship flea() {
        return new Ship("Flea", 10, 0, 0, 0, 1, 500, 1, 2000, 5, 25, -1, -1, 0);

    }

    public static Ship gnat() {
        return new Ship("Gnat", 15, 1, 0, 1, 1, 140, 2, 10000, 50, 100, 0, 0, 1);
    }

    public static Ship firefly() {
        return new Ship("Firefly", 20, 1, 1, 1, 1, 170, 3, 25000, 75, 100, 0,
                        0, 1);
    }

    public static Ship mosquito() {
        return new Ship("Mosquito", 15, 2, 1, 1, 1, 130, 5, 30000, 100, 100, 0,
                        1, 1);
    }

    public static Ship bumblebee() {
        return new Ship("Bumblebee", 25, 1, 2, 2, 2, 150, 7, 60000, 125, 100,
                        0, 1, 2);
    }

    private PresizedList<Goods> cargo;

    private PresizedList<Weapon> weapons;

    private PresizedList<Shield> shields;

    private PresizedList<Gadget> gadgets;

    private PresizedList<Crew> crew;

    private int maxFuel;

    private boolean isVisible;

    private int currentFuel;
    private String name;
    private TechLevel minTechLevel; // use for validation purposes
    private int fuelCost;
    private int price;
    private int bounty;
    private int hullStrength;
    private int policeAggression;
    private int pirateAggression;
    private int size;

    private Ship(String name, int cargoSize, int weaponSize, int shieldSize,
        int gadgetSize, int crewSize, int maxFuel, int fuelCost, int price,
        int bounty, int hullStrength, int police, int pirate, int size) {
        //cargo = new Goods[cargoSize];
        cargo = new PresizedList<Goods>(cargoSize);
        weapons = new PresizedList<Weapon>(weaponSize);
        shields = new PresizedList<Shield>(shieldSize);
        gadgets = new PresizedList<Gadget>(gadgetSize);
        crew = new PresizedList<Crew>(crewSize);
        this.name = name;
        this.maxFuel = maxFuel;
        this.fuelCost = fuelCost;
        this.price = price;
        this.bounty = bounty;
        this.hullStrength = hullStrength;
        policeAggression = police;
        pirateAggression = pirate;
        this.size = size;
        currentFuel = maxFuel;
        isVisible = true;
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
     * used with CargoGadget
     *
     * @param cargo
     *        the new backing cargo for the ship
     */
    public void setCargo(PresizedList<Goods> cargo) {
        this.cargo = cargo;
    }

    /**
     * Used with FuelGadget
     *
     * @param cost
     *        the new fuel cost of the ship
     */
    public void setFuelCost(int cost) {
        fuelCost = cost;
    }

    /**
     * Used with CloakingGadget
     *
     * @param isVisible
     *        whether or not the ship is visible
     */
    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
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

    public int getFuelCost() {
        return fuelCost;
    }

    public int getPrice() {
        return price;
    }

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

    /**
     * Returns a Map of attribute name to atribute value
     *
     * @return map of instance variable -> value
     */
    public Map<String, Integer> specs() {
        Map<String, Integer> retval = new TreeMap<String, Integer>();

        retval.put("Max Fuel", maxFuel);
        retval.put("Fuel Cost", fuelCost);
        retval.put("Base Price", price);
        retval.put("Bounty", bounty);
        retval.put("Hull Strength", hullStrength);
        retval.put("Police Disposition", policeAggression);
        retval.put("Pirate Aggression", pirateAggression);
        retval.put("Cargo Size", cargoSize());
        retval.put("Weapons Size", weaponsSize());
        retval.put("Crew Size", crewSize());
        retval.put("Gadget Size", gadgetSize());
        retval.put("Shield Size", shieldsSize());

        return retval;

    }

    @Override
    public String toString() {
        return name;
    }

}
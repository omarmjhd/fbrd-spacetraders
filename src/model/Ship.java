package model;

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
public class Ship {

    private String name;

    private Goods[] cargo;
    private int numCargo = 0;

    private Weapon[] weapons;
    private int numWeapons = 0;

    private Shield[] shields;
    private int numShields = 0;

    private Gadget[] gadgets;
    private int numGadget = 0;

    private Crew[] crew;
    private int numCrew = 0;

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

    private Ship(String name, int cargoSize, int weaponSize, int shieldSize, int gadgetSize, int crewSize, int maxFuel, int fuelCost, int price, int bounty, int hullStrength, int police, int pirate, int size) {
        this.name = name;
        cargo = new Goods[cargoSize];
        weapons = new Weapon[weaponSize];
        shields = new Shield[shieldSize];
        gadgets = new Gadget[gadgetSize];
        crew = new Crew[crewSize];
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

    /*
     * All ships are exactly the same except for stats
     */

    /**
     * Makes a new "Flea" ship
     *
     * @return flea Ship
     */
    public static Ship makeFlea() {
        return new Ship("Flea", 10, 0, 0, 0, 1, 50, 1, 2000, 5, 25, -1, -1, 0);

    }

    /**
     * Makes a new "Gnat" ship
     *
     * @return new Gnat ship
     */
    public static Ship makeGnat() {
        return new Ship("Gnat", 15, 1, 0, 1, 1, 14, 2, 10000, 50, 100, 0, 0, 1);
    }

    /**
     * Makes a "Firefly" ship
     *
     * @return returns a new ship
     */
    public static Ship makeFirefly() {
        return new Ship("Firefly", 20, 1, 1, 1, 1, 17, 3, 25000, 75, 100, 0, 0, 1);
    }

    /**
     * Makes a "Mosquito" ship
     *
     * @return a new Ship
     */
    public static Ship makeMosquito() {
        return new Ship("Mosquito", 15, 2, 1, 1, 1, 13, 5, 30000, 100, 100, 0, 1, 1);
    }

    /**
     * Makes a "Bumblebee" ship
     *
     * @return a new Ship
     */
    public static Ship makeBumblebee() {
        return new Ship("Bumblebee", 25, 1, 2, 2, 2, 15, 7, 60000, 125, 100, 0, 1, 2);
    }

    /**
     * Returns the number of empty slots for cargo
     *
     * @return number of empty slots for cargo
     */
    public int cargoRoomLeft() {
        return cargo.length - numCargo;
    }

    /**
     * Adds cargo to the ship
     *
     * @param item
     */
    public void addCargo(Goods item) throws IllegalStateException {
        if (numCargo >= cargo.length) {
            throw new IllegalStateException("Cargo bay is full");
        }
        cargo[numCargo] = item;
        numCargo++;
    }

    /**
     * Removes the last cargo from the ship
     *
     * @return Goods the good removed from cargo
     */
    public Goods removeCargo() throws IllegalStateException {
        if (numCargo < 1) {
            throw new IllegalStateException("No cargo in ship");
        }
        numCargo--;
        Goods retval = cargo[numCargo];
        cargo[numCargo] = null;
        return retval;

    }

    /**
     * Looks through the cargo to find the given item.
     *
     * @param item
     *        the item to look for
     * @return the matching item if found or null if no items match
     * @throws IllegalStateException
     *         if no cargo in ship
     * @throws IllegalArgumentException
     *         if item is null
     */
    public Goods removeCargo(Goods item) throws IllegalStateException, IllegalArgumentException {
        if (numCargo < 1) {
            throw new IllegalStateException("No cargo in ship");
        } else if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }

        for (int i = 0; i < cargo.length; i++) {
            if (cargo[i].equals(item)) {
                /*
                 * cargo[i] == the item to search for. since items are same,
                 * just return the item to search for
                 */
                cargo[i] = null;
                return item;
            }
        }

        /*
         * No matching item found in the list
         */
        return null;
    }

    /*
     * Getter methods below
     */

    public String getName() {
        return name;
    }

    public int cargoSize() {
        return cargo.length;
    }

    public int weaponsSize() {
        return weapons.length;
    }

    public int shieldsSize() {
        return shields.length;
    }

    public int crewSize() {
        return crew.length;
    }

    public int gadgetSize() {
        return gadgets.length;
    }
}

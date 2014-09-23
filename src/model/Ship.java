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
public enum Ship {

    FLEA(10, 0, 0, 0, 1, 50, 1, 2000, 5, 25, -1, -1, 0),

    GNAT(15, 1, 0, 1, 1, 14, 2, 10000, 50, 100, 0, 0, 1),

    FIREFLY(20, 1, 1, 1, 1, 17, 3, 25000, 75, 100, 0, 0, 1),

    MOSQUITO(15, 2, 1, 1, 1, 13, 5, 30000, 100, 100, 0, 1, 1),

    BUMBLEBEE(25, 1, 2, 2, 2, 15, 7, 60000, 125, 100, 0, 1, 2);

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

    private Ship(int cargoSize, int weaponSize, int shieldSize, int gadgetSize, int crewSize, int maxFuel, int fuelCost, int price, int bounty, int hullStrength, int police, int pirate, int size) {
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
    public Goods removeCargo() {
        if (numCargo < 1) {
            return null;
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
    public Goods removeCargo(Goods item) throws IllegalArgumentException {
        if (numCargo < 1) {
            return null;
        } else if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }

        /*
         * Should decouple this removal from removeCargo() and encapsulate in
         * the data structure
         */
        for (int i = 0; i < cargo.length; i++) {
            if (cargo[i].equals(item)) {
                /*
                 * cargo[i] == the item to search for. since items are same,
                 * just return the item to search for
                 */
                cargo[i] = null;

                /*
                 * Slide all other items into the appropriate slot
                 */
                for (int j = i; j < (cargo.length - 1); j++) {
                    cargo[j] = cargo[j + 1];
                }

                numCargo--;

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

package model;

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
    }

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

    public String getName() {
        return name;
    }

    public Goods[] getCargo() {
        return cargo;
    }

    public Weapon[] getWeapons() {
        return weapons;
    }

    public Shield[] getShields() {
        return shields;
    }

    public Gadget[] getGadgets() {
        return gadgets;
    }

    public Crew[] getCrew() {
        return crew;
    }

    public int getMaxFuel() {
        return maxFuel;
    }

    public int getMinTechLevel() {
        return minTechLevel;
    }

    public int getFuelCost() {
        return fuelCost;
    }

    public int getPrice() {
        return price;
    }

    public int getBounty() {
        return bounty;
    }

    public int getHullStrength() {
        return hullStrength;
    }

    public int getPoliceAggression() {
        return policeAggression;
    }

    public int getPirateAggression() {
        return pirateAggression;
    }

    public int getSize() {
        return size;
    }
}

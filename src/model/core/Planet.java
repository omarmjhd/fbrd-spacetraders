package model.core;


import java.io.Serializable;
import model.commerce.Goods;
import model.commerce.Marketplace;
import model.commerce.Shipyard;


/**
 * A planet in a SolarSystem. It has a tech level, resources, and a name.
 *
 * @author Nick
 *
 */
public class Planet implements Serializable {

    /**
     * Resource for this planet.
     */
    private Goods resource;
    /**
     * Tech level for this planet.
     */
    private TechLevel tech;
    /**
     * Name of this planet.
     */
    private String name;
    /**
     * Marketplace specific to this planet.
     */
    private Marketplace marketplace;
    /**
     * Shipyard specific to this planet.
     */
    private Shipyard shipyard;

    /**
     * Constructor for Planet.
     *
     * @param nameArg
     *        name of the Planet
     * @param resourceArg
     *        resource of the planet
     * @param techArg
     *        tech level of the planet
     */
    public Planet(String nameArg, Goods resourceArg, TechLevel techArg) {
        this.name = nameArg;
        this.resource = resourceArg;
        this.tech = techArg;
    }

    /**
     * This method initializes the marketplace on a planet. It should be called after the player
     * visits the planet. It creates a random supply of various goods
     *
     * @param player
     *        the player model
     * @return the marketplace created
     */
    public Marketplace enterMarket(Player player) {
        marketplace = new Marketplace(tech, player);
        return marketplace;
    }

    /**
     * Initializes a Shipyard for a Planet.
     *
     * @param player
     *        the Player entering the shipyard
     * @return the shipyard created
     */
    public Shipyard enterShipyard(Player player) {
        shipyard = new Shipyard(marketplace, player);
        return shipyard;
    }

    /**
     * Gets the Marketplace. Used for shipyard
     *
     * @return the shipyard
     */
    public Marketplace getMarketplace() {
        return marketplace;
    }

    /**
     * Determines if a Planet has as shipyard (techLevel is HI_TECH).
     *
     * @return true if Planet is HI_TECH, false otherwise
     */
    public boolean hasShipYard() {
        return tech == TechLevel.HI_TECH;
    }

    /**
     * Gets the Shipyard for buying new ships.
     *
     * @return the shipyard being used
     */
    public Shipyard getShipyard() {
        return shipyard;
    }

    /**
     * Returns the name of the planet.
     *
     * @return name: String
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the resources of this Planet.
     *
     * @return the resources of this planet
     */
    public Goods getResource() {
        return resource;
    }

    /**
     * Gets tech level of planet.
     *
     * @return return tech level of planet
     */
    public TechLevel getTechLevel() {
        return tech;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof Planet)) {
            return false;
        } else if (other == this) {
            return true;
        }
        Planet planet = (Planet) other;
        return name.equals(planet.getName());

    }

    @Override
    public int hashCode() {
        return name.hashCode();

    }

    @Override
    public String toString() {

        return "Planet Name: " + name + "\n\tResources: " + resource
                        + "\n\tTech: " + tech;

    }
}

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
public class Planet implements Serializable{

    private Goods resource;
    private TechLevel tech;
    private String name;
    private Marketplace marketplace;
    private Shipyard shipyard;

    public Planet(String name, Goods resource, TechLevel tech) {
        this.name = name;
        this.resource = resource;
        this.tech = tech;
    }

    /**
     * This method initializes the marketplace on a planet. It should be called
     * after the player visits the planet. It creates a random supply of various
     * goods
     *
     * @param player
     *        the player model
     */
    public Marketplace enterMarket(Player player) {
        marketplace = new Marketplace(this, player);
        return marketplace;
    }

    /**
     * Initializes a Shipyard for a Planet.
     *
     * @param player
     *        the Player entering the shipyard
     * @return
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
     * Determines if a Planet has as shipyard (techLevel is HI_TECH)
     *
     * @return true if Planet is HI_TECH, false otherwise
     */
    public boolean hasShipYard() {
        return tech == TechLevel.HI_TECH;
    }

    /**
     * Gets the Shipyard for buying new ships
     *
     * @return the shipyard being used
     */
    public Shipyard getShipyard() {
        return shipyard;
    }

    /**
     * Returns the name of the planet
     *
     * @return name: String
     */
    public String getName() {
        return name;
    }

    public Goods getResource() {
        return resource;
    }

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

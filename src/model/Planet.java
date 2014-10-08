package model;


import javafx.scene.paint.Paint;

import java.io.*;


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
    private Paint color;

    public Planet(String name, Goods resource, TechLevel tech) {
        this.name = name;
        this.resource = resource;
        this.tech = tech;
    }

    /**
     * This method initializes the marketplace on a planet. It should be called
     * after the player visits the planet. It creates a random supply of a good
     * (from 1-10 right now).
     *
     * @param player
     *        the player model
     */
    public Marketplace enterMarket(Player player) {
        marketplace = new Marketplace(this, player);
        return marketplace;

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

    public Paint getColor() {
        return color;
    }

    public void setColor(Paint color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Planet)) {
            return false;
        } else if (o == this) {
            return true;
        }
        Planet p = (Planet) o;
        return name.equals(p.getName());

    }

    @Override
    public int hashCode() {
        return name.hashCode();

    }

    @Override
    public String toString() {

        return "Planet Name: " + name + "\n\tPlanet Resources: " + resource
                        + "\n\tPlanet Tech: " + tech;

    }
}

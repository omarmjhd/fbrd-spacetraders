package model;


import java.util.HashSet;

/**
 * A planet in a SolarSystem. It has a tech level, resources, and a name.
 *
 * @author Nick
 *
 */
public class Planet {

    private Resource resource;
    private TechLevel tech;
    private String name;
    private TradeInteraction marketplace;

    public Planet(String name, Resource resource, TechLevel tech) {
        this.name = name;
        this.resource = resource;
        this.tech = tech;
    }

    @Override
    public int hashCode() {
        return name.hashCode() + resource.hashCode() + tech.hashCode();

    }

    @Override
    public String toString() {

        return "Planet Name: " + name + " Planet Resources: " + resource + " Planet Tech: " + tech;

    }
}

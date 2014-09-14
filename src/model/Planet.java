package model;

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

    public Planet(String name, Resource resource, TechLevel tech) {
        this.name = name;
        this.resource = resource;
        this.tech = tech;
    }

    @Override
    public int hashCode() {
        return name.hashCode() + resource.hashCode() + tech.hashCode();

    }
}

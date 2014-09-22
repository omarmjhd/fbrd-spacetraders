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
    private TradeInteraction marketplace;

    public Planet(String name, Resource resource, TechLevel tech) {
        this.name = name;
        this.resource = resource;
        this.tech = tech;
    }

    /**
     * Returns the name of the planet
     *
     * @return name: String
     */
    public String getName() {
        return name;
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

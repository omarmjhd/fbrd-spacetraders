package model;





/**
 * A planet in a SolarSystem. It has a tech level, resources, and a name.
 *
 * @author Nick
 *
 */
public class Planet {

    private Goods resource;
    private int supply;
    private TechLevel tech;
    private String name;
    private Marketplace marketplace;

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
     *        the player mddel
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

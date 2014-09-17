package model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * A SolarSystem has a position in the universe and contains planets, which have
 * specific resources and tech levels.
 *
 * @author Nick
 *
 */
public class SolarSystem {

    private String name;
    private Point pos;
    private Set<Planet> planets;

    public SolarSystem(String name, int x, int y, Planet... varPlanets) {
        this(name, new Point(x, y), varPlanets);
    }

    public SolarSystem(String name, Point pos, Planet... varPlanets) {
        this.name = name;
        this.pos = pos;
        planets = new HashSet<Planet>();
        for (Planet p : varPlanets) {
            planets.add(p);
        }
    }

    public SolarSystem(String name, int x, int y, Collection<Planet> varPlanets) {
        this(name, new Point(x, y), varPlanets);
    }

    public SolarSystem(String name, Point pos, Collection<Planet> varPlanets) {
        this.name = name;
        this.pos = pos;
        planets = new HashSet<Planet>();
        planets.addAll(varPlanets);

    }

    /**
     * Returns distance between 2 SolarSystems
     *
     * @param other
     *        a SolarSystem
     * @return int distance between 2 SolarSystems
     */
    public int distance(SolarSystem other) {
        return pos.distance(other.getPosition());
    }

    public Point getPosition() {
        return pos;
    }

    @Override
    public String toString() {
        String planetString = "";

        for (Planet p: planets) {

            planetString = planetString + " " + p.toString();

        }

        return "Solar System Name: " + name + " Point: " + pos.toString() + planetString;

    }

}

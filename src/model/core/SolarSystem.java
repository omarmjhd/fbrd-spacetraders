package model.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A SolarSystem has a position in the universe and contains planets, which have
 * specific resources and tech levels.
 *
 * @author Nick
 *
 */
public class SolarSystem implements Serializable {

    /**
     * Name of solar system.
     */
    private String name;
    /**
     * position of solar system.
     */
    private Point pos;
    /**
     * Planets in a solar system.
     */
    private List<Planet> planets;

    /**
     * Constructor for solar systems.
     *
     * @param nameArg
     *        the name of the solar system
     * @param posArg
     *        the position of the solar system
     * @param varPlanets
     *        the planets in the solar system
     */
    public SolarSystem(String nameArg, Point posArg, Planet... varPlanets) {
        this.name = nameArg;
        this.pos = posArg;
        planets = new ArrayList<>();
        for (Planet p : varPlanets) {
            planets.add(p);
        }
    }

    /**
     * Returns distance between 2 SolarSystems.
     *
     * @param other
     *        a SolarSystem
     * @return int distance between 2 SolarSystems
     */
    public int distance(SolarSystem other) {
        return pos.distance(other.getPosition());
    }

    /**
     * Gets position of planet.
     *
     * @return position
     */
    public Point getPosition() {
        return pos;
    }

    @Override
    public String toString() {
        StringBuilder planetString = new StringBuilder();

        for (Planet p: planets) {

            planetString.append(" " + p.toString());

        }

        return "Solar System Name: " + name + "\nPoint: " + pos.toString() + "\n"
                        + planetString.toString();

    }

    /**
     * Returns list of planets in solar system.
     *
     * @return list of planets
     */
    public List<Planet> getPlanets() {
        return planets;
    }
}

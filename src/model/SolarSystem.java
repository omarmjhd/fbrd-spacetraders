package model;

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

    public SolarSystem(String name, int x, int y) {
        this(name, new Point(x, y));
    }

    public SolarSystem(String name, Point pos) {
        this.name = name;
        this.pos = pos;
        planets = new HashSet<Planet>();
    }

    public int distance(SolarSystem other) {
        return pos.distance(other.getPosition());
    }

    public Point getPosition() {
        return pos;
    }

}

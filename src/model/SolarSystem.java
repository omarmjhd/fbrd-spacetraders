package model;

import java.util.HashSet;

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
    private HashSet<Planet> planets;

    public int distance(SolarSystem other) {
        return pos.distance(other.getPosition());
    }

    public Point getPosition() {
        return pos;
    }

    private class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int distance(Point other) {
            return (int) Math.sqrt(x * x + y * y);
        }
    }

}

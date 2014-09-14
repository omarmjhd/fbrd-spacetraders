package model;

/**
 * Represents a coordinate position for a SolarSystem
 *
 * @author Nick
 *
 */
public class Point {
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

package model;

/**
 * Represents a coordinate position for a SolarSystem
 *
 * @author Nick
 *
 */
class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the distance between 2 points. Casts hypotenuse to an int.
     *
     * @param other
     * @return int closest to the hypotenuse
     */
    public int distance(Point other) {
        return (int) Math.sqrt(x * x + y * y);
    }
}

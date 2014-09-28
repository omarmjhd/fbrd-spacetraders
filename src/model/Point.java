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

    /**
     * Returns the distance between 2 points. Casts hypotenuse to an int.
     *
     * @param other
     * @return int closest to the hypotenuse
     */
    public int distance(Point other) {
        return (int) Math.sqrt(x * x + y * y);
    }


    @Override
    public String toString() {

        return "(" + x + "," + y + ")";
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    @Override
    public int hashCode() {
        return x * y;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof Point)) {
            return false;
        } else if (this == other) {
            return true;
        } else {
            Point o = (Point) other;
            return x == o.getX() && y == o.getY();
        }
    }
}

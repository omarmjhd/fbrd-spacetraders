package model.core;

import java.io.Serializable;

/**
 * Represents a coordinate position for a SolarSystem
 *
 * @author Nick
 *
 */
public class Point implements Serializable{
    private int xpos;
    private int ypos;

    public Point(int xpos, int ypos) {
        this.xpos = xpos;
        this.ypos = ypos;
    }

    /**
     * Returns the distance between 2 points. Casts hypotenuse to an int.
     *
     * @param other
     * @return int closest to the hypotenuse
     */
    public int distance(Point other) {
        return (int) Math.sqrt(Math.pow((xpos - other.getX()), 2)
                        + Math.pow((ypos - other.getY()), 2));
    }


    @Override
    public String toString() {

        return "(" + xpos + "," + ypos + ")";
    }

    public int getY() {
        return ypos;
    }

    public int getX() {
        return xpos;
    }

    @Override
    public int hashCode() {
        return xpos * ypos;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof Point)) {
            return false;
        } else if (this == other) {
            return true;
        } else {
            Point oth = (Point) other;
            return xpos == oth.getX() && ypos == oth.getY();
        }
    }
}

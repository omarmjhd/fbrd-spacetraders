package model;

/**
 * Class containing lots of useful methods for various things that don't really
 * belong in a class
 *
 * @author ngraves3
 *
 */
public class Utilities {

    /**
     * Capitalizes a string
     * 
     * @param s
     *        the string to capitalize
     * @return the capitalized string; null if input is null; empty string if
     *         string is empty
     */
    public static String capitalize(String s) {
        if (s == null) {
            return null;
        }

        if (s.length()  == 0) {
            return s;
        } else if (s.length() == 1) {
            return s.toUpperCase();
        }

        return s.charAt(0) + s.substring(1, s.length()).toLowerCase();
    }

}

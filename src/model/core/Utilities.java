package model.core;

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
    public static String capitalize(String str) {
        if (str == null) {
            return null;
        }

        if (str.length() == 0) {
            return str;
        } else if (str.length() == 1) {
            return str.toUpperCase();
        }

        return str.charAt(0) + str.substring(1, str.length()).toLowerCase();
    }

}

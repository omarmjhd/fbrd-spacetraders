package model.upgrades;

import model.core.Utilities;

/**
 * A class representing shields for use in combat.
 * 
 * @author ngraves3
 *
 */
public enum Shield implements HasPrice {
    /**
     * A basic shield.
     */
    ENERGY_SHIELD,
    /**
     * A premium shield.
     */
    REFLECTIVE_SHIELD;

    @Override
    public int getPrice() {
        return 500 * (ordinal() + 1);
    }

    @Override
    public String toString() {
        return Utilities.capitalize(name());
    }

}

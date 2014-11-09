package model.upgrades;

import model.core.Utilities;

/**
 * A class representing a weapon for combat.
 *
 * @author ngraves3
 *
 */
public enum Weapon implements HasPrice {
    /**
     * A standard laser.
     */
    PULSE_LASER,
    /**
     * A better laser.
     */
    BEAM_LASER,
    /**
     * The best laser.
     */
    MILITARY_LASER;

    @Override
    public int getPrice() {
        return 500 * (ordinal() + 1);
    }

    @Override
    public String toString() {
        return Utilities.capitalize(name());
    }

}

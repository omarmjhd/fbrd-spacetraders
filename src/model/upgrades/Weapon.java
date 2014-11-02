package model.upgrades;

import model.core.Utilities;

public enum Weapon implements HasPrice {

    PULSE_LASER, BEAM_LASER, MILITARY_LASER;

    @Override
    public int getPrice() {
        return 500 * (ordinal() + 1);
    }

    @Override
    public String toString() {
        return Utilities.capitalize(name());
    }

}

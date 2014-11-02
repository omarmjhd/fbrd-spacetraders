package model.upgrades;

import model.core.Utilities;

public enum Shield implements HasPrice {

    ENERGY_SHIELD, REFLECTIVE_SHIELD;

    @Override
    public int getPrice() {
        return 500 * (ordinal() + 1);
    }

    @Override
    public String toString() {
        return Utilities.capitalize(name());
    }

}

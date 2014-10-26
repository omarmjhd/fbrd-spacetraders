package model;

public enum Shield implements HasPrice {

    ENERGY_SHIELD, REFLECTIVE_SHIELD;

    @Override
    public int getPrice() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String toString() {
        return Utilities.capitalize(name());
    }

}

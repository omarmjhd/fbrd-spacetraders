package model;

public enum Weapon implements HasPrice {

    PULSE_LASER, BEAM_LASER, MILITARY_LASER;

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

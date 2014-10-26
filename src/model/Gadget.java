package model;

/**
 * An abstract representation of a gadget. Not much can be said about gadgets
 * because they all have different effects. the 3 implements effects will be:
 * added cargo room, cloaking device, and reduced fuel cost. Gadget implements
 * the Command design pattern
 *
 * @author ngraves3
 *
 */
public abstract class Gadget extends Command implements HasPrice {

    private String name;

    protected Ship ship;

    protected boolean effectApplied;

    public Gadget(String name, Ship ship) {
        this.ship = ship;
        this.name = name;
        effectApplied = false;
    }

    @Override
    public String toString() {
        return Utilities.capitalize(name);
    }


}

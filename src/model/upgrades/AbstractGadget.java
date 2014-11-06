package model.upgrades;

import model.core.Ship;
import model.core.Utilities;

/**
 * An abstract representation of a gadget. Not much can be said about gadgets
 * because they all have different effects. the 3 implements effects will be:
 * added cargo room, cloaking device, and reduced fuel cost. Gadget implements
 * the Command design pattern
 *
 * @author ngraves3
 *
 */
public abstract class AbstractGadget extends AbstractCommand implements HasPrice {

    /**
     * name of the gadget.
     */
    private String name;

    /**
     * The ship to affect.
     */
    protected Ship ship;

    /**
     * whether or not the effect was applied.
     */
    protected boolean effectApplied;

    /**
     * Constructor for an abstract gadget.
     * 
     * @param nameArg
     *        name of gadget
     * @param shipArg
     *        the ship to affect
     */
    public AbstractGadget(String nameArg, Ship shipArg) {
        this.ship = shipArg;
        this.name = nameArg;
        effectApplied = false;
    }

    @Override
    public String toString() {
        return Utilities.capitalize(name);
    }


}

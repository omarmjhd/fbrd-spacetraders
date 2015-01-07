package model.events;

import model.core.Player;

/**
 * This class represents the absence of an effect in a RandomEvent. This is an implementation of the
 * Null Object pattern so that the EventFactory can decide whether an event should be made instead
 * of the RandomEvent deciding whether to execute
 * 
 * @author ngraves3
 *
 */
public class NullStrategy implements EventStrategy {

    @Override
    public String execute(Player p) {
        return "";
    }
}

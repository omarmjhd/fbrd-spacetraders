package model.events;

import java.util.Random;

import model.core.Player;

/**
 * A factory class for creating RandomEvents.
 *
 * @author ngraves3
 *
 */
public class EventFactory {

    /**
     * The chance that an actual event is generated and not a NullStrategy event.
     */
    private static double eventChance = 0.10;

    /**
     * The differnt types of events.
     *
     * @author ngraves3
     *
     */
    public static enum EventType {
        NULL_EVENT, FUEL_EVENT, MONEY_EVENT, GOODS_EVENT;
    }

    /**
     * Gets a random EventType that is not NULL_EVENT
     *
     * @return EventType other than NULL_EVENT
     */
    private static EventType getNonNullEvent() {

        int index = new Random().nextInt(EventType.values().length - 1) + 1;

        return EventType.values()[index];
    }


    /**
     * Creates a truly random RandomEvent.
     *
     * @param player
     *        the player to affect
     * @return a random event
     */
    public static RandomEvent createRandomEvent(Player player) {

        EventType et = (new Random().nextDouble() <= eventChance)
                        ? getNonNullEvent(): EventType.NULL_EVENT;

        return createEvent(player, et);
    }

    /**
     * Creates a RandomEvent based on the input.
     *
     * @param eventType
     *        the key for the RandomEvent
     * @return a specified RandomEvent
     */
    public static RandomEvent createEvent(Player player, EventType eventType) {

        EventStrategy strategy;

        switch (eventType) {
        case NULL_EVENT:
            strategy = new NullStrategy();
            break;
        case FUEL_EVENT:
            strategy = new FuelStrategy();
            break;
        case MONEY_EVENT:
            strategy = new MoneyStrategy();
            break;
        case GOODS_EVENT:
            strategy = new GoodsStrategy();
            break;
        default:
            strategy = new NullStrategy();
            break;
        }

        return new RandomEvent(player, strategy);
    }


}

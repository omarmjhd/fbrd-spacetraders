package model;

/**
 * Represents the abstract model of a trade good. All goods have prices and
 * types. The type is specified in the private
 *
 * @author ngraves3
 *
 */
public abstract class Goods {
    /**
     * Returns the price of this good
     *
     * @return the price of the good
     */
    public abstract int price();

    /**
     * Returns the type of the good as specified in the Resources enum
     *
     * @return type of resource
     */
    public abstract int type();

    private static enum GoodsType {
        WATER, FURS, FOOD, ORE, GAMES, FIREARMS, MEDICINE, MACHINES, NARCOTICS, ROBOTS
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Goods)) {
            return false;
        } else if (this == o) {
            return true;
        } else {
            Goods g = (Goods) o;
            return this.type() == g.type() && this.price() == g.type();
        }
    }
}

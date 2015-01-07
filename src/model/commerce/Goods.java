package model.commerce;

import java.util.Random;
import model.core.TechLevel;

/**
 * Represents a trade good. All goods have prices and different attributes
 *
 * @author ngraves3
 *
 */
public enum Goods {

    /**
     * values for Water good.
     */
    WATER(TechLevel.PRE_AG, TechLevel.PRE_AG, TechLevel.MEDIEVAL, 30, 3, 4, 30, 50),

    /**
     * Values for Furs good.
     */
    FURS(TechLevel.PRE_AG, TechLevel.PRE_AG, TechLevel.PRE_AG, 250, 10, 10, 230, 280),

    /**
     * Values for Food good.
     */
    FOOD(TechLevel.AGRICULTURE, TechLevel.PRE_AG, TechLevel.AGRICULTURE, 100, 5, 5, 90, 260),

    /**
     * Values for Ore good.
     */
    ORE(TechLevel.MEDIEVAL, TechLevel.MEDIEVAL, TechLevel.RENAISSANCE, 350, 20, 10, 350, 420),

    /**
     * Values for Game good.
     */
    GAMES(TechLevel.RENAISSANCE, TechLevel.AGRICULTURE,
                    TechLevel.POST_INDUSTRIAL, 250, -10, 5, 160, 270),
    /**
     * Values for Firearm good.
     */
    FIREARMS(TechLevel.RENAISSANCE, TechLevel.AGRICULTURE,
                    TechLevel.INDUSTRIAL, 1250, -75, 100, 600, 1100),

    /**
     * Values for Medicine good.
     */
    MEDICINE(TechLevel.EARLY_INDUSTRIAL, TechLevel.AGRICULTURE,
                    TechLevel.POST_INDUSTRIAL, 650, -20, 10, 400, 700),
    /**
     * Values for Machines good.
     */
    MACHINES(TechLevel.EARLY_INDUSTRIAL, TechLevel.RENAISSANCE,
                    TechLevel.INDUSTRIAL, 900, -30, 5, 600, 800),
    /**
     * Values for Narcotics good.
     */
    NARCOTICS(TechLevel.INDUSTRIAL, TechLevel.PRE_AG, TechLevel.INDUSTRIAL,
                    3500, -125, 150, 2000, 3000),
    /**
     * Values for Robots good.
     */
    ROBOTS(TechLevel.POST_INDUSTRIAL, TechLevel.EARLY_INDUSTRIAL,
                    TechLevel.HI_TECH, 5000, -150, 100, 3500, 5000);
    /**
     * Min tech to produce on Planet.
     */
    private TechLevel minTechProduce;
    /**
     * Min tech to sell to a planet.
     */
    private TechLevel minTechToUse;
    /**
     * Main producer of this good.
     */
    private TechLevel mainProducer;
    /**
     * Base price of this good.
     */
    private int basePrice;
    /**
     * Delta price per level.
     */
    private int priceIncreasePerLevel;
    /**
     * Amount of randomness allowed in price.
     */
    private int variance;
    /**
     * Min price for trader encounters.
     */
    private int minSpacePrice;
    /**
     * Max price for trader encounters.
     */
    private int maxSpacePrice;


    /**
     * Constructor for a Goods object.
     *
     * @param minTechProduceLocal
     *        the lowest tech on a planet to sell.
     * @param minTechToUseLocal
     *        the lowest tech to sell to a planet.
     * @param mainProducerLocal
     *        the tech level of main producer of good.
     * @param basePriceLocal
     *        the base price before modifiers.
     * @param priceIncreasePerLevelLocal
     *        the increase for each level beyond min.
     * @param varianceLocal
     *        the amount of randomness in prices
     * @param minSpacePriceLocal
     *        the min price for trader encounters
     * @param maxSpacePriceLocal
     *        the max price for trader encounters
     */
    private Goods(TechLevel minTechProduceLocal, TechLevel minTechToUseLocal,
        TechLevel mainProducerLocal, int basePriceLocal, int priceIncreasePerLevelLocal,
        int varianceLocal,
        int minSpacePriceLocal, int maxSpacePriceLocal) {
        this.minTechProduce = minTechProduceLocal;
        this.minTechToUse = minTechToUseLocal;
        this.mainProducer = mainProducerLocal;
        this.basePrice = basePriceLocal;
        this.priceIncreasePerLevel = priceIncreasePerLevelLocal;
        this.variance = varianceLocal;
        this.minSpacePrice = minSpacePriceLocal;
        this.maxSpacePrice = maxSpacePriceLocal;
    }

    /**
     * Gets the adjusted price of a good.
     *
     * @param planetTech
     *        the tech level of the planet
     * @return the adjusted price of the good
     */
    public int price(TechLevel planetTech) {
        int randomVariance = new Random().nextInt(2 * variance) - variance;
        return basePrice
                        + (priceIncreasePerLevel * (planetTech.ordinal() - minTechProduce
                                        .ordinal()))
                        + randomVariance;
    }

    /**
     * Returns lowest tech level for a planet to buy this goods.
     *
     * @return the above
     */
    public TechLevel minTechToUse() {
        return minTechToUse;
    }

    /**
     * Returns lowest tech needed to inherently sell the good.
     *
     * @return lowest tech level
     */
    public TechLevel minTechToProduce() {
        return minTechProduce;
    }

    @Override
    public String toString() {
        return this.name().charAt(0) + name().substring(1, name().length()).toLowerCase();
    }

}

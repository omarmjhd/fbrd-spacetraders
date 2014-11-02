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

    WATER(TechLevel.PRE_AG, TechLevel.PRE_AG, TechLevel.MEDIEVAL, 30, 3, 4, 30, 50),

    FURS(TechLevel.PRE_AG, TechLevel.PRE_AG, TechLevel.PRE_AG, 250, 10, 10, 230, 280),

    FOOD(TechLevel.AGRICULTURE, TechLevel.PRE_AG, TechLevel.AGRICULTURE, 100, 5, 5, 90, 260),

    ORE(TechLevel.MEDIEVAL, TechLevel.MEDIEVAL, TechLevel.RENAISSANCE, 350, 20, 10, 350, 420),

    GAMES(TechLevel.RENAISSANCE, TechLevel.AGRICULTURE,
                    TechLevel.POST_INDUSTRIAL, 250, -10, 5, 160, 270),

    FIREARMS(TechLevel.RENAISSANCE, TechLevel.AGRICULTURE,
                    TechLevel.INDUSTRIAL, 1250, -75, 100, 600, 1100),

    MEDICINE(TechLevel.EARLY_INDUSTRIAL, TechLevel.AGRICULTURE,
                    TechLevel.POST_INDUSTRIAL, 650, -20, 10, 400, 700),

    MACHINES(TechLevel.EARLY_INDUSTRIAL, TechLevel.RENAISSANCE,
                    TechLevel.INDUSTRIAL, 900, -30, 5, 600, 800),

    NARCOTICS(TechLevel.INDUSTRIAL, TechLevel.PRE_AG, TechLevel.INDUSTRIAL,
                    3500, -125, 150, 2000, 3000),

    ROBOTS(TechLevel.POST_INDUSTRIAL, TechLevel.EARLY_INDUSTRIAL,
                    TechLevel.HI_TECH, 5000, -150, 100, 3500, 5000);

    private TechLevel minTechProduce;
    private TechLevel minTechToUse;
    private TechLevel mainProducer;
    private int basePrice;
    private int priceIncreasePerLevel;
    private int variance;
    private int minSpacePrice;
    private int maxSpacePrice;



    private Goods(TechLevel minTechProduce, TechLevel minTechToUse,
        TechLevel mainProducer, int basePrice, int priceIncreasePerLevel,
        int variance, int minSpacePrice, int maxSpacePrice) {
        this.minTechProduce = minTechProduce;
        this.minTechToUse = minTechToUse;
        this.mainProducer = mainProducer;
        this.basePrice = basePrice;
        this.priceIncreasePerLevel = priceIncreasePerLevel;
        this.variance = variance;
        this.minSpacePrice = minSpacePrice;
        this.maxSpacePrice = maxSpacePrice;
    }

    public int price(TechLevel planetTech) {
        int randomVariance = new Random().nextInt(2 * variance) - variance;
        return basePrice
                        + (priceIncreasePerLevel * (planetTech.ordinal() - minTechProduce
                                        .ordinal()))
                        + randomVariance;
    }

    /**
     * Returns lowest tech level for a planet to buy this goods
     *
     * @return the above
     */
    public TechLevel minTechToUse() {
        return minTechToUse;
    }

    /**
     * Returns lowest tech needed to inherently sell the good
     *
     * @return lowest tech level
     */
    public TechLevel minTechToProduce() {
        return minTechProduce;
    }

    @Override
    public String toString() {
        return this.name().charAt(0) + name().substring(1,name().length()).toLowerCase();
    }

}

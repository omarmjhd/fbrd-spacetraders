package model.core;

import java.io.Serializable;


/**
 * This class holds all the specs for skills in a Player/Crew.
 *
 * @author ngraves3
 *
 */
public class SkillSet implements HasSkills, Serializable {

    /**
     * trading skill.
     */
    private final int tradeSkill;

    /**
     * fighting skill.
     */
    private final int fightSkill;

    /**
     * engineering skill.
     */
    private final int engSkill;

    /**
     * piloting skill.
     */
    private final int pilotSkill;

    /**
     * investing skill.
     */
    private final int investSkill;

    /**
     * Constructor for skill set.
     *
     * @param trade
     *        trade skill
     * @param fight
     *        fight skill
     * @param eng
     *        engineering skill
     * @param pilot
     *        piloting skill
     * @param invest
     *        invest skill
     */
    public SkillSet(int trade, int fight, int eng, int pilot, int invest) {
        tradeSkill = trade;
        fightSkill = fight;
        engSkill = eng;
        pilotSkill = pilot;
        investSkill = invest;
    }

    @Override
    public int getTradeSkill() {
        return tradeSkill;
    }

    @Override
    public int getEngineeringSkill() {
        return engSkill;
    }

    @Override
    public int getPilotSkill() {
        return pilotSkill;
    }

    @Override
    public int getFightingSkill() {
        return fightSkill;
    }

    @Override
    public int getInvestingSkill() {
        return investSkill;
    }

    /**
     * Returns the total number of skill points represented by this set of skills.
     *
     * @return total number of skill points
     */
    public int totalSkill() {
        return tradeSkill + fightSkill + engSkill + pilotSkill + investSkill;
    }

}

package model.core;



/**
 * This class holds all the specs for skills in a Player/Crew
 *
 * @author ngraves3
 *
 */
public class SkillSet implements HasSkills {

    private final int TRADE_SKILL;

    private final int FIGHT_SKILL;

    private final int ENG_SKILL;

    private final int PILOT_SKILL;

    private final int INVEST_SKILL;

    public SkillSet(int trade, int fight, int eng, int pilot, int invest) {
        TRADE_SKILL = trade;
        FIGHT_SKILL = fight;
        ENG_SKILL = eng;
        PILOT_SKILL = pilot;
        INVEST_SKILL = invest;
    }

    @Override
    public int getTradeSkill() {
        return TRADE_SKILL;
    }

    @Override
    public int getEngineeringSkill() {
        return ENG_SKILL;
    }

    @Override
    public int getPilotSkill() {
        return PILOT_SKILL;
    }

    @Override
    public int getFightingSkill() {
        return FIGHT_SKILL;
    }

    @Override
    public int getInvestingSkill() {
        return INVEST_SKILL;
    }

    /**
     * Returns the total number of skill points represented by this set of
     * skills
     *
     * @return total number of skill points
     */
    public int totalSkill() {
        return TRADE_SKILL + FIGHT_SKILL + ENG_SKILL + PILOT_SKILL
                        + INVEST_SKILL;
    }

}

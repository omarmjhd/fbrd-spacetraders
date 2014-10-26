package model;

/**
 * Interface to ensure skills are available for a Player/Crew
 *
 * @author ngraves3
 *
 */
public interface HasSkills {

    /**
     * Returns trading skill
     * 
     * @return int of trading skill
     */
    public int getTradeSkill();

    /**
     * Returns engineering skill
     * 
     * @return int of engineering skill
     */
    public int getEngineeringSkill();

    /**
     * Returns piloting skill
     * 
     * @return int of piloting skill
     */
    public int getPilotSkill();

    /**
     * Returns fighting skill
     * 
     * @return int of piloting skill
     */
    public int getFightingSkill();

    /**
     * Returns investing skill
     * 
     * @return int of investing skill
     */
    public int getInvestingSkill();

}

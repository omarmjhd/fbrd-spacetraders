package model;

public class Crew implements HasPrice, HasSkills {

    private SkillSet skills;


    public Crew(int trade, int fight, int eng, int pilot,
        int invest) {
        skills = new SkillSet(trade, fight, eng, pilot, invest);
    }

    @Override
    public int getPrice() {
        //price for a crew member is 10 times his total skills; open to change
        return skills.totalSkill() * 10;
    }

    @Override
    public int getTradeSkill() {
        return skills.getTradeSkill();
    }

    @Override
    public int getEngineeringSkill() {
        return skills.getEngineeringSkill();
    }

    @Override
    public int getPilotSkill() {
        return skills.getPilotSkill();
    }

    @Override
    public int getFightingSkill() {
        return skills.getFightingSkill();
    }

    @Override
    public int getInvestingSkill() {
        return skills.getInvestingSkill();
    }

}

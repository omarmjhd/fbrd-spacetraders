package model;

public class Player {

    private String name;
    private final int PILOT_SKILL;
    private final int FIGHT_SKILL;
    private final int ENG_SKILL;
    private final int TRADE_SKILL;
    private final int INVEST_SKILL;

    private int money = 0;

    private Ship ship;

    public Player(String name, int pilotSkill, int fightSkill, int engSkill, int tradeSkill, int investSkill) {
        this.name = name;
        this.ENG_SKILL = engSkill;
        this.FIGHT_SKILL = fightSkill;
        this.TRADE_SKILL = tradeSkill;
        this.INVEST_SKILL = investSkill;
        this.PILOT_SKILL = pilotSkill;
        this.ship = Ship.makeFlea();

    }
}

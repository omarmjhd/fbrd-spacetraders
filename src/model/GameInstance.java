package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

/**
 * Singleton game monitor. The game model will control the passing of turns and
 * random events
 *
 * @author ngraves3
 *
 */
public class GameInstance {

    private Player player;
    private HashSet<SolarSystem> solarSystems = new HashSet<>();
    private HashSet<Planet> planets = new HashSet<>();
    private HashMap<Point, Planet> points = new HashMap<>();

    private Planet currentPlanet;

    private String[] planetNames = { "Acamar", "Adahn", "Aldea", "Andevian", "Antedi",
            "Balosnee", "Baratas", "Brax", "Bretel",
            "Calondia", "Campor", "Capelle","Carzon", "Castor", "Cestus", "Cheron", "Courteney",
            "Daled", "Damast", "Davlos", "Deneb", "Deneva", "Devidia", "Draylon", "Drema",
            "Endor", "Esmee", "Exo", "Ferris", "Festen", "Fourmi", "Frolix", "Gemulon", "Guinifer",
            "Hades", "Hamlet", "Helena", "Hulst",
            "Iodine", "Iralius",
            "Janus", "Japori", "Jarada", "Jason",
            "Kaylon", "Khefka",
            "Kira", "Klaatu", "Klaestron", "Korma", "Kravat", "Krios",
            "Laertes", "Largo", "Lave", "Ligon", "Lowry",
            "Magrat", "Malcoria", "Melina", "Mentar", "Merik", "Mintaka", "Montor", "Mordan", "Myrthe"};

    private int planetCount = 0;

    private String[] solarSystemNames = { "Nelvana", "Nix", "Nyle",
            "Odet", "Og", "Omega", "Omphalos", "Orias", "Othello",
            "Parade","Penthara","Picard","Pollux",
            "Quator", "Rakhar", "Ran", "Regulas", "Relva", "Rhymus", "Rochani", "Rubicum", "Rutia",
            "Sarpeidon", "Sefalla", "Seltrice", "Sigma", "Sol",	"Somari", "Stakoron", "Styris",
            "Talani", "Tamus", "Tantalos", "Tanuga", "Tarchannen", "Terosa", "Thera", "Titan", "Torin", "Triacus", "Turkana", "Tyrus",
            "Umberlee","Utopia",
            "Vadera","Vagra","Vandor","Ventax",
            "Xenon","Xerxes",
            "Yew", "Yojimbo",
            "Zalkon", "Zuul"};

    private int solarSystemCount = 0;

    private Goods[] resources = Goods.values();
    private TechLevel[] techLevels = TechLevel.values();



    private static GameInstance instance = new GameInstance();

    private GameInstance() { //private constructor for singleton
    }

    public static GameInstance getInstance() {
        return instance;
    }

    public Planet getCurrentPlanet() {
        return currentPlanet;
    }

    public void setCurrentPlanet(Planet destination) {
        currentPlanet = destination;
    }

    public void setPlayer(Player data) {
        player = data;
    }

    public Player getPlayer() {
        return player;
    }

    /**
     * Gets planet at the chosen coordinate;
     *
     * @param coordinate
     * @return a planet if present, null otherwise
     */
    public Planet getPlanet(Point coordinate) {

        return points.get(coordinate);
    }

    /**
     * Creates a universe with number of planets equal to the length of our
     * default list of planet names
     */
    public void createUniverse() {
        createUniverse(Math.min(planetNames.length, solarSystemNames.length) - 1);
    }

    /**
     * create universe with specified number of planets
     *
     * @param number
     *        of planets
     */
    public void createUniverse(int number) {
        if (number >= planetNames.length || number >= solarSystemNames.length) {
            throw new IllegalArgumentException(
                            "Number is bigger than planet names");
        }
        for (int i = 0; i < number; i++) {
            Random rand = new Random();
            int resourceNum =  rand.nextInt(resources.length);
            int techLevelNum = rand.nextInt(techLevels.length);

            Point point = new Point(rand.nextInt(350), rand.nextInt(350));
            while (points.keySet().contains(point)) {
                point = new Point(rand.nextInt(350), rand.nextInt(350));
            }

            Planet planet = new Planet(planetNames[planetCount], resources[resourceNum], techLevels[techLevelNum]);
            planets.add(planet);

            points.put(point, planet);

            planetCount++;
            SolarSystem solarsystem = new SolarSystem(solarSystemNames[solarSystemCount], point, planet);
            System.out.println(solarsystem.toString());
            solarSystemCount++;




        }
    }

    public void endTurn() {
        //Do something to end the turn or whatever
    }

    @Override
    public String toString() {

        String gameString = "SOLAR: ";

        for (SolarSystem s: solarSystems) {

            gameString = gameString + " " + s.toString();

        }

        return gameString;
    }

}

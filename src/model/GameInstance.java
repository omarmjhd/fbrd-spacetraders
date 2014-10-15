package model;

import java.io.*;
import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;

/**
 * Singleton game monitor. The game model will control the passing of turns and
 * random events
 *
 * @author ngraves3
 *
 */
public class GameInstance implements Serializable{

    private Player player;
    private HashSet<SolarSystem> solarSystems = new HashSet<>();
    private HashSet<Planet> planets = new HashSet<>();
    private HashSet<Point> points = new HashSet<>();

    private Planet currentPlanet;
    private SolarSystem currentSolarSystem;

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
        this.currentPlanet = destination;
    }

    public void setPlayer(Player data) {
        player = data;
    }

    public Player getPlayer() {
        return player;
    }

    public SolarSystem getCurrentSolarSystem() { return currentSolarSystem; };

    public void setCurrentSolarSystem(SolarSystem desination) {
        currentSolarSystem = desination;
    };

    public HashSet<SolarSystem> getSolarSystems() {
        return solarSystems;
    }

    public HashSet<Planet> getPlanets() {
        return planets;
    }

    public void setPlanets(HashSet<Planet> planets) {
        this.planets = planets;
    }

    public void setSolarSystems(HashSet<SolarSystem> solarSystems) {
        this.solarSystems = solarSystems;
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
        Random rand = new Random();
        int startingLocation = rand.nextInt(number) - 1;

        for (int i = 0; i < number; i++) {

            int resourceNum =  rand.nextInt(resources.length);
            int techLevelNum = rand.nextInt(techLevels.length);

            Point point = new Point(rand.nextInt(340) + 5, rand.nextInt(340) + 5);
            while (points.contains(point)) {
                point = new Point(rand.nextInt(340) + 5, rand.nextInt(340) + 5);
            }
            points.add(point);

            Planet planet = new Planet(planetNames[planetCount], resources[resourceNum], techLevels[techLevelNum]);
            planets.add(planet);

            planetCount++;
            SolarSystem solarsystem = new SolarSystem(solarSystemNames[solarSystemCount], point, planet);
            solarSystemCount++;

            solarSystems.add(solarsystem);

            if (startingLocation == i) {

                setCurrentPlanet(planet);
                System.out.println(getCurrentPlanet());
                setCurrentSolarSystem(solarsystem);
                System.out.println(getCurrentSolarSystem());

            }

        }
    }

    public void endTurn() {
        //Do something to end the turn or whatever
    }


    /**
     * Saves the game
     * @return boolean to make sure game was saved
     *
     */
    public boolean saveGameInstance() {

        try {
            File file = new File("Game_Saves");
            file.mkdir();
            FileOutputStream saveFile = new FileOutputStream(file.getAbsoluteFile() + File.separator + player.getName() + ".sav");
            ObjectOutputStream save = new ObjectOutputStream(saveFile);
            save.writeObject(planets);
            save.writeObject(solarSystems);
            save.writeObject(player);
            save.writeObject(currentPlanet);
            save.writeObject(currentSolarSystem);
            save.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException f) {
            f.printStackTrace();
        }
        return false;
    }


    /**
     * Loads a saved game
     *
     * @param saveFileLocation
     *        Location of the save file
     * @return boolean to make sure game was loaded
     */
    public boolean loadGameInstance(String saveFileLocation) {

        try {
            FileInputStream openFile = new FileInputStream(saveFileLocation);
            ObjectInputStream restore = new ObjectInputStream(openFile);

            HashSet<Planet> planetsRestored = (HashSet<Planet>) restore.readObject();
            HashSet<SolarSystem> solarSystemsRestored = (HashSet<SolarSystem>) restore.readObject();
            Player restoredPlayer = (Player) restore.readObject();
            Planet restoredCurrentPlanet = (Planet) restore.readObject();
            SolarSystem restoredCurrentSolarSystem = (SolarSystem) restore.readObject();

            this.setPlanets(planetsRestored);
            this.setSolarSystems(solarSystemsRestored);
            this.setPlayer(restoredPlayer);
            this.setCurrentSolarSystem(restoredCurrentSolarSystem);
            this.setCurrentPlanet(restoredCurrentPlanet);

            openFile.close();


            return true;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException f) {
            f.printStackTrace();
        } catch (ClassNotFoundException g) {
            g.printStackTrace();
        }


        return false;

    }

    @Override
    public String toString() {

        String gameString = "SOLAR: ";

        for (SolarSystem s: solarSystems) {

            gameString = gameString + " " + s.toString();

        }

        gameString += "\n\n Current Player: " + player.toString() + "\n\n";
        gameString += "Current Planet: " + currentPlanet.toString()+ "\n\n";
        gameString += "Current SolarSystem: " + currentSolarSystem.toString() + "\n\n";


        return gameString;
    }

}

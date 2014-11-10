package model.core;

import model.commerce.Goods;

import java.io.*;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Singleton game monitor. The game model will control the passing of turns and
 * random events
 *
 * @author ngraves3
 *
 */
public final class GameInstance implements Serializable {

    /**
     * Reference to the Singleton GameInstance.
     */
    private static GameInstance instance = new GameInstance();
    /**
     * The player playing.
     */
    private Player player;
    /**
     * The solar systems in the game.
     */
    private Set<SolarSystem> solarSystems = new HashSet<>();
    /**
     * The planets in the game.
     */
    private Set<Planet> planets = new HashSet<>();
    /**
     * The points which are the locations of the solar systems.
     */
    private Set<Point> points = new HashSet<>();
    /**
     * Player's current location.
     */
    private Planet currentPlanet;
    /**
     * Players current location.
     */
    private SolarSystem currentSolarSystem;
    /**
     * Default location for saving games.
     */
    private File file = new File("game_saves");
    /**
     * Flag to check if game was created.
     */
    private boolean saveDirectoryCreated = false;
    /**
     * All the planet names. May or may not use all of them.
     */
    private String[] planetNames = {"Acamar", "Adahn", "Aldea", "Andevian", "Antedi", "Balosnee",
        "Baratas", "Brax", "Bretel", "Calondia", "Campor", "Capelle", "Carzon", "Castor",
        "Cestus", "Cheron", "Courteney", "Daled", "Damast", "Davlos", "Deneb", "Deneva",
        "Devidia", "Draylon", "Drema", "Endor", "Esmee", "Exo", "Ferris", "Festen", "Fourmi",
        "Frolix", "Gemulon", "Guinifer", "Hades", "Hamlet", "Helena", "Hulst", "Iodine",
        "Iralius", "Janus", "Japori", "Jarada", "Jason", "Kaylon", "Khefka", "Kira", "Klaatu",
        "Klaestron", "Korma", "Kravat", "Krios", "Laertes", "Largo", "Lave", "Ligon", "Lowry",
        "Magrat", "Malcoria", "Melina", "Mentar", "Merik", "Mintaka", "Montor", "Mordan",
        "Myrthe"};
    /**
     * Solar system names for the game. May or may not use all.
     */
    private String[] solarSystemNames = {"Nelvana", "Nix", "Nyle",
        "Odet", "Og", "Omega", "Omphalos", "Orias", "Othello",
        "Parade", "Penthara", "Picard", "Pollux",
        "Quator", "Rakhar", "Ran", "Regulas", "Relva", "Rhymus", "Rochani", "Rubicum", "Rutia",
        "Sarpeidon", "Sefalla", "Seltrice", "Sigma", "Sol", "Somari",
        "Stakoron", "Styris", "Talani", "Tamus", "Tantalos", "Tanuga",
        "Tarchannen", "Terosa", "Thera", "Titan", "Torin", "Triacus",
        "Turkana", "Tyrus",
        "Umberlee", "Utopia",
        "Vadera", "Vagra", "Vandor", "Ventax",
        "Xenon", "Xerxes",
        "Yew", "Yojimbo",
        "Zalkon", "Zuul"};

    /**
     * Private constructor for Singleton. Prevents others from accessing
     */
    private GameInstance() { //private constructor for singleton
    }

    /**
     * Gets the only GameInstance.
     *
     * @return the sole GameInstance
     */
    public static GameInstance getInstance() {
        return instance;
    }

    /**
     * Creates a universe with number of planets equal to the length of our default list of planet
     * names.
     */
    public void createUniverse() {
        createUniverse(Math.min(planetNames.length, solarSystemNames.length) - 1);
    }

    /**
     * create universe with specified number of planets.
     *
     * @param number
     *        of planets
     */
    public void createUniverse(int number) {
        if (number >= planetNames.length || number >= solarSystemNames.length) {
            throw new IllegalArgumentException(
                    "Number is bigger than planet names");
        }
        solarSystems.clear();
        planets.clear();
        points.clear();
        Random rand = new Random();
        int startingLocation = rand.nextInt(number) - 1;
        int planetCount = 0;
        int solarSystemCount = 0;
        for (int i = 0; i < number; i++) {

            int resourceNum =  rand.nextInt(Goods.values().length);
            int techLevelNum = rand.nextInt(TechLevel.values().length);

            Point point = new Point(rand.nextInt(340) + 5, rand.nextInt(340) + 5);
            while (points.contains(point)) {
                point = new Point(rand.nextInt(340) + 5, rand.nextInt(340) + 5);
            }
            points.add(point);

            Planet planet =
                    new Planet(planetNames[planetCount],
                            Goods.values()[resourceNum],
                            TechLevel.values()[techLevelNum]);
            planets.add(planet);

            planetCount++;
            SolarSystem solarsystem =
                    new SolarSystem(solarSystemNames[solarSystemCount],
                            point, planet);
            solarSystemCount++;

            solarSystems.add(solarsystem);

            if (startingLocation == i) {

                setCurrentPlanet(planet);
                //System.out.println(getCurrentPlanet());
                setCurrentSolarSystem(solarsystem);
                //System.out.println(getCurrentSolarSystem());

            }

        }

    }

    /**
     * Saves the game.
     *
     * @return boolean to make sure game was saved
     *
     */
    public boolean saveGameInstance() {

        try {
            if (!saveDirectoryCreated) {
                saveDirectoryCreated = file.mkdir();
            }
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
     * Loads a saved game.
     *
     * @param saveFileLocation
     *        Location of the save file
     * @return boolean to make sure game was loaded
     */
    public boolean loadGameInstance(String saveFileLocation) {

        try (FileInputStream openFile = new FileInputStream(saveFileLocation)) {
            try (ObjectInputStream restore = new ObjectInputStream(openFile)) {

                HashSet<Planet> planetsRestored = (HashSet<Planet>) restore.readObject();
                HashSet<SolarSystem> solarSystemsRestored =
                        (HashSet<SolarSystem>) restore.readObject();
                Player restoredPlayer = (Player) restore.readObject();
                Planet restoredCurrentPlanet = (Planet) restore.readObject();
                SolarSystem restoredCurrentSolarSystem = (SolarSystem) restore.readObject();

                this.setPlanets(planetsRestored);
                this.setSolarSystems(solarSystemsRestored);
                this.setPlayer(restoredPlayer);
                this.setCurrentSolarSystem(restoredCurrentSolarSystem);
                this.setCurrentPlanet(restoredCurrentPlanet);

                openFile.close();
                restore.close();

                return true;
            } catch (IOException ioe) {

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException f) {
            f.printStackTrace();
        } catch (ClassNotFoundException g) {
            g.printStackTrace();
        }

        return false;

    }

    /**
     * Gets the player's current planet.
     *
     * @return player's current planet
     */
    public Planet getCurrentPlanet() {
        return currentPlanet;
    }

    /**
     * Sets the player's location to a planet.
     *
     * @param destination
     *        the planet to go to
     */
    public void setCurrentPlanet(Planet destination) {
        this.currentPlanet = destination;
    }

    /**
     * Returns the player for the instance of this game.
     *
     * @return the Player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Sets a player for this game instance.
     *
     * @param data
     *        the Player to use for this instance
     */
    public void setPlayer(Player data) {
        player = data;
    }

    /**
     * Returns the solar system the player is in.
     *
     * @return the current solar system
     */
    public SolarSystem getCurrentSolarSystem() {
        return currentSolarSystem;
    }

    /**
     * Sets the current solar system to whatever is passed in.
     *
     * @param destination
     *        the new solar system
     */
    public void setCurrentSolarSystem(SolarSystem destination) {
        currentSolarSystem = destination;
    }

    /**
     * Returns a Set of the solar systems.
     *
     * @return Set of solar systems
     */
    public Set<SolarSystem> getSolarSystems() {
        return solarSystems;
    }

    /**
     * Sets the solar systems to the given set.
     *
     * @param solarSystemSet
     *        the set of solarSystems to use
     */
    public void setSolarSystems(Set<SolarSystem> solarSystemSet) {
        this.solarSystems = solarSystemSet;
    }

    /**
     * Returns a set of the planets.
     *
     * @return set of planets
     */
    public Set<Planet> getPlanets() {
        return planets;
    }

    /**
     * Sets the planet to the Set<Planet> arg.
     *
     * @param planetSet
     *        the planets to use as set of planets
     */
    public void setPlanets(Set<Planet> planetSet) {
        this.planets = planetSet;
    }



    @Override
    public String toString() {

        StringBuilder gameString = new StringBuilder();
        gameString.append("SOLAR: ");

        for (SolarSystem s: solarSystems) {

            gameString.append(" ").append(s.toString());

        }
        String term = "\n\n";
        gameString.append(term).append(" Current Player: ").append(player.toString()).append(term);
        gameString.append("Current Planet: ").append(currentPlanet.toString()).append(term);
        gameString.append("Current SolarSystem: ").append(currentSolarSystem.toString()).append(term);


        return gameString.toString();
    }

    public Set<Point> getPoints() {
        return points;
    }
}

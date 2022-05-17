import java.util.*;
import java.io.*;

public class Main {
    //do not change these values
    public static final ArrayList<String> CHALLENGE_NAMES_GROUP = new ArrayList<>(Arrays.asList(
            "It Has Ultimate In the Name!", "They Just... Don't... DIE!", "We're Good Over Here", "Get Over Here",
            "Hold That Pose", "Nowhere to Hide", "It's a Trap!", "We Protec", "Where'd They Go?",
            "Summoners on the Rift", "I'm Helping"));
    public static final String CHALLENGE_NAME_CLASS = "Variety's Overrated";
    public static final ArrayList<String> CHALLENGE_NAMES_PLACE = new ArrayList<>(Arrays.asList("5 Under 5'",
            "All Hands on Deck", "FOR DEMACIA", "Ice Ice Baby", "Everybody was Wuju Fighting",
            "Elemental My Dear Watson", "Strength Above All", "Calculated", "Spooky Scary Skeletons",
            "The Sun Disc Never Sets", "Peak Performance", "(Inhuman Screeching Sounds)", "Chemtech Comrades"));
    public static final int[] REQUIRED_CHAMPIONS_GROUP = {3, 3, 3, 3, 3, 3, 3, 3, 3, 5, 3};
    public static final int MAXIMUM_POSSIBLE_CHALLENGES = 8;

    //can change these addresses
    public static final String INPUT_ADDRESS = "champion-data.csv";
    public static final String OUTPUT_ADDRESS = "team-compositions.csv";

    //can also change these values
    public static final int MINIMUM_CHALLENGES = 6; //try not to set below 5 or the file will be too large
    public static final boolean PRINT_PROGRESS = false ; //if true, prints progress statements in terminal

    public static ArrayList<Champion> champions = new ArrayList<>();
    public static ArrayList<Champion> championsTop = new ArrayList<>();
    public static ArrayList<Champion> championsJungle = new ArrayList<>();
    public static ArrayList<Champion> championsMid = new ArrayList<>();
    public static ArrayList<Champion> championsBottom = new ArrayList<>();
    public static ArrayList<Champion> championsSupport = new ArrayList<>();


    public static void main(String[] args) {
        champions = getChampionData();
        addToRoles();
        makeComps();
    }


    //creates all champion objects and stores them in a list
    public static ArrayList<Champion> getChampionData() {
        ArrayList<Champion> champions = new ArrayList<>();

        Scanner fIn = null;
        try {
            fIn = new Scanner(new File(INPUT_ADDRESS));
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e);
            System.exit(1000);
        }

        String[] currentLn;
        String name;

        fIn.nextLine(); //skips first line of csv
        while (fIn.hasNext()) {
            currentLn = fIn.nextLine().split(",");

            //parses name, group, class, place, and role data from csv
            name = currentLn[0];
            boolean[] groups = new boolean[11];
            for (int i = 0; i < groups.length; i++) {
                groups[i] = Boolean.parseBoolean(currentLn[i + 1]);
            }
            boolean[] classes = new boolean[6];
            for (int i = 0; i < classes.length; i++) {
                classes[i] = Boolean.parseBoolean(currentLn[i + 1 + groups.length]);
            }
            boolean[] places = new boolean[13];
            for (int i = 0; i < places.length; i++) {
                places[i] = Boolean.parseBoolean(currentLn[i + 1 + groups.length + classes.length]);
            }
            boolean[] roles = new boolean[5];
            for (int i = 0; i < roles.length; i++) {
                roles[i] = Boolean.parseBoolean(currentLn[i + 1 + groups.length + classes.length + places.length]);
            }

            champions.add(new Champion(name, groups, classes, places, roles));
        }

        return champions;
    } //getChampionData


    //adds champs to lists for the 5 roles
    public static void addToRoles() {
        //Top
        for (Champion champCurrent : champions) {
            if (champCurrent.isInRole(0)) {
                championsTop.add(champCurrent);
            }
        }

        //Jungle
        for (Champion champCurrent : champions) {
            if (champCurrent.isInRole(1)) {
                championsJungle.add(champCurrent);
            }
        }

        //Mid
        for (Champion champCurrent : champions) {
            if (champCurrent.isInRole(2)) {
                championsMid.add(champCurrent);
            }
        }

        //Bottom
        for (Champion champCurrent : champions) {
            if (champCurrent.isInRole(3)) {
                championsBottom.add(champCurrent);
            }
        }

        //Support
        for (Champion champCurrent : champions) {
            if (champCurrent.isInRole(4)) {
                championsSupport.add(champCurrent);
            }
        }

    } //addToRoles


    //checks every team comp and writes valid results to a csv
    public static void makeComps() {
        CSVWriter csv = new CSVWriter((OUTPUT_ADDRESS));
        ArrayList<Champion> champsCurrentTeam = new ArrayList<>();

        //writes first line of csv
        csv.addEntry("# of Challenges");
        csv.addEntry("Top");
        csv.addEntry("Jungle");
        csv.addEntry("Mid");
        csv.addEntry("Bottom");
        csv.addEntry("Support");
        for(int i = 1; i <= MAXIMUM_POSSIBLE_CHALLENGES; i++) {
            csv.addEntry("Challenge #" + i);
        }
        csv.endLine();

        //placeholder objects
        for (int i = 0; i < 5; i++) {
            champsCurrentTeam.add(new Champion("TEMPORARY"));
        }

        //iterates through every possible combination of 1 top, 1 jungle, 1 mid, 1 bot, and 1 supp champion
        for (Champion champ0 : championsTop) {
            champsCurrentTeam.set(0, champ0);
            if (PRINT_PROGRESS) {
                System.out.println("Currently on: " + champ0.toString());
            }

            //creates every possible comp, excluding those with duplicate champions
            for (Champion champ1 : championsJungle) {
                if (!champsCurrentTeam.contains(champ1)) {
                    champsCurrentTeam.set(1, champ1);

                    for (Champion champ2 : championsMid) {
                        if (!champsCurrentTeam.contains(champ2)) {
                            champsCurrentTeam.set(2, champ2);

                            for (Champion champ3 : championsBottom) {
                                if (!champsCurrentTeam.contains(champ3)) {
                                    champsCurrentTeam.set(3, champ3);

                                    for (Champion champ4 : championsSupport) {
                                        if (!champsCurrentTeam.contains(champ4)) {
                                            champsCurrentTeam.set(4, champ4);

                                            Team team = new Team(champsCurrentTeam);

                                            //adds score and names for group challenges
                                            for (int n = 0; n < 11; n++) {
                                                int sum = 0;

                                                for (int o = 0; o < 5; o++) {
                                                    if (team.champAt(o).isInGroup(n)) {
                                                        ++sum;
                                                    }
                                                }

                                                if (sum >= REQUIRED_CHAMPIONS_GROUP[n]) {
                                                    team.addScore(1);
                                                    team.addCompNames(CHALLENGE_NAMES_GROUP.get(n));
                                                }
                                            }

                                            //adds score and names for same class challenge
                                            for (int n = 0; n < 6; n++) {
                                                int sum = 0;

                                                for (int o = 0; o < 5; o++) {
                                                    if (team.champAt(o).isInClass(n)) {
                                                        ++sum;
                                                    }
                                                }

                                                if (sum >= 5) {
                                                    team.addScore(1);
                                                    team.addCompNames(CHALLENGE_NAME_CLASS);
                                                }
                                            }

                                            //adds score and names for place of origin challenges
                                            for (int n = 0; n < 13; n++) {
                                                int sum = 0;

                                                for (int o = 0; o < 5; o++) {
                                                    if (team.champAt(o).isInPlace(n)) {
                                                        ++sum;
                                                    }
                                                }

                                                if (sum >= 5) {
                                                    team.addScore(1);
                                                    team.addCompNames(CHALLENGE_NAMES_PLACE.get(n));
                                                }
                                            }

                                            //if score is above threshold, write to csv
                                            if (team.getScore() >= MINIMUM_CHALLENGES) {
                                                //writes one line in csv for this team comp
                                                csv.addEntry(team.getScore());
                                                for (int o = 0; o < 5; o++) {
                                                    csv.addEntry(team.champAt(o).toString());
                                                }
                                                for (int o = 0; o < team.getChallengeNames().size(); o++) {
                                                    csv.addEntry(team.getChallengeNames().get(o));
                                                }
                                                csv.endLine();
                                            }
                                        }
                                    } //Support
                                }
                            } //Bottom
                        }
                    } //Mid
                }
            } //Jungle
        } //Top
        csv.close();
    } //makeComps

} //class
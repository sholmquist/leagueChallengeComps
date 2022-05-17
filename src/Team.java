/*  Custom Team class that contains a list of champions and a list and amount of fulfilled challenges.
*/

import java.util.*;

public class Team {
    private ArrayList<Champion> champs;
    private ArrayList<String> challengeNames = new ArrayList<>();
    private int score;

    public Team(ArrayList<Champion> champs) {
        this.champs = champs;
    }

    @Override
    public String toString() {
        return champs.toString();
    }

    public Champion champAt(int i) {
        return champs.get(i);
    }

    public ArrayList<String> getChallengeNames() {
        return this.challengeNames;
    }

    public void addCompNames(String string) {
        this.challengeNames.add(string);
    }

    public int getScore() {
        return this.score;
    }

    public void addScore(int toAdd) {
        this.score += toAdd;
    }

}
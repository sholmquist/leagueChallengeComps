/*  Custom Champion class that contains a name and attributes correlating to eligible challenges.
*/

public class Champion {
    private String name;
    private boolean[] groups; //AOEUlt, Revive, Poke, Hook, CrowdControl, Global, Trap, Heals, Stealth, Summon, Terrain
    private boolean[] classes; //Assassin, Fighter, Mage, Marksman, Support, Tank
    private boolean[] places; //BandleCity, Bilgewater, Demacia, Freljord, Ionia, Ixtal, Noxus, Piltover, ShadowIsles, Shurima, Targon, Void, Zaun
    private boolean[] roles; //Top, Jungle, Mid, Bottom, Support

    public Champion(String name, boolean[] groups, boolean[] classes, boolean[] places, boolean[] roles) {
        this.name = name;
        this.groups = groups;
        this.classes = classes;
        this.places = places;
        this.roles = roles;
    }

    public Champion(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Champion)) {
            return false;
        }

        Champion c = (Champion) o;
        return name.equals(c.name);
    }

    public boolean isInGroup(int i) {
        return groups[i];
    }

    public boolean isInClass(int i) {
        return classes[i];
    }

    public boolean isInPlace(int i) {
        return places[i];
    }

    public boolean isInRole(int i) {
        return roles[i];
    }

}
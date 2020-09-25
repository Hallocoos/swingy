package model;

public class Hero implements java.io.Serializable {
    private String name;
    private String type;
    private int attack;
    private int defence;
    private int totalHitpoints;
    private int currentHitpoints;
    private int level;
    private int experience;
    private int x;
    private int y;

    public Hero(String name, String type, int attack, int defence, int hitpoints) {
        this.name = name;
        this.type = type;
        this.attack = attack;
        this.defence = defence;
        this.totalHitpoints = hitpoints;
        this.currentHitpoints = hitpoints;
        this.level = 1;
        this.experience = 0;
        this.x = 2;
        this.y = 2;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    public int getExperience() {
        return experience;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setCurrentHitpoints(int currentHitpoints) {
        this.currentHitpoints = currentHitpoints;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void incrementAttack(int attack) {
        this.attack += attack;
    }

    public void incrementDefence(int defence) {
        this.defence += defence;
    }

    public void incrementTotalHitpoints(int hitpoints) {
        this.totalHitpoints += hitpoints;
    }

    public void incrementCurrentHitpoints(int hitpoints) {
        this.currentHitpoints += hitpoints;
    }

    public void decrementCurrentHitpoints(int hitpoints) {
        this.currentHitpoints -= hitpoints;
    }

    public boolean incrementExperience(int experience) {
        this.experience = this.experience + experience;
        if (this.experience >= 500 && this.level != 5) {
            this.level = 5;
            return true;
        } else if (this.experience >= 400 && this.level != 4) {
            this.level = 4;
            return true;
        } else if (this.experience >= 300 && this.level != 3) {
            this.level = 3;
            return true;
        } else if (this.experience >= 200 && this.level != 2) {
            this.level = 2;
            return true;
        } else if (this.experience >= 100 && this.level != 1) {
            this.level = 1;
            return true;
        }
        return false;
    }

    public String heroSaveDetails() {
        return name + "," +
        type + "," +
        attack + "," +
        defence + "," +
        totalHitpoints + "," +
        currentHitpoints + "," +
        level + "," +
        experience + "," +
        x + "," +
        y + "\n";
    }

    @Override
    public String toString() {
        return "Hero{" + 
        " name = '" + name + '\'' + 
        ", type = '" + type + '\'' + 
        ", attack = " + attack + 
        ", defence = " + defence + 
        ", totalHitpoints = " + totalHitpoints + 
        ", currentHitpoints = " + currentHitpoints + 
        ", level = " + level + 
        ", experience = " + experience + 
        ", x = " + x + 
        ", y = " + y + '}';
    }
}
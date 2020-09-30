package model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Hero implements java.io.Serializable {
    @NotNull
    @NotBlank(message = "Hero name must not be blank")
    private String name;

    @NotNull
    @NotBlank(message = "Hero class must not be blank")
    private String type;

    @NotNull
    @Min(value = 0, message = "Attack cannot be lower than 0")
    private int attack;

    @NotNull
    @Min(value = 0, message = "Defense cannot be lower than 0")
    private int defense;

    @NotNull
    @Min(value = 0, message = "Total Health cannot be lower than 0")
    private int totalHitpoints;

    private int currentHitpoints;

    @NotNull
    @Min(value = 1, message = "Level cannot be lower than 1")
    @Max(value = 4, message = "Level cannot be higher than 4")
    private int level;

    @NotNull
    @Min(value = 0, message = "Experience cannot be lower than 0")
    private int experience;

    @NotNull
    @Min(value = 0, message = "Edge of map exceeded")
    private int x;

    @NotNull
    @Min(value = 0, message = "Edge of map exceeded")
    private int y;


    public Hero(String name, String type, int attack, int defense, int hitpoints) {
        this.name = name;
        this.type = type;
        this.attack = attack;
        this.defense = defense;
        this.totalHitpoints = hitpoints;
        this.currentHitpoints = hitpoints;
        this.level = 1;
        this.experience = 0;
        this.x = 2;
        this.y = 2;
    }

    public int getTotalHitpoints() {
        return totalHitpoints;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
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

    public int getCurrentHitpoints() {
        return currentHitpoints;
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

    public void incrementDefence(int defense) {
        this.defense += defense;
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
                defense + "," +
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
        ", defense = " + defense + 
        ", totalHitpoints = " + totalHitpoints + 
        ", currentHitpoints = " + currentHitpoints + 
        ", level = " + level + 
        ", experience = " + experience + 
        ", x = " + x + 
        ", y = " + y + '}';
    }
}
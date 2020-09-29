package controller;

import java.lang.Math;

import model.GameSaveHandler;
import model.Hero;
import java.util.Scanner;

public class GameController {
    private Hero hero;
    private int[][] map;
    private GameSaveHandler gameSaveHandler;
    Scanner scanner = new Scanner(System.in);

    public GameController() {
        gameSaveHandler = new GameSaveHandler();
    }

    public void createHero(String name, String heroClass, int attack, int defence, int hitpoints) {
        this.hero = new Hero(name, heroClass, attack, defence, hitpoints);
        saveHero(hero);
        generateMap(hero.getLevel());
    }

    public void saveHero(Hero hero) {
        this.gameSaveHandler.saveHero(hero);
    }

    public boolean loadHero(String heroName) {
        hero = this.gameSaveHandler.loadHero(heroName);
        if (hero != null) {
            System.out.println(hero);
            generateMap(hero.getLevel());
            return true;
        }
        return false;
    }

    public boolean moveHero(String move) {
        int squareRoot = (int) ((hero.getLevel() - 1) * 5 + 10 - (hero.getLevel() % 2));
        if (move.equalsIgnoreCase("n")) {
            if (hero.getX() - 1 >= 0 && hero.getX() - 1 < squareRoot)
                hero.setX(hero.getX() - 1);
        } else if (move.equalsIgnoreCase("s")) {
            if (hero.getX() + 1 >= 0 && hero.getX() + 1 < squareRoot)
                hero.setX(hero.getX() + 1);
        } else if (move.equalsIgnoreCase("w")) {
            if (hero.getY() - 1 >= 0 && hero.getY() - 1 < squareRoot)
                hero.setY(hero.getY() - 1);
        } else if (move.equalsIgnoreCase("e")) {
            if (hero.getY() + 1 >= 0 && hero.getY() + 1 < squareRoot)
                hero.setY(hero.getY() + 1);
        }
        this.gameSaveHandler.saveHero(hero);
        if (this.map[hero.getX()][hero.getY()] == 2)
            return event();
        return true;
    }

    public void generateMap(int heroLevel) {
        int max = 10;
        int min = 3;
        int squareRoot = (int) ((heroLevel - 1) * 5 + 10 - (heroLevel % 2));
        this.map = new int[squareRoot][squareRoot];
        for (int i = 0; i < squareRoot; i++) {
            for (int j = 0; j < squareRoot; j++) {
                int randomInt = (int) (Math.random() * (max - min + 1) + min);
                if (randomInt <= 4)
                    this.map[i][j] = 2;
                else
                    this.map[i][j] = 0;
            }
        }
        if (hero.getX() == 0 && hero.getY() == 0) {
            hero.setX(squareRoot / 2);
            hero.setY(squareRoot / 2);
        }
        this.map[hero.getX()][hero.getY()] = 0;
    }

    public void printMap() {
        for (int i = 0; i < this.map.length; i++) {
            for (int j = 0; j < this.map.length; j++) {
                if (this.hero.getX() == i && this.hero.getY() == j)
                    System.out.print("1");
                else
                    System.out.print(this.map[i][j]);
                System.out.print(" ");
            }
            System.out.println("");
        }
    }

    public boolean event() {
        System.out.flush();
        System.out.print("\033[H\033[2J");
        System.out.println("You have run into an enemy. What do you want to do?\nf) Fight\nr) Run");
        String line = scanner.nextLine();
        int randomInt = (int) (Math.random() * (10 - 1 + 1) + 10);
        if (line.equalsIgnoreCase("f")) {
            return fight();
        } else if (line.equalsIgnoreCase("r")) {
            if (randomInt <= 1) {
                gameSaveHandler.deleteHero(hero.getName());
                System.out.flush();
                System.out.print("\033[H\033[2J");
                System.out.println("You have died!");
                System.out.println("Insert any text to continue!");
                line = scanner.nextLine();
                return false;
            } else {
                System.out.flush();
                System.out.print("\033[H\033[2J");
                System.out.println("You have escaped with your life!");
                System.out.println("Insert any text to continue!");
                line = scanner.nextLine();
                saveHero(hero);
            }
        } else
            event();
        return true;
    }

    public int formulaForDamage(int attack, int defense) {
        return (int)((float)attack * ((100 - (float)defense) / (100)));
    }

    public boolean fight() {
        int randomInt = (int) (Math.random() * (10 - 1 + 1) + 10);
        int villianAttack = (int) (Math.random() * (7 - 3 + 1) + 7);
        int villianHealth = (int) (Math.random() * (10 - 5 + 1) + 10);
        int villianDefense = (int) (Math.random() * (3 - 1 + 1) + 3);
        while (hero.getCurrentHitpoints() > 0 && villianHealth > 0) {
            hero.setCurrentHitpoints(hero.getCurrentHitpoints() - formulaForDamage(villianAttack, hero.getDefense()));
            villianHealth = villianHealth - (formulaForDamage(hero.getAttack(), villianDefense));
        }
        if (hero.getCurrentHitpoints() > villianHealth) {
            if (hero.getLevel() == 5) {
                gameSaveHandler.deleteHero(hero.getName());
                System.out.flush();
                System.out.print("\033[H\033[2J");
                System.out.println("You have beat the game!");
                System.out.println("Insert any text to continue!");
                String line = scanner.nextLine();
                return false;
            } else
                System.out.println("You have won the fight!");
            if (randomInt <= 1) {
                System.out.println("You picked up an artifact that boosts your Hitpoints!");
                hero.incrementCurrentHitpoints(5);
            } else if (randomInt <= 2) {
                System.out.println("You picked up an artifact that boosts your Defence!");
                hero.incrementDefence(5);
            } else if (randomInt <= 3) {
                System.out.println("You picked up an artifact that boosts your Attack!");
                hero.incrementAttack(5);
            }
            hero.setCurrentHitpoints(hero.getTotalHitpoints());
            System.out.println("You rest and regain all of your hitpoints!");
            this.map[hero.getX()][hero.getY()] = 0;
            if (hero.incrementExperience(50)) {
                generateMap(hero.getLevel());
            }
            saveHero(hero);
            String line = scanner.nextLine();
            return true;
        } else {
            gameSaveHandler.deleteHero(hero.getName());
            System.out.flush();
            System.out.print("\033[H\033[2J");
            System.out.println("You have died!");
            System.out.println("Insert any text to continue!");
            String line = scanner.nextLine();
            return false;
        }
    }

    public void generateVillian() {

    }
}

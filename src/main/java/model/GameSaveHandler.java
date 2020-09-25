package model;

import java.io.*;
import java.io.ObjectOutputStream;

public class GameSaveHandler {
    public GameSaveHandler() {
        File directory = new File("heroes/");
        if (!directory.exists())
            directory.mkdir();
    }

    public void saveHero(Hero hero) {
        File saveFile = new File("heroes/" + hero.getName() + ".txt");
        try {
            FileOutputStream fileOut = new FileOutputStream(saveFile);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(hero);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in save.txt\n");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public Hero loadHero(String heroName) {
        File saveFile = new File("heroes/" + heroName + ".txt");
        if (!saveFile.exists()) {
            System.out.println("Hero file not found");
            return null;
        }
        Hero hero = null;
        try {
            FileInputStream fileIn = new FileInputStream(saveFile);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            hero = (Hero) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("Hero class not found");
            c.printStackTrace();
            return null;
        }
        System.out.println(hero);
        return hero;
    }

    public void deleteHero(String heroName) {
        File saveFile = new File("heroes/" + heroName + ".txt");
        saveFile.delete();
    }
}

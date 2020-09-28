package model;

import java.io.*;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.lang.SecurityManager.*;

public class GameSaveHandler {
    Scanner scanner = new Scanner(System.in);

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
        // String line;
        File saveFile = new File("heroes/" + heroName + ".txt");
        if (!saveFile.exists()) {
            System.out.println("Hero file not found");
            // line = scanner.nextLine();
            return null;
        }
        Hero hero = null;
        try {
            FileInputStream fileIn = new FileInputStream(saveFile);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            hero = (Hero) in.readObject();
            in.close();
            fileIn.close();
            System.out.println(hero);
            System.out.println("Save file successfully loaded.");
            System.out.println("Press any key to continue.");
            // line = scanner.nextLine();
            return hero;
        } catch (IOException i) {
            // i.printStackTrace();
            System.out.println("File corrupted.");
            deleteHero(heroName);
            return null;
        } catch (ClassNotFoundException c) {
            // c.printStackTrace();
            System.out.println("Hero class not found.");
            System.out.println("Press any key to continue.");
            // line = scanner.nextLine();
            return null;
        }
    }

    public void deleteHero(String heroName) {
        File saveFile = new File("heroes\\" + heroName + ".txt");
        String path = saveFile.getAbsolutePath();
        if (saveFile.delete()) {
            System.out.println(path + " has been deleted.");
            System.out.println("Press any key to continue.");
            String line = scanner.nextLine();
        } else {
            System.out.println(path + " couldn't be deleted.");
            System.out.println("Press any key to continue.");
            String line = scanner.nextLine();
        }
    }
}

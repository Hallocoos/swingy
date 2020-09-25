package view;

import java.util.Scanner;
import controller.GameController;

public class ConsoleGUI {
    private GameController gameController;
    Scanner scanner = new Scanner(System.in);

    public ConsoleGUI(GameController gameController, int i) {
        this.gameController = gameController;
        if (i == 1)
            displayGameConsole();
        else
            displayMainMenuConsole();
    }

    public void displayMainMenuConsole() {
        System.out.flush();
        System.out.print("\033[H\033[2J");
        System.out.println("1) New Game\n2) Load Game\n3) Quit");
        String line = scanner.nextLine();
        if (line.equalsIgnoreCase("1")) {
            displayNewGameConsole();
        } else if (line.equalsIgnoreCase("2")) {
            displayLoadGameConsole();
        } else if (line.equalsIgnoreCase("3")) {
            System.out.flush();
            System.out.print("\033[H\033[2J");
            System.exit(0);
        } else
            displayMainMenuConsole();
    }

    public void displayNewGameConsole() {
        String name = "";
        while (name.length() < 1 || name.equalsIgnoreCase("b") || name.length() > 16) {
            System.out.flush();
            System.out.print("\033[H\033[2J");
            System.out.println("Choose your hero's name:\n");
            name = scanner.nextLine();
        }
        System.out.flush();
        System.out.print("\033[H\033[2J");
        System.out.println("Choose your hero's class:\n1) Archer\n2) Knight\n3) Mage");
        String heroClass = scanner.nextLine();
        if (heroClass.equalsIgnoreCase("1"))
            gameController.createHero(name, "Archer", 15, 15, 15);
        else if (heroClass.equalsIgnoreCase("2"))
            gameController.createHero(name, "Knight", 10, 20, 15);
        else if (heroClass.equalsIgnoreCase("3"))
            gameController.createHero(name, "Mage", 25, 10, 10);
        else
            displayNewGameConsole();
        displayGameConsole();
    }

    public void displayLoadGameConsole() {
        System.out.flush();
        System.out.print("\033[H\033[2J");
        System.out.println("Type in your hero's name to load from save file\nb) Back to Main Menu");
        String line = scanner.nextLine();
        if (line.equalsIgnoreCase("b")) {
            displayMainMenuConsole();
        } else if (gameController.loadHero(line)) {
            displayGameConsole();
        } else if (!gameController.loadHero(line)) {
            displayLoadGameConsole();
        }
    }

    public void displayGameConsole() {
        String line = "";
        while (!line.equalsIgnoreCase("b")) {
            System.out.flush();
            System.out.print("\033[H\033[2J");
            // gameController.printMap();
            System.out.println(
                    "Which direction do you wish to move?\nn) North\ns) South\nw) West\ne) East\nb) Back to Main Menu");
            line = scanner.nextLine();
            if (!gameController.moveHero(line))
                displayMainMenuConsole();
        }
        displayMainMenuConsole();
    }
}

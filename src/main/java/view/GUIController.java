package view;

import javax.swing.*;
import controller.GameController;

public class GUIController {
    private JFrame frameGUI;
    private NewGameGUI newGameGUI;
    private LoadGameGUI loadGameGUI;
    private MainMenuGUI mainMenuGUI;
    private GameGUI gameGUI;

    public GUIController(String[] args, GameController gameController) {
        if (args[0].equalsIgnoreCase("gui")) {
            frameGUI = new JFrame();
            frameGUI.setSize(700, 600);
            frameGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frameGUI.setVisible(true);
            displayMainMenuGUI(gameController);
        } else if (args[0].equalsIgnoreCase("console")) {
            new ConsoleGUI(gameController, 0);
        } else {
            System.out.flush();
            System.out.print("\033[H\033[2J");
            System.out.println("No view method selected. Options: `gui` or `console`\n Programme is closing");
            System.exit(0);
        }
    }

    public void displayMainMenuGUI(GameController gameController) {
        frameGUI.getContentPane().removeAll();
        mainMenuGUI = new MainMenuGUI(frameGUI, this, gameController);
        frameGUI.getContentPane().add(mainMenuGUI);
        frameGUI.revalidate();
        frameGUI.repaint();
    }

    public void displayNewGameGUI(GameController gameController) {
        frameGUI.getContentPane().removeAll();
        newGameGUI = new NewGameGUI(frameGUI, this, gameController);
        frameGUI.getContentPane().add(newGameGUI);
        frameGUI.revalidate();
        frameGUI.repaint();
    }

    public void displayLoadGameGUI(GameController gameController) {
        frameGUI.getContentPane().removeAll();
        loadGameGUI = new LoadGameGUI(frameGUI, this, gameController);
        frameGUI.getContentPane().add(loadGameGUI);
        frameGUI.revalidate();
        frameGUI.repaint();
    }

    public void displayGameGUI (GameController gameController) {
        frameGUI.getContentPane().removeAll();
        gameGUI = new GameGUI(frameGUI, this, gameController);
        frameGUI.getContentPane().add(gameGUI);
        frameGUI.revalidate();
        frameGUI.repaint();
    }

    public void displayGameConsole(GameController gameController) {
    }
}

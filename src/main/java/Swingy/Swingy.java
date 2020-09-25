package Swingy;

import controller.GameController;
import view.GUIController;

public class Swingy {
    public static void main(String[] args) {
        if (args.length > 0) {
            GameController gameController = new GameController();
            new GUIController(args, gameController);
            while (1 == 1) {
            }
        }
    }
} 
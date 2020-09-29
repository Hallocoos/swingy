package view;

import javax.swing.*;
import java.awt.event.*;
import controller.GameController;

public class GameGUI extends JPanel {
    private JButton btnNorth, btnSouth, btnWest, btnEast, btnBack, btnFight, btnRun;
    private GUIController guiController;
    private GameController gameController;

    public GameGUI(JFrame frameGUI, GUIController guiController, GameController gameController) {
        this.guiController = guiController;
        this.gameController = gameController;

        btnNorth = new JButton("North");
        btnSouth = new JButton("South");
        btnEast = new JButton("West");
        btnWest = new JButton("East");
        btnBack = new JButton("Back");
        btnFight = new JButton("Fight");
        btnRun = new JButton("Run");

        this.add(btnNorth);
        this.add(btnSouth);
        this.add(btnEast);
        this.add(btnWest);
        this.add(btnBack);
        this.add(btnFight);
        this.add(btnRun);


        btnNorth.addActionListener(actions);
        btnSouth.addActionListener(actions);
        btnEast.addActionListener(actions);
        btnWest.addActionListener(actions);
        btnBack.addActionListener(actions);
        btnFight.addActionListener(actions);
        btnRun.addActionListener(actions);

        setVisible(true);
        setSize(400, 400);

        frameGUI.getContentPane().add(this);
    }

    private ActionListener actions = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnNorth) {
                move("n");
            } else if (e.getSource() == btnSouth) {
                move("s");
            } else if (e.getSource() == btnEast) {
                move("w");
            } else if (e.getSource() == btnWest) {
                move("e");
            } else if (e.getSource() == btnBack) {
                back();
            }
        }
    };

    public void back() {
        guiController.displayMainMenuGUI(gameController);
    }

    public void move(String move) {
        System.out.println(gameController.moveHero(move));
        if (gameController.moveHero(move) == false)
            guiController.displayMainMenuGUI(gameController);
    }
}

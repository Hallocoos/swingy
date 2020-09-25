package view;

import javax.swing.*;
import java.awt.event.*;
import controller.GameController;

public class GameGUI extends JPanel {
    private JButton btnNorth, btnSouth, btnWest, btnEast, btnBack;
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

        this.add(btnNorth);
        this.add(btnSouth);
        this.add(btnEast);
        this.add(btnWest);
        this.add(btnBack);

        btnNorth.addActionListener(actions);
        btnSouth.addActionListener(actions);
        btnEast.addActionListener(actions);
        btnWest.addActionListener(actions);
        btnBack.addActionListener(actions);

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
        gameController.moveHero(move);
    }
}

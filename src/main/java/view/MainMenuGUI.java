package view;

import javax.swing.*;
import java.awt.event.*;
import controller.GameController;

public class MainMenuGUI extends JPanel {
    protected JButton btnNewGame, btnLoadGame, btnQuit;
    private GUIController guiController;
    private GameController gameController;

    public MainMenuGUI(JFrame frameGUI, GUIController guiController, GameController gameController) {
        this.guiController = guiController;
        this.gameController = gameController;

        btnNewGame = new JButton("New Game");
        btnLoadGame = new JButton("Load Game");
        btnQuit = new JButton("Quit");

        this.add(btnNewGame);
        this.add(btnLoadGame);
        this.add(btnQuit);

        btnNewGame.addActionListener(actions);
        btnLoadGame.addActionListener(actions);
        btnQuit.addActionListener(actions);

        setVisible(true);
        setSize(400, 400);

        frameGUI.getContentPane().add(this);
    }

    private ActionListener actions = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnNewGame) {
                newGame();
            } else if (e.getSource() == btnLoadGame) {
                loadGame();
            } else if (e.getSource() == btnQuit) {
                quitGame();
            }
        }
    };

    public void newGame() {
        guiController.displayNewGameGUI(gameController);
    }

    public void loadGame() {
        guiController.displayLoadGameGUI(gameController);
    }

    public void quitGame() {
        if (JOptionPane.showConfirmDialog(null, "Are you sure you would like to quit the game?","Warning", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            System.exit(0);
    }
}
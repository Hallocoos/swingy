package view;

import javax.swing.*;
import java.awt.event.*;
import controller.GameController;
import view.ConsoleGUI;

public class LoadGameGUI extends JPanel {
    protected JButton btnLoadChar, btnBack;
    private GUIController guiController;
    private GameController gameController;
    protected JTextField tfCharacterName;

    public LoadGameGUI(JFrame frameGUI, GUIController guiController, GameController gameController) {
        this.guiController = guiController;
        this.gameController = gameController;

        tfCharacterName = new JTextField();
        btnLoadChar = new JButton("Load");
        btnBack = new JButton("Back");

        tfCharacterName.setColumns(5);

        this.add(btnLoadChar);
        this.add(btnBack);

        btnLoadChar.addActionListener(actions);
        btnBack.addActionListener(actions);
        this.add(tfCharacterName);

        setVisible(true);
        setSize(400, 400);

        frameGUI.getContentPane().add(this);
    }

    private ActionListener actions = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnLoadChar) {
                loadHero();
            } else if (e.getSource() == btnBack) {
                back();
            }
        }
    };

    public void loadHero() {
        if (gameController.loadHero(tfCharacterName.getText())) {
            ConsoleGUI consoleGUI = new ConsoleGUI(gameController, 1);
        }
    }

    public void back() {
        guiController.displayMainMenuGUI(gameController);
    }
}

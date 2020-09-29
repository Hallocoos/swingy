package view;

import javax.swing.*;
import java.awt.event.*;
import controller.GameController;

public class NewGameGUI extends JPanel {
    protected JButton btnCreateCharacter, btnBack;
    protected JTextField tfCharacterName;
    protected JComboBox<String> cbClasses;
    protected JLabel lblName;
    protected JTextPane tpConsole;
    private GUIController guiController;
    private GameController gameController;

    public NewGameGUI(JFrame frameGUI, GUIController guiController, GameController gameController) {
        this.guiController = guiController;
        this.gameController = gameController;

        String[] classOptions = { "Archer", "Mage", "Knight" };

        lblName = new JLabel("Hero name: ");
        tfCharacterName = new JTextField();
        cbClasses = new JComboBox<>(classOptions);
        btnCreateCharacter = new JButton("Create Character");
        btnBack = new JButton("Back");
        tpConsole = new JTextPane();

        lblName.setSize(80, 20);
        tfCharacterName.setColumns(5);
        tpConsole.setEditable(false);
        tpConsole.setSize(80, 80);

        this.add(lblName);
        this.add(tfCharacterName);
        this.add(cbClasses);
        this.add(btnCreateCharacter);
        this.add(btnBack);

        btnBack.addActionListener(actions);
        btnCreateCharacter.addActionListener(actions);

        setVisible(true);
        setSize(400, 400);

        frameGUI.getContentPane().add(this);
    }

    private ActionListener actions = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnCreateCharacter) {
                createHero();
            } else if (e.getSource() == btnBack) {
                back();
            }
        }
    };

    public void createHero() {
        if (cbClasses.getSelectedIndex() == 0) {
            gameController.createHero(tfCharacterName.getText(), "Archer", 15, 15, 15);
        } else if (cbClasses.getSelectedIndex() == 1) {
            gameController.createHero(tfCharacterName.getText(), "Knight", 10, 20, 15);
        } else if (cbClasses.getSelectedIndex() == 2) {
            gameController.createHero(tfCharacterName.getText(), "Mage", 25, 10, 10);
        }
        guiController.displayGameGUI(gameController);
    }

    public void back() {
        guiController.displayMainMenuGUI(gameController);
    }
}
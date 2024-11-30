package view;

import interface_adapter.learn_mode.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * This is the moves view for the learn mode use case.
 */

public class MovesView extends JPanel implements ActionListener, PropertyChangeListener {
    private LearnModeController learnModeController;

    public MovesView(ObjectiveViewModel objectiveViewModel, DealingViewModel dealingViewModel, MovesViewModel movesViewModel) {
        objectiveViewModel.addPropertyChangeListener(this);
        movesViewModel.addPropertyChangeListener(this);
        dealingViewModel.addPropertyChangeListener(this);

        this.setLayout(new BorderLayout());
        Font font = new Font("Times New Roman", Font.BOLD, 20);

        // This button should go back to the main menu view
        JButton mainMenuButton = new JButton("MAIN MENU");
        this.add(mainMenuButton, BorderLayout.NORTH);

        // Making a panel in the center which would contain all the instructions and main content
        // of learn mode
        JPanel instructionsPanel = new JPanel(new GridBagLayout());
        // Making grid bag constraints to allow for positioning of elements in the instructionsPanel
        GridBagConstraints constraints = new GridBagConstraints();

        // This label tells player what the overall objective of the game is
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        JLabel objective = new JLabel("If player dealt 21 (in their first two cards) it is a BLACKJACK.\n" +
                "At each turn, a player can HIT or STAY.\n" +
                "\n" +
                "HIT: Player is dealt another card from the deck \n" +
                "STAY: Player is not dealt a card, it’s the next player’s turn \n" +
                "\n" +
                "If player hits and the sum of their card values exceeds 21, it is a BUST.");
        instructionsPanel.add(objective, constraints);

        // This button allows player to click Objective and see what the objective of the game is
        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        JButton objectiveButton = new JButton("Objective");
        instructionsPanel.add(objectiveButton, constraints);

        // This button allows player to click Dealing and see how the cards are dealt in a BlackJack game
        constraints.gridx = 2;
        constraints.gridy = 1;
        JButton dealingButton = new JButton("Dealing");
        instructionsPanel.add(objectiveButton, constraints);

        // This button allows player to click Moves and see what moves are possible in a BlackJack game
        constraints.gridx = 2;
        constraints.gridy = 2;
        JButton movesButton = new JButton("Moves");
        instructionsPanel.add(movesButton, constraints);

        objectiveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                learnModeController.switchToObjectiveView();
            }
        });

        dealingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                learnModeController.switchToDealingView();
            }
        });

    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final MovesState state = (MovesState) evt.getNewValue();
        JOptionPane.showMessageDialog(this, "Moves property change");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(this, "Cancel not implemented yet.");
    }

}


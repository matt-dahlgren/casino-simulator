package view;

/**
 * This is the objective view for the learn mode use case.
 */

import interface_adapter.learn_mode.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class ObjectiveView extends JPanel implements ActionListener, PropertyChangeListener {
    private LearnModeController learnModeController;

    public ObjectiveView(ObjectiveViewModel objectiveViewModel, DealingViewModel dealingViewModel, MovesViewModel movesViewModel) {
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
        JLabel objective = new JLabel("The goal of Blackjack is to have the sum of your card values get closer to 21 than the dealer's total, without going over 21. Each player plays against the dealer to try and get the best total.");
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

        movesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                learnModeController.switchToMovesView();
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
        final ObjectiveState state = (ObjectiveState) evt.getNewValue();
        JOptionPane.showMessageDialog(this, "Objective property change");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(this, "Cancel not implemented yet.");
    }

}









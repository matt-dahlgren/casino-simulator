package view;

import interface_adapter.learn_mode.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static interface_adapter.assisted_mode.AssistedModeColourConstants.TABLECOLOUR;

/**
 * This is the dealing view for the learn mode use case.
 */

public class DealingView extends JPanel implements ActionListener, PropertyChangeListener {
    private LearnModeController learnModeController;

    public DealingView(ObjectiveViewModel objectiveViewModel, DealingViewModel dealingViewModel, MovesViewModel movesViewModel) {
        objectiveViewModel.addPropertyChangeListener(this);
        movesViewModel.addPropertyChangeListener(this);
        dealingViewModel.addPropertyChangeListener(this);

        this.setLayout(new BorderLayout());
        setBackground(TABLECOLOUR);

        Font descriptionFont = new Font("Times New Roman", Font.BOLD, 20);
        Font buttonFont = new Font("Times New Roman", Font.BOLD, 15);

        // This button should go back to the main menu view
        JButton mainMenuButton = new JButton("MAIN MENU");
        mainMenuButton.setFont(buttonFont);
        this.add(mainMenuButton, BorderLayout.NORTH);

        // Making a panel in the center which would contain all the instructions and main content
        // of learn mode
        JPanel instructionsPanel = new JPanel(new GridBagLayout());
        instructionsPanel.setBackground(TABLECOLOUR);
        // Making grid bag constraints to allow for positioning of elements in the instructionsPanel
        GridBagConstraints constraints = new GridBagConstraints();

        // This label tells player what the overall objective of the game is
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        JLabel objective = new JLabel("<html><div style='text-align: center; color: white;'>" + "Each player is dealt 2 cards face up. The dealer has 2 cards, <br>" +
                "where one card is faced down and the other card is visible to players." + "</div></html>");
        objective.setFont(descriptionFont);
        instructionsPanel.add(objective, constraints);

        // This button allows player to click Objective and see what the objective of the game is
        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        JButton objectiveButton = new JButton("Objective");
        objectiveButton.setFont(buttonFont);
        instructionsPanel.add(objectiveButton, constraints);

        // This button allows player to click Dealing and see how the cards are dealt in a BlackJack game
        constraints.gridx = 2;
        constraints.gridy = 1;
        JButton dealingButton = new JButton("Dealing");
        dealingButton.setFont(buttonFont);
        instructionsPanel.add(dealingButton, constraints);

        // This button allows player to click Moves and see what moves are possible in a BlackJack game
        constraints.gridx = 2;
        constraints.gridy = 2;
        JButton movesButton = new JButton("Moves");
        movesButton.setFont(buttonFont);
        instructionsPanel.add(movesButton, constraints);

        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                learnModeController.switchToMainMenuView();
            }
        });

        objectiveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                learnModeController.switchToObjectiveView();
            }
        });

        movesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                learnModeController.switchToMovesView();
            }
        });

        this.add(instructionsPanel, BorderLayout.CENTER);

    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final DealingState state = (DealingState) evt.getNewValue();
        JOptionPane.showMessageDialog(this, "Moves property change");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(this, "Cancel not implemented yet.");
    }

    public void setLearnModeController(LearnModeController controller) {
        this.learnModeController = controller;
    }

    public String getViewName() {
        return "Dealing";
    }
}


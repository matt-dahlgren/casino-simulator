package view;

import interface_adapter.freeplay.FreePlayController;
import interface_adapter.freeplay.hit.HitViewModel;
import interface_adapter.freeplay.setup.SetupController;
import interface_adapter.freeplay.setup.SetupState;
import interface_adapter.freeplay.setup.SetupViewModel;
import interface_adapter.learn_mode.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static interface_adapter.probability.ProbabilityColourConstants.TABLECOLOUR;

public class SetupView extends JPanel implements ActionListener, PropertyChangeListener {
    private SetupController setupController;

    public SetupView(SetupViewModel setupViewModel, HitViewModel hitViewModel) {
        setupViewModel.addPropertyChangeListener(this);
        hitViewModel.addPropertyChangeListener(this);

        setBackground(TABLECOLOUR);

        this.setLayout(new BorderLayout());
        Font font = new Font("Times New Roman", Font.BOLD, 15);

        Panel northPanel = new Panel(new BorderLayout());

        // This button should go back to the main menu view
        JButton mainMenuButton = new JButton("MAIN MENU");
        mainMenuButton.setFont(font);
        mainMenuButton.setSize(40, 20);
        northPanel.add(mainMenuButton, BorderLayout.LINE_START);

        add(northPanel, BorderLayout.NORTH);

        JPanel movesPanel = new JPanel();
        movesPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        // HIT BUTTON
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        JButton hitButton = new JButton("Hit");
        hitButton.setFont(font);
        hitButton.setSize(40, 30);
        movesPanel.add(hitButton, constraints);
        // HIT BUTTON ACTION LISTENER
        hitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setupController.switchToHitView();
            }
        });

        // STAND BUTTON
        constraints.gridx = 0;
        constraints.gridy = 1;
        JButton standButton = new JButton("Stand");
        standButton.setFont(font);
        movesPanel.add(standButton, constraints);
        // STAND BUTTON ACTION LISTENER
        standButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setupController.switchToDealerAfterStandView();
            }
        });

        // QUIT BUTTON
        constraints.gridx = 0;
        constraints.gridy = 2;
        JButton quitGameButton = new JButton("Quit");
        quitGameButton.setFont(font);
        movesPanel.setBackground(TABLECOLOUR);
        movesPanel.add(quitGameButton, constraints);
        // QUIT BUTTON ACTION LISTENER
        quitGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setupController.switchToMainMenuView();
            }
        });


        setupController.execute_setup();
        String dealer_card_one_URL = setupViewModel.DEALER_ONE;
        String dealer_card_two_URL = setupViewModel.DEALER_TWO;
        String player_card_one_URL = setupViewModel.PLAYER_ONE;
        String player_card_two_URL = setupViewModel.PLAYER_TWO;
        // add code to display image URLs here


//        JPanel playerScorePanel = new JPanel(new FlowLayout());
//        playerScorePanel.setBackground(TABLECOLOUR);
//
//        JLabel playerScoreLabel =
//                new JLabel("<html><font color = 'white'>Your Score: " + outputData + "</font></html>");
//        playerScoreLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
//        playerScoreLabel.setSize(50, 100);
//
//        playerScorePanel.add(playerScoreLabel);
//
//        add(playerScorePanel, BorderLayout.SOUTH);
//
//        JPanel cardPanel = new JPanel(new BorderLayout());
//        cardPanel.setBackground(TABLECOLOUR);
//
//        JPanel playerPanel = new JPanel(new FlowLayout());
//        playerPanel.setBackground(TABLECOLOUR);
//
//        // Build the viewing of the Players Hand
//        for (String card : playerCards) {
//            try {
//                URL imageUrl = new URL(card);
//                BufferedImage image = ImageIO.read(imageUrl);
//                ImageIcon icon = new ImageIcon(image);
//                JLabel imageLabel = new JLabel(icon);
//
//                playerPanel.add(imageLabel);
//            } catch (IOException e) {
//                e.printStackTrace();
//                JLabel errorLabel = new JLabel("Failed to load image.");
//                errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
//                cardPanel.add(errorLabel, BorderLayout.CENTER);
//            }
//
//        }
//
//        // Build the viewing of the dealers (flipped) hand.
//        JPanel dealerPanel = new FlippedDealerCards(dealerCards);
//
//        cardPanel.add(playerPanel, BorderLayout.SOUTH);
//        cardPanel.add(dealerPanel, BorderLayout.NORTH);
//
//        add(cardPanel, BorderLayout.CENTER);
//
//        movesPanel.setBounds(810, 100, 230, 50);
//        add(movesPanel, BorderLayout.LINE_START);
//
//    }

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final SetupState state = (SetupState) evt.getNewValue();
        JOptionPane.showMessageDialog(this, "Setup state change.");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(this, "Cancel not implemented yet.");
    }

}

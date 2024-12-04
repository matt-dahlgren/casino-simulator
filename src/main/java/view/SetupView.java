package view;

import interface_adapter.dealer_screen.DealerScreenViewModel;
import interface_adapter.free_play.newhit.NewHitController;
import interface_adapter.free_play.setup.SetupController;
import interface_adapter.free_play.setup.SetupState;
import interface_adapter.free_play.setup.SetupViewModel;
import interface_adapter.free_play.stand.StandController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URL;

import static interface_adapter.assisted_mode.AssistedModeColourConstants.TABLECOLOUR;

/**
 * The Free Play view (yes it says setup, no it is not only setup).
 */
public class SetupView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "setup";
    private final DealerScreenViewModel dealerScreenViewModel;

    private SetupController setupController;
    private NewHitController hitController;
    private StandController standController;

    private final SetupViewModel setupViewModel;

    private final JPanel dealerPanel;
    private final JPanel playerPanel;

    private final JButton hitButton;
    private final JButton standButton;
    private final JButton quitGameButton;

    private int SCORE;
    private boolean end = false;

    public SetupView(SetupViewModel setupViewModel, DealerScreenViewModel dealerScreenViewModel) {
        this.setupViewModel = setupViewModel;
        this.dealerScreenViewModel = dealerScreenViewModel;

        setupViewModel.addPropertyChangeListener(this);

        setBackground(TABLECOLOUR);

        this.setLayout(new BorderLayout());
        Font font = new Font("Times New Roman", Font.BOLD, 15);

        Panel northPanel = new Panel(new BorderLayout());

        add(northPanel, BorderLayout.NORTH);


        JPanel movesPanel = new JPanel();
        movesPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        // HIT BUTTON
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        hitButton = new JButton("Hit");
        hitButton.setFont(font);
        hitButton.setSize(40, 30);
        movesPanel.add(hitButton, constraints);

        // HIT BUTTON ACTION LISTENER
        hitButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(hitButton) && !end) {
                            hitController.execute();
                        }
                    }
                }
        );


        // STAND BUTTON
        constraints.gridx = 0;
        constraints.gridy = 1;
        standButton = new JButton("Stand");
        standButton.setFont(font);
        movesPanel.add(standButton, constraints);

        // STAND BUTTON ACTION LISTENER
        standButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(standButton) && !end) {
                            standController.execute();
                        }
            }
        });

        // QUIT BUTTON
        constraints.gridx = 0;
        constraints.gridy = 2;
        quitGameButton = new JButton("Quit");
        quitGameButton.setFont(font);
        movesPanel.setBackground(TABLECOLOUR);
        movesPanel.add(quitGameButton, constraints);

        // QUIT BUTTON ACTION LISTENER
        quitGameButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        setupController.switchToMainMenuView();
            }
        });

        // add code to display image URLs here
        JPanel cardPanel = new JPanel(new BorderLayout());
        cardPanel.setBackground(TABLECOLOUR);

        this.playerPanel = new JPanel(new FlowLayout());
        playerPanel.setBackground(TABLECOLOUR);

        this.dealerPanel = new JPanel(new FlowLayout());
        dealerPanel.setBackground(TABLECOLOUR);

        cardPanel.add(playerPanel, BorderLayout.SOUTH);
        cardPanel.add(dealerPanel, BorderLayout.NORTH);

        add(cardPanel, BorderLayout.CENTER);

        movesPanel.setBounds(810, 100, 230, 50);
        add(movesPanel, BorderLayout.LINE_START);

        }

    public void propertyChange(PropertyChangeEvent evt) {

        // Happens by default when setup use case happens
        if (evt.getPropertyName().equals("state")) {
            final SetupState state = (SetupState) evt.getNewValue();

            for (String card : state.getPlayerHand()) {
                try {
                    URL imageUrl = new URL(card);
                    BufferedImage image = ImageIO.read(imageUrl);
                    ImageIcon icon = new ImageIcon(image);
                    JLabel imageLabel = new JLabel(icon);

                    playerPanel.add(imageLabel);

                } catch (IOException e) {
                    e.printStackTrace();
                    JLabel errorLabel = new JLabel("Failed to load image.");
                    errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    playerPanel.add(errorLabel, BorderLayout.CENTER);
                }}

            // Hides the first dealer card
            state.setHiddenDealer(state.getDealerHand().getFirst());
            try {
                URL imageUrl = new URL("https://deckofcardsapi.com/static/img/back.png");
                BufferedImage image = ImageIO.read(imageUrl);
                ImageIcon icon = new ImageIcon(image);
                JLabel imageLabel = new JLabel(icon);

                dealerPanel.add(imageLabel);

            } catch (IOException e) {
                e.printStackTrace();
                JLabel errorLabel = new JLabel("Failed to load image.");
                errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
                dealerPanel.add(errorLabel, BorderLayout.CENTER);
            }

            // Creates the rest of the dealer's hand (just the second card)
                try {
                    URL imageUrl = new URL(state.getDealerHand().getLast());
                    BufferedImage image = ImageIO.read(imageUrl);
                    ImageIcon icon = new ImageIcon(image);
                    JLabel imageLabel = new JLabel(icon);

                    dealerPanel.add(imageLabel);

                } catch (IOException e) {
                    e.printStackTrace();
                    JLabel errorLabel = new JLabel("Failed to load image.");
                    errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    dealerPanel.add(errorLabel, BorderLayout.CENTER);
                }

            if (state.getWin()) {
                standController.execute();
            }

            }

        // Occurs when hit use case happens.
        else if (evt.getPropertyName().equals("hit")) {
            final SetupState state = (SetupState) evt.getNewValue();


            int componentCount = playerPanel.getComponentCount();
            int handSize = state.getPlayerHand().size();

            for (int i = componentCount; i < handSize; i++) {
                try {
                    URL imageUrl = new URL(state.getPlayerHand().get(i));
                    BufferedImage image = ImageIO.read(imageUrl);
                    ImageIcon icon = new ImageIcon(image);
                    JLabel imageLabel = new JLabel(icon);

                    playerPanel.add(imageLabel);

                } catch (IOException e) {
                    e.printStackTrace();
                    JLabel errorLabel = new JLabel("Failed to load image.");
                    errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    playerPanel.add(errorLabel, BorderLayout.CENTER);
                }
            }
            //Reloads the page, allows the new cards to be updated to it
            this.revalidate();

            if (state.getWin()) {
                standController.execute();
            }
        }

        else if (evt.getPropertyName().equals("stand")) {
            final SetupState state = (SetupState) evt.getNewValue();

            this.SCORE = state.getScore();

            int componentCount = dealerPanel.getComponentCount();
            int handSize = state.getDealerHand().size();

            dealerPanel.remove(0);

            try {
                URL imageUrl = new URL(state.getHiddenDealer());
                BufferedImage image = ImageIO.read(imageUrl);
                ImageIcon icon = new ImageIcon(image);
                JLabel imageLabel = new JLabel(icon);

                dealerPanel.add(imageLabel);

            } catch (IOException e) {
                e.printStackTrace();
                JLabel errorLabel = new JLabel("Failed to load image.");
                errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
                dealerPanel.add(errorLabel, BorderLayout.CENTER);
            }

            for (int i = componentCount; i < handSize; i++) {
                try {
                    URL imageUrl = new URL(state.getDealerHand().get(i));
                    BufferedImage image = ImageIO.read(imageUrl);
                    ImageIcon icon = new ImageIcon(image);
                    JLabel imageLabel = new JLabel(icon);

                    dealerPanel.add(imageLabel);

                } catch (IOException e) {
                    e.printStackTrace();
                    JLabel errorLabel = new JLabel("Failed to load image.");
                    errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    dealerPanel.add(errorLabel, BorderLayout.CENTER);
                }
            }

            this.revalidate();

            this.end = true;


            String outcome_text = "";

            if (state.getWin()) {
                outcome_text += "YOU WON!\nCongrats!";
            }

            else {
                outcome_text += "YOU LOST!";
                if (state.getScore() > 21) {
                    outcome_text += "\nBusted before the Dealer even drew Cards!";
                }
                else {
                    outcome_text += "\nDealer beat you, should've been more Risky";
                }
            }


            JOptionPane.showMessageDialog(this, outcome_text);



        }


        }


    @Override
    public void actionPerformed(ActionEvent e) {
    }

    public String getViewName() {
        return viewName;
    }

    public void setSetupController(SetupController controller) {
        this.setupController = controller;
    }

    public void setHitController(NewHitController controller) {
        System.out.println("New Controller added!");
        this.hitController = controller;
    }

    public void setStandController(StandController controller) {
        this.standController = controller;
    }
}


// component 4


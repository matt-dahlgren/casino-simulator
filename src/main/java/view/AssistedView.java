package view;

import interface_adapter.assisted_mode.AssistedModeController;
import interface_adapter.assisted_mode.AssistedModeState;
import interface_adapter.assisted_mode.AssistedModeViewModel;
import interface_adapter.dealer_screen.DealerScreenViewModel;
import interface_adapter.learn_mode.ObjectiveState;
import interface_adapter.main_menu.MainMenuViewModel;

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

public class AssistedView extends JPanel implements ActionListener, PropertyChangeListener {
    private AssistedModeController assistedModeController;
    private final AssistedModeViewModel assistedModeViewModel;
    private final MainMenuViewModel mainMenuView;
    private final DealerScreenViewModel dealerScreenViewModel;

    public AssistedView(AssistedModeViewModel assistedModeViewModel, MainMenuViewModel mainMenuView,
                        DealerScreenViewModel dealerScreenViewModel) {
        this.assistedModeViewModel = assistedModeViewModel;
        this.mainMenuView = mainMenuView;
        this.dealerScreenViewModel = dealerScreenViewModel;

        assistedModeViewModel.addPropertyChangeListener(this);
        mainMenuView.addPropertyChangeListener(this);
        dealerScreenViewModel.addPropertyChangeListener(this);

        setBackground(TABLECOLOUR);

        // working with GridLayout(), so here are filler panels with the right background colour to just help with
        // layout issues.
        JPanel fillerTopPanel = new JPanel();
        fillerTopPanel.setBackground(TABLECOLOUR);

        setLayout(new GridLayout(1, 3));
        Font font = new Font("Times New Roman", Font.BOLD, 15);

        JPanel leftPanel = new JPanel(new GridLayout(3, 1));
        leftPanel.setBackground(TABLECOLOUR);

        leftPanel.add(fillerTopPanel);

        JPanel movesPanel = new JPanel(new GridLayout(1, 5));

        JPanel subMovesPanel = new JPanel(new GridLayout(3, 1));

        JPanel topHolderPanel = new JPanel();
        topHolderPanel.setBackground(TABLECOLOUR);
        subMovesPanel.add(topHolderPanel);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1));

        subMovesPanel.setBackground(TABLECOLOUR);

        // This button allows player to click Objective and see what the objective of the game is
        JButton hitButton = new JButton("Hit");
        hitButton.setFont(font);
        hitButton.setPreferredSize(new Dimension(40, 30));
        buttonPanel.add(hitButton);

        hitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                assistedModeController.assistedHit();
            }
        });


        // This button allows player to click Dealing and see how the cards are dealt in a BlackJack game
        JButton standButton = new JButton("Stand");
        standButton.setFont(font);
        buttonPanel.add(standButton);

        standButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                assistedModeController.assistedStand();
            }
        });

        // This button allows player to click Moves and see what moves are possible in a BlackJack game
        JButton quitGameButton = new JButton("Quit");
        quitGameButton.setFont(font);
        movesPanel.setBackground(TABLECOLOUR);
        buttonPanel.add(quitGameButton);
        buttonPanel.setBackground(TABLECOLOUR);

        quitGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                assistedModeController.mainMenu();
            }
        });

        subMovesPanel.add(buttonPanel);
        movesPanel.add(subMovesPanel);

        for (int i = 0; i < 4; i++) {
            JPanel fillerPanel = new JPanel();
            fillerPanel.setBackground(TABLECOLOUR);
            movesPanel.add(fillerPanel);
        }

        leftPanel.add(movesPanel);

        add(leftPanel);

        JPanel centrePanel = new JPanel(new GridLayout(2, 1));
        centrePanel.setBackground(TABLECOLOUR);

        JPanel dealerPanel = new FlippedDealerCards(assistedModeViewModel.getState().getDealerCardImages());
        centrePanel.add(dealerPanel);

        JPanel playerPanel = new JPanel(new BorderLayout());
        playerPanel.setBackground(TABLECOLOUR);

        JPanel playerScorePanel = new JPanel(new FlowLayout());
        playerScorePanel.setBackground(TABLECOLOUR);

        JLabel playerScoreLabel =
                new JLabel("<html><font color = 'white'>Your Score: "
                        + assistedModeViewModel.getState().getPlayerScore() + "</font></html>");
        playerScoreLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
        playerScoreLabel.setSize(50, 100);

        playerScorePanel.add(playerScoreLabel);

        playerPanel.add(playerScorePanel, BorderLayout.SOUTH);

        JPanel cardPanel = new JPanel(new BorderLayout());
        cardPanel.setBackground(TABLECOLOUR);

        JPanel playerCardPanel = new JPanel(new FlowLayout());
        playerCardPanel.setBackground(TABLECOLOUR);

        for (String card : assistedModeViewModel.getState().getPlayerCardImages()) {
            try {
                URL imageUrl = new URL(card);
                BufferedImage image = ImageIO.read(imageUrl);
                ImageIcon icon = new ImageIcon(image);
                JLabel imageLabel = new JLabel(icon);

                playerCardPanel.add(imageLabel);
            } catch (IOException e) {
                e.printStackTrace();
                JLabel errorLabel = new JLabel("Failed to load image.");
                errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
                playerCardPanel.add(errorLabel, BorderLayout.CENTER);
            }

        }

        playerPanel.add(playerCardPanel, BorderLayout.CENTER);

        centrePanel.add(playerPanel, BorderLayout.NORTH);

        add(centrePanel);

        JPanel fillerRightPanel = new JPanel();
        fillerRightPanel.setBackground(TABLECOLOUR);

        add(fillerRightPanel);

        JPanel probabilityPanel = new JPanel(new GridLayout(2, 1));
        probabilityPanel.setBackground(TABLECOLOUR);

        // creating a way for the hitPanel to be updated whenever a new Assisted View is initialized.
        JPanel hitPanel = new ProbabilityHitView(assistedModeViewModel.getState().getHitWin());

        // creating a way for the standPanel to be updated whenever a new Assisted View is initialized.
        JPanel standPanel = new ProbabilityStandView(assistedModeViewModel.getState().getStandWin());

        probabilityPanel.add(hitPanel);
        probabilityPanel.add(standPanel);

        leftPanel.add(probabilityPanel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(this, "Cancel not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final AssistedModeState state = (AssistedModeState) evt.getNewValue();
        JOptionPane.showMessageDialog(this, "Objective property change");

    }
}

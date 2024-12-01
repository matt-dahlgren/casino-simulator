package view;

import javax.swing.*;

public class FreePlayView extends JPanel {

    // FreePlayViewModel in the Intializer hasn't had a variable set yet, and the necessary method calls throughout
    // the class have variables set in place that should later be substituted with calls to the ViewModel

//    public FreePlayVIew(FreePlayViewModel) {
//
//        setBackground(TABLECOLOUR);
//
//        this.setLayout(new BorderLayout());
//        Font font = new Font("Times New Roman", Font.BOLD, 15);
//
//        Panel northPanel = new Panel(new BorderLayout());
//
//        // This button should go back to the main menu view
//        JButton mainMenuButton = new JButton("MAIN MENU");
//        mainMenuButton.setFont(font);
//        mainMenuButton.setSize(40, 20);
//        northPanel.add(mainMenuButton, BorderLayout.LINE_START);
//
//        add(northPanel, BorderLayout.NORTH);
//
//        JPanel movesPanel = new JPanel();
//        movesPanel.setLayout(new GridBagLayout());
//        GridBagConstraints constraints = new GridBagConstraints();
//
//
//        // This button allows player to click Objective and see what the objective of the game is
//        constraints.gridx = 0;
//        constraints.gridy = 0;
//        constraints.gridwidth = 1;
//        JButton hitButton = new JButton("Hit");
//        hitButton.setFont(font);
//        hitButton.setSize(40, 30);
//        movesPanel.add(hitButton, constraints);
//
//
//        // This button allows player to click Dealing and see how the cards are dealt in a BlackJack game
//        constraints.gridx = 0;
//        constraints.gridy = 1;
//        JButton standButton = new JButton("Stand");
//        standButton.setFont(font);
//        movesPanel.add(standButton, constraints);
//
//
//        // This button allows player to click Moves and see what moves are possible in a BlackJack game
//        constraints.gridx = 0;
//        constraints.gridy = 2;
//        JButton quitGameButton = new JButton("Quit");
//        quitGameButton.setFont(font);
//        movesPanel.setBackground(TABLECOLOUR);
//        movesPanel.add(quitGameButton, constraints);
//
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

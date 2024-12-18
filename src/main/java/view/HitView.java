//package view;
//
//import interface_adapter.freePlay.setup.SetupViewModel;
//import interface_adapter.freePlay.hit.HitController;
//import interface_adapter.freePlay.hit.HitState;
//import interface_adapter.freePlay.hit.HitViewModel;
//
//
//import javax.imageio.ImageIO;
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.image.BufferedImage;
//import java.beans.PropertyChangeEvent;
//import java.beans.PropertyChangeListener;
//import java.io.IOException;
//import java.net.URL;
//import java.util.ArrayList;
//
//import static interface_adapter.assisted_mode.AssistedModeColourConstants.TABLECOLOUR;
//
//public class HitView extends JPanel implements ActionListener, PropertyChangeListener {
//    private HitController hitController;
//
//    private final String viewName = "hit view";
//
//    private final JPanel dealerPanel;
//    private final JPanel playerPanel;
//
//    public HitView(HitViewModel hitViewModel) {
//        hitViewModel.addPropertyChangeListener(this);
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
//        // HIT BUTTON
//        constraints.gridx = 0;
//        constraints.gridy = 0;
//        constraints.gridwidth = 1;
//        JButton hitButton = new JButton("Hit");
//        hitButton.setFont(font);
//        hitButton.setSize(40, 30);
//        movesPanel.add(hitButton, constraints);
//        // HIT BUTTON ACTION LISTENER
//        hitButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                hitController.switchToHitView();
//            }
//        });
//
//        // STAND BUTTON
//        constraints.gridx = 0;
//        constraints.gridy = 1;
//        JButton standButton = new JButton("Stand");
//        standButton.setFont(font);
//        movesPanel.add(standButton, constraints);
//        // STAND BUTTON ACTION LISTENER
//        standButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                hitController.switchToDealerAfterStandView();
//            }
//        });
//
//        // QUIT BUTTON
//        constraints.gridx = 0;
//        constraints.gridy = 2;
//        JButton quitGameButton = new JButton("Quit");
//        quitGameButton.setFont(font);
//        movesPanel.setBackground(TABLECOLOUR);
//        movesPanel.add(quitGameButton, constraints);
//        // QUIT BUTTON ACTION LISTENER
//        quitGameButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                hitController.switchToMainMenuView();
//            }
//        });
//
//        // add code to display image URLs here
//        JPanel cardPanel = new JPanel(new BorderLayout());
//        cardPanel.setBackground(TABLECOLOUR);
//
//        this.playerPanel = new JPanel(new FlowLayout());
//        playerPanel.setBackground(TABLECOLOUR);
//
//        this.dealerPanel = new JPanel(new FlowLayout());
//
////        JPanel dealerPanel = new FlippedDealerCards(dealerCards);
//
//        cardPanel.add(playerPanel, BorderLayout.SOUTH);
//        cardPanel.add(dealerPanel, BorderLayout.NORTH);
//
//        add(cardPanel, BorderLayout.CENTER);
//
//        movesPanel.setBounds(810, 100, 230, 50);
//        add(movesPanel, BorderLayout.LINE_START);
//
//        JPanel playerScorePanel = new JPanel(new FlowLayout());
//        playerScorePanel.setBackground(TABLECOLOUR);
//
//        JLabel playerScoreLabel =
//                new JLabel("<html><font color = 'white'>Your Score: " + 50 + "</font></html>");
//        playerScoreLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
//        playerScoreLabel.setSize(50, 100);
//
//        playerScorePanel.add(playerScoreLabel);
//
//        add(playerScorePanel, BorderLayout.SOUTH);
//
//    }
//    public void propertyChange(PropertyChangeEvent evt) {
//        if (evt.getPropertyName().equals("state")) {
//            final HitState state = (HitState) evt.getNewValue();
//            for (String card : state.getPlayerHand()) {
//                try {
//                    URL imageUrl = new URL(card);
//                    BufferedImage image = ImageIO.read(imageUrl);
//                    ImageIcon icon = new ImageIcon(image);
//                    JLabel imageLabel = new JLabel(icon);
//
//                    playerPanel.add(imageLabel);
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    JLabel errorLabel = new JLabel("Failed to load image.");
//                    errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
//                    playerPanel.add(errorLabel, BorderLayout.CENTER);
//                }}
//
//            for (String card : state.getDealerHand()) {
//                try {
//                    URL imageUrl = new URL(card);
//                    BufferedImage image = ImageIO.read(imageUrl);
//                    ImageIcon icon = new ImageIcon(image);
//                    JLabel imageLabel = new JLabel(icon);
//
//                    dealerPanel.add(imageLabel);
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    JLabel errorLabel = new JLabel("Failed to load image.");
//                    errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
//                    dealerPanel.add(errorLabel, BorderLayout.CENTER);
//                }}
//            JOptionPane.showMessageDialog(this, "Hit state change.");
//
//        }
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        JOptionPane.showMessageDialog(this, "Cancel not implemented yet.");
//    }
//
//    public String getViewName() {
//        return viewName;
//    }
//
//    public void setHitController(HitController controller) {
//        this.hitController = controller;
//    }
//}





package view;

import interface_adapter.dealer_screen.DealerScreenViewModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import static interface_adapter.assisted_mode.AssistedModeColourConstants.TABLECOLOUR;

public class DealerAfterStandView extends JPanel implements ActionListener, PropertyChangeListener {

    static final String TIMES = "Times New Roman";
    DealerScreenViewModel viewModel;

    public DealerAfterStandView(DealerScreenViewModel viewModel) {
        this.viewModel = viewModel;

        int dealerScore = viewModel.getState().getDealerScore();
        int playerScore = viewModel.getState().getPlayerScore();
        ArrayList<String> cards = viewModel.getState().getCardImages();
        boolean playerWin = viewModel.getState().getPlayerWin();
        String playerWinString = null;

        if (playerWin) {
            playerWinString = "Won";
        }
        else {
            playerWinString = "Lost";
        }

        setLayout(new BorderLayout());
        setSize(1400, 800);
        setBackground(TABLECOLOUR);

        JPanel titleCard = new JPanel(new GridLayout(1,3));
        JPanel fillerCard = new JPanel();
        fillerCard.setBackground(TABLECOLOUR);
        titleCard.setBackground(TABLECOLOUR);

        JLabel dealerHandLabel =
                new JLabel("<html><font color = 'white'>"+playerWinString+"</font></html>");

        dealerHandLabel.setFont(new Font(TIMES, Font.BOLD, 36));

        JPanel scorePanel = new JPanel(new FlowLayout());

        titleCard.add(fillerCard);
        titleCard.add(dealerHandLabel);

        add(titleCard, BorderLayout.NORTH);

        JLabel dealerLabel =
                new JLabel("<html><font color = 'white'>Dealer's Score: " + dealerScore + "</font></html>");
        dealerLabel.setFont(new Font(TIMES, Font.BOLD, 24));

        scorePanel.add(dealerLabel);
        scorePanel.setBackground(TABLECOLOUR);

        JLabel playerLabel =
                new JLabel("<html><font color = 'white'>Player's Score: " + playerScore + "</font></html>");
        playerLabel.setFont(new Font(TIMES, Font.BOLD, 24));

        scorePanel.add(playerLabel);

        JPanel cardPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        cardPanel.setBackground(TABLECOLOUR);

        for (String card : cards) {
            try {
                URL imageUrl = new URL(card);
                BufferedImage image = ImageIO.read(imageUrl);
                ImageIcon icon = new ImageIcon(image);
                JLabel imageLabel = new JLabel(icon);

                cardPanel.add(imageLabel, BorderLayout.CENTER);
            } catch (IOException e) {
                e.printStackTrace();
                JLabel errorLabel = new JLabel("Failed to load image.");
                errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
                cardPanel.add(errorLabel, BorderLayout.CENTER);
            }

        }

        add(cardPanel, BorderLayout.CENTER);
        add(scorePanel, BorderLayout.SOUTH);

        JButton toGameReview = new JButton("End of Game Review");
        titleCard.add(toGameReview);

        toGameReview.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO ADD
            }
        });

    }

    public String getViewName() {
        return "Dealer After Stand";
    }
}

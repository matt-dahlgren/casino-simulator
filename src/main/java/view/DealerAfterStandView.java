package view;

import interface_adapter.assisted_mode.AssistedModeState;
import interface_adapter.dealer_screen.DealerScreenController;
import interface_adapter.dealer_screen.DealerScreenViewModel;
import interface_adapter.report.GameReportController;
import interface_adapter.report.ReportViewModel;

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
import java.util.ArrayList;

import static interface_adapter.assisted_mode.AssistedModeColourConstants.TABLECOLOUR;

public class DealerAfterStandView extends JPanel implements ActionListener, PropertyChangeListener {

    static final String TIMES = "Times New Roman";
    private final DealerScreenViewModel viewModel;
    private final ReportViewModel reportViewModel;
    private GameReportController gameReportController;
    private DealerScreenController dealerScreenController;

    public DealerAfterStandView(DealerScreenViewModel viewModel, ReportViewModel reportViewModel) {
        this.viewModel = viewModel;
        this.reportViewModel = reportViewModel;

        viewModel.addPropertyChangeListener(this);
        reportViewModel.addPropertyChangeListener(this);

        int dealerScore = viewModel.getState().getDealerScore();
        int playerScore = viewModel.getState().getPlayerScore();
        ArrayList<String> cards = viewModel.getState().getCardImages();

        if (cards == null) {
            cards = new ArrayList<>();
        }

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

        if (!cards.isEmpty()) {
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

        }

        add(cardPanel, BorderLayout.CENTER);
        add(scorePanel, BorderLayout.SOUTH);

        JButton toGameReview = new JButton("Continue");
        titleCard.add(toGameReview);

        if (viewModel.getState().getGameType() == 0) {
            toGameReview.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gameReportController.execute(viewModel.getState().getNumGame());
                }
            });
        }
        else {
            toGameReview.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dealerScreenController.toMainMenu();
                }
            });
        }


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

    public void setDealerScreenController(DealerScreenController controller) {
        dealerScreenController = controller;
    }

    public String getViewName() {
        return "DealerAfterStand";
    }
}

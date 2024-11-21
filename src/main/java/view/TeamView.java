package view;

import entities.*;
import interface_adapter.team_use_case.TeamController;
import interface_adapter.team_use_case.TeamState;
import interface_adapter.team_use_case.TeamViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;

/**
 * This is the view for the team use case, which corresponds to free play mode in our
 * game-- a BlackJack game with no hints.
 */
public class TeamView extends JPanel implements ActionListener, PropertyChangeListener {

    private final TeamViewModel teamViewModel;
    private TeamController teamController;

    public JLabel hitCard;
    public JLabel dealerCardOne;

    public TeamView(TeamViewModel teamViewModel, JLabel hitCard) {
        this.teamViewModel = teamViewModel;
        this.hitCard = hitCard;
        this.dealerCardOne = new JLabel();
        teamViewModel.addPropertyChangeListener(this);

        setup();

        this.setLayout(new BorderLayout());

        Font font = new Font("Georgia", Font.BOLD, 20);

        final JLabel appNameLabel = new JLabel(TeamViewModel.APP_LABEL);
        appNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        appNameLabel.setFont(font);
        appNameLabel.setForeground(Color.BLACK);

        final JPanel moves = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;

        final JButton hit = new JButton(TeamViewModel.HIT_BUTTON_LABEL);
        final JButton stand = new JButton(TeamViewModel.STAND_BUTTON_LABEL);

        final JLabel DEALERLABEL = new JLabel("DEALER -->");
        DEALERLABEL.setFont(font);
        DEALERLABEL.setForeground(Color.BLACK);

        final JLabel PLAYERLABEL = new JLabel("PLAYER -->");
        PLAYERLABEL.setFont(font);
        PLAYERLABEL.setForeground(Color.BLACK);


        hit.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        hitClick();
                    }

                }
        );
        stand.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        standClick();
                    }
                }
        );

        constraints.gridy = 0;
        moves.add(DEALERLABEL, constraints);

        constraints.gridy = 1;
        moves.add(Box.createVerticalStrut(100), constraints);

        constraints.gridy = 2;
        moves.add(hit, constraints);

        constraints.gridy = 3;
        moves.add(stand, constraints);

        constraints.gridy = 4;
        moves.add(Box.createVerticalStrut(100), constraints);

        constraints.gridy = 5;
        moves.add(PLAYERLABEL, constraints);


//        hit.addActionListener(
//                new ActionListener() {
//                    public void actionPerformed(ActionEvent evt) {
//                        initialController.switchToLoginView();
//                    }
//                }
//        );

        dCardOne = draw();
        Card dCardTwo = draw();

        Dealer.hit(dCardOne);
        Dealer.hit(dCardTwo);

        final JPanel dealer = new JPanel(new GridBagLayout());
        dealerCardOne.setText("FACE DOWN,");
        dealerCardOne.setFont(font);
        dealerCardOne.setForeground(Color.BLACK);

        final JLabel dealerCardTwo = new JLabel(dCardTwo.getRank());
        dealerCardTwo.setFont(font);
        dealerCardTwo.setForeground(Color.BLACK);

        constraints = new GridBagConstraints();

        constraints.gridy = 0;
        dealer.add(dealerCardOne, constraints);

        constraints.gridy = 1;
        dealer.add(dealerCardTwo, constraints);

        Card CardOne = draw();
        Card CardTwo = draw();

        User.hit(CardOne);
        User.hit(CardTwo);

        final JPanel player = new JPanel(new GridBagLayout());
        final JLabel playerCardOne = new JLabel(CardOne.getRank());
        final JLabel playerCardTwo = new JLabel(CardTwo.getRank());

        playerCardOne.setFont(font);
        playerCardTwo.setFont(font);
        hitCard.setFont(font);
        playerCardOne.setForeground(Color.BLACK);
        playerCardTwo.setForeground(Color.BLACK);
        hitCard.setForeground(Color.BLACK);

        constraints = new GridBagConstraints();
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        player.add(playerCardOne, constraints);

        constraints.gridy = 1;
        player.add(playerCardTwo, constraints);

        constraints.gridy = 2;
        player.add(hitCard, constraints);

        this.add(appNameLabel, BorderLayout.NORTH);
        this.add(moves, BorderLayout.WEST);
        this.add(dealer, BorderLayout.NORTH);
        this.add(player, BorderLayout.SOUTH);

    }

    //GARBAGE INITIALIZER CODE SLOP (I WANT TO SLEEP) - ANDRIY

    private final ArrayList<Card> cards = new ArrayList<>();
    private UserPlayer User = new UserPlayer(cards);
    private Dealer Dealer = new Dealer(cards);
    private Card dCardOne = new Card("1", "hearts");


    private void setup() {
        ArrayList<String> ranks = new ArrayList<>(Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "JACK", "QUEEN", "KING", "ACE"));

        for (int i = 0; i< 4; i++) {
            for (int j = 0; j < ranks.size(); j++) {
                this.cards.add(new Card(ranks.get(j), "hearts"));
            }

        }
        this.User = new UserPlayer(this.cards);
        this.Dealer = new Dealer(this.cards);
    }

    private Card draw() {
        Random random = new Random();
        int size = random.nextInt(this.cards.size());
        Card card = this.cards.get(size);
        this.cards.remove(size);

        return card;
    }


    // Both of these fellas probably need to be thrown in their own classes / used as interfaces here
    String text = "";
    public void hitClick() {
        if (User.getScore() > 21) {
            System.out.println("Already Busted!");
        }
        else {
            Random random = new Random();
            int size = random.nextInt(this.cards.size());
            Card card = this.cards.get(size);
            this.cards.remove(size);

            this.User.hit(card);

            text = text + ", " + card.getRank();
            hitCard.setText(text);

            if (User.getScore() > 21) {
                System.out.println("Busted!");
                standClick(); // TERRIBLE CA, PLEASE MAKE A DEDICATED STAND/BUSTED CLASS! -andriy
                return;
            }

            System.out.println(this.User.getScore());
        }
    }

    public void standClick() {
        System.out.println("Dealer's Turn!");
        String dealerText = dCardOne.getRank();
        dealerCardOne.setText(dealerText);
        Dealer.doTurn();

        // Below is just temporary because I want to go to sleep and don't have time to make it all separate classes
        // Very YandereDev-esque code

        if (User.getScore() > 21) {
            System.out.println("You lost");
        }
        else if (Dealer.getScore() > 21) {
            System.out.println("Dealer Busted, Bets Returned");
        }
        else if (User.getScore() > Dealer.getScore()) {
            System.out.println("You Won! 1.5x Bet Returned");
        }
        else if (Dealer.getScore() > User.getScore()) {
            System.out.println("You Lost! Should've risked it some more!");
        }
        else {
            System.out.println("Tie"); // Only if 21
        }

    }
    // END OF GARBAGE

    @Override
    public void actionPerformed(ActionEvent evt) {JOptionPane.showMessageDialog(this, "Not Implemented Yet"); }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final TeamState state = (TeamState) evt.getNewValue();
    }

    public String getViewName() {
        return "initial"; }

    public void setTeamController(TeamController controller) {this.teamController = controller;}


}

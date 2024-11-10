package view;

import interface_adapter.initial.InitialController;
import interface_adapter.initial.InitialState;
import interface_adapter.initial.InitialViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;
import java.util.List;

/**
 * The View for the Initial Use Case.
 */
public class InitialView extends JPanel implements ActionListener, PropertyChangeListener {

    private final InitialViewModel initialViewModel;
    private InitialController initialController;

    public JLabel hitCard;
    public JLabel dealerCardOne;

    public InitialView(InitialViewModel initialViewModel, JLabel hitCard) {
        this.initialViewModel = initialViewModel;
        this.hitCard = hitCard;
        this.dealerCardOne = new JLabel();
        initialViewModel.addPropertyChangeListener(this);

        this.setLayout(new BorderLayout());

        Font font = new Font("Georgia", Font.BOLD, 20);

        final JLabel appNameLabel = new JLabel(InitialViewModel.APP_LABEL);
        appNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        appNameLabel.setFont(font);
        appNameLabel.setForeground(Color.BLACK);

        final JPanel moves = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;

        final JButton hit = new JButton(InitialViewModel.HIT_BUTTON_LABEL);
        final JButton stand = new JButton(InitialViewModel.STAND_BUTTON_LABEL);

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

        final JPanel dealer = new JPanel(new GridBagLayout());
        dealerCardOne.setText("FACE DOWN,");
        dealerCardOne.setFont(font);
        dealerCardOne.setForeground(Color.BLACK);

        final JLabel dealerCardTwo = new JLabel("QUEEN");
        dealerCardTwo.setFont(font);
        dealerCardTwo.setForeground(Color.BLACK);

        constraints = new GridBagConstraints();

        constraints.gridy = 0;
        dealer.add(dealerCardOne, constraints);

        constraints.gridy = 1;
        dealer.add(dealerCardTwo, constraints);

        final JPanel player = new JPanel(new GridBagLayout());
        final JLabel playerCardOne = new JLabel("KING,");
        final JLabel playerCardTwo = new JLabel("3");

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

    String text = "";
    public void hitClick() {
        List<String> cards = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "JACK", "QUEEN", "KING"));
        Random random = new Random();
        String card = cards.get(random.nextInt(cards.size()));
        text = text + ", " + card;
        hitCard.setText(text);
    }

    public void standClick() {
        List<String> cards = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "JACK", "QUEEN", "KING"));
        Random random = new Random();
        String dealerText = cards.get(random.nextInt(cards.size()));
        dealerCardOne.setText(dealerText);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {JOptionPane.showMessageDialog(this, "Not Implemented Yet"); }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final InitialState state = (InitialState) evt.getNewValue();
    }

    public String getViewName() {
        return "initial"; }

    public void setInitialController(InitialController controller) {this.initialController = controller;}


}

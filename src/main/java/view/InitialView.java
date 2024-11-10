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
import java.util.Objects;

/**
 * The View for the Initial Use Case.
 */
public class InitialView extends JPanel implements ActionListener, PropertyChangeListener {

    private final InitialViewModel initialViewModel;
    private InitialController initialController;

//    private final JButton useless;

    public InitialView(InitialViewModel initialViewModel) {
        this.initialViewModel = initialViewModel;
        initialViewModel.addPropertyChangeListener(this);

        this.setLayout(new BorderLayout());

        final JLabel appNameLabel = new JLabel(InitialViewModel.APP_LABEL);
        appNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        final JPanel moves = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = GridBagConstraints.RELATIVE;

        final JButton hit = new JButton(InitialViewModel.HIT_BUTTON_LABEL);
        final JButton stand = new JButton(InitialViewModel.STAND_BUTTON_LABEL);
        moves.add(hit, constraints);
        moves.add(stand, constraints);

        final JPanel dealer = new JPanel(new GridBagLayout());
        final JLabel dealerCardOne = new JLabel("FACE DOWN,");
        final JLabel dealerCardTwo = new JLabel("QUEEN");


        constraints = new GridBagConstraints();

        constraints.gridy = 0;
        dealer.add(dealerCardOne, constraints);

        constraints.gridy = 1;
        dealer.add(dealerCardTwo, constraints);

        final JPanel player = new JPanel(new GridBagLayout());
        final JLabel playerCardOne = new JLabel("KING,");
        final JLabel playerCardTwo = new JLabel("3");

        constraints = new GridBagConstraints();
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        player.add(playerCardOne, constraints);

        constraints.gridy = 1;
        player.add(playerCardTwo, constraints);

//        useless.addActionListener(
//                new ActionListener() {
//                    public void actionPerformed(ActionEvent evt) {
//                        initialController.switchToLoginView();
//                    }
//                }
//        );

        this.add(appNameLabel, BorderLayout.NORTH);
        this.add(moves, BorderLayout.WEST);
        this.add(dealer, BorderLayout.NORTH);
        this.add(player, BorderLayout.SOUTH);
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

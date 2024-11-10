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
        appNameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        final JPanel moves = new JPanel();
        moves.setLayout(new BoxLayout(moves, BoxLayout.Y_AXIS));
        moves.setAlignmentX(Component.RIGHT_ALIGNMENT);
        moves.setAlignmentY(Component.CENTER_ALIGNMENT);
        final JButton hit = new JButton(InitialViewModel.HIT_BUTTON_LABEL);
        final JButton stand = new JButton(InitialViewModel.STAND_BUTTON_LABEL);
        moves.add(hit);
        moves.add(stand);

        final JPanel dealer = new JPanel();
        dealer.setLayout(new FlowLayout(FlowLayout.CENTER));
        final JLabel dealerCardOne = new JLabel("FACE DOWN,");
        final JLabel dealerCardTwo = new JLabel("QUEEN");
        dealer.add(dealerCardOne);
        dealer.add(dealerCardTwo);

        final JPanel player = new JPanel();
        player.setLayout(new FlowLayout(FlowLayout.CENTER));
        final JLabel playerCardOne = new JLabel("KING,");
        final JLabel playerCardTwo = new JLabel("3");
        player.add(playerCardOne);
        player.add(playerCardTwo);

//        useless.addActionListener(
//                new ActionListener() {
//                    public void actionPerformed(ActionEvent evt) {
//                        initialController.switchToLoginView();
//                    }
//                }
//        );

        this.add(appNameLabel, BorderLayout.NORTH);
        this.add(moves, BorderLayout.WEST);
        this.add(dealer, BorderLayout.CENTER);
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

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
    private final String viewName = "initial";

    private final InitialViewModel initialViewModel;
    private InitialController initialController;

    private final JButton useless;

    public InitialView(InitialViewModel initialViewModel) {
        this.initialViewModel = initialViewModel;
        initialViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(InitialViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JLabel description = new JLabel(InitialViewModel.DESCRIPTION_LABEL);
        description.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        useless = new JButton(InitialViewModel.USELESS_BUTTON_LABEL);
        buttons.add(useless);

//        useless.addActionListener(
//                new ActionListener() {
//                    public void actionPerformed(ActionEvent evt) {
//                        initialController.switchToLoginView();
//                    }
//                }
//        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(description);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {JOptionPane.showMessageDialog(this, "Not Implemented Yet"); }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final InitialState state = (InitialState) evt.getNewValue();
    }

    public String getViewName() { return viewName; }

    public void setInitialController(InitialController controller) {this.initialController = controller;}


}

package interface_adapter.probability;

import interface_adapter.probability.hit.ProbabilityHitViewModel;
import interface_adapter.probability.stand.ProbabilityStandViewModel;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import static interface_adapter.probability.ColorConstants.TABLECOLOUR;

/**
 * This is the view presenting the information of the Probability Use Case.
 */
public class ProbabilityViewModel extends JPanel {

    public ProbabilityViewModel(ProbabilityHitViewModel hit, ProbabilityStandViewModel stand) {
        setSize(300, 200);
        setLayout(new BorderLayout());
        setBackground(TABLECOLOUR);

        add(hit, BorderLayout.NORTH);
        add(stand, BorderLayout.SOUTH);
    }

}

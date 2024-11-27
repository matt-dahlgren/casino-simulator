package interface_adapter.probability;

import javax.swing.JPanel;

import static interface_adapter.probability.ProbabilityColourConstants.TABLECOLOUR;

/**
 * This class is the container that will hold the HitProbabilityViewModel and the StandProbabilityViewModel.
 */
public class ProbabilityViewModel extends JPanel {

    public ProbabilityViewModel() {
        setSize(300, 200);
        setBackground(TABLECOLOUR);
    }
}

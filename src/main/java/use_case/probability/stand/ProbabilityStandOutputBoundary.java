package use_case.probability.stand;

import interface_adapter.probability.stand.ProbabilityStandPresenter;
import interface_adapter.probability.stand.ProbabilityStandViewModel;

/**
 * The output boundary for the Probability Stand Use case.
 */
public interface ProbabilityStandOutputBoundary {

    /**
     * Prepares the view for the probabilityStand Use Case.
     */

    ProbabilityStandViewModel prepareProbabilityStandView(ProbabilityStandPresenter standData);
}

package use_case.probability.hit;

import interface_adapter.probability.hit.ProbabilityHitPresenter;
import interface_adapter.probability.hit.ProbabilityHitViewModel;

/**
 * The output boundary for the ProbabilityHit Usecase.
 */
public interface ProbabilityHitOutputBoundary {

    /**
     * Prepares the view for the probabilityHit Use Case.
     * @param probabilityHitOutputData is valid output data.
     */
    ProbabilityHitViewModel prepareProbabilityHitView(ProbabilityHitOutputData probabilityHitOutputData);
}

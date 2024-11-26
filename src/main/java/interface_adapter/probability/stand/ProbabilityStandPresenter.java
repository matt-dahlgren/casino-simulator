package interface_adapter.probability.stand;

import use_case.probability.stand.ProbabilityStandOutputBoundary;
import use_case.probability.stand.ProbabilityStandOutputData;

/**
 * Presenter for the ProbabilityStand sub-UseCase.
 */
public class ProbabilityStandPresenter {

    private final ProbabilityStandOutputData outputData;
    private final ProbabilityStandViewModel viewModel;

    public ProbabilityStandPresenter(ProbabilityStandOutputData outputData) {

        this.outputData = outputData;
        this.viewModel = new ProbabilityStandViewModel(outputData.getStandWinProbability());
    }

    public ProbabilityStandViewModel getViewModel() {
        return viewModel;
    }
}

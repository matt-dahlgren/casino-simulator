package interface_adapter.probability.stand;

import interface_adapter.probability.ProbabilityViewModel;
import use_case.probability.stand.ProbabilityStandOutputBoundary;
import use_case.probability.stand.ProbabilityStandOutputData;

public class ProbabilityStandPresenter implements ProbabilityStandOutputBoundary {

    private final ProbabilityViewModel probabilityViewModel;

    public ProbabilityStandPresenter(ProbabilityViewModel probabilityViewModel) {
        this.probabilityViewModel = probabilityViewModel;
    }

    @Override
    public void prepareProbabilityStandView(ProbabilityStandOutputData outputData) {

        ProbabilityStandViewModel standModel = new ProbabilityStandViewModel(outputData.getStandWinProbability());
    }
}

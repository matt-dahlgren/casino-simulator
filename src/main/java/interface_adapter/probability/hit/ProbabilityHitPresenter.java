package interface_adapter.probability.hit;

import interface_adapter.probability.stand.ProbabilityStandViewModel;
import use_case.probability.hit.ProbabilityHitOutputBoundary;
import use_case.probability.hit.ProbabilityHitOutputData;
import use_case.probability.stand.ProbabilityStandOutputData;

public class ProbabilityHitPresenter implements ProbabilityHitOutputBoundary {
    private final ProbabilityHitOutputData outputData;
    private final ProbabilityHitViewModel viewModel;

    public ProbabilityHitPresenter(ProbabilityHitOutputData outputData) {

        this.outputData = outputData;
        this.viewModel = new ProbabilityHitViewModel(outputData.getHitWinProbability());
    }

    @Override
    public ProbabilityHitViewModel prepareProbabilityHitView(ProbabilityHitOutputData outputData) {
        return this.viewModel;
    }

    public ProbabilityHitViewModel getViewModel() {
        return viewModel;
    }
}

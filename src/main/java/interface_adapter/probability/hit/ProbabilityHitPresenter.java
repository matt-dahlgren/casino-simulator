package interface_adapter.probability.hit;

import interface_adapter.probability.ProbabilityViewModel;
import use_case.probability.hit.ProbabilityHitOutputBoundary;
import interface_adapter.probability.ProbabilityViewModel;
import use_case.probability.hit.ProbabilityHitOutputData;

public class ProbabilityHitPresenter implements ProbabilityHitOutputBoundary {

    private final ProbabilityHitViewModel probabilityHitViewModel;
    private final ProbabilityViewModel probabilityView;

    public ProbabilityHitPresenter(ProbabilityViewModel probabilityViewModel) {
        this.probabilityView = probabilityViewModel;

    }

    @Override
    public void prepareProbabilityHitView(ProbabilityHitOutputData outputData) {

    }
}

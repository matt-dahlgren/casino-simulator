package interface_adapter.probability.hit;

import use_case.probability.hit.ProbabilityHitOutputBoundary;
import use_case.probability.hit.ProbabilityHitOutputData;

public class ProbabilityHitPresenter implements ProbabilityHitOutputBoundary {

    private final ProbabilityHitViewModel probabilityView;

    public ProbabilityHitPresenter(ProbabilityHitViewModel probabilityHitViewModel) {
        this.probabilityView = probabilityHitViewModel;

    }

    //TODO: connect this view model with the state of an active game.
    @Override
    public void prepareProbabilityHitView(ProbabilityHitOutputData outputData) {

        final ProbabilityHitState probabilityState = probabilityView.getState();
        probabilityState.setStandProbability(outputData.getHitWinProbability());

    }
}

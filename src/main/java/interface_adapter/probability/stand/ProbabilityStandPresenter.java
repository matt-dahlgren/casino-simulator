package interface_adapter.probability.stand;

import use_case.probability.stand.ProbabilityStandOutputBoundary;
import use_case.probability.stand.ProbabilityStandOutputData;

public class ProbabilityStandPresenter implements ProbabilityStandOutputBoundary {

    private final ProbabilityStandViewModel probabilityViewModel;

    public ProbabilityStandPresenter(ProbabilityStandViewModel probabilityViewModel) {
        this.probabilityViewModel = probabilityViewModel;
    }

    //TODO: implement listener and add to the state of an active BLACKJACK GAME
    @Override
    public void prepareProbabilityStandView(ProbabilityStandOutputData outputData) {

        final ProbabilityStandState probabilityState = probabilityViewModel.getState();
        probabilityState.setStandProbability(outputData.getStandWinProbability());
    }
}

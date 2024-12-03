package interface_adapter.free_play.newhit;

import interface_adapter.free_play.setup.SetupState;
import interface_adapter.free_play.setup.SetupViewModel;
import use_case.freeplay.newhit.NewHitOutputBoundary;
import use_case.freeplay.newhit.NewHitOutputData;

/**
 * The Presenter for the Hit Use Case...
 */
public class NewHitPresenter implements NewHitOutputBoundary {

    private final SetupViewModel setupViewModel;

    public NewHitPresenter(SetupViewModel setupViewModel) {
        this.setupViewModel = setupViewModel;}

    @Override
    public void prepareSuccessView(NewHitOutputData outputData) {

        SetupState setupState = setupViewModel.getState();
        setupState.setPlayerHand(outputData.getUserPlayerHand());
        setupState.setScore(outputData.getScore());

        setupViewModel.firePropertyChanged("hit");

    }

    /**
     * Used for displaying the end of the game, either a WINNER or YOU BUSTED screen.
     * @param outcome the string with the details from the NewHitInteractor
     */
    @Override
    public void prepareBustView(String outcome) {
        SetupState setupState = setupViewModel.getState();
        setupState.setOutcome(outcome);

        setupViewModel.firePropertyChanged("display result");
    }
}

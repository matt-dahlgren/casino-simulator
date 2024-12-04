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
        setupState.setWin(outputData.getWin());

        setupViewModel.firePropertyChanged("hit");

    }
}

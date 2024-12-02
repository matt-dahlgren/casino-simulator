package interface_adapter.freePlay.newhit;

import interface_adapter.freePlay.setup.SetupState;
import interface_adapter.freePlay.setup.SetupViewModel;
import use_case.freeplay.newhit.NewHitOutputBoundary;
import use_case.freeplay.newhit.NewHitOutputData;

import java.util.ArrayList;

/**
 * The Presenter for the Hit Use Case...
 */
public class NewHitPresenter implements NewHitOutputBoundary {

    private final SetupViewModel setupViewModel;

    public NewHitPresenter(SetupViewModel setupViewModel) {
        this.setupViewModel = setupViewModel;}

    @Override
    public void prepareSuccessView(NewHitOutputData outputData) {
        setupViewModel.firePropertyChanged("hit");

        SetupState setupState = setupViewModel.getState();
        setupState.setPlayerHand(outputData.getUserPlayerHand());

    System.out.println("Success NewHitPresenter Success view prepared!");
    }
}

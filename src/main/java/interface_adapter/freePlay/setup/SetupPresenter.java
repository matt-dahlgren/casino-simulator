package interface_adapter.freePlay.setup;

import interface_adapter.ViewManagerModel;
import interface_adapter.freePlay.setup.SetupViewModel;
import interface_adapter.freePlay.hit.HitViewModel;
import interface_adapter.main_menu.MainMenuViewModel;
import use_case.freeplay.setup.SetupOutputBoundary;
import use_case.freeplay.setup.SetupOutputData;

import java.util.ArrayList;

/**
 * The Presenter for the Setup Use Case.
 */

public class SetupPresenter implements SetupOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    SetupViewModel setupViewModel;
    HitViewModel hitViewModel;
//    FreePlayStandViewModel freePlayStandViewModel;
    MainMenuViewModel mainMenuViewModel;

    public SetupPresenter(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Prepares success view for setup
     *
     * @param outputData the output data from interactor
     */
    @Override
    public void prepareSuccessView(SetupOutputData outputData) {
        ArrayList<String> dealerHand = outputData.getDealerHand();
        ArrayList<String> playerHand = outputData.getUserPlayerHand();
        final SetupState setupState = setupViewModel.getState();

        setupState.setDealerCardOne(dealerHand.get(0));
        setupState.setDealerCardTwo(dealerHand.get(1));
        setupState.setPlayerCardOne(playerHand.get(0));
        setupState.setPlayerCardTwo(playerHand.get(1));

        setupViewModel.setState(setupState);
        setupViewModel.firePropertyChanged();

        viewManagerModel.setState(setupViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }

    @Override
    public void prepareFailView(String errorMessage) {

    }

    @Override
    public void switchToHitView() {
        viewManagerModel.setState(hitViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToDealerAfterStandView() {
//        viewManagerModel.setState(freePlayStandViewModel.getViewName());
//        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToMainMenuView() {
        viewManagerModel.setState(mainMenuViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

//    /**
//     * Fail view aka bust
//     *
//     * @param message explanation for why bust
//     */
//    @Override
//    public void prepareBustView(String message) {
//
//    }
//
//    /**
//     * @param message
//     */
//    @Override
//    public void prepareExitView(String message) {
//
//    }
}

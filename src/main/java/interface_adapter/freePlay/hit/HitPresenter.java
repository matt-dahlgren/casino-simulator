package interface_adapter.freePlay.hit;

import interface_adapter.ViewManagerModel;
import interface_adapter.freePlay.setup.SetupViewModel;
import interface_adapter.main_menu.MainMenuViewModel;
import use_case.freeplay.hit.HitOutputBoundary;
import use_case.freeplay.hit.HitOutputData;
import interface_adapter.freePlay.hit.HitState;

import java.util.ArrayList;

public class HitPresenter implements HitOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    SetupViewModel setupViewModel;
    interface_adapter.freePlay.hit.HitViewModel hitViewModel;
    MainMenuViewModel mainMenuViewModel;

    public HitPresenter(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Prepares success view for hit use case
     *
     * @param outputData the output data from interactor
     */
    @Override
    public void prepareSuccessView(HitOutputData outputData) {
        ArrayList<String> playerHand = outputData.getPlayerHandImages();
        final HitState hitState = hitViewModel.getState();

        hitState.setPlayerHand(playerHand);

        hitViewModel.setState(hitState);
        hitViewModel.firePropertyChanged();

        viewManagerModel.setState(hitViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Fail view aka bust
     *
     * @param message explanation for why bust
     */
    @Override
    public void prepareBustView(String message) {

    }

    /**
     * @param message
     */
    @Override
    public void prepareExitView(String message) {

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
}

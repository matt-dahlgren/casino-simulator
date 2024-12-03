package interface_adapter.freeplay.hit;

import interface_adapter.ViewManagerModel;
import interface_adapter.freeplay.setup.SetupViewModel;
import interface_adapter.freePlay.*;
import use_case.freeplay.hit.HitOutputBoundary;
import use_case.freeplay.hit.HitOutputData;
import interface_adapter.*;

import java.util.ArrayList;

public class HitPresenter implements HitOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    HitViewModel hitViewModel;
    SetupViewModel setupViewModel;

    public HitPresenter(ViewManagerModel viewManagerModel, SetupViewModel setupViewModel, HitViewModel hitView) {
        this.viewManagerModel = viewManagerModel;
        this.hitViewModel = hitView;
        this.setupViewModel = setupViewModel;

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

    /**
     * Prepare hit view from the setup view via the hit use case.
     */
    @Override
    public void switchToHitView(HitOutputData outputData) {
        ArrayList<String> dealerHand = outputData.getDealerHandImages();
        ArrayList<String> playerHand = outputData.getPlayerHandImages();
        final HitState hitState = hitViewModel.getState();

        hitState.setDealerHand(dealerHand);
        hitState.setPlayerHand(playerHand);

        hitViewModel.setState(hitState);
        hitViewModel.firePropertyChanged();

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
        viewManagerModel.setState(setupViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}

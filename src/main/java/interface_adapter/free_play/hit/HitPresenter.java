package interface_adapter.free_play.hit;

import interface_adapter.ViewManagerModel;
import interface_adapter.dealer_screen.DealerScreenState;
import interface_adapter.dealer_screen.DealerScreenViewModel;
import interface_adapter.free_play.setup.SetupViewModel;
import use_case.freeplay.hit.HitOutputBoundary;
import use_case.freeplay.hit.HitOutputData;
import use_case.freeplay.setup.SetupOutputData;

import java.util.ArrayList;

public class HitPresenter implements HitOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final DealerScreenViewModel dealerScreenViewModel;
    interface_adapter.free_play.hit.HitViewModel hitViewModel;
    SetupViewModel setupViewModel;

    public HitPresenter(ViewManagerModel viewManagerModel, SetupViewModel setupViewModel, HitViewModel hitView, DealerScreenViewModel dealerScreenViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.hitViewModel = hitView;
        this.setupViewModel = setupViewModel;
        this.dealerScreenViewModel = dealerScreenViewModel;

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
    public void switchToMainMenuView() {
        viewManagerModel.setState(setupViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}

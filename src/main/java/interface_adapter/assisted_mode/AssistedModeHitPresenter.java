package interface_adapter.assisted_mode;

import interface_adapter.ViewManagerModel;
import interface_adapter.dealer_screen.DealerScreenState;
import interface_adapter.dealer_screen.DealerScreenViewModel;
import interface_adapter.main_menu.MainMenuState;
import interface_adapter.main_menu.MainMenuViewModel;
import use_case.assisted_mode.game.AssistedGameOutputData;
import use_case.assisted_mode.game.AssistedGameOutputDataBoundary;

public class AssistedModeHitPresenter implements AssistedGameOutputDataBoundary {

    private final ViewManagerModel viewManagerModel;
    private final AssistedModeViewModel assistedModeViewModel;
    private final MainMenuViewModel mainMenuViewModel;
    private final DealerScreenViewModel dealerScreenViewModel;

    public AssistedModeHitPresenter(ViewManagerModel viewManagerModel, AssistedModeViewModel assistedModeViewModel,
                                    DealerScreenViewModel dealerScreenViewModel, MainMenuViewModel mainMenuViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.assistedModeViewModel = assistedModeViewModel;
        this.dealerScreenViewModel = dealerScreenViewModel;
        this.mainMenuViewModel = mainMenuViewModel;
    }

    public void prepareAssistedView(AssistedGameOutputData data) {
        final AssistedModeState assistedModeState = assistedModeViewModel.getState();
        assistedModeState.setHitWin(data.getHitWin());
        assistedModeState.setStandWin(data.getStandWin());
        assistedModeState.setPlayerScore(data.getPlayerScore());
        assistedModeState.setDealerCardImages(data.getDealerCards());
        assistedModeState.setPlayerCardImages(data.getPlayerCards());

        this.assistedModeViewModel.setState(assistedModeState);
        this.assistedModeViewModel.firePropertyChanged();

        this.viewManagerModel.setState(assistedModeViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    public void prepareMainMenu() {
        final MainMenuState mainMenuState = mainMenuViewModel.getState();
        this.mainMenuViewModel.setState(mainMenuState);
        this.mainMenuViewModel.firePropertyChanged();

        this.viewManagerModel.setState(mainMenuViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    public void prepareAssistedStand(AssistedGameOutputData data) {
        final DealerScreenState dealerScreenState = dealerScreenViewModel.getState();
        dealerScreenState.setDealerScore(data.getDealerScore());
        dealerScreenState.setPlayerScore(data.getPlayerScore());
        dealerScreenState.setCardImages(data.getDealerCards());
        dealerScreenState.setPlayerWin(data.isGameWin());

        this.dealerScreenViewModel.setState(dealerScreenState);
        this.dealerScreenViewModel.firePropertyChanged();
    }
}

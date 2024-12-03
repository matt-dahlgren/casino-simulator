package interface_adapter.dealer_screen;

import interface_adapter.ViewManagerModel;
import interface_adapter.main_menu.MainMenuState;
import interface_adapter.main_menu.MainMenuViewModel;
import use_case.dealer_screen.DealerScreenOutputData;
import use_case.dealer_screen.DealerScreenOutputDataBoundary;

public class DealerScreenPresenter implements DealerScreenOutputDataBoundary {

    private final DealerScreenViewModel viewModel;
    private final ViewManagerModel viewManagerModel;
    private final MainMenuViewModel mainMenuViewModel;

    public DealerScreenPresenter(ViewManagerModel managerModel, DealerScreenViewModel freePlayStandViewModel,
                                 MainMenuViewModel mainMenuViewModel) {
        this.viewModel = freePlayStandViewModel;
        this.viewManagerModel = managerModel;
        this.mainMenuViewModel = mainMenuViewModel;
    }

    @Override
    public void prepareStandView(DealerScreenOutputData outputData) {
        final DealerScreenState standState = viewModel.getState();
        standState.setCardImages(outputData.getDealerCardPictures());
        standState.setDealerScore(outputData.getDealerScore());
        standState.setPlayerScore(outputData.getPlayerScore());
        standState.setPlayerWin(outputData.isPlayerWin());
        standState.setNumGame(outputData.getGameNum());

        this.viewModel.setState(standState);
        this.viewModel.firePropertyChanged();

        this.viewManagerModel.setState(viewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void toMainView() {
        final MainMenuState mainMenuState = mainMenuViewModel.getState();
        this.mainMenuViewModel.setState(mainMenuState);
        this.mainMenuViewModel.firePropertyChanged();

        this.viewManagerModel.setState(viewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}

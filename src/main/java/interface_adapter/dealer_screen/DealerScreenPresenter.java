package interface_adapter.dealer_screen;

import interface_adapter.ViewManagerModel;
import use_case.dealer_screen.DealerScreenOutputData;
import use_case.dealer_screen.DealerScreenOutputDataBoundary;

public class DealerScreenPresenter implements DealerScreenOutputDataBoundary {

    private final DealerScreenViewModel viewModel;
    private final ViewManagerModel viewManagerModel;

    public DealerScreenPresenter(ViewManagerModel managerModel, DealerScreenViewModel freePlayStandViewModel) {
        this.viewModel = freePlayStandViewModel;
        this.viewManagerModel = managerModel;
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
}

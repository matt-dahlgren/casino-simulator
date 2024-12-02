package interface_adapter.dealer_screen;

import use_case.dealer_screen.DealerScreenOutputData;
import use_case.dealer_screen.DealerScreenOutputDataBoundary;

public class DealerScreenPresenter implements DealerScreenOutputDataBoundary {

    private final DealerScreenViewModel viewModel;

    public DealerScreenPresenter(DealerScreenViewModel freePlayStandViewModel) {
        this.viewModel = freePlayStandViewModel;
    }

    //TODO: Add Listeners and actions
    @Override
    public void prepareStandView(DealerScreenOutputData outputData) {
        final DealerScreenState standState = viewModel.getState();
        standState.setCardImages(outputData.getDealerCardPictures());
        standState.setDealerScore(outputData.getDealerScore());
        standState.setPlayerScore(outputData.getPlayerScore());
        standState.setPlayerWin(outputData.isPlayerWin());

    }
}

package interface_adapter.freeplay.stand;

import interface_adapter.ViewManagerModel;
import use_case.freeplay.stand.FreePlayStandOutputData;
import use_case.freeplay.stand.FreePlayStandOutputDataBoundary;

public class FreePlayStandPresenter implements FreePlayStandOutputDataBoundary {

    private final FreePlayStandViewModel viewModel;

    public FreePlayStandPresenter( FreePlayStandViewModel freePlayStandViewModel) {
        this.viewModel = freePlayStandViewModel;
    }

    //TODO: Add Listeners and actions
    @Override
    public void prepareStandView(FreePlayStandOutputData outputData) {
        final FreePlayStandState standState = viewModel.getState();
        standState.setCardImages(outputData.getDealerCardPictures());
        standState.setDealerScore(outputData.getDealerScore());
        standState.setPlayerScore(outputData.getPlayerScore());
        standState.setPlayerWin(outputData.isPlayerWin());

    }
}

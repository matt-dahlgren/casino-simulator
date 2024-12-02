package interface_adapter.assisted_mode.hit;

import interface_adapter.ViewManagerModel;
import interface_adapter.assisted_mode.AssistedModeState;
import interface_adapter.assisted_mode.AssistedModeViewModel;
import use_case.assisted_mode.hit.AssistedHitOutputData;
import use_case.assisted_mode.hit.AssistedHitOutputDataBoundary;

public class AssistedModeHitPresenter implements AssistedHitOutputDataBoundary {

    private final ViewManagerModel viewManagerModel;
    private final AssistedModeViewModel assistedModeViewModel;

    public AssistedModeHitPresenter(ViewManagerModel viewManagerModel, AssistedModeViewModel assistedModeViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.assistedModeViewModel = assistedModeViewModel;
    }

    public void prepareAssistedHitView(AssistedHitOutputData data) {
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
}

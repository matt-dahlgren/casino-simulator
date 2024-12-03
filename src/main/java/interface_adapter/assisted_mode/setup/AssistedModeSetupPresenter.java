package interface_adapter.assisted_mode.setup;

import interface_adapter.ViewManagerModel;
import interface_adapter.assisted_mode.AssistedModeHitPresenter;
import interface_adapter.assisted_mode.AssistedModeState;
import interface_adapter.assisted_mode.AssistedModeViewModel;
import use_case.assisted_mode.setup.AssistedSetUpOutputData;
import use_case.assisted_mode.setup.AssistedSetupOutputBoundary;

public class AssistedModeSetupPresenter implements AssistedSetupOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final AssistedModeViewModel assistedModeViewModel;

    public AssistedModeSetupPresenter(ViewManagerModel viewModel, AssistedModeViewModel assistedViewModel) {
        this.viewManagerModel = viewModel;
        this.assistedModeViewModel = assistedViewModel;

    }

    @Override
    public void prepareAssistedView(AssistedSetUpOutputData outputData) {
        final AssistedModeState assistedModeState = assistedModeViewModel.getState();
        assistedModeState.setPlayerCardImages(outputData.getPlayerCards());
        assistedModeState.setDealerCardImages(outputData.getDealerCards());
        assistedModeState.setStandWin(outputData.getStandWin());
        assistedModeState.setHitWin(outputData.getHitWin());
        assistedModeState.setPlayerScore(outputData.getPlayerScore());

        assistedModeViewModel.setState(assistedModeState);
        assistedModeViewModel.firePropertyChanged();

        viewManagerModel.setState(assistedModeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}

package interface_adapter.free_play.stand;

import interface_adapter.ViewManagerModel;
import interface_adapter.free_play.setup.SetupPresenter;
import interface_adapter.free_play.setup.SetupState;
import interface_adapter.free_play.setup.SetupViewModel;
import interface_adapter.main_menu.MainMenuViewModel;
import use_case.freeplay.stand.StandOutputBoundary;
import use_case.freeplay.stand.StandOutputData;

import java.util.ArrayList;

public class StandPresenter implements StandOutputBoundary {
    SetupViewModel setupViewModel;

    public StandPresenter(SetupViewModel setupViewModel) {
        this.setupViewModel = setupViewModel;
    }

    /**
     * Prepares success view for setup
     *
     * @param outputData the output data from interactor
     */
    @Override
    public void prepareSuccessView(StandOutputData outputData) {
        ArrayList<String> dealerHand = outputData.getDealerHand();
        int dealerScore = outputData.getDscore();
        boolean isWin = outputData.isWinGame();

        final SetupState setupState = setupViewModel.getState();

        setupState.setDealerHand(dealerHand);
        setupState.setDealerScore(dealerScore);
        setupState.setWin(isWin);

        setupViewModel.setState(setupState);
        setupViewModel.firePropertyChanged("stand");
    }
}

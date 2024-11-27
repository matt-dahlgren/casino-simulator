package interface_adapter.freeplay.setup;

import interface_adapter.ViewManagerModel;
import interface_adapter.team_use_case.TeamViewModel;
import use_case.freeplay.setup.SetupOutputBoundary;
import use_case.freeplay.setup.SetupOutputData;

/**
 * The Presenter for the Setup Use Case.
 */
public class SetupPresenter implements SetupOutputBoundary {
    private TeamViewModel teamViewModel;
    private ViewManagerModel viewManagerModel;

    public SetupPresenter(ViewManagerModel viewManagerModel, TeamViewModel teamViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.teamViewModel = teamViewModel;
    }

    @Override
    public void prepareSuccessView(SetupOutputData outputData) {
        teamViewModel.firePropertyChanged("dealer");
        teamViewModel.firePropertyChanged("player");
    }

    //TODO delete I think? Can't fail currently
    @Override
    public void prepareFailView(String error) {
    }
}

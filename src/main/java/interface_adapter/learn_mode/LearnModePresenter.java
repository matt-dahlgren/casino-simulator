package interface_adapter.learn_mode;

import interface_adapter.ViewManagerModel;
import interface_adapter.main_menu.MainMenuViewModel;
import use_case.learn_mode.LearnModeOutputBoundary;

public class LearnModePresenter implements LearnModeOutputBoundary {
    ViewManagerModel viewManagerModel;
    MovesViewModel movesViewModel;
    DealingViewModel dealingViewModel;
    ObjectiveViewModel objectiveViewModel;
    MainMenuViewModel mainMenuViewModel;

    public LearnModePresenter(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
    }

    public void switchToObjectiveView() {
        viewManagerModel.setState(objectiveViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    public void switchToMovesView() {
        viewManagerModel.setState(movesViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    public void switchToDealingView() {
        viewManagerModel.setState(dealingViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    public void switchToMainMenuView() {
        viewManagerModel.setState(mainMenuViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}

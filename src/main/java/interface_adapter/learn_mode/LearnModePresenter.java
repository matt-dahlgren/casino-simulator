package interface_adapter.learn_mode;

import interface_adapter.ViewManagerModel;

public class LearnModePresenter {
    ViewManagerModel viewManagerModel;
    MovesViewModel movesViewModel;
    DealingViewModel dealingViewModel;
    ObjectiveViewModel objectiveViewModel;

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

}

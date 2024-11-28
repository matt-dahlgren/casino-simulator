package interface_adapter.learn_mode;

public class ObjectivePresenter {

    @Override
    public void switchToMovesView() {
        viewManagerModel.setState(movesViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}

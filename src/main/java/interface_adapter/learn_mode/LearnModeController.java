package interface_adapter.learn_mode;

public class ObjectiveController {

    private final ObjectiveInputBoundary objectiveInteractor;
    public void switchToMovesView() {
        objectiveInteractor.switchToMovesView();
    }

}

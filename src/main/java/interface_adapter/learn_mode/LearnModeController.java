package interface_adapter.learn_mode;
import use_case.learn_mode.LearnModeInputBoundary;

public class LearnModeController {
    private final LearnModeInputBoundary learnModeInteractor;

    public LearnModeController(LearnModeInputBoundary learnModeInteractor) {
        this.learnModeInteractor = learnModeInteractor;
    }

    public void switchToObjectiveView(){
        learnModeInteractor.switchToObjectiveView();
    }

    public void switchToMovesView() {
        learnModeInteractor.switchToMovesView();
    }

    public void switchToDealingView() {
        learnModeInteractor.switchToDealingView();
    }

    public void switchToMainMenuView() {
        learnModeInteractor.switchToMainMenuView();
    }
}

package use_case.learn_mode;
import use_case.learn_mode.LearnModeOutputBoundary;

public class LearnModeInteractor implements LearnModeInputBoundary {
    LearnModeOutputBoundary learnModePresenter;

    public LearnModeInteractor(LearnModeOutputBoundary learnModePresenter) {
        this.learnModePresenter = learnModePresenter;
    }

    public void switchToObjectiveView(){
        learnModePresenter.switchToObjectiveView();
    }

    public void switchToMovesView(){
        learnModePresenter.switchToMovesView();
    }

    public void switchToDealingView(){
        learnModePresenter.switchToDealingView();
    }

    public void switchToMainMenuView(){
        learnModePresenter.switchToMainMenuView();
    }

}

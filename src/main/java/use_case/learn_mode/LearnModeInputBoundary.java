package use_case.learn_mode;

import use_case.signup.SignupInputData;

public interface LearnModeInputBoundary {

    /**
     * Executes the switch to moves instruction view for learn mode.
     */
    void switchToMovesView();

    void switchToDealingView();

    void switchToObjectiveView();
}

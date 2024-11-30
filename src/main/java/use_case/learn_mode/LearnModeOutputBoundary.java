package use_case.learn_mode;

public interface LearnModeOutputBoundary {
    /**
     * Switches to the Moves view for learn mode use case.
     */
    void switchToMovesView();

    void switchToDealingView();

    void switchToObjectiveView();
}

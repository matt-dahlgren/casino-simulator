package use_case.endgame_report;

/**
 * The output boundary for the game report use case.
 */
public interface GameReportOutputBoundary {
    /**
     * Prepares the success view for the game report use case.
     * @param outputData the output data
     */
    void prepareSuccessView(GameReportOutputData outputData);

    /**
     * Prepares the failure view for the game report use case.
     * @param error the explanation of the failure
     */
    void prepareFailView(String error);

    /**
     * Switches to the main menu view.
     */
    void switchToMainMenuView();
}

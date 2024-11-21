package use_case.game_report;

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
     * Prepares the failure view for the Login Use Case.
     * @param error the explanation of the failure
     */
    void prepareFailView(String error);
}

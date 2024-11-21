package use_case.game_report;

/**
 * Input Boundary for actions which are related to the game report.
 */
public interface GameReportInputBoundary {
    /**
     * Executes the game report use case.
     * @param gameReportInputData the input data
     */
    void execute(GameReportInputData gameReportInputData);
}

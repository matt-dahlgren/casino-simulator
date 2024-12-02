package use_case.endgame_report;

/**
 * Input Boundary for actions which are related to the game report.
 */
public interface GameReportInputBoundary {
    /**
     * Executes the game report use case.
     * @param gameReportInputData the input data
     */
    void execute(GameReportInputData gameReportInputData);

    /**
     * Executes the switch to main menu view use case.
     */
    void switchToMainMenuView();
}
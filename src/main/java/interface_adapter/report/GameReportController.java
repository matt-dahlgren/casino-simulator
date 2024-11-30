package interface_adapter.report;

import use_case.endgame_report.GameReportInputBoundary;
import use_case.endgame_report.GameReportInputData;

/**
 * Controller for the game report use case.
 */
public class GameReportController {
    private final GameReportInputBoundary gameReportUseCaseInteractor;

    public GameReportController(GameReportInputBoundary gameReportUseCaseInteractor) {
        this.gameReportUseCaseInteractor = gameReportUseCaseInteractor;
    }

    /**
     * Executes the game report use case.
     * @param gameNum the number of the game whose report the user wants to see
     */
    public void execute(String gameNum) {
        final GameReportInputData gameReportInputData = new GameReportInputData(gameNum);
        gameReportUseCaseInteractor.execute(gameReportInputData);
    }


    /**
     * Executes the switch to main menu view use case.
     */
    public void switchToMainMenuView() {
        gameReportUseCaseInteractor.switchToMainMenuView();
    }
}

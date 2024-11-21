package use_case.game_report;

/**
 * The game report interactor.
 */
public class GameReportInteractor implements GameReportInputBoundary {
    private final GameReportDataAccessInterface reportDataAccessObject;
    private final GameReportOutputBoundary reportPresenter;

    public GameReportInteractor(GameReportDataAccessInterface reportDataAccessObject,
                                GameReportOutputBoundary reportOutputBoundary) {
        this.reportDataAccessObject = reportDataAccessObject;
        this.reportPresenter = reportOutputBoundary;
    }

    @Override
    public void execute(GameReportInputData gameReportInputData) {
        int gameNum = isValidGameNum(gameReportInputData.getGameNum());

        if (gameNum == -1) {
            reportPresenter.prepareFailView("Please enter a valid game number");
        }
        else {
            String[][] gameData = reportDataAccessObject.getGameSummary(gameNum);
            GameReportOutputData gameReportOutputData = new GameReportOutputData(gameNum, gameData);
            reportPresenter.prepareSuccessView(gameReportOutputData);
        }
    }

    /**
     * Returns the number of the game the user wants to see the summary of. If the user has not input a number,
     * or they have input an invalid game number (i.e. they wrote 7 but have only played 6 games), then -1 is returned.
     * @param input the user input
     * @return the number of the game the user wants to see a summary of, or -1 in the case of invalid input
     */
    private int isValidGameNum(String input) {
        if (!isNum(input)) {
            return -1;
        }

        int gameNum = Integer.parseInt(input);
        int numGames = reportDataAccessObject.getNumGames();

        if (gameNum > 0 && gameNum <= numGames) {
            return gameNum;
        }

        return -1;
    }

    /**
     * Returns true if and only if the input is an integer.
     * @param num the input
     * @return whether num is an integer
     */
    private boolean isNum(String num) {
        try {
            Integer.parseInt(num);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

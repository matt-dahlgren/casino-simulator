package interface_adapter.report;

import data_access.GameReportDataAccessObject;

import java.io.FileNotFoundException;

/**
 * The state for the game report View Model.
 */
public class ReportState {
    private String[][] gameData;
    private String inputGameNum;
    private int outputGameNum;
    private String gameNumError;

    public ReportState() {

    }

    public ReportState(String csvPath, int gameNum) throws FileNotFoundException {
        // TODO: This function will be modified once the team use case is done and writing user data to files
        // is implemented
        GameReportDataAccessObject reportDAO = new GameReportDataAccessObject(csvPath);
        gameData = reportDAO.getGameSummary(gameNum);
        outputGameNum = gameNum;
    }

    public String[][] getGameData() { return gameData; }

    public String getInputGameNum() { return inputGameNum; }

    public int getOutputGameNum() { return outputGameNum; }

    public String getGameNumError() { return gameNumError; }

    public void setGameData(String[][] gameData) { this.gameData = gameData; }

    public void setInputGameNum(String gameNum) { this.inputGameNum = gameNum; }

    public void setOutputGameNum(int gameNum) { this.outputGameNum = gameNum; }

    public void setGameNumError(String error) { this.gameNumError = error; }
}

package interface_adapter.report;

import data_access.GameReportDataAccessObject;
import entities.User;

import java.io.FileNotFoundException;

/**
 * The state for the game report View Model.
 */
public class ReportState {
    private String[][] gameData;
    private String inputGameNum;
    private int outputGameNum;
    private String feedback;
    private User user;

    public ReportState() {

    }

    public ReportState(String username, int gameNum) throws FileNotFoundException {
        GameReportDataAccessObject reportDAO = new GameReportDataAccessObject(username);
        gameData = reportDAO.getGameSummary(gameNum);
        outputGameNum = gameNum;
    }

    public String[][] getGameData() { return gameData; }

    public String getInputGameNum() { return inputGameNum; }

    public int getOutputGameNum() { return outputGameNum; }

    public String getFeedback() { return feedback; }

    public User getUser() { return user; }

    public void setGameData(String[][] gameData) { this.gameData = gameData; }

    public void setInputGameNum(String gameNum) { this.inputGameNum = gameNum; }

    public void setOutputGameNum(int gameNum) { this.outputGameNum = gameNum; }

    public void setFeedback(String feedback) { this.feedback = feedback; }

    public void setUser(User user) { this.user = user; }
}

package interface_adapter.game_report;

/**
 * The state for the game report view model.
 */
public class GameReportState {
    private String[][] gameData;
    private int gameNum;

    public String[][] getGameData() { return gameData; }

    public int getGameNum() { return gameNum; }

    public void setGameData(String[][] gameData) { this.gameData = gameData; }

    public void setGameNum(int gameNum) { this.gameNum = gameNum; }
}

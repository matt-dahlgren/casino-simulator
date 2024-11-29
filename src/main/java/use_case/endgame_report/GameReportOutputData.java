package use_case.endgame_report;

/**
 * Output Data for the game report use case.
 */
public class GameReportOutputData {
    private final int gameNum;
    private final String[][] gameStats;

    public GameReportOutputData(int gameNum, String[][] gameStats) {
        this.gameNum = gameNum;
        this.gameStats = gameStats;
    }

    public int getGameNum() { return gameNum; }

    public String[][] getGameStats() { return gameStats; }
}

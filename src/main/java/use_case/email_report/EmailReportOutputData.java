package use_case.email_report;

/**
 * Output Data for the game report use case.
 */
public class EmailReportOutputData {
    private final int gameNum;
    private final String[][] gameStats;

    public EmailReportOutputData(int gameNum, String[][] gameStats) {
        this.gameNum = gameNum;
        this.gameStats = gameStats;
    }

    public int getGameNum() { return gameNum; }

    public String[][] getGameStats() { return gameStats; }
}

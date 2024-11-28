package use_case.endgame_report;

/**
 * The Input Data for the game report use case.
 */
public class GameReportInputData {
    private final String gameNum;

    public GameReportInputData(String gameNum) { this.gameNum = gameNum; }

    public String getGameNum() { return gameNum; }
}

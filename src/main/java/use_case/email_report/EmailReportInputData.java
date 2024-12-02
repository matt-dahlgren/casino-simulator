package use_case.email_report;

/**
 * The Input Data for the emailing game report use case.
 */
public class EmailReportInputData {
    public final int gameNum;

    public EmailReportInputData(int gameNum) {
        this.gameNum = gameNum;
    }

    public int getGameNum() { return gameNum; }
}

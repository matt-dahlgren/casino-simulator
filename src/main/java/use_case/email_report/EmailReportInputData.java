package use_case.email_report;

/**
 * The Input Data for the emailing game report use case.
 */
public class EmailReportInputData {
    private final String email;
    public final int gameNum;

    public EmailReportInputData(String email, int gameNum) {
        this.email = email;
        this.gameNum = gameNum;
    }

    public String getEmail() { return email; }

    public int getGameNum() { return gameNum; }
}

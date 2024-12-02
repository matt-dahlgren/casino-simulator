package use_case.email_report;

/**
 * Output Data for the game report use case.
 */
public class EmailReportOutputData {
    private final String feedback;

    public EmailReportOutputData(String feedback) {
        this.feedback = feedback;
    }

    public String getFeedback() { return feedback; }
}

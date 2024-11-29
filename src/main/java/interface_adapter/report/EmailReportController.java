package interface_adapter.report;

import use_case.email_report.EmailReportInputBoundary;
import use_case.email_report.EmailReportInputData;

/**
 * Controller for the (emailing) game report use case.
 */
public class EmailReportController {
    private final EmailReportInputBoundary emailReportUseCaseInteractor;

    public EmailReportController(EmailReportInputBoundary emailReportUseCaseInteractor) {
        this.emailReportUseCaseInteractor = emailReportUseCaseInteractor;
    }

    /**
     * Executes the email game report use case.
     * @param email the email that the user wants to send the game report to
     * @param gameNum the number of the game whose report the user wants to see
     */
    public void execute(String email, int gameNum) {
        final EmailReportInputData emailReportInputData = new EmailReportInputData(email, gameNum);
        emailReportUseCaseInteractor.execute(emailReportInputData);
    }
}

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
     * @param gameNum the number of the game whose report the user wants to see
     */
    public void execute(int gameNum) {
        final EmailReportInputData emailReportInputData = new EmailReportInputData(gameNum);
        emailReportUseCaseInteractor.execute(emailReportInputData);
    }
}

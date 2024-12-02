package use_case.email_report;

/**
 * The output boundary for the emailing game report use case.
 */
public interface EmailReportOutputBoundary {
    /**
     * Prepares the success view for the emailing game report use case.
     * @param outputData the output data
     */
    void prepareSuccessView(EmailReportOutputData outputData);

    /**
     * Prepares the failure view for the emailing game report use case.
     * @param error the explanation of the failure
     */
    void prepareFailView(String error);
}
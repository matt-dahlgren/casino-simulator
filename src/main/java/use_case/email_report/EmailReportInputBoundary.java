package use_case.email_report;

import use_case.endgame_report.GameReportInputData;

/**
 * Input Boundary for actions which are related to emailing the game report.
 */
public interface EmailReportInputBoundary {
    /**
     * Executes the emailing game report use case.
     * @param emailReportInputData the input data
     */
    void execute(EmailReportInputData emailReportInputData);
}
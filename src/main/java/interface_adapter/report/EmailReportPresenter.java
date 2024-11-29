package interface_adapter.report;

import interface_adapter.ViewManagerModel;
import use_case.email_report.EmailReportOutputBoundary;
import use_case.email_report.EmailReportOutputData;

/**
 * The Presenter for the emailing game report use case.
 */
public class EmailReportPresenter implements EmailReportOutputBoundary {

    private final ReportViewModel reportViewModel;
    private final ViewManagerModel viewManagerModel;

    public EmailReportPresenter(ReportViewModel reportViewModel, ViewManagerModel viewManagerModel) {
        this.reportViewModel = reportViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(EmailReportOutputData outputData) {
        // TODO: This function will be modified once the team use case is done and writing user data to files
        // is implemented
        final ReportState reportState = reportViewModel.getState();
        // reportState.setEmail(outputData.getEmail());
        reportViewModel.setState(reportState);
        reportViewModel.firePropertyChanged();

        this.viewManagerModel.setState(reportViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        // TODO: This function will be modified once the team use case is done and writing user data to files
        // is implemented
    }
}

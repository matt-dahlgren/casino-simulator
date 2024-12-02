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
        final ReportState reportState = reportViewModel.getState();
        reportState.setFeedback(outputData.getFeedback());
        reportViewModel.setState(reportState);
        reportViewModel.firePropertyChanged();

        this.viewManagerModel.setState(reportViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final ReportState reportState = reportViewModel.getState();
        reportState.setFeedback(error);
        reportViewModel.firePropertyChanged();
    }
}

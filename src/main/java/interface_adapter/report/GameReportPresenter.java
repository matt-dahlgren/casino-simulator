package interface_adapter.report;

import interface_adapter.ViewManagerModel;
import use_case.endgame_report.GameReportOutputBoundary;
import use_case.endgame_report.GameReportOutputData;

/**
 * The Presenter for the Login Use Case.
 */
public class GameReportPresenter implements GameReportOutputBoundary {
    private final ReportViewModel reportViewModel;
    private final ViewManagerModel viewManagerModel;

    public GameReportPresenter(ReportViewModel reportViewModel, ViewManagerModel viewManagerModel) {
        this.reportViewModel = reportViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(GameReportOutputData outputData) {
        // On success, modify the game state to hold the correct game number and game data to display
        final ReportState reportState = reportViewModel.getState();
        reportState.setOutputGameNum(outputData.getGameNum());
        reportState.setGameData(outputData.getGameStats());
        reportState.setFeedback("");
        reportViewModel.setState(reportState);
        reportViewModel.firePropertyChanged();

        this.viewManagerModel.setState(reportViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        // On failure, indicate to the user that they entered bad input
        final ReportState reportState = reportViewModel.getState();
        reportState.setFeedback(error);
        reportViewModel.firePropertyChanged();
    }

    @Override
    public void switchToMainMenuView() {
        // TODO
        viewManagerModel.setState(reportViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}

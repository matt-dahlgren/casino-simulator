package interface_adapter.report;

import interface_adapter.ViewModel;

import java.io.FileNotFoundException;

/**
 * The ViewModel for the game report view.
 */
public class ReportViewModel extends ViewModel<ReportState> {
    public ReportViewModel() {
        super("game report");
        setState(new ReportState());
    }

    public ReportViewModel(String username, int gameNum) throws FileNotFoundException {
        super("game report");
        setState(new ReportState(username, gameNum));
    }
}

package interface_adapter.report;

import interface_adapter.ViewModel;

import java.io.FileNotFoundException;

/**
 * The ViewModel for the game report view.
 */
public class ReportViewModel extends ViewModel<ReportState> {
    public static final String ROUND = "ROUND";
    public static final String MOVE = "MOVE";
    public static final String WIN_PROBABILITY = "PROBABILITY OF WINNING AFTER MOVE";
    public static final String ALT_WIN_PROBABILITY = "PPROBABILITY OF WINNING IF YOU ACTED DIFFERENTLY";
    public static final String PROBABILITY_DIFFERENCE = "PROBABILITY DIFFERENCE";
    public static final String MOVE_QUALITY = "MOVE QUALITY";

    public static final String VIEW_GAME_NUM = "View Game Number:";
    public static final String EMAIL_REPORT = "Email Report to:";

    public static final String EMAIL_ERROR = "Please enter a valid email";
    public static final String GAME_ERROR = "Please enter a valid game number";

    public ReportViewModel() {
        super("game report");
        setState(new ReportState());
    }

    public ReportViewModel(String csvPath) throws FileNotFoundException {
        // TODO: This function will be modified once the team use case is done and writing user data to files
        // is implemented
        super("game report");
        setState(new ReportState(csvPath, 1));
    }
}

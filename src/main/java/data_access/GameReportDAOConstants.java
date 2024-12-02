package data_access;

/**
 * Constants associated with game data.
 */
public class GameReportDAOConstants {
    public static final int MOVE = 0;
    public static final int WIN_PROBABILITY = 1;
    public static final int ALTERNATE_WIN_PROBABILITY = 2;
    public static final int PROBABILITY_DIFFERENCE = 3;
    public static final int SCORE_AFTER_TURN = 4;
    public static final int NUM_STATISTICS = 5;

    public static final String HIT = "H";
    public static final String STAND = "S";

    private GameReportDAOConstants() { }

    private static final String[] STATISTIC_LABELS = new String[]{
            "ROUND",
            "MOVE",
            "PROBABILITY OF WINNING",
            "ALTERNATE PROBABILITY OF WINNING",
            "PROBABILITY DIFFERENCE",
            "SCORE AFTER TURN" };

    // Indicates how much to pad columns to format the game summary email
    private static final String[] COLUMN_SPACING = new String[]{
            "                 ",
            "              ",
            "                                               ",
            "                                                                    ",
            "                                              ",
            "" };

    public static String[] getStatisticLabels() { return STATISTIC_LABELS.clone(); }

    public static String[] getColumnSpacing() { return COLUMN_SPACING.clone(); }
}

package data_access;

/**
 * Constants associated with game data.
 */
public class GameReportDAOConstants {
    public static final int MOVE = 0;
    public static final int WIN_PROBABILITY = 1;
    public static final int ALTERNATE_WIN_PROBABILITY = 2;
    public static final int PROBABILITY_DIFFERENCE = 3;
    public static final int MOVE_QUALITY = 4;
    public static final int NUM_STATISTICS = 5;

    public static final String HIT = "H";
    public static final String STAND = "S";

    public static final String[] STATISTIC_LABELS = new String[]{
            "ROUND",
            "MOVE",
            "PROBABILITY OF WINNING",
            "ALTERNATE PROBABILITY OF WINNING",
            "PROBABILITY DIFFERENCE",
            "MOVE QUALITY" };
}

package use_case.endgame_report;

/**
 * The interface of the DAO for the game report use case.
 */
public interface GameReportDataAccessInterface {
    /**
     * Gets the summary of one of the user's past games.
     * @param gameNum the number of the game that the user is looking for
     * @return the table of data associated with the given game, stored as a 2D array
     */
    String[][] getGameSummary(int gameNum);

    /**
     * Gets the game data for one round.
     * @param gameNum the game to look for
     * @param roundNum the round to look for
     * @return the array of data for the given round
     */
    String[] getRoundSummary(int gameNum, int roundNum);

    /**
     * Gets the specific statistic for one round.
     * @param gameNum the game to look for
     * @param roundNum the round to look for
     * @param statistic the specific statistic to look for
     * @return the specific statistic from the given round and game
     */
    String getStatistic(int gameNum, int roundNum, int statistic);

    /**
     * Gets the number of games played by the user.
     * @return the number of games played by the user
     */
    int getNumGames();
}
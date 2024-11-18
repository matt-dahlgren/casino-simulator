package data_access;

import use_case.game_report.GameReportDataAccessInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static data_access.GameReportDAOConstants.NUM_STATISTICS;

/**
 * The DAO for game summary data. Assumes that data is stored in the following format:
 *
 * move,probOfWinning,alternateProbOfWinning,difference,moveQuality;move,probOfWinning,...
 * move,probOfWinning,alternateProbOfWinning,difference,moveQuality;move,probOfWinning,...
 *
 * Where move is H or S for "Hit" or "Stand", probOfWinning represents the probability the player will win after a move
 * with a percentage (float), alternateProbOfWinning is what probOfWinning would have been had the player choose to Hit/
 * Stand instead of Stand/Hit, difference is probOfWinning - alternateProbOfWinning, and moveQuality is a string
 * communicating how good the player's move was. Line breaks are used to seperate information about different games.
 * Semicolons are used to seperate information about different rounds.
 */
public class GameReportDataAccessObject implements GameReportDataAccessInterface {

    private final File csvFile;
    private final ArrayList<String[][]> gameSummaries; // Each element of gameSummaries corresponds to one game
    // The first index of an element of an element of gameSummaries corresponds to a round number
    // The second index corresponds to the statistic of interest (e.g. moveQuality)

    public GameReportDataAccessObject(String csvPath) throws FileNotFoundException {
        csvFile = new File(csvPath);
        gameSummaries = new ArrayList<>();
        Scanner fileReader = new Scanner(csvFile);

        while (fileReader.hasNextLine()) {
            String[] data = fileReader.nextLine().split(";");
            String[][] gameSummary = new String[data.length][NUM_STATISTICS];

            for (int i = 0; i < data.length; i++) {
                gameSummary[i] = data[i].split(",");
            }

            gameSummaries.add(gameSummary);
        }

        fileReader.close();
    }

    /**
     * Gets the game data for one game.
     * @param gameNum the game to look for
     * @return the table of game data, as a 2D array
     */
    @Override
    public String[][] getGameSummary(int gameNum) { return gameSummaries.get(gameNum - 1); }

    /**
     * Gets the game data for one round.
     * @param gameNum the game to look for
     * @param roundNum the round to look for
     * @return the array of data for the given round
     */
    @Override
    public String[] getRoundSummary(int gameNum, int roundNum) {
        return gameSummaries.get(gameNum - 1)[roundNum - 1];
    }

    /**
     * Gets the specific statistic for one round.
     * @param gameNum the game to look for
     * @param roundNum the round to look for
     * @param statistic the specific statistic to look for
     * @return the specific statistic from the given round and game
     */
    @Override
    public String getStatistic (int gameNum, int roundNum, int statistic) {
        return gameSummaries.get(gameNum - 1)[roundNum - 1][statistic];
    }

    /**
     * Gets the types of game statistics (which is constant for every game).
     * @return an array containing the types of game statistics
     */
    public String[] getStatLabels() {
        return new String[]{ "MOVE", "PROBABILITY OF WINNING", "ALTERNATE PROBABILITY OF WINNING",
                "PROBABILITY DIFFERENCE", "MOVE QUALITY" };
    }
}

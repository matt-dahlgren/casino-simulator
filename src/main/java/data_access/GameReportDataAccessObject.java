package data_access;

import entities.Card;
import entities.UserPlayer;
import use_case.endgame_report.GameReportDataAccessInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import static data_access.GameReportDAOConstants.*;

/**
 * The DAO for game summary data. Assumes that data is stored in the following format:
 * move,probOfWinning,alternateProbOfWinning,difference,moveQuality;move,probOfWinning,...
 * move,probOfWinning,alternateProbOfWinning,difference,moveQuality;move,probOfWinning,...
 * Where move is H or S for "Hit" or "Stand", probOfWinning represents the probability the player will win after a move
 * with a percentage (float), alternateProbOfWinning is what probOfWinning would have been had the player choose to Hit/
 * Stand instead of Stand/Hit, difference is probOfWinning - alternateProbOfWinning, and moveQuality is a string
 * communicating how good the player's move was. Line breaks are used to seperate information about different games.
 * Semicolons are used to seperate information about different rounds.
 */
public class GameReportDataAccessObject implements GameReportDataAccessInterface {
    private final ArrayList<String[][]> gameSummaries; // Each element of gameSummaries corresponds to one game
    // The first index of an element of an element of gameSummaries corresponds to a round number
    // The second index corresponds to the statistic of interest (e.g. moveQuality)

    private static final int BLACKJACK = 21;

    public GameReportDataAccessObjectDAO() { }

    public GameReportDataAccessObject(String username) throws FileNotFoundException {
        String csvPath = "src/main/java/user_data/" + username + ".csv";
        File csvFile = new File(csvPath);
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
     * Gets the number of games played by the user.
     * @return the number of games played by the user
     */
    @Override
    public int getNumGames() { return gameSummaries.size(); }

    /**
     * Updates game data after a game is over.
     * @param hitProbability the probabilities of the player winning after hitting for each of their moves
     * @param standProbability the probabilities of the player winning after standing for each of their moves
     * @param handScore the scores of the user before each move
     * @param user the user of interest
     * @param username the username of the user of interest
     * @return true if and only if the file writing is successful
     */
    public boolean updateGameData(Map<Integer, Integer> hitProbability, Map<Integer, Integer> standProbability,
                                  Map<Integer, Integer> handScore, UserPlayer user, String username) {
        String[][] gameData = new String[hitProbability.size()][NUM_STATISTICS];

        // Get data for all but the last of the player's turn
        for (int i = 1; i < hitProbability.size(); i++) {
            gameData[i] = getTurnData(hitProbability, standProbability, handScore, i);
        }

        // Get data for the player's last turn
        gameData[hitProbability.size() - 1] = getLastTurnData(hitProbability, standProbability, user);

        // Add the new game summary to the list of game summaries
        gameSummaries.add(gameData);

        // Create the text to be added to the user's file
        String[] temp = new String[hitProbability.size()];

        for (int i = 0; i < hitProbability.size() - 1; i++) {
            temp[i] = String.join(",", gameData[i]);
        }

        String forFile = String.join(";", temp) + "\n";

        // Write the new game data to the user's file
        String csvPath = "src/main/java/user_data/" + username + ".csv";

        try (FileWriter writer = new FileWriter(csvPath, true)) {
            writer.write(forFile);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Gets an array of Strings representing the data associated with a given one of the player's moves.
     * @param hitProbability the probabilities of the player winning after hitting for each of their moves
     * @param standProbability the probabilities of the player winning after standing for each of their moves
     * @param handScore the scores of the user before each move
     * @param turnNumber the turn number of interest
     * @return the array of data for the player's given move
     */
    private String[] getTurnData(Map<Integer, Integer> hitProbability, Map<Integer, Integer> standProbability,
                                 Map<Integer, Integer> handScore, int turnNumber) {
        String[] turnData = new String[NUM_STATISTICS];

        // Store all move data
        turnData[MOVE] = HIT;
        turnData[WIN_PROBABILITY] = hitProbability.get(turnNumber).toString();
        turnData[ALTERNATE_WIN_PROBABILITY] = standProbability.get(turnNumber).toString();
        turnData[PROBABILITY_DIFFERENCE] = String.valueOf
                (hitProbability.get(turnNumber) - standProbability.get(turnNumber));
        turnData[SCORE_AFTER_TURN] = handScore.get(turnNumber + 1).toString();

        // Pad the elements of the data array for formatting reasons
        while (turnData[WIN_PROBABILITY].length() < 3) {
            turnData[WIN_PROBABILITY] = "0" + turnData[WIN_PROBABILITY];
        }

        while (turnData[ALTERNATE_WIN_PROBABILITY].length() < 3) {
            turnData[ALTERNATE_WIN_PROBABILITY] = "0" + turnData[ALTERNATE_WIN_PROBABILITY];
        }

        while (turnData[PROBABILITY_DIFFERENCE].length() < 4) {
            turnData[PROBABILITY_DIFFERENCE] = "0" + turnData[PROBABILITY_DIFFERENCE];
        }

        return turnData;
    }

    /**
     * Gets an array of Strings representing the data associated with the player's last move.
     * @param hitProbability the probabilities of the player winning after hitting for each of their moves
     * @param standProbability the probabilities of the player winning after standing for each of their moves
     * @param user the user of interest
     * @return the array of data for the player's last move
     */
    private String[] getLastTurnData(Map<Integer, Integer> hitProbability, Map<Integer, Integer> standProbability,
                                     UserPlayer user) {
        String[] turnData = new String[NUM_STATISTICS];
        int index = hitProbability.size();
        int score = getScore(user);

        // If the player busted, then they hit on their last turn so act accordingly
        // Otherwise, they stood on their last turn, so act accordingly
        // (i.e. deciding if the probability of the player winning after this turn is the probability of them winning
        // after hitting, or winning after standing.)
        if (score > BLACKJACK) {
            turnData[MOVE] = HIT;
            turnData[WIN_PROBABILITY] = hitProbability.get(index).toString();
            turnData[ALTERNATE_WIN_PROBABILITY] = standProbability.get(index).toString();
            turnData[PROBABILITY_DIFFERENCE] = String.valueOf
                    (hitProbability.get(index) - standProbability.get(index));

        }
        else {
            turnData[MOVE] = STAND;
            turnData[WIN_PROBABILITY] = standProbability.get(index).toString();
            turnData[ALTERNATE_WIN_PROBABILITY] = hitProbability.get(index).toString();
            turnData[PROBABILITY_DIFFERENCE] = String.valueOf
                    (standProbability.get(index) - hitProbability.get(index));
        }

        turnData[SCORE_AFTER_TURN] = String.valueOf(score);

        // Pad the elements of the data array for formatting reasons
        while (turnData[WIN_PROBABILITY].length() < 3) {
            turnData[WIN_PROBABILITY] = "0" + turnData[WIN_PROBABILITY];
        }

        while (turnData[ALTERNATE_WIN_PROBABILITY].length() < 3) {
            turnData[ALTERNATE_WIN_PROBABILITY] = "0" + turnData[ALTERNATE_WIN_PROBABILITY];
        }

        while (turnData[PROBABILITY_DIFFERENCE].length() < 4) {
            turnData[PROBABILITY_DIFFERENCE] = "0" + turnData[PROBABILITY_DIFFERENCE];
        }

        return turnData;
    }

    /**
     * Gets the player's score.
     * @param user the player of interest
     * @return the player's score
     */
    private int getScore(UserPlayer user) {
        ArrayList<Card> hand = user.getHand();

        int result = 0;
        int aceCount = 0;

        // Find the number of aces in the player's hand
        for (Card card : hand) {
            result += card.getValue();
            if (card.getValue() == 11) {
                aceCount++;
            }
        }

        // Treat the appropriate amount of aces as having value 1 instead of 11
        while (aceCount > 0) {
            if (result > BLACKJACK) {
                result -= 10;
                aceCount--;
            }
            else {
                aceCount = 0;
            }
        }

        return result;
    }
}

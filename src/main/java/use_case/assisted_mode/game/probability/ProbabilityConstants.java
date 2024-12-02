package use_case.assisted_mode.game.probability;

import java.util.Map;

import static java.util.Map.entry;

/**
 * Class containing constants used in calculating probabilities.
 */
public class ProbabilityConstants {

    public static final int BLACKJACK = 21;
    public static final int BUST = 22;
    public static final int INITIALSCORE = 23;
    public static final int ACETOONE = 24;
    public static final String SCENARIO = "SCENARIO";
    public static final String WINS = "WINS";

    // a full deck of cards
    // keys refer to the score a card can have whereas the value is the frequency that it appears in the deck.
    public static final Map<Integer, Integer> fullDeck = Map.ofEntries(
            entry(1, 4),
            entry(2, 4),
            entry(3, 4),
            entry(4, 4),
            entry(5, 4),
            entry(6, 4),
            entry(7, 4),
            entry(8, 4),
            entry(9, 4),
            entry(10, 16),
            entry(11, 4)
    );
    public static final Map<Integer, Integer> sampleDeck = Map.ofEntries(
            entry(1, 0),
            entry(2, 0),
            entry(3, 0),
            entry(4, 0),
            entry(5, 0),
            entry(6, 0),
            entry(7, 0),
            entry(8, 0),
            entry(9, 0),
            entry(10, 0),
            entry(11, 0)
    );
    public static final Map<Integer, Integer> values = Map.ofEntries(
            entry(1, 0),
            entry(2, 0),
            entry(3, 0),
            entry(4, 0),
            entry(5, 0),
            entry(6, 0),
            entry(7, 0),
            entry(8, 0),
            entry(9, 0),
            entry(10, 0),
            entry(11, 0),
            entry(12, 0),
            entry(13, 0),
            entry(14, 0),
            entry(15, 0),
            entry(16, 0),
            entry(17,0),
            entry(18,0),
            entry(19,0),
            entry(20,0),
            entry(BLACKJACK, 0),
            entry(BUST,0)
    );
}

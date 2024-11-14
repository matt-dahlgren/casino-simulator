package use_case.probability;

import java.util.Arrays;
import java.util.List;

/**
 * Class containing constants used in calculating probabilities.
 */
public class ProbabilityConstants {
    // a full deck of cards
    // each card is defined by an alphanumeric code, two numbers (00 - 13)
    // where the number refers to the cards rank, 00: Ace, 11: Jack, 12: Queen, 13: King
    // and one uppercase letter referring to the first letter of its suit.
    public static final List<String> fullDeck = Arrays.asList("00H", "00D", "00S", "00C",
            "01H", "01D", "01S", "01C",
            "02H", "02D", "02S", "02C",
            "03H", "03D", "03S", "03C",
            "04H", "04D", "04S", "04C",
            "05H", "05D", "05S", "05C",
            "06H", "06D", "06S", "06C",
            "07H", "07D", "07S", "07C",
            "08H", "08D", "08S", "08C",
            "09H", "09D", "09S", "09C",
            "10H", "10D", "10S", "10C",
            "11H", "11D", "11S", "11C",
            "12H", "12D", "12S", "12C",
            "13H", "13D", "13S", "13C");

    public static final int BLACKJACK = 21;

    public static final String SCENARIO = "SCENARIO";
    public static final String WINS = "WINS";
}

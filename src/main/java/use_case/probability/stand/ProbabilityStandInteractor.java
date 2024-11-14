package use_case.probability.stand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static use_case.probability.ProbabilityConstants.BLACKJACK;
import static use_case.probability.ProbabilityConstants.SCENARIO;
import static use_case.probability.ProbabilityConstants.WINS;

/**
 * The class that calculates the probability of winning a game of BlackJack with a current hand.
 */
public class ProbabilityStandInteractor {

    public final ArrayList<String> unknownCards;
    public final ArrayList<String> userCards;
    public final String dealerCard;

    // TO-DO: reconfigure initialization with input data

    /**
     * initialize an instance of ProbabilityStandInteractor.
     * @param unknownCards is a list of cards configured in the same format as fullDeck in ProbabilityConstants
     *                     , this list consists of all cards in a game that are not shown (all cards in the deck and the
     *                     dealer's non-visible card.
     * @param userCards is a list of cards configured in the same format as fullDeck in ProbabiltyConstants, this list
     *                  consists of all cards in the user's hand.
     * @param dealerCard is a string congfigured in the same format as a card in fullDeck in ProbabilityConstants. This
     *                   is the card that the dealer is showing.
     */
    public ProbabilityStandInteractor(ArrayList<String> unknownCards, ArrayList<String> userCards, String dealerCard) {
        this.unknownCards = unknownCards;
        this.userCards = userCards;
        this.dealerCard = dealerCard;
    }

    /**
     * The probability in form of a percentage, rounded to two decimal points, of you chances of winning based off of
     * your current hand.
     * @return a float with two decimal points representing the chance that your current hand beats the dealer.
     */
    public int standProbability() {

        int score = this.handScore(userCards);

        // User hits a blackjack and automatically wins, our simulator does not take into account for Blackjack ties
        // with the dealer.
        if (score == BLACKJACK) {
            return 100;
        }

        // User busts and immediately loses.
        if (score > BLACKJACK) {
            return 0;
        }

        //enters here if and only if 0 <= score < BLACKJACK
        return this.winPercentage(score);
    }

    /**
     * The score of your current hand. If you have aces in your hand, handScore will return the highest score possible
     * before any possible bust (greater than 21). If the hand cannot be under 21 this handScore will return the
     * smallest possible score.
     * @return an integer representing the score of your current hand.
     */
    private int handScore(List<String> hand) {

        int result = 0;
        int aceCount = 0;

        for (String card : hand) {
            int rank = Integer.parseInt(card.substring(0, 2));

            if (rank == 0) {
                result += 11;
                aceCount++;
            }
            else if (1 <= rank && rank <= 9) {
                result += rank;
            }
            else {
                result += 10;
            }
        }

        // If the player has a hand with aces and have bust (using any aces as a score of 11), reduce the score by 10
        // until either all aces have been evaluated as score 1 or the hand is scored to be less than or equal to 21.
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

    /**
     * Counts the amount of times userDeck wins a game of Blackjack versus any possible version of dealerDeck.
     * Since a card of the dealerDeck is hidden, winPercentage will condition the probability of each win on
     * each card in unknownCards. If that card results in a handScore less than 17 another card will be pulled,
     * meaning that every card in unkownDeck that has not been pulled yet in each test will be pulled. Once counted
     * winPercentage will divide the amount of winning scenarios by the total amount of scenarios.
     * @param userScore is the handScore of a player that is an integer element of [0, 20]
     * @return an int representing the floor of the percentage of winning scenarios userDeck beats dealerDeck.
     */
    private int winPercentage(int userScore) {

        ArrayList<String> dealerDeck = new ArrayList<>();
        dealerDeck.add(dealerCard);
        Map<String, Integer> winScenario = this.countWinsandGames(userScore, dealerDeck, unknownCards);

        return Math.floorDiv(winScenario.get(WINS), winScenario.get(SCENARIO));
    }

    /**
     * Actively counts the amount of times userDeck wins a game and builds ALL possible decks the dealer can feasibly
     * have knowing that the dealer will only ever pull another card from the deck if their score is less than 17.
     * To take care of a very unlikely edge case, if the deck of cards runs out before the dealer hits 21, this is
     * automatically counted as a player win.
     * @param userScore an integer representing the score of the player's hand.
     * @param currentDealer a list of strings representing what cards a dealer could POSSIBLY have with one known card.
     * @param unknownDeck a list of strings representing what cards are assumed to be unknown.
     * @return a map that contains a key representing how many scenarios have been tested and in how many of them the
     * player wins.
     */
    private Map<String, Integer> countWinsandGames(int userScore, ArrayList<String> currentDealer,
                                                   ArrayList<String> unknownDeck) {

        int playerWin = 0;
        int scenarioCount = 0;
        Map<String, Integer> result = new HashMap<>();

        result.put(SCENARIO, 0);
        result.put(WINS, 0);

        for (String card : unknownDeck) {
            ArrayList<String> conditionalDealer = new ArrayList<>(currentDealer);
            conditionalDealer.add(card);

            int dealerScore = handScore(conditionalDealer);

            if (dealerScore > BLACKJACK) {
                playerWin++;
                scenarioCount++;
            }

            // dealerScore will not result in a bust.
            else if (dealerScore >= 17) {

                if (dealerScore < userScore) {
                    playerWin++;
                }
                scenarioCount++;
            }


            else  {
                ArrayList<String> conditionalUnknown = new ArrayList<>(unknownCards);
                conditionalUnknown.remove(card);

                // edge-case that the deck is empty to prevent infinite recursion
                if (conditionalUnknown.isEmpty()) {
                    playerWin++;
                    scenarioCount++;
                }

                // calculates all possible scenarios assuming dealer has card in hand and pulls again.
                // from a non-recursive call to this function the max recursive calls that can be made is 7
                // assuming the dealer pulls/has four Ones, four twos and one three (score of 15, then there will
                // one more recursive call).
                else {
                    Map<String, Integer> heldResult = this.countWinsandGames(userScore,
                            conditionalDealer, conditionalUnknown);

                    result.compute(SCENARIO, (k,v) -> v + heldResult.get(SCENARIO));
                    result.compute(WINS, (k,v) -> v + heldResult.get(WINS));
                }
            }

            int finalScenarioCount = scenarioCount;
            int finalWinCount = playerWin;

            result.compute(SCENARIO, (k, v) -> v + finalScenarioCount);
            result.compute(WINS, (k, v) -> v + finalWinCount);
        }

        return result;
    }
}

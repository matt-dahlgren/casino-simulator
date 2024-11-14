package use_case.probability.stand;

import entities.Card;
import entities.Dealer;
import entities.Player;
import entities.UserPlayer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static use_case.probability.ProbabilityConstants.BLACKJACK;
import static use_case.probability.ProbabilityConstants.fullDeck;
import static use_case.probability.ProbabilityConstants.sampleDeck;
import static use_case.probability.ProbabilityConstants.SCENARIO;
import static use_case.probability.ProbabilityConstants.WINS;

// To reduce runtime of this algorithm, all cards of similar value (bar Ace) are recognized to be the same, and outcomes
// are multiplied by their frequency as one king's branching possibilities will be the same as another king, queen,
// jack or ten pulled at the same branch.

/**
 * The class that calculates the probability of winning a game of BlackJack with a current hand.
 */
public class ProbabilityStandInteractor {

    private final Map<Integer, Integer> unknownCards;
    private final Map<Integer, Integer> userCards;
    private final Map<Integer, Integer> dealerCards;

    // TO-DO: reconfigure initialization with input data

    /**
     * Initializes an instance of ProbabilityStandInteractor.
     * @param players consists of a list of One Dealer, One UsePlayer, and optionally other non User, non Dealer
     *                Players.
     */
    public ProbabilityStandInteractor(ArrayList<Player> players) {

        this.unknownCards = new HashMap<>(fullDeck);
        this.userCards = new HashMap<>(sampleDeck);
        this.dealerCards = new HashMap<>(sampleDeck);

        for (Player player : players) {
            if (player instanceof Dealer) {
                for (Card card: player.getDeck()) {
                    if (card.isVisible()) {
                        int value = card.getValue();
                        this.unknownCards.compute(value, (k, v) -> v - 1);
                        this.dealerCards.compute(value, (k, v) -> v + 1);
                    }
                }
            }
            else if (player instanceof UserPlayer) {
                for (Card card: player.getDeck()) {
                    int value = card.getValue();
                    this.unknownCards.compute(value, (k, v) -> v - 1);
                    this.userCards.compute(value, (k, v) -> v + 1);
                }
            }
            else {
                for (Card card: player.getDeck()) {
                    this.unknownCards.compute(card.getValue(), (k, v) -> v - 1);
                }
            }
        }
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
    private int handScore (Map<Integer, Integer> hand) {

        int result = 0;
        int aceCount = hand.get(11);

        for (int key : hand.keySet()) {
            result += hand.get(key) * key;
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

        Map<String, Integer> winScenario = this.countWinsandGames(userScore, dealerCards, unknownCards);

        return Math.floorDiv(winScenario.get(WINS), winScenario.get(SCENARIO));
    }

    /**
     * Actively counts the amount of times userDeck wins a game and builds ALL possible decks the dealer can feasibly
     * have knowing that the dealer will only ever pull another card from the deck if their score is less than 17.
     * To take care of a very unlikely edge case, if the deck of cards runs out before the dealer hits 21, this is
     * automatically counted as a player win.
     * @param userScore an integer representing the score of the player's hand.
     * @param currentDealer a map containing all cards dealer theoretically has at time of call.
     * @param unknownDeck a map containing what cards are assumed to be unknown.
     * @return a map that contains a key representing how many scenarios have been tested and in how many of them the
     * player wins.
     */
    private Map<String, Integer> countWinsandGames(int userScore, Map<Integer, Integer> currentDealer,
                                                   Map<Integer, Integer> unknownDeck) {

        int playerWin = 0;
        int scenarioCount = 0;
        Map<String, Integer> result = new HashMap<>();

        result.put(SCENARIO, 0);
        result.put(WINS, 0);

        for (int i = 1; i <= 11; i++) {

            // check if there is at least one card of this value left in the unknown card list, if there is not then
            // continue to the next value to remove useless iterations.
            if (unknownDeck.get(i) < 1) {
                continue;
            }

            Map<Integer, Integer> conditionalDealer = new HashMap<>(currentDealer);
            conditionalDealer.compute(i, (k, v) -> v + 1);


            int dealerScore = handScore(conditionalDealer);

            if (dealerScore > BLACKJACK) {
                playerWin += unknownDeck.get(i);
                scenarioCount += unknownDeck.get(i);
            }

            // dealerScore will not result in a bust.
            else if (dealerScore >= 17) {

                if (dealerScore < userScore) {
                    playerWin += unknownDeck.get(i);
                }
                scenarioCount += unknownDeck.get(i);
            }

            else  {

                Map<Integer, Integer> conditionalUnknown = new HashMap<>(unknownDeck);
                conditionalUnknown.compute(i, (k, v) -> v - 1);

                // edge-case that the deck is empty to prevent infinite recursion
                if (this.sumCollection(conditionalUnknown.values()) == 0) {
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

    /**
     * Sums the elements of a collection of integers.
     * @param values is a list of integers representing the values of a map.
     * @return an integer representing the sum of a collection containing integers.
     */
    private Integer sumCollection(Collection<Integer> values) {
        int result = 0;

        for (Integer value : values) {
            result += value;
        }
        return result;
    }

}

package use_case.assisted_mode.game.probability;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static use_case.assisted_mode.game.probability.ProbabilityConstants.BLACKJACK;
import static use_case.assisted_mode.game.probability.ProbabilityConstants.SCENARIO;
import static use_case.assisted_mode.game.probability.ProbabilityConstants.WINS;

/**
 * Interface that carries out common functions between ProbabilityStandInteractor and ProbabilityHitInteractor.
 */
public interface ProbabilityInteractorInterface {

    /**
     * The score of your current hand. If you have aces in your hand, handScore will return the highest score possible
     * before any possible bust (greater than 21). If the hand cannot be under 21 this handScore will return the
     * smallest possible score.
     * @return an integer representing the score of your current hand.
     */
    default int handScore(Map<Integer, Integer> hand) {

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
     * Actively counts the amount of times userDeck wins a game and builds ALL possible decks the dealer can feasibly
     * have knowing that the dealer will only ever pull another card from the deck if their score is less than 17.
     * To take care of a very unlikely edge case, if the deck of cards runs out before the dealer hits 21, this is
     * automatically counted as a player win.
     * @param userScore an integer representing the score of the player's hand.
     * @param currentDealer a map containing all cards dealer theoretically has at time of call.
     * @param unknownDeck a map containing what cards are assumed to be unknown.
     * @param relativeFrequency always initialized as 1.0)
     * @return a map that contains a key representing how many scenarios have been tested and in how many of them the
     * player wins.
     */
    default Map<String, Float> countWinsandGames(int userScore, Map<Integer, Integer> currentDealer,
                                                 Map<Integer, Integer> unknownDeck, float relativeFrequency) {

        float playerWin = 0f;
        float scenarioCount = 0f;
        Map<String, Float> result = new HashMap<>();

        result.put(SCENARIO, 0f);
        result.put(WINS, 0f);

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

            else if (dealerScore > userScore) {
                scenarioCount += unknownDeck.get(i);
            }

            else  {

                Map<Integer, Integer> conditionalUnknown = new HashMap<>(unknownDeck);
                conditionalUnknown.compute(i, (k, v) -> v - 1);
                int deckSize = this.sumCollection(conditionalUnknown.values());

                // edge-case that the deck is empty to prevent infinite recursion
                if (deckSize == 0) {
                    playerWin++;
                    scenarioCount++;
                }

                // calculates all possible scenarios assuming dealer has card in hand and pulls again.
                // from a non-recursive call to this function the max recursive calls that can be made is 7
                // assuming the dealer pulls/has four Ones, four twos and one three (score of 15, then there will
                // one more recursive call).
                // getting the relative frequencies of these probabilities will weigh probabilities to their actual
                // chances of happening. Without this a branch of pulls 3 deep would be equally likely as just 1.
                else {
                    float newRelativeFrequency = relativeFrequency * (1f / deckSize);
                    Map<String, Float> heldResult = this.countWinsandGames(userScore,
                            conditionalDealer, conditionalUnknown, newRelativeFrequency);

                    result.compute(SCENARIO, (k,v) -> v + heldResult.get(SCENARIO));
                    result.compute(WINS, (k,v) -> v + heldResult.get(WINS));
                }
            }

            float finalScenarioCount = scenarioCount * relativeFrequency;
            float finalWinCount = playerWin * relativeFrequency;

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

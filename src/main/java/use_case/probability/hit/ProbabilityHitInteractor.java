package use_case.probability.hit;

import entities.Card;
import entities.Dealer;
import entities.Player;
import entities.UserPlayer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static use_case.probability.ProbabilityConstants.BLACKJACK;
import static use_case.probability.ProbabilityConstants.BUST;
import static use_case.probability.ProbabilityConstants.fullDeck;
import static use_case.probability.ProbabilityConstants.sampleDeck;
import static use_case.probability.ProbabilityConstants.SCENARIO;
import static use_case.probability.ProbabilityConstants.WINS;
import static use_case.probability.ProbabilityConstants.values;
import static use_case.probability.ProbabilityConstants.INITIALSCORE;
import static use_case.probability.ProbabilityConstants.ACETOONE;

// To reduce runtime of this algorithm, all cards of similar value (bar Ace) are recognized to be the same, and outcomes
// are multiplied by their frequency as one king's branching possibilities will be the same as another king, queen,
// jack or ten pulled at the same branch.

/**
 * The class that carries out the logic behind calculating probabilities of a Player winning if they choose to hit.
 */
public class ProbabilityHitInteractor implements ProbabilityInteractorInterface, ProbabilityHitInputBoundary {

    private final ProbabilityHitInputData probabilityHitInputData;
    private final Map<Integer, Integer> unknownCards;
    private final Map<Integer, Integer> userCards;
    private final Map<Integer, Integer> dealerCards;
    private final ProbabilityHitOutputBoundary hitPresenter;


    /**
     * Initializes an instance of ProbabilityHitInteractor.
     */
    public ProbabilityHitInteractor(ProbabilityHitInputData probabilityHitInputData,
                                    ProbabilityHitOutputBoundary hitPresenter) {

        this.probabilityHitInputData = probabilityHitInputData;
        this.unknownCards = new HashMap<>(fullDeck);
        this.userCards = new HashMap<>(sampleDeck);
        this.dealerCards = new HashMap<>(sampleDeck);
        this.hitPresenter = hitPresenter;

        for (Player player : this.probabilityHitInputData.getPlayers()) {
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
     * Collect all possible score outcomes if the player hits, then for each of those scores calculate the chance of
     * winning against the dealer to return the player's entire chance of winning if they are to hit.
     * @return an integer representing the player's chance of winning if they are to hit at a certain round.
     */
    public int hitProbability() {

        Map<Integer, Integer> possibleOutcomes = this.possibleHits();
        Collection<Integer> possibleScores = possibleOutcomes.keySet();

        Map<String, Float> summationWinsGames = new HashMap<>();
        summationWinsGames.put(SCENARIO, 1.0f);
        summationWinsGames.put(WINS, 1.0f);

        if (possibleOutcomes.get(INITIALSCORE) >= BLACKJACK) {
            return 0;
        }

        for (Integer score : possibleScores) {

            if (possibleOutcomes.get(score) == 0) {
                continue;
                // there are no outcomes of this score, continue to the next key in this.possibleOutcomes
            }

            if (score == BLACKJACK) {
                summationWinsGames.compute(SCENARIO, (k,v) -> v + possibleOutcomes.get(score));
                summationWinsGames.compute(WINS, (k,v) -> v + possibleOutcomes.get(score));
            }
            else if (score != BUST) {
                // 0 < score < 22
                int value = score - possibleOutcomes.get(INITIALSCORE);
                Map<Integer, Integer> conditionalUnknownCards = new HashMap<>(this.unknownCards);

                // the possible score has aces set to one and there are also one's in the unknown deck
                if (value == 1 && possibleOutcomes.get(ACETOONE) > 0 && (possibleOutcomes.get(score) -
                        possibleOutcomes.get(ACETOONE)) > 0) {

                    int numberofOnes = possibleOutcomes.get(score) - possibleOutcomes.get(ACETOONE);

                    conditionalUnknownCards.compute(11, (k, v) -> v - 1);
                    Map<String, Float> aceCount = countWinsandGames(score, dealerCards, conditionalUnknownCards, 1f);

                    conditionalUnknownCards.compute(11, (k, v) -> v + 1);

                    conditionalUnknownCards.compute(1, (k, v) -> v - 1);

                    Map<String, Float> oneCount = countWinsandGames(score, dealerCards, conditionalUnknownCards, 1f);

                    summationWinsGames.compute(SCENARIO, (k,v) -> v + ((aceCount.get(SCENARIO))
                            * possibleOutcomes.get(ACETOONE)) + ((oneCount.get(SCENARIO)) * numberofOnes));

                    summationWinsGames.compute(WINS, (k,v) -> v + ((aceCount.get(WINS))
                            * possibleOutcomes.get(ACETOONE)) + ((oneCount.get(WINS)) * numberofOnes));
                }

                else if (value == 1 && possibleOutcomes.get(ACETOONE) > 0) {

                    conditionalUnknownCards.compute(11, (k, v) -> v - 1);
                    Map<String, Float> aceCount = countWinsandGames(score, dealerCards, conditionalUnknownCards, 1f);

                    summationWinsGames.compute(SCENARIO, (k,v) -> v + (aceCount.get(SCENARIO))
                            * possibleOutcomes.get(ACETOONE));

                    summationWinsGames.compute(WINS, (k,v) -> v + (aceCount.get(WINS))
                            * possibleOutcomes.get(ACETOONE));
                }

                else {

                    conditionalUnknownCards.compute(value, (k, v) -> v - 1);
                    Map<String, Float> winCount = countWinsandGames(score, dealerCards, conditionalUnknownCards, 1f);

                    summationWinsGames.compute(SCENARIO, (k,v) -> v + (winCount.get(SCENARIO))
                            * possibleOutcomes.get(value));

                    summationWinsGames.compute(WINS, (k,v) -> v + (winCount.get(WINS))
                            * possibleOutcomes.get(value));
                }
            }

            else {
                summationWinsGames.compute(SCENARIO, (k,v) -> v + possibleOutcomes.get(score));
                // Player has bust in these scenarios.
            }
        }

        return Math.floorDiv((int) Math.ceil(summationWinsGames.get(WINS)),
                (int) Math.ceil(summationWinsGames.get(SCENARIO)));
    }

    /**
     * Creates a map of the frequency of handScores taken from a userHand.
     * @return a map whose keys are handScore values less than or equal to 21, or BUST, and values are the frequency
     * that result occurs from a hands hit.
     */
    private Map<Integer, Integer> possibleHits() {
        Map<Integer, Integer> result = new HashMap<>(values);
        int initialScore = handScore(this.userCards);
        result.put(INITIALSCORE, initialScore);
        result.put(ACETOONE, 0);

        Collection<Integer> keys = unknownCards.keySet();

        for (Integer card : keys) {
            if (unknownCards.get(card) > 0) {
                Map<Integer, Integer> conditionalUserHand = new HashMap<>(userCards);
                conditionalUserHand.compute(card, (k, v) -> v + 1);

                int score = handScore(conditionalUserHand);

                if (score > BLACKJACK) {
                    result.compute(BUST, (k, v) -> v + unknownCards.get(card));
                }

                else {
                    result.compute(score, (k, v) -> v + unknownCards.get(card));

                    // checking whether the set of aces in unknownCards results in any additions of 1 to our
                    // hypothetical hit to better compute the probability to win in hitProbability()
                    if (card == 11 && (score - initialScore) == 1) {
                        result.compute(ACETOONE, (k, v) -> v + unknownCards.get(card));
                    }
                }
            }
        }
        return result;
    }

    @Override
    public void execute(ProbabilityHitInputData inputData) {
        hitPresenter.prepareProbabilityHitView(new ProbabilityHitOutputData(this.hitProbability()));
    }
}

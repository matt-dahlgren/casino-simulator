package use_case.probability.stand;

import data_access.GameDataAccessObject;
import entities.Card;
import entities.Dealer;
import entities.Player;
import entities.UserPlayer;
import use_case.probability.ProbabilityInteractorInterface;

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
public class ProbabilityStandInteractor implements ProbabilityInteractorInterface, ProbabilityStandInputBoundary {

    private final GameDataAccessObject gameDataAccessObject;
    private final ProbabilityStandOutputBoundary standPresenter;
    private final Map<Integer, Integer> unknownCards;
    private final Map<Integer, Integer> userCards;
    private final Map<Integer, Integer> dealerCards;


    /**
     * Initializes an instance of ProbabilityStandInteractor.
     */
    public ProbabilityStandInteractor(GameDataAccessObject gameDataAccessObject,
                                      ProbabilityStandOutputBoundary probabilityStandOutputBoundary) {

        this.gameDataAccessObject = gameDataAccessObject;
        this.standPresenter = probabilityStandOutputBoundary;
        this.unknownCards = new HashMap<>(fullDeck);
        this.userCards = new HashMap<>(sampleDeck);
        this.dealerCards = new HashMap<>(sampleDeck);

        for (Card card: gameDataAccessObject.getDealer().getHand()) {
            if (card.isVisible()) {
                int value = card.getValue();
                this.unknownCards.compute(value, (k, v) -> v - 1);
                this.dealerCards.compute(value, (k, v) -> v + 1);
            }
        }

        for (Card card: gameDataAccessObject.getPlayer().getHand()) {
            int value = card.getValue();
            this.unknownCards.compute(value, (k, v) -> v - 1);
            this.userCards.compute(value, (k, v) -> v + 1);
        }

        for (Player player: gameDataAccessObject.getComputerPlayers())
            for (Card card: player.getHand()) {
                this.unknownCards.compute(card.getValue(), (k, v) -> v - 1);
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
     * Counts the amount of times userDeck wins a game of Blackjack versus any possible version of dealerDeck.
     * Since a card of the dealerDeck is hidden, winPercentage will condition the probability of each win on
     * each card in unknownCards. If that card results in a handScore less than 17 another card will be pulled,
     * meaning that every card in unknownDeck that has not been pulled yet in each test will be pulled. Once counted
     * winPercentage will divide the amount of winning scenarios by the total amount of scenarios.
     * @param userScore is the handScore of a player that is an integer element of [0, 20]
     * @return an int representing the floor of the percentage of winning scenarios userDeck beats dealerDeck.
     */
    private int winPercentage(int userScore) {

        Map<String, Float> winScenario = this.countWinsandGames(userScore, dealerCards, unknownCards, 1f);

        return Math.floorDiv((int) Math.ceil(winScenario.get(WINS)), (int) Math.ceil(winScenario.get(SCENARIO)));
    }

    @Override
    public void execute() {
        standPresenter.prepareProbabilityStandView(new ProbabilityStandOutputData(this.standProbability()));
    }

}

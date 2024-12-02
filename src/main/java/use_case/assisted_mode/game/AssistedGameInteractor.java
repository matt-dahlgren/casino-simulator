package use_case.assisted_mode.game;

import data_access.APIDataAccessObject;
import data_access.GameDataAccessObject;
import entities.Card;
import entities.Dealer;
import entities.Player;
import use_case.assisted_mode.game.probability.ProbabilityHitCalculator;
import use_case.assisted_mode.game.probability.ProbabilityStandCalculator;

import java.util.ArrayList;

import static use_case.assisted_mode.game.probability.ProbabilityConstants.BLACKJACK;

/**
 * Interactor for the Hit sub-UseCase of the Assisted_Mode
 */
public class AssistedGameInteractor implements AssistedGameInputDataBoundary {

    private final GameDataAccessObject gameDataAccessObject;
    private final AssistedGameOutputDataBoundary assistedHitOutputDataBoundary;

    public AssistedGameInteractor(GameDataAccessObject gameDataAccessObject,
                                  AssistedGameOutputDataBoundary assistedHitOutputDataBoundary) {
        this.gameDataAccessObject = gameDataAccessObject;
        this.assistedHitOutputDataBoundary = assistedHitOutputDataBoundary;
    }

    /**
     * The score of the dealer's current hand. If you have aces in your hand, handScore will return the highest score
     * possible before any possible bust (greater than 21). If the hand cannot be under 21 this handScore will return
     * the smallest possible score.
     * @return an integer representing the score of your current hand.
     */
    private int handScore (Player player) {

        int result = 0;
        int aceCount = 0;

        if (player instanceof Dealer) {
            for (Card card : gameDataAccessObject.getDealer().getHand()) {
                result += card.getValue();
                if (card.getValue() == 11) {
                    aceCount++;
                }
            }
        }
        else {
            for (Card card : gameDataAccessObject.getPlayer().getHand()) {
                result += card.getValue();
                if (card.getValue() == 11) {
                aceCount++;
                }
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

    private boolean gameWin(int handscore, int dealerScore) {
        return handscore <= BLACKJACK && ((dealerScore > BLACKJACK) || handscore >= dealerScore);
    }

    @Override
    public void execute() {
        ProbabilityHitCalculator hitCalculator = new ProbabilityHitCalculator(gameDataAccessObject);
        ProbabilityStandCalculator standCalculator = new ProbabilityStandCalculator(gameDataAccessObject);
        int handscore = handScore(gameDataAccessObject.getPlayer());

        ArrayList<String> playerVisibleCards = new ArrayList<>();
        ArrayList<String> dealerVisibleCards = new ArrayList<>();

        for (Card card: gameDataAccessObject.getPlayer().getHand()) {
            playerVisibleCards.add(card.getImage());
        }

        buildDealerInPlay(dealerVisibleCards);

        int dealerScore = handScore((gameDataAccessObject.getDealer()));

        AssistedGameOutputData outputData = new AssistedGameOutputData(hitCalculator.execute(),
                standCalculator.execute(), handscore, dealerScore, playerVisibleCards,
                dealerVisibleCards, gameWin(handscore, dealerScore));

        assistedHitOutputDataBoundary.prepareAssistedView(outputData);
    }

    /**
     * Prepare the GameOutputData if the player's view is going to be moved to the dealerscreen.
     * @return Output Data to help make the view for the dealer screen.
     */
    private AssistedGameOutputData getAssistedStandOutputData(int standProbability, int handScore) {
        ProbabilityHitCalculator hitCalculator = new ProbabilityHitCalculator(gameDataAccessObject);
        ArrayList<String> playerVisibleCards = new ArrayList<>();
        ArrayList<String> dealerVisibleCards = new ArrayList<>();

        for (Card card: gameDataAccessObject.getPlayer().getHand()) {
            playerVisibleCards.add(card.getImage());
        }

        for (Card card: gameDataAccessObject.getDealer().getHand()) {
            dealerVisibleCards.add(card.getImage());
        }
        int dealerScore = handScore((gameDataAccessObject.getDealer()));

        return new AssistedGameOutputData(hitCalculator.execute(),
                standProbability, handScore, dealerScore, playerVisibleCards,
                dealerVisibleCards, gameWin(handScore, dealerScore));
    }

    @Override
    public void assistedHit() {
        //pull a card and add it to the player's hand.
        APIDataAccessObject apiDataAccessObject = new APIDataAccessObject();
        gameDataAccessObject.getPlayer().hit(apiDataAccessObject.getCard(gameDataAccessObject.getDeckID()));

        //verify if the player has bust.
        ProbabilityStandCalculator standCalculator = new ProbabilityStandCalculator(gameDataAccessObject);
        int handscore = standCalculator.handScore(standCalculator.getUserCards());

        if (handscore >= 21) {
            assistedHitOutputDataBoundary.prepareAssistedStand(getAssistedStandOutputData(standCalculator.execute(),
                    handscore));
        }
        else {
            ProbabilityHitCalculator hitCalculator = new ProbabilityHitCalculator(gameDataAccessObject);
            ArrayList<String> playerVisibleCards = new ArrayList<>();
            ArrayList<String> dealerVisibleCards = new ArrayList<>();

            for (Card card: gameDataAccessObject.getPlayer().getHand()) {
                playerVisibleCards.add(card.getImage());
            }

            buildDealerInPlay(dealerVisibleCards);
            int dealerScore = handScore((gameDataAccessObject.getDealer()));
            AssistedGameOutputData outputData = new AssistedGameOutputData(hitCalculator.execute(),
                    standCalculator.execute(), handscore, dealerScore, playerVisibleCards, dealerVisibleCards,
                    gameWin(handscore, dealerScore));
            assistedHitOutputDataBoundary.prepareAssistedView(outputData);
        }
    }

    private void buildDealerInPlay(ArrayList<String> dealerVisibleCards) {
        for (Card card: gameDataAccessObject.getDealer().getHand()) {
            if (card.isVisible()) {
                dealerVisibleCards.add(card.getImage());
            }
            else {
                dealerVisibleCards.add("https://deckofcardsapi.com/static/img/back.png");
            }
        }
    }

    /**
     * The dealer pulls cards from the Deck until they have a score of at least 17.
     */
    private void pullToSeventeen() {

        APIDataAccessObject apiDataAccessObject = new APIDataAccessObject();

        while (this.handScore(gameDataAccessObject.getDealer()) < 17) {
            this.gameDataAccessObject.getDealer().hit(apiDataAccessObject.getCard(gameDataAccessObject.getDeckID()));
        }
    }

    @Override
    public void assistedStand() {
        pullToSeventeen();
        ProbabilityStandCalculator standCalculator = new ProbabilityStandCalculator(gameDataAccessObject);
        int handscore = standCalculator.handScore(standCalculator.getUserCards());
        assistedHitOutputDataBoundary.prepareAssistedStand(getAssistedStandOutputData(standCalculator.execute(),
                handscore));
    }

    @Override
    public void mainMenu() {
        assistedHitOutputDataBoundary.prepareMainMenu();
    }
}

package use_case.dealer_screen;

import data_access.GameDataAccessObject;
import data_access.APIDataAccessObject;

import data_access.GameReportDataAccessObject;
import entities.Card;

import java.util.ArrayList;

import static use_case.assisted_mode.game.probability.ProbabilityConstants.BLACKJACK;

/**
 * The interactor for the FreePlayStand UseCase, it is assumed that at this point this player has not Bust.
 */
public class DealerScreenInteractor implements DealerScreenInputBoundary {

    private final GameDataAccessObject gameDataAccessObject;
    private final DealerScreenOutputDataBoundary outputPresenter;
    private final GameReportDataAccessObject reportDataAccessObject;

    public DealerScreenInteractor(GameDataAccessObject gameDataAccessObject,
                                  DealerScreenOutputDataBoundary outputPresenter,
                                  GameReportDataAccessObject reportDataAccessObject) {
        this.gameDataAccessObject = gameDataAccessObject;
        this.outputPresenter = outputPresenter;
        this.reportDataAccessObject = reportDataAccessObject;
    }

    /**
     * The score of the dealer's current hand. If you have aces in your hand, handScore will return the highest score
     * possible before any possible bust (greater than 21). If the hand cannot be under 21 this handScore will return
     * the smallest possible score.
     * @return an integer representing the score of your current hand.
     */
    private int dealerHandScore() {

        int result = 0;
        int aceCount = 0;

        for (Card card : gameDataAccessObject.getDealer().getHand()) {
            result += card.getValue();
            if (card.getValue() == 11) {
                aceCount++;
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
     * The score of the player's current hand. If you have aces in your hand, handScore will return the highest score
     * possible before any possible bust (greater than 21). If the hand cannot be under 21 this handScore will return
     * the smallest possible score.
     * @return an integer representing the score of your current hand.
     */
    private int playerHandScore () {

        int result = 0;
        int aceCount = 0;

        for (Card card : gameDataAccessObject.getPlayer().getHand()) {
            result += card.getValue();
            if (card.getValue() == 11) {
                aceCount++;
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
     * Pull cards from the DeckofCardsAPI until the dealer has a handScore of at least 17.
     */
    private void pullToSeventeen() {

        APIDataAccessObject apiDataAccessObject = new APIDataAccessObject();

        while (this.dealerHandScore() < 17) {
            this.gameDataAccessObject.getDealer().hit(apiDataAccessObject.getCard(gameDataAccessObject.getDeckID()));
        }
    }

    /**
     * Execute the FreePlayStand UsecCase
     */
    public void execute() {

        this.pullToSeventeen();

        int dealerScore = this.dealerHandScore();
        int playerScore = this.playerHandScore();

        boolean gameWin = playerScore <= BLACKJACK && ((dealerScore > BLACKJACK) || playerScore >= dealerScore);

        ArrayList<String> cardStrings = new ArrayList<>();

        for (Card card : gameDataAccessObject.getDealer().getHand()) {
            cardStrings.add(card.getImage());
        }

        DealerScreenOutputData outputData = new DealerScreenOutputData(dealerScore, playerScore,
                cardStrings, gameWin, String.valueOf(reportDataAccessObject.getNumGames()));

        outputPresenter.prepareStandView(outputData);
    }

}

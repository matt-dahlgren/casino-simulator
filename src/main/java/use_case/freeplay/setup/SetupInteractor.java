package use_case.freeplay.setup;

import data_access.APIDataAccessObject;
import entities.Card;
import entities.Dealer;
import entities.Player;
import entities.UserPlayer;
import use_case.freeplay.FreePlayDA;
import use_case.freeplay.GameDataAccess;

import java.util.ArrayList;

import static use_case.assisted_mode.game.probability.ProbabilityConstants.BLACKJACK;

/**
 * The Setup Interactor, does the main backend stuff with setting up the game through the Game Data Object.
 */
public class SetupInteractor implements SetupInputBoundary {
    private final FreePlayDA deckDataObject;
    private final SetupOutputBoundary setupPresenter;
    private final GameDataAccess gameDataObject;

    public SetupInteractor(GameDataAccess gameDataObject,
                           FreePlayDA setupDeckDataAccessInterface,
                           SetupOutputBoundary setupPresenter) {
        this.gameDataObject = gameDataObject;
        this.setupPresenter = setupPresenter;
        this.deckDataObject = setupDeckDataAccessInterface;
    }

    @Override
    public void execute() {
        //Generates new Deck Object + gets a deck ID through the API
        String deckID = deckDataObject.getDeckID();

        ArrayList<Card> userPlayerHand = new ArrayList<>();
        userPlayerHand.add(deckDataObject.getCard(deckID));
        userPlayerHand.add(deckDataObject.getCard(deckID));

        ArrayList<Card> dealerHand = new ArrayList<>();
        dealerHand.add(deckDataObject.getCard(deckID));
        dealerHand.add(deckDataObject.getCard(deckID));

        //Creates hidden card
        dealerHand.getFirst().setVisible(false);

        Dealer dealer = new Dealer(dealerHand);
        UserPlayer userPlayer = new UserPlayer(userPlayerHand);

        //Creates the lists that hold the strings for the image links for the cards.
        ArrayList<String> userPlayerHandLinks = new ArrayList<>();
        ArrayList<String> dealerHandLinks = new ArrayList<>();

        for (Card card : dealer.getHand()) {
            dealerHandLinks.add(card.getImage());
        }

        for (Card card : userPlayer.getHand()) {
            userPlayerHandLinks.add(card.getImage());
        }

        gameDataObject.setDealer(dealer);
        gameDataObject.setPlayer(userPlayer);
        gameDataObject.setDeckID(deckID);

        final SetupOutputData setupOutputData = new SetupOutputData(dealerHandLinks, userPlayerHandLinks, false, 0, 0);
        setupPresenter.prepareSuccessView(setupOutputData);
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
            for (Card card : gameDataObject.getDealer().getHand()) {
                result += card.getValue();
                if (card.getValue() == 11) {
                    aceCount++;
                }
            }
        }
        else {
            for (Card card : gameDataObject.getPlayer().getHand()) {
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

    /**
     * The dealer pulls cards from the Deck until they have a score of at least 17.
     */
    private void pullToSeventeen() {

        APIDataAccessObject apiDataAccessObject = new APIDataAccessObject();

        while (this.handScore(gameDataObject.getDealer()) < 17) {
            this.gameDataObject.getDealer().hit(apiDataAccessObject.getCard(gameDataObject.getDeckID()));
        }
    }

    @Override
    public void switchToDealerAfterStandView() {

        int playerScore = handScore(gameDataObject.getPlayer());
        pullToSeventeen();
        int dealerScore = handScore(gameDataObject.getDealer());
        boolean winGame = gameWin(playerScore, dealerScore);

        ArrayList<String> playerVisibleCards = new ArrayList<>();
        ArrayList<String> dealerVisibleCards = new ArrayList<>();

        for (Card card: gameDataObject.getPlayer().getHand()) {
            playerVisibleCards.add(card.getImage());
        }

        for (Card card: gameDataObject.getDealer().getHand()) {
            dealerVisibleCards.add(card.getImage());
        }

        SetupOutputData outputData = new SetupOutputData(dealerVisibleCards, playerVisibleCards, winGame, playerScore,
                dealerScore);

        setupPresenter.switchToDealerAfterStandView(outputData);
    }

    @Override
    public void switchToMainMenuView() {
        setupPresenter.switchToMainMenuView();
    }
}

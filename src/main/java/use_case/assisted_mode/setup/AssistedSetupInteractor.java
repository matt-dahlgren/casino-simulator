package use_case.assisted_mode.setup;

import data_access.APIDataAccessObject;
import data_access.GameDataAccessObject;
import entities.Card;
import entities.Dealer;
import entities.Player;
import entities.UserPlayer;
import use_case.assisted_mode.game.AssistedGameOutputData;
import use_case.assisted_mode.game.probability.ProbabilityHitCalculator;
import use_case.assisted_mode.game.probability.ProbabilityStandCalculator;

import java.util.ArrayList;

import static use_case.assisted_mode.game.probability.ProbabilityConstants.BLACKJACK;

public class AssistedSetupInteractor implements AssistedSetupInputBoundary{

    private final AssistedSetupOutputBoundary outputBoundary;
    private final GameDataAccessObject gameDataAccessObject;
    // TODO: SETUP JACOBS DAO

    public AssistedSetupInteractor(AssistedSetupOutputBoundary outputBoundary,
                                   GameDataAccessObject gameDataAccessObject) {
        this.outputBoundary = outputBoundary;
        this.gameDataAccessObject = gameDataAccessObject;
    }

    /**
     * The score of the dealer's current hand. If you have aces in your hand, handScore will return the highest score
     * possible before any possible bust (greater than 21). If the hand cannot be under 21 this handScore will return
     * the smallest possible score.
     * @return an integer representing the score of your current hand.
     */
    private int handScore(Player player, GameDataAccessObject gameDataAccessObject) {

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

    @Override
    public void execute() {
        //Access the Deck of Cards API, and populate a new GameDataAccessObject
        APIDataAccessObject apiDataAccessObject = new APIDataAccessObject();

        //Objects to be put into the GameDataAccessObject
        String deckId = apiDataAccessObject.getDeckID();

        ArrayList<Card> dealerCards = new ArrayList<>();
        Dealer dealer = new Dealer(dealerCards);
        dealer.hit(apiDataAccessObject.getCard(deckId));
        //One of the dealer's cards must be hidden
        Card hiddenCard = apiDataAccessObject.getCard(deckId);
        hiddenCard.setVisible(false);
        dealer.hit(hiddenCard);

        //Both of the player's cards are visible so we just hit twice
        ArrayList<Card> playerCards = new ArrayList<>();
        UserPlayer player = new UserPlayer(playerCards);
        player.hit(apiDataAccessObject.getCard(deckId));
        player.hit(apiDataAccessObject.getCard(deckId));

        //There are no computer players in an assisted game, thus this stays empty.
        ArrayList<Player> computerPlayers = new ArrayList<>();

        //This Game's Data Access Object
        gameDataAccessObject.setPlayer(player);
        gameDataAccessObject.setDealer(dealer);
        gameDataAccessObject.setDeckID(deckId);
        gameDataAccessObject.setComputerPlayers(computerPlayers);

        //Initialize the AssistedSetupOutputData for the Presenter
        ProbabilityHitCalculator hitCalculator = new ProbabilityHitCalculator(gameDataAccessObject);
        ProbabilityStandCalculator standCalculator = new ProbabilityStandCalculator(gameDataAccessObject);
        int handscore = handScore(gameDataAccessObject.getPlayer(), gameDataAccessObject);

        ArrayList<String> playerVisibleCards = new ArrayList<>();
        ArrayList<String> dealerVisibleCards = new ArrayList<>();

        for (Card card: gameDataAccessObject.getPlayer().getHand()) {
            playerVisibleCards.add(card.getImage());
        }

        for (Card card: gameDataAccessObject.getDealer().getHand()) {
            if (card.isVisible()) {
                dealerVisibleCards.add(card.getImage());
            }
            else {
                dealerVisibleCards.add("https://deckofcardsapi.com/static/img/back.png");
            }
        }

        AssistedSetUpOutputData outputData = new AssistedSetUpOutputData(gameDataAccessObject, hitCalculator.execute(),
                standCalculator.execute(), handscore, playerVisibleCards, dealerVisibleCards);

        outputBoundary.prepareAssistedView(outputData);
    }
}

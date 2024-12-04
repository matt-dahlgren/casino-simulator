package use_case.freeplay.setup;

import data_access.APIDataAccessObject;
import data_access.GameDataAccessObject;
import entities.Card;
import entities.Dealer;
import entities.Player;
import entities.UserPlayer;
import use_case.freeplay.FreePlayDA;
import use_case.freeplay.GameDataAccess;

import java.util.ArrayList;
import java.util.Objects;

import static use_case.assisted_mode.game.probability.ProbabilityConstants.BLACKJACK;

/**
 * The Setup Interactor, does the main backend stuff with setting up the game through the Game Data Object.
 */
public class SetupInteractor implements SetupInputBoundary {
    private final FreePlayDA deckDataObject;
    private final SetupOutputBoundary setupPresenter;
    private GameDataAccess gameDataObject;
    private boolean winGame = false;
    private int dealerScore = 0;

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

        //Checks if userPlayer has an ace and updates the userPlayer's score, also provides the score for output purposes!
        int score = updateScore(userPlayerHand);

        if (score == 21) {
            this.winGame = true;
        }

        //Creates the dealer and userPlayer.
        Dealer dealer = new Dealer(dealerHand);
        UserPlayer userPlayer = new UserPlayer(userPlayerHand);

        //Creates the lists that hold the strings for the image links for the cards.
        ArrayList<String> userPlayerHandLinks = new ArrayList<>();
        ArrayList<String> dealerHandLinks = new ArrayList<>();

        //Only the second card (non-hidden) of the dealer needs to be calculated for their score.
        if (dealerHand.get(1) != null) {
            this.dealerScore = dealerHand.get(1).getValue();
        }

        for (Card card : dealer.getHand()) {
            dealerHandLinks.add(card.getImage());
        }

        for (Card card : userPlayer.getHand()) {
            userPlayerHandLinks.add(card.getImage());
        }


        gameDataObject.setDealer(dealer);
        gameDataObject.setPlayer(userPlayer);
        gameDataObject.setDeckID(deckID);

        final SetupOutputData setupOutputData = new SetupOutputData(dealerHandLinks, userPlayerHandLinks, winGame,
                score, dealerScore);
        setupPresenter.prepareSuccessView(setupOutputData);
    }

    @Override
    public void switchToMainMenuView() {
        setupPresenter.switchToMainMenuView();
    }


    /**
     * Helper function for execute score calculation, updates Aces to equal to 1 if needed.
     */
    public int updateScore(ArrayList<Card> hand) {
        int score = 0;
        ArrayList<Card> aces11 = new ArrayList<>();
        for (Card card : hand) {
            score += card.getValue();
            if (Objects.equals(card.getValue(), 11)) {
                aces11.add(card);
            }
        }

        while (score > 21 && !aces11.isEmpty()) {
            aces11.removeFirst().setValue(1);
        }
        return score;
    }
}

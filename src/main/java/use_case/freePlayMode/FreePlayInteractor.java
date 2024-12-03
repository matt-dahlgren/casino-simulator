package use_case.freePlayMode;

import entities.*;
import data_access.FreePlayDA;

import java.util.ArrayList;

/**
 * This is the use case interactor for our group use case: free play mode.
 * This is a game of blackjack with no hints, i.e. does not make use of our probability algorithm.
 */
public class FreePlayInteractor {
    private final FreePlayDA freePlayDAO;
    private final FreePlayOutputBoundary freePlayPresenter;

    public FreePlayInteractor(FreePlayDA freePlayDAO, FreePlayOutputBoundary freePlayPresenter) {
        this.freePlayDAO = freePlayDAO;
        this.freePlayPresenter = freePlayPresenter;
    }

    public void hit() {

    }

    public void stand() {

    }

    public void start() {
        //need to get a deck, need to add cards to player hand, dealer hand, send hands to output data

        DeckFactory deck = new DeckFactory();

        //make player and dealer objects
        ArrayList<Card> playerHand = new ArrayList<>();
        ArrayList<Card> dealerHand = new ArrayList<>();
        UserPlayer player = new UserPlayer(playerHand);
        Dealer dealer = new Dealer(dealerHand);

        //get new deck from API
        String deckID = freePlayDAO.getDeckID();
        deck.createDeck(deckID);

        //deal cards to player
        for (int i = 0; i < 2; i ++) {
            // add card
        }



    }


}


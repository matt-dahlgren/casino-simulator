package use_case.freeplay.setup;

import data_access.GameDataAccessObject;
import entities.Card;
import entities.Dealer;
import entities.User;
import entities.UserPlayer;

import java.util.ArrayList;

/**
 * The Setup Interactor, does the main backend stuff with setting up the game through the Game Data Object.
 */
public class SetupInteractor implements SetupInputBoundary {
    private final SetupDeckDataAccessInterface deckDataObject;
    private final SetupOutputBoundary setupPresenter;
    private final GameDataAccessObject gameDataObject;

    public SetupInteractor(GameDataAccessObject gameDataAccessObject,
                           SetupDeckDataAccessInterface setupDeckDataAccessInterface,
                           SetupOutputBoundary setupPresenter) {
        this.gameDataObject = gameDataAccessObject;
        this.setupPresenter = setupPresenter;
        this.deckDataObject = setupDeckDataAccessInterface;
    }

    @Override
    public void execute() {
        String deckID = deckDataObject.getDeckID();

        ArrayList<Card> userPlayerHand = new ArrayList<Card>();
        userPlayerHand.add(deckDataObject.getCard());
        userPlayerHand.add(deckDataObject.getCard());

        ArrayList<Card> dealerHand = new ArrayList<Card>();
        dealerHand.add(deckDataObject.getCard());
        dealerHand.add(deckDataObject.getCard());

        //Creates hidden card
        dealerHand.getFirst().setVisible(false);

        Dealer dealer = new Dealer(dealerHand);
        UserPlayer userPlayer = new UserPlayer(userPlayerHand);

        //Creates the lists that hold the strings for the image links for the cards.
        ArrayList<String> userPlayerHandLinks = new ArrayList<String>();
        ArrayList<String> dealerHandLinks = new ArrayList<String>();

        for (Card card : dealer.getHand()) {
            dealerHandLinks.add(card.getImage());
        }

        for (Card card : userPlayer.getHand()) {
            userPlayerHandLinks.add(card.getImage());
        }

        gameDataObject.setDealer(dealer);
        gameDataObject.setPlayer(userPlayer);
        gameDataObject.setDeckID(deckID);

        final SetupOutputData setupOutputData = new SetupOutputData(dealerHandLinks, userPlayerHandLinks);
        setupPresenter.prepareSuccessView(setupOutputData);
    }
}

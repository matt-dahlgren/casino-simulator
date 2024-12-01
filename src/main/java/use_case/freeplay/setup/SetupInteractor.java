package use_case.freeplay.setup;

import entities.Card;
import entities.Dealer;
import entities.UserPlayer;

import java.util.ArrayList;

/**
 * The Setup Interactor, does the main backend stuff with setting up the game through the Game Data Object.
 */
public class SetupInteractor implements SetupInputBoundary {
    private final SetupDeckDataAccessInterface deckDataObject;
    private final SetupOutputBoundary setupPresenter;
    private final SetupGameDataAccessInterface gameDataObject;

    public SetupInteractor(SetupGameDataAccessInterface gameDataAccessObject,
                           SetupDeckDataAccessInterface setupDeckDataAccessInterface,
                           SetupOutputBoundary setupPresenter) {
        this.gameDataObject = gameDataAccessObject;
        this.setupPresenter = setupPresenter;
        this.deckDataObject = setupDeckDataAccessInterface;
    }

    @Override
    public void execute() {
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

        final SetupOutputData setupOutputData = new SetupOutputData(dealerHandLinks, userPlayerHandLinks);
        setupPresenter.prepareSuccessView(setupOutputData);
    }

    @Override
    public void switchToHitView() {
        setupPresenter.switchToHitView();
    }

    @Override
    public void switchToDealerAfterStandView() {
        setupPresenter.switchToDealerAfterStandView();
    }

    @Override
    public void switchToMainMenuView() {
        setupPresenter.switchToMainMenuView();
    }
}

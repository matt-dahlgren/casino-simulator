package use_case.freeplay.setup;

import entities.Card;
import entities.Dealer;
import entities.UserPlayer;
import use_case.freeplay.FreePlayDA;
import use_case.freeplay.GameDataAccess;

import java.util.ArrayList;

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

        final SetupOutputData setupOutputData = new SetupOutputData(dealerHandLinks, userPlayerHandLinks);
        setupPresenter.prepareSuccessView(setupOutputData);
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

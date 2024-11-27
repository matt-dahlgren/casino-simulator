package use_case.freeplay.setup;

import data_access.GameDataAccessObject;
import entities.Card;
import entities.Dealer;
import entities.UserPlayer;

import java.util.ArrayList;

/**
 * The Setup Interactor.
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
        Dealer dealer = new Dealer();
        UserPlayer userPlayer = new UserPlayer();

        String deckID = deckDataObject.getDeckID();

        dealer.hit(deckDataObject.draw());
        Card hidden_card = deckDataObject.draw();
        hidden_card.setVisible(false);
        dealer.hit(hidden_card);

        userPlayer.hit(deckDataObject.draw());
        userPlayer.hit(deckDataObject.draw());

        ArrayList<String> dealerHand = new ArrayList<String>();
        ArrayList<String> userPlayerHand = new ArrayList<String>();

        for (Card card : dealer.getHand()) {
            dealerHand.add(card.link());
        }

        for (Card card : userPlayer.getHand()) {
            userPlayerHand.add(card.link());
        }

        gameDataObject.setDealer(dealer);
        gameDataObject.setPlayer(userPlayer);
        gameDataObject.setDeckID(deckID);

        final SetupOutputData setupOutputData = new SetupOutputData(dealerHand, userPlayerHand);
        setupPresenter.prepareSuccessView(setupOutputData);
    }
}

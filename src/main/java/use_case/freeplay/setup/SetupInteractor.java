package use_case.freeplay.setup;

import entities.Card;
import entities.Dealer;
import entities.UserPlayer;

/**
 * The Setup Interactor.
 */
public class SetupInteractor implements SetupInputBoundary {
    private final SetupDeckDataAccessInterface deckDataObject;
    private final SetupOutputBoundary setupPresenter;

    public SetupInteractor(SetupDeckDataAccessInterface setupDeckDataAccessInterface, SetupOutputBoundary setupPresenter) {
        this.deckDataObject = setupDeckDataAccessInterface;
        this.setupPresenter = setupPresenter;
    }

    @Override
    public void execute() {
        Dealer dealer = new Dealer();
        UserPlayer userPlayer = new UserPlayer();

        dealer.hit(deckDataObject.draw());
        Card hidden_card = deckDataObject.draw();
        hidden_card.setVisible(false);
        dealer.hit(hidden_card);

        userPlayer.hit(deckDataObject.draw());
        userPlayer.hit(deckDataObject.draw());

        final SetupOutputData setupOutputData = new SetupOutputData(dealer, userPlayer);
        setupPresenter.prepareSuccessView(setupOutputData);
    }
}

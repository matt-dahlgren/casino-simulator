package use_case.freeplay.stand;

import entities.Card;
import entities.Dealer;
import entities.UserPlayer;
import use_case.freeplay.FreePlayDA;
import use_case.freeplay.GameDataAccess;
import use_case.freeplay.setup.SetupInputBoundary;
import use_case.freeplay.setup.SetupOutputBoundary;
import use_case.freeplay.setup.SetupOutputData;

import java.util.ArrayList;
import java.util.Objects;

/**
 * The Setup Interactor, does the main backend stuff with setting up the game through the Game Data Object.
 */
public class StandInteractor implements StandInputBoundary {
    private final FreePlayDA deckDataObject;
    private final StandOutputBoundary standPresenter;
    private final GameDataAccess gameDataObject;
    private boolean winGame;
    private int dealerScore = 0;
    ArrayList<String> dealer_images = new ArrayList<>();

    public StandInteractor(GameDataAccess gameDataObject,
                           FreePlayDA setupDeckDataAccessInterface,
                           StandOutputBoundary standPresenter) {
        this.gameDataObject = gameDataObject;
        this.standPresenter = standPresenter;
        this.deckDataObject = setupDeckDataAccessInterface;
    }

    @Override
    public void execute() {
        int pscore = updateScore(gameDataObject.getPlayer().getHand());
        int dscore = updateScore(gameDataObject.getDealer().getHand());

        if (pscore < 21) {
            while (dscore < 17) {
                Card newCard = deckDataObject.getCard(gameDataObject.getDeckID());

                ArrayList<Card> hand = gameDataObject.getDealer().getHand();
                hand.add(newCard);
                gameDataObject.getDealer().setHand(hand);

                dscore = updateScore(gameDataObject.getDealer().getHand());
            }

            for (Card card : gameDataObject.getDealer().getHand()) {
                dealer_images.add(card.getImage());
            }

            if (pscore < 21 && dscore < pscore) {
                winGame = true;
            }
            else {
                winGame = false;
            }
        }

        else if (pscore > 21) {
            for (Card card : gameDataObject.getDealer().getHand()) {
                dealer_images.add(card.getImage());
            }
            winGame = false;
        }

        else {
            winGame = false;
        }

        final StandOutputData outputData = new StandOutputData(dealer_images, dscore, winGame);

        standPresenter.prepareSuccessView(outputData);
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

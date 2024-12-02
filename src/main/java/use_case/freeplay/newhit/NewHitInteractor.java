package use_case.freeplay.newhit;

import entities.Card;
import entities.Player;
import entities.UserPlayer;
import use_case.freeplay.FreePlayDA;
import use_case.freeplay.GameDataAccess;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Objects;

/**
 * The hit interactor.
 */
public class NewHitInteractor implements NewHitInputBoundary {
    private final FreePlayDA freePlayDA;
    private final GameDataAccess gameDataAccess;
    private final NewHitOutputBoundary hitPresenter;

    public NewHitInteractor(FreePlayDA freePlayDA, GameDataAccess gameDataAccess, NewHitOutputBoundary hitOutputBoundary) {
        this.freePlayDA = freePlayDA;
        this.gameDataAccess = gameDataAccess;
        this.hitPresenter = hitOutputBoundary;
    }

    @Override
    public void execute() {
        UserPlayer player = gameDataAccess.getPlayer();

        if (getScore(player.getHand()) < 21) {

            Card newCard = freePlayDA.getCard(gameDataAccess.getDeckID());
            player.hit(newCard);

            System.out.println(player.getHand());

            ArrayList<String> player_images = new ArrayList<>();
            for (Card card : player.getHand()) {
                player_images.add(card.getImage());
            }
            System.out.println(player.getHand());

            final NewHitOutputData outputData = new NewHitOutputData(player_images);

            hitPresenter.prepareSuccessView(outputData);
        }


    }

    /**
     * Helper function for execute score calculation, updates Aces to equal to 1 if needed.
     */
    public int getScore(ArrayList<Card> hand) {
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

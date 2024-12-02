package use_case.freeplay.hit;

/**
 * Use case interactor for hit use case
 */

import entities.Card;
import entities.CardFactory;
import entities.Player;
import entities.UserPlayer;
import use_case.freePlayMode.FreePlayDA;
import use_case.freeplay.GameDataAccess;

import java.util.ArrayList;

public class HitUseCaseInteractor implements HitInputBoundary {
    private final FreePlayDA freePlayDataAccessObject;
    private final GameDataAccess gameDAO;
    private final HitOutputBoundary hitPresenter;

    public HitUseCaseInteractor(FreePlayDA freePlayDataAccessObject, GameDataAccess gameDAO, HitOutputBoundary hitPresenter) {
        this.freePlayDataAccessObject = freePlayDataAccessObject;
        this.gameDAO = gameDAO;
        this.hitPresenter = hitPresenter;
    }

    /**
     * Executes hit
     */
    public void execute() {
        //get player hand
        UserPlayer player = gameDAO.getPlayer();
        ArrayList<Card> playerHand = player.getHand();

        //if the player can take their turn
        if (canHit(getHandValue(playerHand))) {
            //hit branch
            //add card to player hand
            playerHand.add(freePlayDataAccessObject.getCard(gameDAO.getDeckID()));
            player.setHand(playerHand);
            //update player
            gameDAO.setPlayer(player);

            //output data and presenter
            HitOutputData outputData = new HitOutputData(makeImages(playerHand));
            hitPresenter.prepareSuccessView(outputData);
        }
        else if ((getHandValue(playerHand)).equals(21)) {
            hitPresenter.prepareExitView("Winner!");
        }
        else {
            //bust
            hitPresenter.prepareBustView("Bust! You're over 21");
        }
    }

    @Override
    public void switchToHitView() {
        hitPresenter.switchToHitView();
    }

    @Override
    public void switchToDealerAfterStandView() {
        hitPresenter.switchToDealerAfterStandView();
    }

    @Override
    public void switchToMainMenuView() {
        hitPresenter.switchToMainMenuView();
    }

    @Override
    public int getHandVal() {
        UserPlayer player = gameDAO.getPlayer();
        ArrayList<Card> playerHand = player.getHand();
        int valOfHand = 0;
        for (Card card : playerHand) {
            valOfHand += card.getValue();
        }
        return valOfHand;
    }

    /**
     * Helper function that makes array with image links
     * @param hand the hand list
     * @return list of strings with links
     */
    private ArrayList<String> makeImages (ArrayList<Card> hand) {
        ArrayList<String> images = new ArrayList<>();
        for (Card card : hand) {
            images.add(card.getImage());
        }
        return images;
    }

    /**
     * Helper function that checks if player hand vals sum to < 21
     * @param handVal value of player hand
     * @return True if less than 21, if = 21 they win, false if bust
     */
    private Boolean canHit(int handVal) {
        return handVal < 21;
    }

    private Integer getHandValue(ArrayList<Card> hand) {
        int valOfHand = 0;
        for (Card card : hand) {
            valOfHand += card.getValue();
        }
        return valOfHand;
    }
}

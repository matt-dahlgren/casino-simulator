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

public class HitUseCaseInteractor {
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
        if (canHit(getHandVal(playerHand))) {
            //hit branch
            //add card to player hand
            playerHand.add(freePlayDataAccessObject.getCard(gameDAO.getDeckID()));
            player.setHand(playerHand);
            //update player
            gameDAO.setPlayer(player);

            //output data and presenter

        }
        else if ((getHandVal(playerHand)).equals(21)) {
            //win
        }
        else {
            //bust
        }


    }

    /**
     * Helper function that checks if player hand vals sum to < 21
     * @param handVal value of player hand
     * @return True if less than 21, if = 21 they win, false if bust
     */
    private Boolean canHit(int handVal) {
        return handVal < 21;
    }

    private Integer getHandVal(ArrayList<Card> hand) {
        int valOfHand = 0;
        for (Card card : hand) {
            valOfHand += card.getValue();
        }
        return valOfHand;
    }
}

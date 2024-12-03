package use_case.freeplay.hit;

/**
 * Use case interactor for hit use case
 */

import entities.*;
import use_case.freeplay.FreePlayDA;
import use_case.freeplay.GameDataAccess;

import java.util.ArrayList;
import java.util.Objects;

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
        // get player hand
        UserPlayer player = gameDAO.getPlayer();
        ArrayList<Card> playerHand = player.getHand();

        // add card to player hand
        playerHand.add(freePlayDataAccessObject.getCard(gameDAO.getDeckID()));
        player.setHand(playerHand);
        // update player
        gameDAO.setPlayer(player);

        // checking if they've bust or won
        if (canHit(getHandValue(playerHand))) {
            // successful hit, success view
            HitOutputData outputData = new HitOutputData(makeImages(playerHand),
                    makeImages(gameDAO.getDealer().getHand()));
            hitPresenter.prepareSuccessView(outputData);
        }
        else if (getHandValue(playerHand).equals(21)) {
            hitPresenter.prepareExitView("Winner!");
        }
        else {
            // bust
            hitPresenter.prepareBustView("Bust! You're over 21");
        }
    }

    @Override
    public void switchToHitView() {

    }

    @Override
    public void executeSetuptoHit() {
        UserPlayer player = gameDAO.getPlayer();
        Dealer dealer = gameDAO.getDealer();

        ArrayList<Card> playerHand = player.getHand();
        ArrayList<Card> dealerHand = dealer.getHand();

        playerHand.add(freePlayDataAccessObject.getCard(gameDAO.getDeckID()));
        player.setHand(playerHand);

        gameDAO.setPlayer(player);

        final HitOutputData outputData = new HitOutputData(makeImages(dealerHand), makeImages(playerHand));

        if (getHandValue(playerHand) == 21) {
            hitPresenter.prepareBustView("Winner!");
        }
        else if (canHit(getHandValue(playerHand))) {
            hitPresenter.switchToHitView(outputData);
        }
        else {
            hitPresenter.prepareBustView("Bust! You're over 21");
        }

    }

    @Override
    public void switchToDealerAfterStandView() {
        hitPresenter.switchToDealerAfterStandView();
    }

    @Override
    public void switchToMainMenuView() {
        hitPresenter.switchToMainMenuView();
    }

    /**
     * Helper function that makes array with image links.
     *
     * @param hand the hand list
     * @return list of strings with links
     */
    private ArrayList<String> makeImages(ArrayList<Card> hand) {
        ArrayList<String> images = new ArrayList<>();
        for (Card card : hand) {
            images.add(card.getImage());
        }
        return images;
    }

    /**
     * Helper function that checks if player hand vals sum to < 21.
     *
     * @param handVal value of player hand
     * @return True if less than 21, if = 21 they win, false if bust
     */
    private Boolean canHit(int handVal) {
        return handVal < 21;
    }

    private Integer getHandValue(ArrayList<Card> hand) {
        int valOfHand = 0;
        int aces = 0;
        for (Card card : hand) {
            valOfHand += card.getValue();
            if (Objects.equals(card.getRank(), "ACE")) {
                aces += 1;
            }

            while (valOfHand > 21 && aces > 0) {
                valOfHand -= 10;
            }

        }
        return valOfHand;
    }
}

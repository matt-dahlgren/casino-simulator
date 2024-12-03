package use_case.freeplay.hit;

/**
 * Use case interactor for hit use case
 */

import data_access.APIDataAccessObject;
import entities.*;
import use_case.freeplay.FreePlayDA;
import use_case.freeplay.GameDataAccess;
import use_case.freeplay.setup.SetupOutputData;

import java.util.ArrayList;
import java.util.Objects;

import static use_case.assisted_mode.game.probability.ProbabilityConstants.BLACKJACK;

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

        //add card to player hand
        playerHand.add(freePlayDataAccessObject.getCard(gameDAO.getDeckID()));
        player.setHand(playerHand);
        //update player
        gameDAO.setPlayer(player);

        //checking if they've bust or won
        if (canHit(getHandValue(playerHand))) {
            //successful hit, success view
            HitOutputData outputData = new HitOutputData(makeImages(playerHand),
                    makeImages(gameDAO.getDealer().getHand()));
            hitPresenter.prepareSuccessView(outputData);
        } else if ((getHandValue(playerHand)).equals(21)) {
            hitPresenter.prepareExitView("Winner!");
        } else {
            //bust
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
        int playerScore = handScore(gameDAO.getPlayer());
        pullToSeventeen();
        int dealerScore = handScore(gameDAO.getDealer());
        boolean winGame = gameWin(playerScore, dealerScore);

        ArrayList<String> playerVisibleCards = new ArrayList<>();
        ArrayList<String> dealerVisibleCards = new ArrayList<>();

        for (Card card: gameDAO.getPlayer().getHand()) {
            playerVisibleCards.add(card.getImage());
        }

        for (Card card: gameDAO.getDealer().getHand()) {
            dealerVisibleCards.add(card.getImage());
        }

        SetupOutputData outputData = new SetupOutputData(dealerVisibleCards, playerVisibleCards, winGame, playerScore,
                dealerScore);

        hitPresenter.switchToDealerAfterStandView(outputData);
    }

    /**
     * The score of the dealer's current hand. If you have aces in your hand, handScore will return the highest score
     * possible before any possible bust (greater than 21). If the hand cannot be under 21 this handScore will return
     * the smallest possible score.
     *
     * @return an integer representing the score of your current hand.
     */
    private int handScore (Player player) {

        int result = 0;
        int aceCount = 0;

        if (player instanceof Dealer) {
            for (Card card : gameDAO.getDealer().getHand()) {
                result += card.getValue();
                if (card.getValue() == 11) {
                    aceCount++;
                }
            }
        }
        else {
            for (Card card : gameDAO.getPlayer().getHand()) {
                result += card.getValue();
                if (card.getValue() == 11) {
                    aceCount++;
                }
            }

        while (aceCount > 0) {
            if (result > BLACKJACK) {
                result -= 10;
                aceCount--;
            }
            else {
                aceCount = 0;
            }
        }

        return result;
        }
    }

    @Override
    public void switchToMainMenuView() {
        hitPresenter.switchToMainMenuView();
    }

    private boolean gameWin(int handscore, int dealerScore) {
        return handscore <= BLACKJACK && ((dealerScore > BLACKJACK) || handscore >= dealerScore);
    }

    /**
     * The dealer pulls cards from the Deck until they have a score of at least 17.
     */
    public void pullToSeventeen() {

        APIDataAccessObject apiDataAccessObject = new APIDataAccessObject();

        while (this.handScore(gameDAO.getDealer()) < 17) {
            this.gameDAO.getDealer().hit(apiDataAccessObject.getCard(gameDAO.getDeckID()));
        }
    }

    /**
     * Helper function that makes array with image links
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
     * Helper function that checks if player hand vals sum to < 21
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

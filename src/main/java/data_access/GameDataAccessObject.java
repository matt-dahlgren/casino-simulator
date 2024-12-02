package data_access;

import entities.Dealer;
import entities.Player;
import entities.UserPlayer;
import use_case.freeplay.GameDataAccess;
import use_case.freeplay.setup.SetupGameDataAccessInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Game Data woohoo
 */
public class GameDataAccessObject implements GameDataAccess, SetupGameDataAccessInterface {
    UserPlayer userPlayer;
    Dealer dealer;
    ArrayList<Player> computerPlayers;
    String deckID;
    Map<Integer, Integer> hitProbability;
    Map<Integer, Integer> standProbability;
    Map<Integer, Integer> handScore;

    public GameDataAccessObject(UserPlayer userPlayer, Dealer dealer, String deckID, ArrayList<Player> computerPlayers) {
        this.userPlayer = userPlayer;
        this.dealer = dealer;
        this.deckID = deckID;
        this.computerPlayers = computerPlayers;
        this.hitProbability = new HashMap<>();
        this.standProbability = new HashMap<>();
        this.handScore = new HashMap<>();
    }

    /**
     * Gets the user player
     * @return player
     */
    @Override
    public UserPlayer getPlayer() {
        return userPlayer;
    }

    /**
     * Sets player
     * @param player entity
     */
    @Override
    public void setPlayer(UserPlayer player) {
        userPlayer = player;
    }

    /**
     * Get Dealer
     * @return dealer
     */
    @Override
    public Dealer getDealer() {
        return dealer;
    }

    /**
     * Sets dealer
     * @param deal entity
     */
    @Override
    public void setDealer(Dealer deal) {
        dealer = deal;
    }

    /**
     * Gets deck id
     * @return deck id
     */
    @Override
    public String getDeckID() {
        return deckID;
    }

    /**
     * Sets deck ID
     * @param id is deck id
     */
    @Override
    public void setDeckID(String id) {
        deckID = id;
    }

    /**
     * Sets the computer players (if any in a game)
     * @param computerPlayers are NPCs of a Player subclass.
     */
    @Override
    public void setComputerPlayers(ArrayList<Player> computerPlayers) {
        this.computerPlayers = computerPlayers;
    }

    /**
     * Gets the computer players of a game.
     * @return a list consisting of computer players in a game of blackjack.
     */
    public ArrayList<Player> getComputerPlayers() {
        return computerPlayers;
    }

    /**
     * Returns a map of hit probability per turn.
     * @param score is the HitProbability Score of that turn.
     */
    @Override
    public void updateHitProbability(int score) {
        hitProbability.put(hitProbability.size() + 1, score);
    }

    /**
     * Returns a map of stand probability per turn.
     * @param score is the StandProbability Score of that turn.
     */
    @Override
    public void updateStandProbability(int score) {
        standProbability.put(standProbability.size() + 1, score);
    }

    /**
     * Returns a map of player hand score of turns of play.
     * @param score is the handscore of the player at that turn
     */
    @Override
    public void updateHandScore(int score) {
        handScore.put(handScore.size() + 1, score);
    }

}

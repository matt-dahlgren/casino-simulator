package data_access;

import entities.Dealer;
import entities.Player;
import entities.UserPlayer;
import use_case.freeplay.GameDataAccess;
import use_case.freeplay.setup.SetupGameDataAccessInterface;

import java.util.ArrayList;

/**
 * Game Data woohoo
 */
public class GameDataAccessObject implements GameDataAccess, SetupGameDataAccessInterface {
    UserPlayer userPlayer;
    Dealer dealer;
    ArrayList<Player> computerPlayers;
    String deckID;

    public GameDataAccessObject() {}

    public GameDataAccessObject(UserPlayer userPlayer, Dealer dealer, String deckID, ArrayList<Player> computerPlayers) {
        this.userPlayer = userPlayer;
        this.dealer = dealer;
        this.deckID = deckID;
        this.computerPlayers = computerPlayers;
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

    public void setComputerPlayers(ArrayList<Player> computerPlayers) {
        this.computerPlayers = computerPlayers;
    }

    public ArrayList<Player> getComputerPlayers() {
        return computerPlayers;
    }
}

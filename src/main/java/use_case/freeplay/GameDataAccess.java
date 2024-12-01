package use_case.freeplay;


import entities.Dealer;
import entities.Player;
import entities.UserPlayer;

import java.util.ArrayList;
import java.util.Map;

public interface GameDataAccess {
    UserPlayer getPlayer();
    void setPlayer(UserPlayer player);

    Dealer getDealer();
    void setDealer(Dealer dealer);

    String getDeckID();
    void setDeckID(String deckID);

    ArrayList<Player> getComputerPlayers();
    void setComputerPlayers(ArrayList<Player> computerPlayers);

    void updateHitProbability(int score);

    void updateStandProbability(int score);

    void updateHandScore(int score);
}

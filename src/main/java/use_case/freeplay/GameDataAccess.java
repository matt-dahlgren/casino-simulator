package use_case.freeplay;


import entities.Dealer;
import entities.UserPlayer;

public interface GameDataAccess {
    UserPlayer getPlayer();
    void setPlayer(UserPlayer player);

    Dealer getDealer();
    void setDealer(Dealer dealer);

    String getDeckID();
    void setDeckID(String deckID);
}

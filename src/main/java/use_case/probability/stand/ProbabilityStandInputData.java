package use_case.probability.stand;

import java.util.ArrayList;

import entities.Player;

public class ProbabilityStandInputData {

    private final ArrayList<Player> players;

    public ProbabilityStandInputData(ArrayList<Player> players) {
        this.players = players;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}

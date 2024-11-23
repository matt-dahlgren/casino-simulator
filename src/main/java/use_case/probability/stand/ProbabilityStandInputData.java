package use_case.probability.stand;

import java.util.List;

import entities.Player;

public class ProbabilityStandInputData {

    private final List<Player> players;

    public ProbabilityStandInputData(List<Player> players) {
        this.players = players;
    }

    public List<Player> getPlayers() {
        return players;
    }
}

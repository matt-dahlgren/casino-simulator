package use_case.probability.hit;

import java.util.List;

import entities.Player;

/**
 * Class representing the InputData for the HitProbability usecase.
 */
public class ProbabilityHitInputData {

    private final List<Player> players;

    public ProbabilityHitInputData(List<Player> players) {
        this.players = players;
    }

    public List<Player> getPlayers() {
        return players;
    }
}

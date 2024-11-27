package interface_adapter.probability.hit;

import use_case.probability.hit.ProbabilityHitInputBoundary;
import use_case.probability.hit.ProbabilityHitInputData;

import java.util.List;

import entities.Player;

/**
 * The controller for the ProbabilityHit Use-Case.
 */
public class ProbabilityHitController {

    private final ProbabilityHitInputBoundary probabilityHitInputBoundary;

    public ProbabilityHitController(ProbabilityHitInputBoundary probabilityHitUseCaseInteractor) {
        this.probabilityHitInputBoundary = probabilityHitUseCaseInteractor;
    }

    /**
     * Executes the ProbabilityHit use-case.
     * @param players is a list of valid players playing a game of BlackJack.
     */
    public void execute(List<Player> players) {
        probabilityHitInputBoundary.execute();
    }
}

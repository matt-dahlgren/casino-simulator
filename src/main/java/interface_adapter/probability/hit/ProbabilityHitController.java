package interface_adapter.probability.hit;

import use_case.probability.hit.ProbabilityHitInputBoundary;

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
     */
    public void execute() {
        probabilityHitInputBoundary.execute();
    }
}

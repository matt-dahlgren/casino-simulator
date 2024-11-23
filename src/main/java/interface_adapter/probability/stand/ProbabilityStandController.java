package interface_adapter.probability.stand;

import use_case.probability.stand.ProbabilityStandInputBoundary;
import use_case.probability.stand.ProbabilityStandInputData;

import java.util.List;

import entities.Player;

/**
 * The controller for the ProbabilityStand UseCase.
 */
public class ProbabilityStandController {

    private final ProbabilityStandInputBoundary probabilityStandInputBoundary;

    public ProbabilityStandController(ProbabilityStandInputBoundary probabilityStandUseCaseInteractor) {
        this.probabilityStandInputBoundary = probabilityStandUseCaseInteractor;
    }

    /**
     * Executes the ProbabilityStand use-case.
     * @param players is a list of valid players playing a game of BlackJack.
     */
    public void execute(List<Player> players) {
        final ProbabilityStandInputData probabilityStandInputData = new ProbabilityStandInputData(players);

        probabilityStandInputBoundary.execute(probabilityStandInputData);
    }
}

package use_case.probability.stand;

/**
 * Input Boundary for actions which are related to collecting win probabilities when standing.
 */
public interface ProbabilityStandInputBoundary {

    /**
     * Executes the probabilityStand usecase.
     * @param probabilityStandInputData is valid input data.
     */
    void execute(ProbabilityStandInputData probabilityStandInputData);
}

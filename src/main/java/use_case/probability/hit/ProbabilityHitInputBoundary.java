package use_case.probability.hit;

/**
 * Input Boundary for actions which are related to collecting win probabilities when hitting.
 */
public interface ProbabilityHitInputBoundary {

    /**
     * Executes the probabilityHit usecase
     * @param probabilityHitInputData is valid input data.
     */
    void execute();
}

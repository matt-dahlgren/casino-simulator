package use_case.probability.hit;

/**
 * The output boundary for the ProbabilityHit Usecase.
 */
public interface ProbabilityHitOutputBoundary {

    /**
     * Prepares the view for the probabilityHit Use Case.
     * @param probabilityHitOutputData is valid output data.
     */
    void prepareProbabilityHitView(ProbabilityHitOutputData probabilityHitOutputData);
}

package use_case.probability.stand;

/**
 * The output boundary for the Probability Stand Use case.
 */
public interface ProbabilityStandOutputBoundary {

    /**
     * Prepares the view for the probabilityStand Use Case.
     * @param probabilityStandOutputData is valid output data.
     */
    void prepareProbabilityStandView(ProbabilityStandOutputData probabilityStandOutputData);
}

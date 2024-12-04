package use_case.freeplay.setup;

public interface SetupOutputBoundary {
    /**
     * Prepares the success view for the Setup Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(SetupOutputData outputData);
    /**
     * Prepares the failure view for the Setup Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    void switchToMainMenuView();
}

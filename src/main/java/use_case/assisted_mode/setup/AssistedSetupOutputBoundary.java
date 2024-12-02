package use_case.assisted_mode.setup;

public interface AssistedSetupOutputBoundary {

    /**
     * Prepare the view of the Assisted Setup Game with the information from the output data
     */
    void prepareAssistedView(AssistedSetUpOutputData outputData);
}

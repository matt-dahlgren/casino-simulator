package use_case.freeplay.setup;

/**
 * Input Boundary for actions related to setting up (nothing).
 */
public interface SetupInputBoundary {

    /**
     * Executes the setup use case.
     */
    void execute();

    void switchToHitView();

    void switchToDealerAfterStandView();

    void switchToMainMenuView();
}

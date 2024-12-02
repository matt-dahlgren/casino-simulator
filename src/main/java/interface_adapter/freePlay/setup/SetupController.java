package interface_adapter.freePlay.setup;

import use_case.freeplay.setup.SetupInputBoundary;

/**
 * The controller for the Setup Use Case.
 */
public class SetupController {
    private final SetupInputBoundary setupInteractor;

    public SetupController(SetupInputBoundary setupInteractor) {
        this.setupInteractor = setupInteractor;
    }

    public void switchToHitView() {
        setupInteractor.switchToHitView();
    }

    public void switchToDealerAfterStandView() {
        setupInteractor.switchToDealerAfterStandView();
    }

    public void switchToMainMenuView() {
        setupInteractor.switchToMainMenuView();
    }

    /**
     * Execute the setup use case. No need for parameters.
     */
    public void execute() {
        setupInteractor.execute();
    }
}

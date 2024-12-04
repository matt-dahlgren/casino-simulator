package interface_adapter.free_play.setup;

import use_case.freeplay.setup.SetupInputBoundary;

/**
 * The controller for the Setup Use Case.
 */
public class SetupController {
    private final SetupInputBoundary setupInteractor;

    public SetupController(SetupInputBoundary setupInteractor) {
        this.setupInteractor = setupInteractor;
    }

    /**
     * Execute the setup use case. No need for parameters.
     */
    public void execute() {
        setupInteractor.execute();
    }

    public void switchToMainMenuView() {
        setupInteractor.switchToMainMenuView();
    }
}

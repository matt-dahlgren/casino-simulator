package interface_adapter.freeplay.setup;

import use_case.freeplay.setup.SetupInputBoundary;

public class SetupController {
    private final SetupInputBoundary setupInteractor;

    public SetupController(SetupInputBoundary setupInteractor) {
        this.setupInteractor = setupInteractor;
    }

    public void execute_setup() {
        setupInteractor.execute();
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
}

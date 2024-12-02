package interface_adapter.assisted_mode.setup;

import use_case.assisted_mode.setup.AssistedSetupInputBoundary;
import use_case.assisted_mode.setup.AssistedSetupOutputBoundary;

public class AssistedModeSetupController {

    private final AssistedSetupInputBoundary assistedSetupInputBoundary;

    public AssistedModeSetupController(AssistedSetupInputBoundary assistedSetupInputBoundary) {
        this.assistedSetupInputBoundary = assistedSetupInputBoundary;
    }

    /**
     * Set up an assisted blackjack game.
     */
    public void execute() {
        assistedSetupInputBoundary.execute();
    }
}

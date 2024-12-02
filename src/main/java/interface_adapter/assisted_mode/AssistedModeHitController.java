package interface_adapter.assisted_mode;

import use_case.assisted_mode.game.AssistedGameInputDataBoundary;

public class AssistedModeHitController {

    private final AssistedGameInputDataBoundary assistedHitInputDataBoundary;

    public AssistedModeHitController(AssistedGameInputDataBoundary assistedHitInputDataBoundary) {
        this.assistedHitInputDataBoundary = assistedHitInputDataBoundary;
    }

    public void execute() {
        assistedHitInputDataBoundary.execute();
    }
}

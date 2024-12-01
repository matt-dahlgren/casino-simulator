package interface_adapter.assisted_mode.hit;

import use_case.assisted_mode.hit.AssistedHitInputDataBoundary;

public class AssistedModeHitController {

    private final AssistedHitInputDataBoundary assistedHitInputDataBoundary;

    public AssistedModeHitController(AssistedHitInputDataBoundary assistedHitInputDataBoundary) {
        this.assistedHitInputDataBoundary = assistedHitInputDataBoundary;
    }

    public void execute() {
        assistedHitInputDataBoundary.execute();
    }
}

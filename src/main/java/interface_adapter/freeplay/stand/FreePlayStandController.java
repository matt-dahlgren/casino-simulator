package interface_adapter.freeplay.stand;

import use_case.freeplay.stand.FreePlayStandInputBoundary;
import use_case.freeplay.stand.FreePlayStandInteractor;

/**
 * Controller for the FreePlayStand Use Case.
 */
public class FreePlayStandController {

    private final FreePlayStandInputBoundary freePlayStandInteractor;

    public FreePlayStandController(FreePlayStandInputBoundary freePlayStandInteractor) {
        this.freePlayStandInteractor = freePlayStandInteractor;
    }

    public void execute() {
        freePlayStandInteractor.execute();
    }
}

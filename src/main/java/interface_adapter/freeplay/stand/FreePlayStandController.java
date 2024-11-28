package interface_adapter.freeplay.stand;

import use_case.freeplay.stand.FreePlayStandInteractor;

/**
 * Controller for the FreePlayStand Use Case.
 */
public class FreePlayStandController {

    private final FreePlayStandInteractor freePlayStandInteractor;

    public FreePlayStandController(FreePlayStandInteractor freePlayStandInteractor) {
        this.freePlayStandInteractor = freePlayStandInteractor;
    }

    public void execute() {
        freePlayStandInteractor.execute();
    }
}

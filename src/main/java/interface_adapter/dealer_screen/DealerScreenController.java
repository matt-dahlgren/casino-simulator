package interface_adapter.dealer_screen;

import use_case.dealer_screen.DealerScreenInputBoundary;

/**
 * Controller for the FreePlayStand Use Case.
 */
public class DealerScreenController {

    private final DealerScreenInputBoundary freePlayStandInteractor;

    public DealerScreenController(DealerScreenInputBoundary freePlayStandInteractor) {
        this.freePlayStandInteractor = freePlayStandInteractor;
    }

    public void execute() {
        freePlayStandInteractor.execute();
    }

    public void toMainMenu() {
        freePlayStandInteractor.toMainMenu();
    }
}

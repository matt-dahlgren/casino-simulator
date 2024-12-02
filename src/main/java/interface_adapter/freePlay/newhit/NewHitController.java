package interface_adapter.freePlay.newhit;

import use_case.freeplay.newhit.NewHitInputBoundary;

/**
 * The controller for the hit use case.
 */
public class NewHitController {
    private final NewHitInputBoundary hitInteractor;

    public NewHitController(NewHitInputBoundary hitInteractor) {
        this.hitInteractor = hitInteractor;
    }
    /**
     * Executes the Hit use case.
     */
    public void execute() {
        hitInteractor.execute();
    }
}

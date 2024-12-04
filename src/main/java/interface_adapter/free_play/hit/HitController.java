package interface_adapter.freePlay.hit;

import use_case.freeplay.hit.HitInputBoundary;

/**
 * The controller for the Hit Use Case.
 */
public class HitController {

    private final HitInputBoundary hitInteractor;

    public HitController(HitInputBoundary hitInteractor) {
        this.hitInteractor = hitInteractor;
    }

    /**
     * Executes the Hit Use Case by taking the action of hit.
     */
    public void execute() {
        hitInteractor.execute();
    }

    public void switchToHitView() {
        hitInteractor.switchToHitView();
    }

    public void switchToMainMenuView() {
        hitInteractor.switchToMainMenuView();
    }

    public void executeSetuptoHit() {
        hitInteractor.executeSetuptoHit();
    }
}

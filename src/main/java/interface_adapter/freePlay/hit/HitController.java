package interface_adapter.freePlay.hit;

import use_case.freeplay.hit.HitInputBoundary;

public class HitController {
    private final HitInputBoundary hitInteractor;

    public HitController(HitInputBoundary hitInteractor) {
        this.hitInteractor = hitInteractor;
    }

    public void execute() {
        hitInteractor.execute();
    }

    public void switchToHitView() {
        hitInteractor.switchToHitView();
    }

    public void switchToDealerAfterStandView() {
        hitInteractor.switchToDealerAfterStandView();
    }

    public void switchToMainMenuView() {
        hitInteractor.switchToMainMenuView();
    }

    public int getHandVal() {
        return hitInteractor.getHandVal();
    }

    public void execute_setup() {
        hitInteractor.execute_setup();
    }
}

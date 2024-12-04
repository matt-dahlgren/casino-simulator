package interface_adapter.free_play.stand;

import use_case.freeplay.newhit.NewHitInputBoundary;
import use_case.freeplay.stand.StandInputBoundary;

/**
 * The controller for the stand use case.
 */
public class StandController {
    private final StandInputBoundary hitInteractor;

    public StandController(StandInputBoundary hitInteractor) {
        this.hitInteractor = hitInteractor;
    }
    /**
     * Executes the Stand use case.
     */
    public void execute() {
        hitInteractor.execute();
    }

}
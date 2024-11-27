package use_case.freeplay.hit;

/**
 * Use case interactor for hit use case
 */

import use_case.freePlayMode.FreePlayDA;

public class HitUseCaseInteractor {
    private final FreePlayDA freePlayDataAccessObject;
    private final HitOutputBoundary hitPresenter;

    public HitUseCaseInteractor(FreePlayDA freePlayDataAccessObject, HitOutputBoundary hitPresenter) {
        this.freePlayDataAccessObject = freePlayDataAccessObject;
        this.hitPresenter = hitPresenter;
    }

    /**
     * Executes hit
     */
    public void execute() {

    }
}

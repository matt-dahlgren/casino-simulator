package use_case.freeplay.hit;

import use_case.freeplay.setup.SetupOutputData;

/**
 * Output boundary
 */
public interface HitOutputBoundary {

    /**
     * Prepares success view for hit use case
     * @param outputData the output data from interactor
     */
    void prepareSuccessView(HitOutputData outputData);

    /**
     * Fail view aka bust
     * @param message explanation for why bust
     */
    void prepareBustView(String message);

    void prepareExitView(String message);

    void switchToHitView(HitOutputData outputData);

    void switchToMainMenuView();
}

package interface_adapter.freeplay.hit;

import use_case.freeplay.hit.HitOutputBoundary;
import use_case.freeplay.hit.HitOutputData;

public class HitPresenter implements HitOutputBoundary {
    /**
     * Prepares success view for hit use case
     *
     * @param outputData the output data from interactor
     */
    @Override
    public void prepareSuccessView(HitOutputData outputData) {

    }

    /**
     * Fail view aka bust
     *
     * @param message explanation for why bust
     */
    @Override
    public void prepareBustView(String message) {

    }

    /**
     * @param message
     */
    @Override
    public void prepareExitView(String message) {

    }
}

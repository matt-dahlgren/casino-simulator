package interface_adapter.freePlay;

import use_case.freePlayMode.FreePlayInputBoundary;
import use_case.freePlayMode.FreePlayInputData;

import java.util.ArrayList;

/**
 * Controller for free play mode use case.
 */
public class FreePlayController {
    private final FreePlayInputBoundary freePlayInteractor;

    public FreePlayController(FreePlayInputBoundary freePlayInputBoundary) {
        this.freePlayInteractor = freePlayInputBoundary;
    }

    public void execute_hit(ArrayList<Integer> playerHandVals, Boolean playing) {
        final FreePlayInputData freePlayInputData = new FreePlayInputData(playerHandVals, playing);

        freePlayInteractor.hit(freePlayInputData);
    }

    public void execute_stand(ArrayList<Integer> playerHandVals, Boolean playing) {
        final FreePlayInputData freePlayInputData = new FreePlayInputData(playerHandVals, playing);

        freePlayInteractor.stand(freePlayInputData);
    }

}

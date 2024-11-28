package interface_adapter.freeplay;

import use_case.freePlayMode.FreePlayInteractor;

/**
 * Controller for free play mode use case.
 */
public class FreePlayController {
    private final FreePlayInteractor freePlayInteractor;

    public FreePlayController(FreePlayInteractor freePlayInteractor) {
        this.freePlayInteractor = freePlayInteractor;
    }

    public void execute_hit() {
        freePlayInteractor.hit();
    }

    public void execute_stand() {

        freePlayInteractor.stand();
    }

    public void set_up_game() {

        freePlayInteractor.start();
    }

}

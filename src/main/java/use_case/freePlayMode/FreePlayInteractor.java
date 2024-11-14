package use_case.freePlayMode;

import java.util.ArrayList;

/**
 * This is the use case interactor for our group use case: free play mode.
 * This is a game of blackjack with no hints, i.e. does not make use of our probability algorithm.
 */
public class FreePlayInteractor implements FreePlayInputBoundary {
    private final FreePlayDA freePlayDAO;
    private final FreePlayOutputBoundary freePlayPresenter;

    public FreePlayInteractor(FreePlayDA freePlayDAO, FreePlayOutputBoundary freePlayPresenter) {
        this.freePlayDAO = freePlayDAO;
        this.freePlayPresenter = freePlayPresenter;
    }

    /**
     * @param freePlayInputData is the input data from the user
     */
    @Override
    public void hit(FreePlayInputData freePlayInputData) {
        final Boolean hitStatus = freePlayInputData.getHitStatus();

        if (freePlayDAO.canHit()) {
            //get a card
            //update player hand
        }
        else{
            //bust!
        }



    }

    /**
     */
    @Override
    public void stand(FreePlayInputData freePlayInputData) {

    }

}


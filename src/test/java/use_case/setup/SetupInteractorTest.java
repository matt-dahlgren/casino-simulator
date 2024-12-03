package use_case.setup;

import data_access.APIDataAccessObject;
import data_access.GameDataAccessObject;
import org.junit.jupiter.api.Test;
import use_case.freeplay.FreePlayDA;
import use_case.freeplay.GameDataAccess;
import use_case.freeplay.setup.*;


import static org.junit.jupiter.api.Assertions.*;

public class SetupInteractorTest {

    /**
     * Tests whether the dealer's hand's image links are empty or not. Must be 2 cards.
     */
    @Test
    void linkDealerTest() {
        FreePlayDA deckRepository = new APIDataAccessObject();
        GameDataAccess gameRepository = new GameDataAccessObject();

        SetupOutputBoundary successPresenter = new SetupOutputBoundary() {
            @Override
            public void prepareSuccessView(SetupOutputData outputData) {

                boolean allStrings = true;
                for (Object obj : outputData.getDealerHand()) {
                    if (!(obj instanceof String)) {
                        allStrings = false;
                        break;
                    }
                }

                assert allStrings;
                assertEquals(2, outputData.getDealerHand().size());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Failure, unfortunate");
            }
        };

        SetupInputBoundary interactor = new SetupInteractor(gameRepository, deckRepository, successPresenter);
        interactor.execute();
    }

    /**
     * Tests whether the userPlayer's hand's image links are empty or not. Must be 2 cards.
     */
    @Test
    void linkUserPlayerTest() {
        FreePlayDA deckRepository = new APIDataAccessObject();
        GameDataAccess gameRepository = new GameDataAccessObject();

        SetupOutputBoundary successPresenter = new SetupOutputBoundary() {
            @Override
            public void prepareSuccessView(SetupOutputData outputData) {

                boolean allStrings = true;
                for (Object obj : outputData.getUserPlayerHand()) {
                    if (!(obj instanceof String)) {
                        allStrings = false;
                        break;
                    }
                }

                assert allStrings;
                assertEquals(2, outputData.getUserPlayerHand().size());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Failure, unfortunate");
            }
        };

        SetupInputBoundary interactor = new SetupInteractor(gameRepository, deckRepository, successPresenter);
        interactor.execute();
    }

}

//package hit;
//
//import data_access.APIDataAccessObject;
//import data_access.GameDataAccessObject;
//import entities.Card;
//import entities.Dealer;
//import entities.UserPlayer;
//import org.junit.jupiter.api.Test;
//import use_case.freeplay.GameDataAccess;
//import use_case.freeplay.hit.HitOutputBoundary;
//import use_case.freeplay.hit.HitOutputData;
//import use_case.freeplay.hit.HitUseCaseInteractor;
//
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class HitInteractorTest {
//
//    @Test
//    void successTest() {
//        //make a sample player
//        UserPlayer player = new UserPlayer(new ArrayList<Card>());
//
//        //data access init
//        APIDataAccessObject deck = new APIDataAccessObject();
//        GameDataAccess gameRepository = new GameDataAccessObject(player, new Dealer(new ArrayList<Card>()),
//                deck.getDeckID());
//
//        //test presenter
//        HitOutputBoundary successPresenter = new HitOutputBoundary() {
//            @Override
//            public void prepareSuccessView(HitOutputData outputData) {
//                assertFalse(outputData.getPlayerHandImages().isEmpty());
//            }
//
//            @Override
//            public void prepareBustView(String message) {
//                fail("Bust unexpected");
//            }
//
//            @Override
//            public void prepareExitView(String message) {
//
//            }
//        };
//        HitUseCaseInteractor interactor = new HitUseCaseInteractor(deck, gameRepository, successPresenter);
//        interactor.execute();
//
//    }
//}

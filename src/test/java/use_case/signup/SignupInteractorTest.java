package use_case.signup;

import data_access.AccountInfoDAO;
import entities.CommonUserFactory;
import interface_adapter.signup_adapter.SignupPresenter;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class SignupInteractorTest {

    @Test
    void successTest() throws FileNotFoundException {
        SignupUserDataAccessInterface accountRepo = new AccountInfoDAO();

        SignupInputData inputData = new SignupInputData(
                "bob", "bob@gmail.com", "bob1", "bob1");

        SignupOutputBoundary successPresenter = new SignupOutputBoundary() {
            /**
             * Sign up was successful
             * @param outputData username and pass
             */
            @Override
            public void prepareSuccessView(SignupOutputData outputData) {
                assertEquals("bob", outputData.getUsername());
                assertTrue(accountRepo.userExists("bob"));
            }

            /**
             * Failed sign up
             * @param errorMessage string message
             */
            @Override
            public void prepareFailView(String errorMessage) {
                fail("Unexpected failure");
            }
        };
        SignupInputBoundary interactor = new SignupInteractor(accountRepo, successPresenter, new CommonUserFactory());
        interactor.execute(inputData);
    }

    @Test
    void failPasswordMismatchTest() throws FileNotFoundException {
        SignupUserDataAccessInterface accountRepo = new AccountInfoDAO();

        SignupInputData inputData = new SignupInputData("bob", "bob@gmail.com", "bob1", "notbob1");

        SignupOutputBoundary failPresenter = new SignupOutputBoundary() {
            /**
             * @param outputData user and pass
             */
            @Override
            public void prepareSuccessView(SignupOutputData outputData) {
                fail("Unexpected success");

            }

            /**
             * @param errorMessage string
             */
            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Passwords do not match", errorMessage);
            }
        };
        SignupInputBoundary interactor = new SignupInteractor(accountRepo, failPresenter, new CommonUserFactory());
        interactor.execute(inputData);
    }
}

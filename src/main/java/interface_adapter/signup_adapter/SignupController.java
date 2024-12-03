package interface_adapter.signup_adapter;

import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;
import use_case.signup.SignupInteractor;

import java.io.IOException;

/**
 * Controller for sign up use case
 */
public class SignupController {
    private final SignupInputBoundary signupInteractor;

    public SignupController(SignupInputBoundary signupInputBoundary) {
        this.signupInteractor = signupInputBoundary;
    }

    public void execute(String username, String email, String password, String repeatPassword) {
        final SignupInputData signupInputData = new SignupInputData(username, email, password, repeatPassword);

        signupInteractor.execute(signupInputData);
    }

    /**
     * Executes the "switch to LoginView" use case.
     */
    public void switchToLoginView() {
        signupInteractor.switchToLoginView();
    }
}

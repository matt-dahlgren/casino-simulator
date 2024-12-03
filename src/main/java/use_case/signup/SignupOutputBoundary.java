package use_case.signup;

/**
 * Output boundary for sign up
 */
public interface SignupOutputBoundary {

    void prepareSuccessView(SignupOutputData outputData);

    void prepareFailView(String errorMessage);

    void switchToLoginView();
}

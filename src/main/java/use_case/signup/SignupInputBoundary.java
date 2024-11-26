package use_case.signup;

/**
 * Input boundary for sign up use case
 */
public interface SignupInputBoundary {
    /**
     * executes signup use case
     * @param signupInputData is the input data containing account info
     */
    void execute(SignupInputData signupInputData);
}

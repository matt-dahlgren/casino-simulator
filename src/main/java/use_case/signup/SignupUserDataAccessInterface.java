package use_case.signup;

public interface SignupUserDataAccessInterface {
    /**
     * Returns the email of the player currently logged in.
     * @return the current player's email
     */
    String getCurrentEmail();
}

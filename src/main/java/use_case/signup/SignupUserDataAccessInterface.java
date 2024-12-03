package use_case.signup;

import entities.User;

import java.io.IOException;

public interface SignupUserDataAccessInterface {
    /**
     * Adds a new user to the system
     * @param user is the user that needs to be added
     */
    void addUser(User user);

    /**
     * Checks if the username is already linked to an existing account
     * @param username the desired username
     * @return true if user with desired username already exists
     */
    boolean userExists(String username);

    /**
     * Returns the email of the player currently logged in.
     * @return the current player's email
     */
    String getCurrentEmail();
}

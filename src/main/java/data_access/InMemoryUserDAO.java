package data_access;

import java.util.HashMap;
import java.util.Map;

import entities.User;
import use_case.signup.SignupUserDataAccessInterface;

/**
 * Data Access Object that happens in memory.
 */
public class InMemoryUserDAO implements SignupUserDataAccessInterface {

    /**
     * This is a map that stores all users. The username is the key and the user object is the value.
     */
    private final Map<String, User> users = new HashMap<>();

    private String currentUser;

    /**
     * Set the currentUser
     * @param username is the username of the current user
     */
    public void setCurrentUser(String username) {
        this.currentUser = username;
    }

    /**
     * Gets the username of the currentUser
     * @return username
     */
    public String getCurrentUser() {
        return currentUser;
    }

    // the following methods deal with the map of users

    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }

    public User getUser(String username) {
        return users.get(username);
    }

    public boolean userExists(String username) {
        return users.containsKey(username);
    }

    public void updateUser(User user) {
        // Replace the old entry with the new user object
        users.put(user.getUsername(), user);
    }

}
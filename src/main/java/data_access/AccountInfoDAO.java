package data_access;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import entities.CommonUser;
import entities.User;
import use_case.signup.SignupUserDataAccessInterface;

/**
 * Data Access Object that happens in memory.
 */
public class AccountInfoDAO implements SignupUserDataAccessInterface {
    // This is a map that stores all users. The username is the key and the user object is the value.
    private final Map<String, User> users = new HashMap<>();

    // The data path and file storing usernames, passwords, and emails
    private final String dataPath = "src/main/java/user_data/Account_Info";
    private final File dataFile = new File(dataPath);

    // The current user logged in
    private String currentUser;

    public AccountInfoDAO() throws FileNotFoundException {
        Scanner fileReader = new Scanner(dataFile);
        User temp;
        String[] userData;

        // Reads all user data from the file
        while (fileReader.hasNextLine()) {
            userData = fileReader.nextLine().split(",");
            temp = new CommonUser(userData[0], userData[1], userData[2]);
            users.put(temp.getUsername(), temp);
        }

        fileReader.close();
    }

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

    /**
     * Adds a user
     * @param user the user to be added
     */
    public void addUser(User user) {
        // Adds the user to the user map
        users.put(user.getUsername(), user);

        // Adds a user to the user file
        try {
            FileWriter writer = new FileWriter(dataPath, true);
            writer.write(user.getUsername() + "," + user.getPassword() + "," + user.getEmail() + "\n");
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public User getUser(String username) {
        return users.get(username);
    }

    public boolean userExists(String username) {
        return users.containsKey(username);
    }

    /**
     * Updates information about a user
     * @param user the user to be updated
     */
    public void updateUser(User user) throws IOException {
        FileWriter writer = new FileWriter(dataPath);

        // Updates the user in the user map
        users.put(user.getUsername(), user);

        // Updates the user in the use file
        for (User u : users.values()) {
            writer.write(u.getUsername() + "," + u.getPassword() + "," + u.getEmail() + "\n");
        }

        writer.close();
    }
}
package entities;

/**
 * Interface that represents a user.
 */
public interface User {
    /**
     * Returns the username of the user.
     * @return username
     */
    String getUsername();

    /**
     * Return the password of the user
     * @return password
     */
    String getPassword();

    /**
     * Return the email of the given user.
     * @return email
     */
    String getEmail();

    String getName();
}

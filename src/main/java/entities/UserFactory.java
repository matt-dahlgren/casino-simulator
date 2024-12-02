package entities;

/**
 * User factory interface.
 */
public interface UserFactory {
    /**
     * Create User.
     * @param email string email
     * @param password password string
     * @param username username string
     * @return user
     */
    User create(String username, String email, String password);
}

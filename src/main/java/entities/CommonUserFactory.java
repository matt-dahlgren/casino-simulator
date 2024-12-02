package entities;

/**
 * Common user factory class.
 */
public class CommonUserFactory implements UserFactory {

    /**
     * Create User.
     *
     * @param username string
     * @param email string
     * @param password string
     */
    @Override
    public User create(String username, String email, String password) {
        return new CommonUser(username, email, password);
    }
}

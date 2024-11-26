package entities;

public class CommonUserFactory implements UserFactory {

    /**
     * Create User
     *
     * @param username
     * @param email
     * @param password
     */
    @Override
    public User create(String username, String email, String password) {
        return new CommonUser(username, email, password);
    }
}

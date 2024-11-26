package entities;

public interface UserFactory {
    /**
     * Create User
     */
    User create(String username, String email, String password);
}

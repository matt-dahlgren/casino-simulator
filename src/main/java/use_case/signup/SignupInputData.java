package use_case.signup;

/**
 * Input data for Signup use case
 */
public class SignupInputData {
    private final String username;
    private final String email;
    private final String password;
    public final String repeatPassword;

    public SignupInputData(String username, String email, String password, String repeatPassword) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.repeatPassword = repeatPassword;
    }

    String getUsername() { return username; }
    String getEmail() { return email; }
    String getPassword() { return password; }
    String getRepeatPassword() { return repeatPassword; }
}

package entities;

public class CommonUser implements User {

    private final String username;
    private final String password;
    private final String email;

    public CommonUser(String username, String password, String email) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    @Override
    public String getUsername() { return username; }

    @Override
    public String getPassword() { return password; }

    @Override
    public String getEmail() { return email; }

    @Override
    public String getName() {
        return "";
    }

}

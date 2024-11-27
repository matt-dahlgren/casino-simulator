package use_case.logout;

public class LogoutInputData {
    private final String user;

    public LogoutInputData(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }
}

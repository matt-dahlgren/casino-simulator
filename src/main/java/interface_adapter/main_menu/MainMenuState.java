package interface_adapter.main_menu;

import app.Main;

/**
 * The state for the Main Menu page's View Model.
 *
 */
public class MainMenuState {
    private String username = "";
    private String password = "";
    private String passwordError;

    public MainMenuState(MainMenuState copy) {
        username = copy.username;
        password = copy.password;
        passwordError = copy.passwordError;
    }

    public MainMenuState() {}

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

    public void setPassword(String password) {this.password = password;}

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;}

    public String getPassword() {return password;}

}

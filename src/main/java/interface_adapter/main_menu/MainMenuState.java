package interface_adapter.main_menu;

import app.Main;

/**
 * The state for the Main Menu page's View Model.
 *
 */
public class MainMenuState {
    private String username = "";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "MainMenuState";
    }

}

package interface_adapter.main_menu;

import app.Main;

/**
 * The state for the Main Menu page's View Model.
 *
 */
public class MainMenuState {
    private String username = "";

    public MainMenuState(MainMenuState copy) {
        username = copy.username;
    }

    public MainMenuState() {

    }

    public String getUsername() {
        return username;
    }

    public String setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "MainMenuState";
    }

}

package interface_adapter.main_menu;

import interface_adapter.ViewModel;

/**
 * The View Model for the Main Menu View.
 */
public class MainMenuViewModel extends ViewModel<MainMenuState> {

    public MainMenuViewModel() {
        super("main menu");
        setState(new MainMenuState());
    }
}

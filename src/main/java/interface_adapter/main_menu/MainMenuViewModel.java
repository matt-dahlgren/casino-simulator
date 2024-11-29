package interface_adapter.main_menu;

import interface_adapter.ViewModel;

public class MainMenuViewModel extends ViewModel<MainMenuState> {

    public MainMenuViewModel() {
        super("main menu");
        setState(new MainMenuState());
    }
}

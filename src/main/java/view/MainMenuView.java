package view;


import interface_adapter.assisted_mode.AssistedModeController;
import interface_adapter.assisted_mode.setup.AssistedModeSetupController;
import interface_adapter.free_play.setup.SetupController;
import interface_adapter.learn_mode.LearnModeController;
import interface_adapter.logout_adapter.LogoutController;
import interface_adapter.main_menu.MainMenuState;
import interface_adapter.main_menu.MainMenuViewModel;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


/**
 * The View for the user when they are logged in and in the main menu,
 */
public class MainMenuView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "main menu";
    private final MainMenuViewModel mainMenuViewModel;


    // Locate all other controllers accessible from Main Menu as attributes
    private AssistedModeSetupController assistedSetupController;
    private SetupController setupController;
    private LearnModeController learnController;
    private LogoutController logoutController;


    private final JLabel username;


    private final JButton logOut;
    private final JButton assisted;
    private final JButton freePlay;
    private final JButton learn;


    public MainMenuView(MainMenuViewModel mainMenuViewModel) {
        this.mainMenuViewModel = mainMenuViewModel;
        this.mainMenuViewModel.addPropertyChangeListener(this);


        //Title
        final JLabel title = new JLabel("BLACKJACK");
        title.setAlignmentX(CENTER_ALIGNMENT);


        //Username Info
        final JLabel usernameInfo = new JLabel("Logged in as:");
        username = new JLabel();


        //Buttons
        final JPanel buttons = new JPanel();
        logOut = new JButton("☰");
        assisted = new JButton("Assisted Mode");
        freePlay = new JButton("Free-Play Mode");
        learn = new JButton("Learn Mode");


        logOut.addActionListener(this);
        assisted.addActionListener(this);
        freePlay.addActionListener(this);
        learn.addActionListener(this);


        //Action Listeners that make buttons functional
        logOut.addActionListener(
                evt -> {
                    if (evt.getSource().equals(logOut)) {
                        final MainMenuState currentState = mainMenuViewModel.getState();
                        // Get from pooja
                        this.logoutController.execute(username.getText());
                    }
                }
        );


        assisted.addActionListener(
                evt -> {
                    if (evt.getSource().equals(assisted)) {
                        // Get from matt
                        this.assistedSetupController.execute();
                    }
                }
        );


        freePlay.addActionListener(
                evt -> {
                    if (evt.getSource().equals(freePlay)) {
                        this.setupController.execute();
                    }
                }
        );


        learn.addActionListener(
                evt -> {
                    if (evt.getSource().equals(learn)) {
                        this.learnController.switchToObjectiveView();
                    }
                }
        );




        buttons.add(assisted);
        buttons.add(freePlay);
        buttons.add(learn);


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(usernameInfo);
        this.add(username);
        this.add(buttons);
        this.add(logOut);


    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final MainMenuState state = (MainMenuState) evt.getNewValue();
            username.setText(state.getUsername());
        }
    }


    public void setAssistedController(AssistedModeSetupController assistedController) {
        this.assistedSetupController = assistedController;
    }


    public void setSetupController(SetupController setupController) {
        this.setupController = setupController;
    }


    public void setLogoutController(LogoutController logoutController) {
        this.logoutController = logoutController;
    }


    public void setLearnController(LearnModeController learnController) {
        this.learnController = learnController;
    }


    public String getViewName() {
        return "main menu";
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(this, "Cancel not implemented yet.");
    }

    public void setAssistedModeSetupController(AssistedModeSetupController controller) {
        this.assistedSetupController = controller;
    }
}

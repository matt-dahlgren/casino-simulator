package view;

import interface_adapter.freeplay.setup.SetupController;
import interface_adapter.learn_mode.LearnModeController;
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

    //TODO Locate all other controllers accessible from Main Menu as attributes
//    private AssistedController assistedController;
    private SetupController setupController;
    private LearnModeController learnController;
//    private LogoutController logoutController;

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
        //TODO Change the .execute() parameters based on the requirements (none I think)
//        logOut.addActionListener(
//                evt -> {
//                    if (evt.getSource().equals(logOut)) {
//                        final MainMenuState currentState = mainMenuViewModel.getState();
//                        this.logoutController.execute(currentState.getUsername());
//                    }
//                }
//        );
//
//        assisted.addActionListener(
//                evt -> {
//                    if (evt.getSource().equals(assisted)) {
//                        this.assistedController.execute();
//                    }
//                }
//        );

        freePlay.addActionListener(
                evt -> {
                    if (evt.getSource().equals(freePlay)) {
                        this.setupController.execute_setup();
                    }
                }
        );
//
//        learn.addActionListener(
//                evt -> {
//                    if (evt.getSource().equals(learn)) {
//                        this.learnController.execute();
//                    }
//                }
//        );


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

    //TODO Include all relevant controllers for the classes
//    public void setAssistedController(AssistedController assistedController) {
//        this.assistedController = assistedController;
//    }

    public void setSetupController(SetupController setupController) {
        this.setupController = setupController;
    }

    @Override
    /**
     * React to a button click that results in evt.
     * @param evt the ActionEvent to react to
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    public String getViewName() {
        return viewName;
    }


//    public void setLogoutController(LogoutController logoutController) {
//        this.logoutController = logoutController;
//    }
//
//    public void setLearnController(LearnController learncontroller) {
//        this.learnController = learncontroller;
//    }
}

package view;

import interface_adapter.login_adapter.LoginController;
import interface_adapter.login_adapter.LoginState;
import interface_adapter.login_adapter.LoginViewModel;
import interface_adapter.main_menu.MainMenuViewModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "log in";
    private final LoginViewModel loginViewModel;
    private final MainMenuViewModel mainMenuViewModel;

    private final JTextField usernameInputField = new JTextField(15);
    private final JLabel usernameErrorLabel = new JLabel();

    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JLabel passwordErrorLabel = new JLabel();

    private final JButton logIn;
    private final JButton exit;

    private LoginController controller;

    public LoginView(LoginViewModel loginViewModel, MainMenuViewModel mainMenuViewModel) {
        this.loginViewModel = loginViewModel;
        this.mainMenuViewModel = mainMenuViewModel;
        this.loginViewModel.addPropertyChangeListener(this);
        this.mainMenuViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Blackjack");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel usernameInfo = new LabelTextPanel(new JLabel("Username"), usernameInputField);
        final LabelTextPanel passwordInfo = new LabelTextPanel(new JLabel("Password"), passwordInputField);

        final JPanel buttons = new JPanel();
        logIn = new JButton("Log In");
        buttons.add(logIn);
        exit = new JButton("Exit");
        buttons.add(exit);

        logIn.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(logIn)) {
                            final LoginState currentState = loginViewModel.getState();

                            controller.execute(currentState.getUsername(), currentState.getPassword());
                            controller.switchToMainMenuView();

                        }
                    }
                }
        );
        exit.addActionListener(this);

        usernameInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final LoginState currentState = loginViewModel.getState();
                currentState.setUsername(usernameInputField.getText());
                loginViewModel.setState(currentState);
            }

            /**
             * Gives notification that there was an insert into the document.  The
             * range given by the DocumentEvent bounds the freshly inserted region.
             *
             * @param e the document event
             */
            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            /**
             * Gives notification that a portion of the document has been
             * removed.  The range is given in terms of what the view last
             * saw (that is, before updating sticky positions).
             *
             * @param e the document event
             */
            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            /**
             * Gives notification that an attribute or set of attributes changed.
             *
             * @param e the document event
             */
            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        passwordInputField.getDocument().addDocumentListener(new DocumentListener() {
            /**
             * Gives notification that there was an insert into the document.  The
             * range given by the DocumentEvent bounds the freshly inserted region.
             *
             * @param e the document event
             */
            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            /**
             * Gives notification that a portion of the document has been
             * removed.  The range is given in terms of what the view last
             * saw (that is, before updating sticky positions).
             *
             * @param e the document event
             */
            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            /**
             * Gives notification that an attribute or set of attributes changed.
             *
             * @param e the document event
             */
            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            private void documentListenerHelper() {
                final LoginState currentState = loginViewModel.getState();
                currentState.setPassword(new String(passwordInputField.getPassword()));
                loginViewModel.setState(currentState);
            }


        });

        this.add(title);
        this.add(usernameInfo);
        this.add(usernameErrorLabel);
        this.add(passwordInfo);
        this.add(buttons);
    }


    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click " + e.getActionCommand());
    }

    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final LoginState state = (LoginState) evt.getNewValue();
        setFields(state);
        usernameErrorLabel.setText(state.getLoginError());
    }

    private void setFields(LoginState state) {
        usernameInputField.setText(state.getUsername());
        passwordInputField.setText(state.getPassword());
    }

    public String getViewName() {
        return viewName;
    }

    public void setLoginController(LoginController loginController) {
        this.controller = loginController;
    }

}

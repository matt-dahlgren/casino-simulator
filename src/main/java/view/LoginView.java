package view;

import interface_adapter.login_adapter.LoginController;
import interface_adapter.login_adapter.LoginState;
import interface_adapter.login_adapter.LoginViewModel;

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

    private final JTextField usernameInputField = new JTextField(15);
    private final JLabel usernameErrorLabel = new JLabel();

    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JLabel passwordErrorLabel = new JLabel();

    private final JButton logIn;
    private final JButton exit;

    private LoginController controller;

    public LoginView(LoginViewModel loginViewModel) {
        this.loginViewModel = loginViewModel;
        this.loginViewModel.addPropertyChangeListener(this);

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

                        }
                    }
                }
        );
        exit.addActionListener(this);

        usernameInputField.getDocument().addDocumentListener(new DocumentListener() {
            /**
             * Gives notification that there was an insert into the document.  The
             * range given by the DocumentEvent bounds the freshly inserted region.
             *
             * @param e the document event
             */
            @Override
            public void insertUpdate(DocumentEvent e) {

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

            }

            /**
             * Gives notification that an attribute or set of attributes changed.
             *
             * @param e the document event
             */
            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        })

        this.logIn = logIn;
        this.exit = exit;
    }


    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}

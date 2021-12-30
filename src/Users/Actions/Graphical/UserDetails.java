package Users.Actions.Graphical;

import LoginSystem.LoginSystem;

import javax.swing.*;
import java.awt.*;

public abstract class UserDetails extends GUIAction{

    protected JPanel mainPanel;
    protected JLabel noteLabel;
    protected JLabel usernameLabel;
    protected JLabel usernameField;
    protected JTextField fullnameField;
    protected JLabel fullnameLabel;
    protected JPasswordField passwordField;
    protected JLabel passwordLabel;
    protected JLabel roleLabel;
    protected JLabel roleField;
    protected JButton updateButton;
    protected JScrollPane scrollPane;

    @Override
    protected String getName() {
        return "User Profile";
    }

    @Override
    protected void invoke() {
        actionArea.setLayout(new BoxLayout(actionArea, BoxLayout.Y_AXIS));

        scrollPane = new JScrollPane();
        mainPanel = new JPanel();
        noteLabel = new JLabel();
        usernameLabel = new JLabel();
        usernameField = new JLabel();
        fullnameField = new JTextField();
        fullnameLabel = new JLabel();
        passwordField = new JPasswordField();
        passwordLabel = new JLabel();
        roleLabel = new JLabel();
        roleField = new JLabel();
        updateButton = new JButton();

        scrollPane.add(mainPanel);
        mainPanel.setLayout(null);

        //---- noteLabel ----
        noteLabel.setText("Profile Details");
        noteLabel.setFont(noteLabel.getFont().deriveFont(noteLabel.getFont().getStyle() | Font.BOLD, noteLabel.getFont().getSize() + 5f));
        mainPanel.add(noteLabel);
        noteLabel.setBounds(20, 0, 175, 40);

        //---- usernameLabel ----
        usernameLabel.setText("Username");
        mainPanel.add(usernameLabel);
        usernameLabel.setBounds(20, 85, 100, 20);
        mainPanel.add(usernameField);
        usernameField.setText(parentUser.getUsername());
        usernameField.setBounds(140, 85, 205, 20);

        //---- fullnameLabel ----
        fullnameLabel.setText("Full name");
        mainPanel.add(fullnameLabel);
        fullnameLabel.setBounds(20, 120, 100, 20);
        mainPanel.add(fullnameField);
        fullnameField.setText(parentUser.getFullName());
        fullnameField.setBounds(140, 120, 205, 20);

        //---- passwordLabel ----
        passwordLabel.setText("Password");
        mainPanel.add(passwordLabel);
        passwordLabel.setBounds(20, 155, 100, 20);
        mainPanel.add(passwordField);
        passwordField.setText(parentUser.getPassword());
        passwordField.setBounds(140, 155, 205, 20);

        //---- roleLabel ----
        roleLabel.setText("Role");
        mainPanel.add(roleLabel);
        roleLabel.setBounds(20, 190, 100, 20);

        //---- roleField ----
        roleField.setText(parentUser.getUserType());
        mainPanel.add(roleField);
        roleField.setBounds(145, 190, 200, 20);

        //---- updateButton ----
        updateButton.setText("Update");
        mainPanel.add(updateButton);
        updateButton.setBounds(20, 40, 78, 20);
        updateButton.addActionListener(e -> {
            if(JOptionPane.showConfirmDialog(mainPanel, "Are you sure you want to update your profile?",
                    "Confirm changes", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                changePassword();
                changeFullName();
                changeOthers();
            }
            refresh();
        });

        scrollPane.setViewportView(mainPanel);
        scrollPane.setVisible(true);

        actionArea.add(scrollPane);
        actionArea.add(Box.createRigidArea(new Dimension(0, 5)));
    }

    protected void setPanelSize() {
        Dimension preferredSize = new Dimension();
        for (int i = 0; i < mainPanel.getComponentCount(); i++) {
            Rectangle bounds = mainPanel.getComponent(i).getBounds();
            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
        }
        Insets insets = mainPanel.getInsets();
        preferredSize.width += insets.right;
        preferredSize.height += insets.bottom;
        mainPanel.setMinimumSize(preferredSize);
        mainPanel.setPreferredSize(preferredSize);
    }

    protected void changeFullName() {
        parentUser.setFullName(fullnameField.getText().length()!=0 ? fullnameField.getText() : parentUser.getFullName());
    }

    protected void changePassword() {
        if (LoginSystem.checkPassword(new String(passwordField.getPassword())))
            JOptionPane.showMessageDialog(mainPanel, "Password should contain at least one digit and have a length between 6 and 20 characters." +
                    " Please try entering another one.", "Warning", JOptionPane.WARNING_MESSAGE);
        else
            parentUser.setPassword(new String(passwordField.getPassword()));
    }

    protected abstract void changeOthers();
}

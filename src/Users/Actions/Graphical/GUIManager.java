package Users.Actions.Graphical;

import Users.User;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.Objects;

public class GUIManager implements Serializable {

    protected User parentUser;
    protected GUIAction[] guiActions;
    JFrame userGUI;

    public GUIManager(User parentUser) {
        this.parentUser = parentUser;
    }

    public void showInterface(){
        userGUI = new UserFrame("My Booking - " + parentUser.getFullName());

        // Segmenting the JFrame into usable panels
        userGUI.setLayout(new BorderLayout());

        JPanel topArea = new JPanel();
        userGUI.add(topArea, BorderLayout.BEFORE_FIRST_LINE);
        topArea.setBorder(BorderFactory.createTitledBorder(parentUser.getFullName()));

        JPanel buttonArea = new JPanel();
        userGUI.add(buttonArea, BorderLayout.LINE_START);
        buttonArea.setBorder(BorderFactory.createTitledBorder("Actions"));
        buttonArea.setLayout(new BoxLayout(buttonArea, BoxLayout.Y_AXIS));

        JPanel actionArea = new JPanel();
        userGUI.add(actionArea, BorderLayout.CENTER);
        actionArea.setBorder(BorderFactory.createTitledBorder("Welcome"));

        JPanel messageArea = new JPanel();
        userGUI.add(messageArea, BorderLayout.LINE_END);
        messageArea.setBorder(BorderFactory.createTitledBorder("Messages"));


        for (var action : this.guiActions){
            buttonArea.add(action.makeButton());
            action.setActionArea(actionArea, buttonArea);
            buttonArea.add(Box.createRigidArea(new Dimension(0, 5)));
        }


        actionArea.add(new JLabel("Welcome " + parentUser.getFullName() + "!"));

        messageArea.add(new Button("Test0"));
        messageArea.add(new Button("Test1"));
        messageArea.add(new Button("Test2"));
        messageArea.add(new Button("Test3"));

        userGUI.setVisible(true);
    }

}

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
        userGUI = new JFrame("My Booking");
        userGUI.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        int sizeFactor = 2;
        userGUI.setSize((int)screensize.getWidth()/sizeFactor, (int)screensize.getHeight()/sizeFactor);
        userGUI.setResizable(true);

        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Misc/images/logoIcon.png")));
        userGUI.setIconImage(imageIcon.getImage());



        userGUI.setLocationRelativeTo(null);
        userGUI.setLayout(new BorderLayout());



        JPanel topArea = new JPanel();
        userGUI.add(topArea, BorderLayout.BEFORE_FIRST_LINE);
        topArea.setBorder(BorderFactory.createTitledBorder("Top area"));


        JPanel buttonArea = new JPanel();
        userGUI.add(buttonArea, BorderLayout.LINE_START);
        buttonArea.setBorder(BorderFactory.createTitledBorder("Actions"));
        buttonArea.setLayout(new BoxLayout(buttonArea, BoxLayout.Y_AXIS));

        JPanel actionArea = new JPanel();
        userGUI.add(actionArea, BorderLayout.CENTER);
        actionArea.setBorder(BorderFactory.createTitledBorder("Main"));

        JPanel messageArea = new JPanel();
        userGUI.add(messageArea, BorderLayout.LINE_END);
        messageArea.setBorder(BorderFactory.createTitledBorder("Messages"));


        for (var action : this.guiActions){
            buttonArea.add(action.makeButton());
            action.setActionArea(actionArea);
            buttonArea.add(Box.createRigidArea(new Dimension(0, 5)));
        }


        actionArea.add(new Button("Test0"));
        actionArea.add(new Button("Test1"));
        actionArea.add(new Button("Test2"));
        actionArea.add(new Button("Test3"));

        messageArea.add(new Button("Test0"));
        messageArea.add(new Button("Test1"));
        messageArea.add(new Button("Test2"));
        messageArea.add(new Button("Test3"));

        userGUI.setVisible(true);
    }

}

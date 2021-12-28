package Users.Actions.Graphical;

import Users.User;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;


public abstract class GUIAction implements Serializable {
    protected abstract String getName();
    protected JPanel actionArea;
    protected JPanel buttonArea;
    protected User parentUser;

    void setActionArea(JPanel actionArea, JPanel buttonArea){
        this.actionArea = actionArea;
        this.buttonArea = buttonArea;
    }

    void setParentUser(User user){
        this.parentUser = user;
    }

    //Generate the action button to be placed in the button area
    JButton makeButton(){
        JButton btn = new JButton(this.getName());
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setFocusable(false);
        btn.addActionListener(e -> {
            actionArea.removeAll();
            actionArea.revalidate();
            actionArea.repaint();
            actionArea.setBorder(BorderFactory.createTitledBorder(getName()));
            invoke();
            for (Component component : buttonArea.getComponents()){
                if (component instanceof JButton){
                    component.setEnabled(true);
                }
            }
            ((JButton)e.getSource()).setEnabled(false);
        });
        return btn;
    }

    //Preset error label for parameter issues with the actions
    protected JLabel error(String errorText){
        JLabel errorLabel = new JLabel(errorText);
        errorLabel.setForeground(Color.red);
        return errorLabel;
    }

    //Invoke method, gets called whenever the corresponding button is pressed
    protected abstract void invoke();

}

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

    protected JLabel error(String errorText){
        JLabel errorLabel = new JLabel(errorText);
        errorLabel.setForeground(Color.red);
        return errorLabel;
    }

    protected abstract void invoke();

}

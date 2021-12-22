package Users.Actions.Graphical;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;


public abstract class GUIAction implements Serializable {
    protected abstract String getName();
    protected JPanel actionArea;
    protected JPanel buttonArea;

    void setActionArea(JPanel actionArea, JPanel buttonArea){
        this.actionArea = actionArea;
        this.buttonArea = buttonArea;
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

    protected abstract void invoke();

}

package Users.Actions.Graphical;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;


public abstract class GUIAction implements Serializable {
    protected abstract String getName();
    protected JPanel actionArea;

    void setActionArea(JPanel actionArea){
        this.actionArea = actionArea;
    }

    JButton makeButton(){
        JButton btn = new JButton(this.getName());
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.addActionListener(e -> {
            actionArea.removeAll();
            actionArea.revalidate();
            actionArea.repaint();
            actionArea.setBorder(BorderFactory.createTitledBorder(getName()));
            invoke();
//                ((JButton)e.getSource()).setEnabled(false);
        });
        return btn;
    }

    protected abstract void invoke();

}

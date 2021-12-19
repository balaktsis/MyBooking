package Users.Actions.Graphical;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class GUIAction {
    protected abstract String getName();
    private JPanel actionArea;

    void setActionArea(JPanel actionArea){
        this.actionArea = actionArea;
    }

    JButton makeButton(){
        JButton btn = new JButton(this.getName());
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                invoke(e);
            }
        });
        return btn;
    }

    void invoke(ActionEvent e){
        actionArea.removeAll();
        JButton test = new JButton(this.getName());
        actionArea.setLayout(new FlowLayout());
        actionArea.add(test);
    }

}

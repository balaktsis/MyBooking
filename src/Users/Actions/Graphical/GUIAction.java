package Users.Actions.Graphical;

import javax.swing.*;
import java.awt.*;

public abstract class GUIAction {
    protected abstract String getName();

//    JPanel makeButton(){
//        JPanel panel = new JPanel();
//        JButton btn = new JButton(this.getName());
//        panel.add(btn);
//        panel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
//        return panel;
//    }

    JButton makeButton(){
        JButton btn = new JButton(this.getName());
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        return btn;
    }

    void invoke(){

    }
}

package Misc;

import javax.swing.*;

/**
 * HTML JLabel
 * Meant to make adding html tags faster.
 */
public class HLabel extends JLabel {

    public HLabel(String s, int i){
        super(s, i);
    }

    public HLabel(String s) {
        super(s);
    }

    @Override
    public void setText(String text){
        super.setText("<html>" + text + "</html>");
    }

}

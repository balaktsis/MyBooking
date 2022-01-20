package Misc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class HintedJTextField extends JTextField {

    final String hint;

    public HintedJTextField(String inputHint){
        hint = inputHint;
        setText(hint);

        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (getRawText().equals(hint)){
                    setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (getRawText().isBlank()){
                    setText(hint);
                }
            }
        });

        //fix clipping of the characters
        setPreferredSize(new Dimension((int) getPreferredSize().getWidth() + 2, (int) getPreferredSize().getHeight()));

    }

    public String getRawText(){
        return super.getText();
    }

    @Override
    public String getText() {
        String txt = super.getText();
        if (txt.equals(hint)){
            return "";
        }
        return txt;
    }
}

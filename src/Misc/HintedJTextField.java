package Misc;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTextFieldUI;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class HintedJTextField extends JTextField {

    String hint;

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

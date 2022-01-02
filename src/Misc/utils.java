package Misc;

import javax.swing.*;

public class utils {
    public static void dateSanitize(JTextField day, JTextField month, JTextField year) {
        if (day.getText().length()==1){
            day.setText("0" + day.getText());
        }
        if (month.getText().length()==1){
            month.setText("0" + month.getText());
        }
        if (year.getText().length()==1){
            year.setText("0" + year.getText());
        }
    }
}

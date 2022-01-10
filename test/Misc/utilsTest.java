package Misc;


import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.*;

public class utilsTest {

    @Before
    public void setUp() {

    }

    @Test
    public void testSanitizeDates(){
        JTextField day = new JTextField("01");
        JTextField month = new JTextField("11");
        JTextField year = new JTextField("2011");
        JTextField day2 = new JTextField("01");
        JTextField month2 = new JTextField("11");
        JTextField year2 = new JTextField("2011");

        Utils.dateSanitize(day2, month2, year2);
        assertEquals(day.getText(), day2.getText());
        assertEquals(month.getText(), month2.getText());
        assertEquals(year.getText(), year2.getText());

        day = new JTextField("1");
        month = new JTextField("1");
        year = new JTextField("11");

        Utils.dateSanitize(day, month, year);
        assertEquals("01", day.getText());
        assertEquals("01", month.getText());
        assertEquals("2011", year.getText());

    }
}
package Users.Actions.Graphical;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Objects;

public class UserFrame extends JFrame {


    public UserFrame(String window_title) {
        super(window_title);

        // On window close, run the app-system exit handler, to save unsaved data
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        WindowListener exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showOptionDialog(
                        null, "Are you sure to close the application?",
                        "Exit Confirmation", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (confirm == 0) {
                    //TODO run save in AppSystem
                    System.exit(0);
                }}};
        addWindowListener(exitListener);


        // Use logo as the app image
        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Misc/images/logoIcon.png")));
        setIconImage(imageIcon.getImage());

        // Set appropriate size
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        int sizeFactor = 2;
        setSize((int)screensize.getWidth()/sizeFactor, (int)screensize.getHeight()/sizeFactor);
        setResizable(true);
        setLocationRelativeTo(null);
        Dimension minSize = new Dimension((int)screensize.getWidth()/3, (int)screensize.getHeight()/3);
        setMinimumSize(minSize);

    }




}

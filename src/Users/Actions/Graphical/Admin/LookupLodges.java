package Users.Actions.Graphical.Admin;

import Lodges.Lodge;
import Misc.HintedJTextField;
import Misc.Storage;
import Users.Actions.Graphical.GUIAction;
import Users.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import java.util.ArrayList;

public class LookupLodges extends GUIAction {
    @Override
    protected String getName() {
        return "Look up Lodges";
    }

    @Override
    protected void invoke() {
        actionArea.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        actionArea.add(topPanel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));

        actionArea.add(scrollPane, BorderLayout.CENTER);

        topPanel.setLayout(new FlowLayout());

        topPanel.add(new JLabel("Landlord:"));
        HintedJTextField lrdUsername = new HintedJTextField("Username");
        topPanel.add(lrdUsername);
        JButton search = new JButton("Search");
        JButton searchAll = new JButton("Show All");
        search.setFocusable(false);
        searchAll.setFocusable(false);
        topPanel.add(search);
        topPanel.add(searchAll);

        search.addActionListener(e -> {
            mainPanel.removeAll();
            mainPanel.revalidate();
            mainPanel.repaint();

            if (User.getUserFromUsername(lrdUsername.getText()) == null){
                mainPanel.add(error("No user found under that username!"));
                return;
            }

            ArrayList<Lodge> lodges = new ArrayList<>();
            for (Lodge lodge : Storage.getLodges()){
                if (lodge.getLandlord().getUsername().equalsIgnoreCase(lrdUsername.getText())){
                    lodges.add(lodge);
                }
            }

            if (lodges.size() == 0){
                mainPanel.add(error("No lodges were found to be submitted by that username!"));
                return;
            }

            for (Lodge lodge : lodges){
                mainPanel.add(lodge.toJPanel());
            }

        });

        searchAll.addActionListener(e -> {
            mainPanel.removeAll();
            mainPanel.revalidate();
            mainPanel.repaint();

            if (Storage.getLodges().size() == 0){
                mainPanel.add(error("No lodges were found to be submitted by that username!"));
                return;
            }

            for (Lodge lodge : Storage.getLodges()){
                mainPanel.add(lodge.toJPanel());
            }

        });

    }
}

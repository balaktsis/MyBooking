package Users.Actions.Graphical.Admin;

import Lodges.Lodge;
import Misc.HintedJTextField;
import Misc.Storage;
import Users.Actions.Graphical.GUIAction;
import Users.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        actionArea.add(mainPanel, BorderLayout.CENTER);

        topPanel.setLayout(new FlowLayout());

        topPanel.add(new JLabel("Landlord:"));
        HintedJTextField lrdUsername = new HintedJTextField("Username");
        topPanel.add(lrdUsername);
        JButton search = new JButton("Search");
        JButton searchall = new JButton("Show All");
        search.setFocusable(false);
        searchall.setFocusable(false);
        topPanel.add(search);
        topPanel.add(searchall);

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

        searchall.addActionListener(e -> {
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

package Users.Actions.Graphical.Admin;

import Users.Actions.Graphical.GUIAction;
import Users.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ApproveUser extends GUIAction {
    @Override
    protected String getName() {
        return "Approve Users";
    }

    @Override
    protected void invoke() {

        actionArea.setLayout(new BoxLayout(actionArea, BoxLayout.Y_AXIS));

        for (User user : User.getUsersWithApproval(false)){
            JPanel area = new JPanel();
            area.setLayout(new FlowLayout());
//            area.setLayout(new GridLayout(0, 2));
            area.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
            area.add(user.toJPanel());

            JButton approveButton = new JButton("Approve");
            approveButton.addActionListener(e -> {
                user.setApprovalStatus(true);
                actionArea.removeAll();
                actionArea.revalidate();
                actionArea.repaint();
                invoke();

            });
            area.add(approveButton);

            actionArea.add(area);
            actionArea.add(Box.createRigidArea(new Dimension(0, 5)));
        }

    }
}

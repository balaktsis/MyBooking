package Users.Actions.Graphical.Landlord;

import Users.Actions.Graphical.UserDetails;
import Users.Landlord;

import javax.swing.*;

/**
 * This is a child of class UserDetails, adding to the user detail
 * panel some extra information fields that only apply to Landlords.
 * @author Christos Balaktsis
 */

public class ShowDetails extends UserDetails {

    private final JTextField baseField = new JTextField();
    private Landlord landlord;

    @Override
    protected void invoke() {
        super.invoke();
        landlord = (Landlord) parentUser;
        JLabel baseLabel = new JLabel();
        JLabel numberOfCancellationsLabel = new JLabel();
        JLabel numberOfCancellationsField = new JLabel();
        JLabel numberOfBookingsLabel = new JLabel();
        JLabel numberOfBookingsField = new JLabel();
        JLabel numberOfLodgesLabel = new JLabel();
        JLabel numberOfLodgesField = new JLabel();
        JLabel totalCostLabel = new JLabel();
        JLabel totalCostField = new JLabel();

        //---- baseLabel ----
        baseLabel.setText("Base");
        mainPanel.add(baseLabel);
        baseLabel.setBounds(20, 225, 100, 20);

        //---- baseField ----
        baseField.setText(((Landlord) parentUser).getBase());
        mainPanel.add(baseField);
        baseField.setBounds(140, 225, 200, 20);

        //---- numberOfLodgesLabel ----
        numberOfLodgesLabel.setText("Lodges");
        mainPanel.add(numberOfLodgesLabel);
        numberOfLodgesLabel.setBounds(20, 260, 100, 20);

        //---- numberOfLodgesField ----
        numberOfLodgesField.setText(String.valueOf(landlord.getNumOfLodges()));
        mainPanel.add(numberOfLodgesField);
        numberOfLodgesField.setBounds(140, 260, 200, 20);

        //---- numberOfCancellationsLabel ----
        numberOfCancellationsLabel.setText("Cancellations");
        mainPanel.add(numberOfCancellationsLabel);
        numberOfCancellationsLabel.setBounds(20, 295, 100, 20);

        //---- numberOfCancellationsField ----
        numberOfCancellationsField.setText(String.valueOf(landlord.getNumOfCancellations()));
        mainPanel.add(numberOfCancellationsField);
        numberOfCancellationsField.setBounds(140, 295, 200, 20);

        //---- numberOfBookingsLabel ----
        numberOfBookingsLabel.setText("Bookings");
        mainPanel.add(numberOfBookingsLabel);
        numberOfBookingsLabel.setBounds(20, 330, 100, 20);

        //---- numberOfBookingsField ----
        numberOfBookingsField.setText(String.valueOf(landlord.getNumOfBookings()));
        mainPanel.add(numberOfBookingsField);
        numberOfBookingsField.setBounds(140, 330, 200, 20);

        //---- totalCostLabel ----
        totalCostLabel.setText("Total profit (€)");
        mainPanel.add(totalCostLabel);
        totalCostLabel.setBounds(20, 365, 100, 20);

        //---- totalCostField ----
        totalCostField.setText(String.valueOf(landlord.getTotalProfit()));
        mainPanel.add(totalCostField);
        totalCostField.setBounds(140, 365, 200, 20);

        setPanelSize();
    }

    @Override
    protected void changeOthers() {
        landlord = (Landlord) parentUser;
        landlord.setBase(baseField.getText().length()!=0 ? baseField.getText() : landlord.getBase());
    }
}

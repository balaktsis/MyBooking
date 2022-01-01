package Users;

import Booking.BookingEntry;
import Lodges.Lodge;
import Misc.Storage;
import Users.Actions.CommandLine.CommandLineManager;
import Users.Actions.CommandLine.CustomerCommandLineManager;
import Users.Actions.Graphical.GUIManager;
import Users.Actions.Graphical.Landlord.ShowDetailsPanel;
import Users.Actions.Graphical.LandlordGUIManager;

import javax.swing.*;
import java.awt.*;


/**
 * This is a child of the User class, which contains functionality unique
 * to the Landlord User Type
 * @author Christos Balaktsis
 */
public class Landlord extends User{
    private String base;

    /**
     * Primary constructor of a Landlord User object, takes in credentials,
     * initializes the class fields and assigns a unique ID to the object
     *
     * @param username Login username of the new user
     * @param password Login password of the new user
     */
    public Landlord(String username, String password) {
        super(username, password);
        this.typeName = "Landlord";
    }

    @Override
    protected GUIManager getGUIManager() {
        return new LandlordGUIManager(this);
    }

    @Override
    protected CommandLineManager getCMDManager() {
        return new CustomerCommandLineManager(this);
    }

    /**
     * Set the user's address
     * @param base String containing a landlord's base
     */
    public void setBase(String base){
        this.base = base;
    }

    /**
     * @return String containing a landlord's base
     */
    public String getBase(){
        return this.base;
    }

    /**
     * @return Total profit of all valid bookings on the current landlord's property.
     */
    public double getTotalProfit() {
        double profit = 0;
        for(BookingEntry bookingEntry : Storage.getBookings())
            if(bookingEntry.getLodge().getLandlord().equals(this))
                profit += bookingEntry.getTotalCost();
        return profit;
    }

    /**
     * @return Number of bookings in lodges that belong to the current landlord.
     */
    public int getNumOfBookings() {
        int num = 0;
        for(BookingEntry bookingEntry : Storage.getBookings())
            if(bookingEntry.getLodge().getLandlord().equals(this) && bookingEntry.isValid())
                num++;
        return num;
    }

    /**
     * @return Number of lodges in current landlord's property.
     */
    public int getNumOfLodges() {
        int num = 0;
        for(Lodge lodge : Storage.getLodges())
            if(lodge.getLandlord().equals(this)) num++;
        return num;
    }

    /**
     * @return Number of cancellations in current landlord's property.
     */
    public int getNumOfCancellations() {
        int num = 0;
        for(BookingEntry bookingEntry : Storage.getBookings())
            if(bookingEntry.getLodge().getLandlord().equals(this) && !bookingEntry.isValid()) num++;
        return num;
    }

    @Override
    public String toString(){
        StringBuilder returnStr = new StringBuilder();
        returnStr.append("Landlord\n");
        returnStr.append(super.toString()).append("\n");
        returnStr.append("Base: ");
        returnStr.append(this.getBase());
        returnStr.append("\nNumber of lodges: ");
        returnStr.append(this.getNumOfLodges());
        returnStr.append("\nNumber of Bookings: ");
        returnStr.append(this.getNumOfBookings());
        returnStr.append("\nNumber of Cancellations: ");
        returnStr.append(this.getNumOfCancellations());
        returnStr.append("\nTotal Profit: € ");
        returnStr.append(this.getTotalProfit());
        return returnStr.toString();
    }

    @Override
    protected void addFields(JPanel mainPanel) {
        JLabel baseLabel = new JLabel();
        JLabel baseField = new JLabel();
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
        baseField.setName("Base");
        baseField.setText(this.getBase());
        mainPanel.add(baseField);
        baseField.setBounds(140, 225, 100, 20);

        //---- numberOfLodgesLabel ----
        numberOfLodgesLabel.setText("Lodges");
        mainPanel.add(numberOfLodgesLabel);
        numberOfLodgesLabel.setBounds(20, 260, 100, 20);

        //---- numberOfLodgesField ----
        numberOfLodgesField.setText(String.valueOf(this.getNumOfLodges()));
        mainPanel.add(numberOfLodgesField);
        numberOfLodgesField.setBounds(140, 260, 100, 20);

        //---- numberOfCancellationsLabel ----
        numberOfCancellationsLabel.setText("Cancellations");
        mainPanel.add(numberOfCancellationsLabel);
        numberOfCancellationsLabel.setBounds(20, 295, 100, 20);

        //---- numberOfCancellationsField ----
        numberOfCancellationsField.setText(String.valueOf(this.getNumOfCancellations()));
        mainPanel.add(numberOfCancellationsField);
        numberOfCancellationsField.setBounds(140, 295, 100, 20);

        //---- numberOfBookingsLabel ----
        numberOfBookingsLabel.setText("Bookings");
        mainPanel.add(numberOfBookingsLabel);
        numberOfBookingsLabel.setBounds(20, 330, 100, 20);

        //---- numberOfBookingsField ----
        numberOfBookingsField.setText(String.valueOf(this.getNumOfBookings()));
        mainPanel.add(numberOfBookingsField);
        numberOfBookingsField.setBounds(140, 330, 100, 20);

        //---- totalCostLabel ----
        totalCostLabel.setText("Total profit (€)");
        mainPanel.add(totalCostLabel);
        totalCostLabel.setBounds(20, 365, 100, 20);

        //---- totalCostField ----
        totalCostField.setText(String.valueOf(this.getTotalProfit()));
        mainPanel.add(totalCostField);
        totalCostField.setBounds(140, 365, 100, 20);
    }

}

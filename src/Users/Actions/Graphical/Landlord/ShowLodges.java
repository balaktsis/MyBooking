package Users.Actions.Graphical.Landlord;

import Lodges.Amenities;
import Lodges.Lodge;
import Misc.Storage;
import Users.Actions.Graphical.GUIAction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.Objects;

public class ShowLodges extends GUIAction {
    @Override
    protected String getName() {
        return "Show Lodges";
    }

    @Override
    protected void invoke() {
        actionArea.setLayout(new BoxLayout(actionArea, BoxLayout.Y_AXIS));
        String[] columnNames = {"ID",
                "Title",
                "Location",
                "Type",
                "Size",
                "Price"};

        String[][] data = new String[Storage.getLodges().size()][];
        int k = 0;
        for(Lodge lodge : Storage.getLodges()) {
            if(lodge.getLandlord().equals(parentUser)) {
                data[k] = new String[]{lodge.getLodgeId(), lodge.getDetails().getTitle(),
                        lodge.getDetails().getLocation(), lodge.getType().toString(),
                        String.valueOf(lodge.getDetails().getSize()), String.valueOf(lodge.getDetails().getPrice())};
                k++;
            }
        }
        lodgeList = new JTable(data, columnNames);


        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        ShowLodges = new JScrollPane();
        Panel = new JPanel();
        noteLabel = new JLabel();
        lodgeTable = new JScrollPane();
        currentLodge = new JFrame();
        imageLabel = new JLabel();
        titleLabel = new JLabel();
        landlordLabel = new JLabel();
        idLabel = new JLabel();
        locationLabel = new JLabel();
        amenitiesLabel = new JLabel();
        sizeLabel = new JLabel();
        bedsLabel = new JLabel();
        bookedLabel = new JLabel();
        bookedPanel = new JPanel();
        priceLabel = new JLabel();
        priceLabel = new JLabel();
        descriptionLabel = new JLabel();

        //======== Panel ========
        Panel.setLayout(null);

        //---- noteLabel ----
        noteLabel.setText("Here is a list of all your lodges.");
        Panel.add(noteLabel);
        noteLabel.setBounds(20, 5, 190, 40);

        //---- lodgeList ----
        lodgeList.setAutoCreateRowSorter(true);
        lodgeList.setCellSelectionEnabled(true);
        lodgeList.setShowVerticalLines(false);
        lodgeList.setDefaultEditor(Object.class, null);
        lodgeTable.setViewportView(lodgeList);

        lodgeList.getColumnModel().getColumn(0).setMaxWidth(40);
        lodgeList.getColumnModel().getColumn(1).setMaxWidth(200);
        lodgeList.getColumnModel().getColumn(1).setPreferredWidth(100);
        lodgeList.getColumnModel().getColumn(2).setPreferredWidth(200);
        lodgeList.getColumnModel().getColumn(3).setMaxWidth(150);
        lodgeList.getColumnModel().getColumn(4).setMaxWidth(60);
        lodgeList.getColumnModel().getColumn(5).setMaxWidth(70);

        Panel.add(lodgeTable);
        lodgeTable.setBounds(20, 55, 1100, 600);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < Panel.getComponentCount(); i++) {
                Rectangle bounds = Panel.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = Panel.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            Panel.setMinimumSize(preferredSize);
            Panel.setPreferredSize(preferredSize);
        }

        ShowLodges.setViewportView(Panel);

        lodgeList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow();
                    for(Lodge lodge : Storage.getLodges())
                        if(lodge.getLodgeId().equals(data[row][0])) {
                            currentLodge.setVisible(true);
                            titleLabel.setText(lodge.getDetails().getTitle());
                            landlordLabel.setText(lodge.getLandlord().getFullName());
                            idLabel.setText(idLabel.getText() + lodge.getLodgeId());
                            locationLabel.setText(lodge.getDetails().getLocation());
                            sizeLabel.setText(sizeLabel.getText() + lodge.getDetails().getSize());
                            bedsLabel.setText(bedsLabel.getText() + lodge.getDetails().getBeds());
                            priceLabel.setText(priceLabel.getText() + lodge.getDetails().getPrice());
                            for(LocalDate date : lodge.getAvailability().getBookCalendar())
                                bookedPanel.add(new JLabel(date.toString() + ", "));
                            if(bookedPanel.getComponents().length!=0 && bookedPanel.getComponent(bookedPanel.getComponents().length-1) instanceof JLabel label)
                                label.setText(label.getText().substring(0,label.getText().length()-2));
                            for(Amenities amenity : lodge.getAmenities())
                                amenitiesLabel.setText(amenitiesLabel.getText() + amenity.label + ", ");
                            if(!amenitiesLabel.getText().equals("Offered amenities: "))
                                amenitiesLabel.setText(amenitiesLabel.getText().substring(0,amenitiesLabel.getText().length()-2));
                            descriptionLabel.setText(lodge.getDetails().getDescription());
                            break;
                        }
                }
            }
        });

        //======== currentLodge ========

            currentLodge.setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/Misc/images/logoIcon.png"))).getImage());
            var currentLodgeContentPane = currentLodge.getContentPane();
            currentLodgeContentPane.setLayout(null);
            currentLodgeContentPane.add(imageLabel);
            currentLodge.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    titleLabel.setText("");
                    bedsLabel.setText("Beds: ");
                    idLabel.setText("#");
                    sizeLabel.setText("Size: ");
                    amenitiesLabel.setText("Offered amenities: ");
                    bookedPanel = new JPanel();
                    priceLabel.setText("€ ");
                    descriptionLabel.setText("Description: ");

                }
            });

            imageLabel.setBounds(10, 20, 130, 115);
            imageLabel.setIcon(new ImageIcon("src/Misc/101-1014243_buildings-building-house-home-cartoon-homes-apartment-clipart.png"));

            //---- titleLabel ----
            titleLabel.setFont(titleLabel.getFont().deriveFont(titleLabel.getFont().getStyle() | Font.BOLD, titleLabel.getFont().getSize() + 4f));
            currentLodgeContentPane.add(titleLabel);
            titleLabel.setBounds(145, 20, 180, 25);

            //---- landlordLabel ----
            currentLodgeContentPane.add(landlordLabel);
            landlordLabel.setBounds(145, 45, 85, 25);

            //---- idLabel ----
            idLabel.setText("#");
            currentLodgeContentPane.add(idLabel);
            idLabel.setBounds(300, 45, 45, 25);

            //---- locationLabel ----
            currentLodgeContentPane.add(locationLabel);
            locationLabel.setBounds(145, 80, 185, 25);

            //---- amenitiesLabel ----
            amenitiesLabel.setText("Offered amenities: ");
            currentLodgeContentPane.add(amenitiesLabel);
            amenitiesLabel.setBounds(10, 140, 325, 45);

            //---- sizeLabel ----
            sizeLabel.setText("Size: ");
            currentLodgeContentPane.add(sizeLabel);
            sizeLabel.setBounds(145, 115, 95, 25);

            //---- bedsLabel ----
            bedsLabel.setText("Beds: ");
            currentLodgeContentPane.add(bedsLabel);
            bedsLabel.setBounds(260, 115, 90, 25);

            //---- bookedLabel ----
            bookedLabel.setText("Booked dates:");
            currentLodgeContentPane.add(bookedLabel);
            bookedLabel.setBounds(10, 185, 95, 25);

            //======== bookedPanel ========
            {
                bookedPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 3, 3));
            }
            currentLodgeContentPane.add(bookedPanel);
            bookedPanel.setBounds(10, 210, 335, 120);

            //---- priceLabel ----
            priceLabel.setText("\u20ac ");
            priceLabel.setFont(priceLabel.getFont().deriveFont(priceLabel.getFont().getStyle() | Font.BOLD));
            currentLodgeContentPane.add(priceLabel);
            priceLabel.setBounds(270, 185, 46, 21);



        //---- descriptionLabel ----
        descriptionLabel.setText("Description: ");
        currentLodgeContentPane.add(descriptionLabel);
        descriptionLabel.setBounds(10, 345, 335, 125);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < currentLodgeContentPane.getComponentCount(); i++) {
                Rectangle bounds = currentLodgeContentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = currentLodgeContentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            currentLodgeContentPane.setMinimumSize(preferredSize);
            currentLodgeContentPane.setPreferredSize(preferredSize);
        }
            currentLodge.pack();
            currentLodge.setLocationRelativeTo(currentLodge.getOwner());

            currentLodge.setVisible(false);

        actionArea.add(ShowLodges);
        actionArea.add(Box.createRigidArea(new Dimension(0, 5)));

    }
    private JScrollPane ShowLodges;
    private JPanel Panel;
    private JLabel noteLabel;
    private JScrollPane lodgeTable;
    private JTable lodgeList;
    private JFrame currentLodge;
    private JLabel imageLabel;
    private JLabel titleLabel;
    private JLabel landlordLabel;
    private JLabel idLabel;
    private JLabel locationLabel;
    private JLabel amenitiesLabel;
    private JLabel sizeLabel;
    private JLabel bedsLabel;
    private JLabel bookedLabel;
    private JPanel bookedPanel;
    private JLabel priceLabel;
    private JLabel descriptionLabel;
}

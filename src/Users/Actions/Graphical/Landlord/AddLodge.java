package Users.Actions.Graphical.Landlord;

import Lodges.Amenities;
import Lodges.Hotel;
import Lodges.Lodge;
import Lodges.LodgeType;
import Misc.Storage;
import Users.Actions.Graphical.GUIAction;
import Users.Landlord;
import Users.User;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Objects;


public class AddLodge extends GUIAction {

    private JScrollPane NewLodge;
    private JPanel BasicInfo;
    private JLabel noteLabel;
    private JLabel typeLabel;
    private JComboBox typeBox;
    private JLabel titleLabel;
    private JTextField titleField;
    private JLabel locationLabel;
    private JTextField locationField;
    private JLabel descriptionLabel;
    private JScrollPane descriptionPane;
    private JTextArea descriptionField;
    private JLabel priceLabel;
    private JTextField priceField;
    private JLabel sizeLabel;
    private JTextField sizeField;
    private JLabel bedsLabel;
    private JTextField bedsField;
    private JLabel amenitiesLabel;
    private JButton confirmButton;
    private JButton clearButton;
    private JComboBox hotelBox;
    private JLabel nullTitleLabel;
    private JLabel nullHotelLabel;
    private JPanel amenitiesPanel;
    private JLabel nullPriceLabel;
    private JLabel nullSizeLabel;
    private JLabel nullBedsLabel;

    private void replaceLocationFields(boolean show) {
        if(show) {
            locationLabel.setText("Hotel");
            locationField.setVisible(false);
            hotelBox.setVisible(true);
            if(hotelBox.getItemCount() == 0) {
                hotelBox.setEnabled(false);
                nullHotelLabel.setVisible(true);
                confirmButton.setEnabled(false);
            } else {
                hotelBox.setEnabled(true);
                nullHotelLabel.setVisible(false);
                confirmButton.setEnabled(true);
            }

        } else {
            hotelBox.setVisible(false);
            locationLabel.setText("Location");
            locationField.setVisible(true);
            nullHotelLabel.setVisible(false);
        }
    }

    private void enableSizeFields(boolean show) {
        sizeField.setEnabled(show);
        sizeLabel.setEnabled(show);
    }

    private void enableBedFields(boolean show) {
        bedsField.setEnabled(show);
        bedsLabel.setEnabled(show);
    }

    private void enableAmenities(boolean show) {
        for(Component component: amenitiesPanel.getComponents())
            component.setEnabled(show);
        amenitiesLabel.setEnabled(show);
    }

    private void enablePriceFields(boolean show) {
        priceField.setEnabled(show);
        priceLabel.setEnabled(show);
    }

    private void typeBoxItemStateChanged(ItemEvent e) {
        confirmButton.setEnabled(true);
        titleField.setText("");
        locationField.setText("");
        descriptionField.setText("");
        priceField.setText("");
        sizeField.setText("");
        bedsField.setText("");
        nullBedsLabel.setVisible(false);
        nullSizeLabel.setVisible(false);
        nullPriceLabel.setVisible(false);
        enableBedFields(true);
        enableSizeFields(true);
        enableAmenities(true);
        enablePriceFields(true);
        replaceLocationFields(false);

        for(Component component : amenitiesPanel.getComponents())
            if(component instanceof JCheckBox checkBox) checkBox.setSelected(false);

        if(e.getItem().equals("HOTEL")) {
            enableBedFields(false);
            enableSizeFields(false);
            enableAmenities(false);
            enablePriceFields(false);
        } else if(e.getItem().equals("ROOM")) {
            replaceLocationFields(true);
        }
    }

    private void confirmButtonMouseClicked(MouseEvent e) {
        if (confirmButton.isEnabled() && titleField.getDocument().getLength() > 0) {
            String type = Objects.requireNonNull(typeBox.getSelectedItem()).toString();
            Lodge lodge;
            if (type.equals("HOTEL")) {
                lodge = new Hotel((Landlord) parentUser, locationField.getText());
                lodge.getDetails().setTitle(titleField.getText());
                lodge.getDetails().setDescription(descriptionField.getText());
                lodge.getDetails().setNumOfBookings(0);
                lodge.getDetails().setNumOfBookings(0);
                Storage.getLodges().add(lodge);
                clearButtonMouseClicked(null);
                JOptionPane.showMessageDialog(NewLodge,"Lodge successfully added to your properties!","Addition complete",JOptionPane.INFORMATION_MESSAGE);
                return;
            } else if (priceField.getText().length() * bedsField.getText().length() * sizeField.getText().length() * locationField.getText().length() > 0) {
                lodge = new Lodge((Landlord) parentUser, locationField.getText(), LodgeType.valueOf(typeBox.getSelectedItem().toString()));
                lodge.getDetails().setTitle(titleField.getText());
                lodge.getDetails().setDescription(descriptionField.getText());
                lodge.getDetails().setBeds(Integer.parseInt(bedsField.getText()));
                Integer size = Integer.parseInt(sizeField.getText());
                lodge.getDetails().setPrice(Double.parseDouble(priceField.getText()));
                lodge.getDetails().setRating(0);
                lodge.getDetails().setNumOfBookings(0);
                HashSet<Amenities> amenities = new HashSet<>();
                for (Component component : amenitiesPanel.getComponents())
                    if (component instanceof JCheckBox checkBox)
                        if (checkBox.isSelected())
                            amenities.add(Amenities.valueOf(checkBox.getText().toUpperCase().replace(' ', '_')));
                lodge.setAmenities(amenities);
                Storage.getLodges().add(lodge);
                JOptionPane.showMessageDialog(NewLodge,"Lodge successfully added to your properties!","Addition complete",JOptionPane.INFORMATION_MESSAGE);
                clearButtonMouseClicked(null);
                return;
            }
        }
        JOptionPane.showMessageDialog(NewLodge,"Invalid lodge details! Please check again!","Invalid details",JOptionPane.ERROR_MESSAGE);
    }

    private void clearButtonMouseClicked(MouseEvent e) {
        for(Component component : BasicInfo.getComponents()) {
            if(component instanceof JTextField textField) textField.setText("");
            else if(component instanceof JCheckBox checkBox) checkBox.setSelected(false);
        }
        nullTitleLabel.setVisible(false);
        nullHotelLabel.setVisible(false);
        nullBedsLabel.setVisible(false);
        nullSizeLabel.setVisible(false);
        nullPriceLabel.setVisible(false);
    }



    @Override
    protected String getName() {
        return "Add Lodge";
    }

    @Override
    protected void invoke() {

        actionArea.setLayout(new BoxLayout(actionArea, BoxLayout.Y_AXIS));

        NewLodge = new JScrollPane();
        BasicInfo = new JPanel();
        noteLabel = new JLabel();
        typeLabel = new JLabel();
        typeBox = new JComboBox();
        titleLabel = new JLabel();
        titleField = new JTextField();
        locationLabel = new JLabel();
        locationField = new JTextField();
        descriptionLabel = new JLabel();
        descriptionPane = new JScrollPane();
        descriptionField = new JTextArea();
        priceLabel = new JLabel();
        priceField = new JTextField();
        sizeLabel = new JLabel();
        sizeField = new JTextField();
        bedsLabel = new JLabel();
        bedsField = new JTextField();
        amenitiesLabel = new JLabel();
        confirmButton = new JButton();
        clearButton = new JButton();
        hotelBox = new JComboBox();
        nullTitleLabel = new JLabel();
        nullHotelLabel = new JLabel();
        amenitiesPanel = new JPanel();
        nullPriceLabel = new JLabel();
        nullSizeLabel = new JLabel();
        nullBedsLabel = new JLabel();

        //======== NewLodge ========
        {

            //======== BasicInfo ========
            {
                BasicInfo.setLayout(null);

                //---- noteLabel ----
                noteLabel.setText("Fill in the following details for the new lodge.");
                BasicInfo.add(noteLabel);
                noteLabel.setBounds(20, 5, 251, 31);

                //---- typeLabel ----
                typeLabel.setText("Type of lodge");
                BasicInfo.add(typeLabel);
                typeLabel.setBounds(20, 55, 125, 31);

                //---- typeBox ----
                typeBox.addItemListener(e -> typeBoxItemStateChanged(e));
                BasicInfo.add(typeBox);
                typeBox.setBounds(170, 55, 251, 31);

                //---- titleLabel ----
                titleLabel.setText("Title");
                BasicInfo.add(titleLabel);
                titleLabel.setBounds(20, 105, 125, 31);
                BasicInfo.add(titleField);
                titleField.setBounds(170, 105, 251, 31);

                //---- locationLabel ----
                locationLabel.setText("Location");
                BasicInfo.add(locationLabel);
                locationLabel.setBounds(20, 155, 125, 31);
                BasicInfo.add(locationField);
                locationField.setBounds(170, 155, 251, 31);

                //---- descriptionLabel ----
                descriptionLabel.setText("Description");
                BasicInfo.add(descriptionLabel);
                descriptionLabel.setBounds(20, 210, 125, 31);

                //======== descriptionPane ========
                {
                    descriptionPane.setViewportView(descriptionField);
                }
                BasicInfo.add(descriptionPane);
                descriptionPane.setBounds(170, 205, 251, 31);

                //---- priceLabel ----
                priceLabel.setText("Price per night");
                BasicInfo.add(priceLabel);
                priceLabel.setBounds(20, 260, 125, 31);
                BasicInfo.add(priceField);
                priceField.setBounds(170, 255, 251, 31);

                //---- sizeLabel ----
                sizeLabel.setText("Size (m2)");
                BasicInfo.add(sizeLabel);
                sizeLabel.setBounds(20, 310, 125, 31);
                BasicInfo.add(sizeField);
                sizeField.setBounds(170, 310, 251, 31);

                //---- bedsLabel ----
                bedsLabel.setText("Beds ");
                BasicInfo.add(bedsLabel);
                bedsLabel.setBounds(20, 360, 125, 31);
                BasicInfo.add(bedsField);
                bedsField.setBounds(170, 360, 251, 31);

                //---- amenitiesLabel ----
                amenitiesLabel.setText("Amenities");
                BasicInfo.add(amenitiesLabel);
                amenitiesLabel.setBounds(20, 410, 125, 31);

                //---- confirmButton ----
                confirmButton.setText("Confirm");
                confirmButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        confirmButtonMouseClicked(e);
                    }
                });
                BasicInfo.add(confirmButton);
                confirmButton.setBounds(290, 540, 80, 35);

                //---- clearButton ----
                clearButton.setText("Clear");
                clearButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        clearButtonMouseClicked(e);
                    }
                });
                BasicInfo.add(clearButton);
                clearButton.setBounds(205, 540, 80, 35);
                BasicInfo.add(hotelBox);
                hotelBox.setBounds(175, 155, 240, hotelBox.getPreferredSize().height);

                //---- nullTitleLabel ----
                nullTitleLabel.setText("Title cannot be empty.");
                nullTitleLabel.setForeground(Color.red);
                BasicInfo.add(nullTitleLabel);
                nullTitleLabel.setBounds(175, 135, 250, nullTitleLabel.getPreferredSize().height);

                //---- nullHotelLabel ----
                nullHotelLabel.setText("There are not any hotels under your property.");
                nullHotelLabel.setForeground(Color.red);
                BasicInfo.add(nullHotelLabel);
                nullHotelLabel.setBounds(175, 185, 255, 16);

                //======== amenitiesPanel ========
                {
                    amenitiesPanel.setLayout(new FlowLayout());
                }
                BasicInfo.add(amenitiesPanel);
                amenitiesPanel.setBounds(170, 420, 250, 110);

                //---- nullPriceLabel ----
                nullPriceLabel.setText("Provide a valid cost per night.");
                nullPriceLabel.setForeground(Color.red);
                BasicInfo.add(nullPriceLabel);
                nullPriceLabel.setBounds(175, 285, 245, 16);

                //---- nullSizeLabel ----
                nullSizeLabel.setText("Provide a valid size in square-meters.");
                nullSizeLabel.setForeground(Color.red);
                BasicInfo.add(nullSizeLabel);
                nullSizeLabel.setBounds(175, 340, 245, 16);

                //---- nullBedsLabel ----
                nullBedsLabel.setText("Provide a valid number of beds.");
                nullBedsLabel.setForeground(Color.red);
                BasicInfo.add(nullBedsLabel);
                nullBedsLabel.setBounds(175, 390, 245, 16);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < BasicInfo.getComponentCount(); i++) {
                        Rectangle bounds = BasicInfo.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = BasicInfo.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    BasicInfo.setMinimumSize(preferredSize);
                    BasicInfo.setPreferredSize(preferredSize);
                }
            }
            NewLodge.setViewportView(BasicInfo);
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

        nullTitleLabel.setVisible(false);
        nullTitleLabel.setFont(nullTitleLabel.getFont().deriveFont(nullTitleLabel.getFont().getSize() - 2f));

        nullHotelLabel.setVisible(false);
        nullHotelLabel.setFont(nullHotelLabel.getFont().deriveFont(nullHotelLabel.getFont().getSize() - 2f));

        nullPriceLabel.setVisible(false);
        nullPriceLabel.setFont(nullPriceLabel.getFont().deriveFont(nullPriceLabel.getFont().getSize() - 2f));

        nullBedsLabel.setVisible(false);
        nullBedsLabel.setFont(nullBedsLabel.getFont().deriveFont(nullBedsLabel.getFont().getSize() - 2f));

        nullSizeLabel.setVisible(false);
        nullSizeLabel.setFont(nullSizeLabel.getFont().deriveFont(nullSizeLabel.getFont().getSize() - 2f));


        for(LodgeType type : LodgeType.values())
            typeBox.addItem(type.toString());
        BasicInfo.setVisible(true);
        hotelBox.setVisible(false);

        for (Lodge lodge : Storage.getLodges())
            if(lodge.getLandlord().equals(this.parentUser) && lodge.getType().equals(LodgeType.HOTEL))
                hotelBox.addItem(lodge.getLodgeId() + " - " + lodge.getDetails().getTitle());

        titleField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                confirmButton.setEnabled(true);
                if(e.toString().equals("")) {
                    nullTitleLabel.setVisible(true);
                    confirmButton.setEnabled(false);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                confirmButton.setEnabled(true);
                if(e.toString().equals("")) {
                    nullTitleLabel.setVisible(true);
                    confirmButton.setEnabled(false);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                confirmButton.setEnabled(true);
                if(e.toString().equals("")) {
                    nullTitleLabel.setVisible(true);
                    confirmButton.setEnabled(false);
                }
            }
        });

        priceField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                nullPriceLabel.setVisible(false);
                confirmButton.setEnabled(true);
                if(priceField.isEnabled()) {
                    try{
                        Double.parseDouble(priceField.getText());
                    } catch (NumberFormatException exception) {
                        nullPriceLabel.setVisible(true);
                        confirmButton.setEnabled(false);
                    }
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                nullPriceLabel.setVisible(false);
                confirmButton.setEnabled(true);
                if(priceField.isEnabled()) {
                    try{
                        Double.parseDouble(priceField.getText());
                    } catch (NumberFormatException exception) {
                        nullPriceLabel.setVisible(true);
                        confirmButton.setEnabled(false);
                    }
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                nullPriceLabel.setVisible(false);
                confirmButton.setEnabled(true);
                if(priceField.isEnabled()) {
                    try{
                        Double.parseDouble(priceField.getText());
                    } catch (NumberFormatException exception) {
                        nullPriceLabel.setVisible(true);
                        confirmButton.setEnabled(false);
                    }
                }
            }
        });

        bedsField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                nullBedsLabel.setVisible(false);
                confirmButton.setEnabled(true);
                if(bedsField.isEnabled()) {
                    try{
                        Integer.parseInt(bedsField.getText());
                    } catch (NumberFormatException exception) {
                        nullBedsLabel.setVisible(true);
                        confirmButton.setEnabled(false);
                    }
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                nullBedsLabel.setVisible(false);
                confirmButton.setEnabled(true);
                if(bedsField.isEnabled()) {
                    try{
                        Integer.parseInt(bedsField.getText());
                    } catch (NumberFormatException exception) {
                        nullBedsLabel.setVisible(true);
                        confirmButton.setEnabled(false);
                    }
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                nullBedsLabel.setVisible(false);
                confirmButton.setEnabled(true);
                if(bedsField.isEnabled()) {
                    try{
                        Integer.parseInt(bedsField.getText());
                    } catch (NumberFormatException exception) {
                        nullBedsLabel.setVisible(true);
                        confirmButton.setEnabled(false);
                    }
                }
            }
        });

        sizeField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                nullSizeLabel.setVisible(false);
                confirmButton.setEnabled(true);
                if(sizeField.isEnabled()) {
                    try{
                        Integer.parseInt(sizeField.getText());
                    } catch (NumberFormatException exception) {
                        nullSizeLabel.setVisible(true);
                        confirmButton.setEnabled(false);
                    }
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                nullSizeLabel.setVisible(false);
                confirmButton.setEnabled(true);
                if(sizeField.isEnabled()) {
                    try{
                        Integer.parseInt(sizeField.getText());
                    } catch (NumberFormatException exception) {
                        nullSizeLabel.setVisible(true);
                        confirmButton.setEnabled(false);
                    }
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                nullSizeLabel.setVisible(false);
                confirmButton.setEnabled(true);
                if(sizeField.isEnabled()) {
                    try{
                        Integer.parseInt(sizeField.getText());
                    } catch (NumberFormatException exception) {
                        nullSizeLabel.setVisible(true);
                        confirmButton.setEnabled(false);
                    }
                }
            }
        });

        for(Amenities amenity : Amenities.values())
            amenitiesPanel.add(new JCheckBox(amenity.label,false));

        actionArea.add(NewLodge);
        actionArea.add(Box.createRigidArea(new Dimension(0, 5)));
    }
}

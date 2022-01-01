package Users.Actions.Graphical.Landlord;

import Booking.BookingEntry;
import Lodges.Amenities;
import Lodges.Hotel;
import Lodges.Lodge;
import Lodges.LodgeType;
import Misc.Storage;
import Users.Actions.Graphical.AdjustSize;
import Users.Actions.Graphical.GUIAction;
import Users.Landlord;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.Objects;


/**
 * This class represents α panel that contains a table of lodges that belong to
 * the current landlord. Double-clicking on some entry, the user can see all the
 * important details of the selected lodge, delete or edit it.
 * @author Christos Balaktsis
 */

public class ShowLodges extends GUIAction implements Serializable {

    @Override
    protected String getName() {
        return "Show Lodges";
    }

    @Override
    protected void invoke() {
        actionArea.setLayout(new BoxLayout(actionArea, BoxLayout.Y_AXIS));

        TableModel lodgeTableModel = new lodgeTableModel((Landlord) parentUser);
        lodgeList = new JTable(lodgeTableModel);
        ShowLodges = new JScrollPane();
        Panel = new JPanel();
        noteLabel = new JLabel();
        lodgeTable = new JScrollPane();
        currentLodge = new JFrame();
        image = new JButton();
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
        deleteButton = new JButton();
        editButton = new JButton();
        editLodge = new JFrame();
        editTitleLabel = new JLabel();
        editTitleField = new JTextField();
        editSizeLabel = new JLabel();
        editSizeField = new JTextField();
        editNoteLabel = new JLabel();
        editIdLabel = new JLabel();
        editBedsField = new JTextField();
        editBedsLabel = new JLabel();
        editDescriptionPanel = new JScrollPane();
        editDescriptionField = new JTextPane();
        editDescriptionLabel = new JLabel();
        editPriceLabel = new JLabel();
        editPriceField = new JTextField();
        editAmenitiesLabel = new JLabel();
        editConfirmButton = new JButton();
        editCancelButton = new JButton();
        editAmenitiesPanel = new JPanel();
        editImageButton = new JButton();

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

        AdjustSize.AdjustPanelSize(Panel);

        ShowLodges.setViewportView(Panel);

        lodgeList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow();
                    for(Lodge lodge : Storage.getLodges())
                        if(lodge.getLodgeId().equals(lodgeList.getValueAt(row,0))) {
                            bedsLabel.setVisible(!lodge.getType().equals(LodgeType.HOTEL));
                            sizeLabel.setVisible(!lodge.getType().equals(LodgeType.HOTEL));
                            bookedLabel.setVisible(!lodge.getType().equals(LodgeType.HOTEL));
                            amenitiesLabel.setVisible(!lodge.getType().equals(LodgeType.HOTEL));
                            priceLabel.setVisible(!lodge.getType().equals(LodgeType.HOTEL));
                            bookedPanel.setVisible(!lodge.getType().equals(LodgeType.HOTEL));

                            currentLodge.setVisible(true);
                            currentLodge.setTitle("#" + lodge.getLodgeId() + " - " + lodge.getDetails().getTitle());
                            titleLabel.setText(lodge.getDetails().getTitle());
                            landlordLabel.setText(lodge.getLandlord().getFullName());
                            idLabel.setText(idLabel.getText() + lodge.getLodgeId());
                            descriptionLabel.setText(lodge.getDetails().getDescription());

                            if(lodge.getType().equals(LodgeType.ROOM)) {
                                for(Lodge tmpLodge : Storage.getLodges())
                                    if(tmpLodge instanceof Hotel hotel)
                                        if(hotel.getRooms().get(lodge.getLodgeId())!=null) {
                                            locationLabel.setText(hotel.getDetails().getLocation());
                                            break;
                                        }
                            }
                            else locationLabel.setText(lodge.getDetails().getLocation());

                            locationLabel.setToolTipText(locationLabel.getText());

                            if(lodge.getType().equals(LodgeType.HOTEL))
                                descriptionLabel.setBounds(10, 140, 325, 45);
                            else
                                descriptionLabel.setBounds(10, 345, 335, 125);

                            sizeLabel.setText(sizeLabel.getText() + lodge.getDetails().getSize());
                            bedsLabel.setText(bedsLabel.getText() + lodge.getDetails().getBeds());
                            priceLabel.setText(priceLabel.getText() + lodge.getDetails().getPrice());

                            ImageIcon imageIcon = lodge.getDetails().getImage();
                            image.setIcon(new ImageIcon(getScaledImage(imageIcon.getImage())));

                            for(LocalDate date : lodge.getAvailability().getBookCalendar())
                                bookedPanel.add(new JLabel(date.toString() + ", "));
                            if(bookedPanel.getComponents().length!=0 && bookedPanel.getComponent(bookedPanel.getComponents().length-1) instanceof JLabel label)
                                label.setText(label.getText().substring(0,label.getText().length()-2));
                            else if(bookedPanel.getComponents().length == 0) bookedPanel.add(new JLabel("No booking entries yet."));

                            for(Amenities amenity : lodge.getAmenities())
                                amenitiesLabel.setText(amenitiesLabel.getText() + amenity.label + ", ");
                            if(!amenitiesLabel.getText().equals("Offered amenities: "))
                                amenitiesLabel.setText(amenitiesLabel.getText().substring(0,amenitiesLabel.getText().length()-2));

                            break;
                        }
                }
            }
        });

        //======== currentLodge ========
        currentLodge.setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/Misc/images/logoIcon.png"))).getImage());
        var currentLodgeContentPane = currentLodge.getContentPane();
        currentLodgeContentPane.setLayout(null);
        currentLodgeContentPane.add(image);
        currentLodge.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                refresh();
            }
        });
        image.setBounds(10, 20, 130, 115);
        ImageIcon imageIcon = new ImageIcon("src/Misc/images/defaultLodgeImage.png");
        image.setIcon(new ImageIcon(getScaledImage(imageIcon.getImage())));

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
        locationLabel.setBounds(145, 60, 185, 25);

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

        //---- deleteButton ----
        deleteButton.setText("Delete");
        currentLodgeContentPane.add(deleteButton);
        deleteButton.setBounds(145, 90, deleteButton.getPreferredSize().width, deleteButton.getPreferredSize().height);
        deleteButton.addActionListener(e -> {
            String lodgeId = idLabel.getText().substring(1);
            boolean removed = false;
            if (JOptionPane.showConfirmDialog(currentLodge, "Are you sure you want to delete lodge " + idLabel.getText() + " and cancel " +
                            "any booking entries applied on it?",
                    "Delete lodge", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

                for (Lodge tempLodge : Storage.getLodges())
                    if (Objects.equals(tempLodge.getLodgeId(), lodgeId) && tempLodge.getLandlord().equals(parentUser)) {
                        //If the requested lodge is a room, the room list of the hotel, it belongs to, has to be updated.
                        if (tempLodge.getType().equals(LodgeType.ROOM)) {
                            for (Lodge tempLodge2 : Storage.getLodges())
                                if (Objects.equals(tempLodge2.getType(), LodgeType.HOTEL)) {
                                    Hotel hotel = (Hotel) tempLodge2;
                                    if (hotel.getRooms().containsKey(lodgeId)) {
                                        removed = hotel.removeRoom(lodgeId);
                                        break;
                                    }
                                }
                        } else {
                            //If the requested lodge is a hotel, all the rooms of the hotel have to be removed both from the hotel's
                            //room list and the system's storage.
                            if (tempLodge.getType().equals(LodgeType.HOTEL)) {
                                Hotel hotel = (Hotel) tempLodge;
                                for (String room : hotel.getRooms().keySet()) {
                                    hotel.removeRoom(room);
                                    break;
                                }
                            }
                            removed = Storage.getLodges().remove(tempLodge);
                        }
                        if (removed) {
                            for (BookingEntry bookingEntry : Storage.getBookings())
                                if (bookingEntry.getLodge().equals(tempLodge)) bookingEntry.cancelBooking();
                            JOptionPane.showMessageDialog(currentLodge, "Deletion completed!", "Delete Lodge", JOptionPane.INFORMATION_MESSAGE);
                        } else
                            JOptionPane.showMessageDialog(currentLodge,"Deletion not completed!","Delete Lodge",JOptionPane.ERROR_MESSAGE);
                        currentLodge.dispose();
                        refresh();
                        break;
                    }
            }
        });

        //---- editButton ----
        editButton.setText("Edit");
        currentLodgeContentPane.add(editButton);
        editButton.setBounds(250, 90, editButton.getPreferredSize().width, editButton.getPreferredSize().height);
        editButton.addActionListener(e -> {
            String lodgeId = idLabel.getText().substring(1);
            for (Lodge tempLodge : Storage.getLodges())
                if (Objects.equals(tempLodge.getLodgeId(), lodgeId) && tempLodge.getLandlord().equals(parentUser)) {
                    lodge = tempLodge;

                    editLodge.setVisible(true);

                    editBedsLabel.setEnabled(!tempLodge.getType().equals(LodgeType.HOTEL));
                    editSizeLabel.setEnabled(!tempLodge.getType().equals(LodgeType.HOTEL));
                    editAmenitiesLabel.setEnabled(!tempLodge.getType().equals(LodgeType.HOTEL));
                    editPriceLabel.setVisible(!tempLodge.getType().equals(LodgeType.HOTEL));
                    editBedsField.setEnabled(!tempLodge.getType().equals(LodgeType.HOTEL));
                    editSizeField.setEnabled(!tempLodge.getType().equals(LodgeType.HOTEL));
                    editAmenitiesPanel.setEnabled(!tempLodge.getType().equals(LodgeType.HOTEL));
                    editPriceField.setEnabled(!tempLodge.getType().equals(LodgeType.HOTEL));

                    editTitleField.setText(tempLodge.getDetails().getTitle());
                    editDescriptionField.setText(tempLodge.getDetails().getDescription());
                    editBedsField.setText(String.valueOf(tempLodge.getDetails().getBeds()));
                    editSizeField.setText(String.valueOf(tempLodge.getDetails().getSize()));
                    editPriceField.setText(String.valueOf(tempLodge.getDetails().getPrice()));
                    editIdLabel.setText(idLabel.getText());

                    for(Component component : editAmenitiesPanel.getComponents())
                        if(component instanceof JCheckBox box) box.setSelected(lodge.getAmenities().contains(Amenities.valueOfLabel(box.getText())));
                    break;
                }

        });

        AdjustSize.AdjustContainerSize(currentLodgeContentPane);

        currentLodge.pack();
        currentLodge.setLocationRelativeTo(currentLodge.getOwner());

        currentLodge.setVisible(false);
        currentLodge.setResizable(false);


        //======== EditLodge ========
        editLodge.setTitle("Edit lodge");
        editLodge.setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/Misc/images/logoIcon.png"))).getImage());
        var EditLodgeContentPane = editLodge.getContentPane();
        EditLodgeContentPane.setLayout(null);

        //---- titleLabel ----
        editTitleLabel.setText("Title");
        EditLodgeContentPane.add(editTitleLabel);
        editTitleLabel.setBounds(25, 90, 85, 30);
        EditLodgeContentPane.add(editTitleField);
        editTitleField.setBounds(120, 90, 205, 30);

        //---- sizeLabel ----
        editSizeLabel.setText("Size");
        EditLodgeContentPane.add(editSizeLabel);
        editSizeLabel.setBounds(25, 125, 85, 30);
        EditLodgeContentPane.add(editSizeField);
        editSizeField.setBounds(120, 125, 205, 30);

        //---- noteLabel ----
        editNoteLabel.setText("Edit Lodge");
        editNoteLabel.setFont(editNoteLabel.getFont().deriveFont(editNoteLabel.getFont().getStyle() | Font.BOLD, editNoteLabel.getFont().getSize() + 10f));
        editNoteLabel.setIcon(null);
        EditLodgeContentPane.add(editNoteLabel);
        editNoteLabel.setBounds(25, 25, 175, 40);

        //---- idLabel ----
        editIdLabel.setText("#");
        editIdLabel.setFont(editIdLabel.getFont().deriveFont(Font.BOLD, editIdLabel.getFont().getSize() + 4f));
        EditLodgeContentPane.add(editIdLabel);
        editIdLabel.setBounds(270, 30, 55, 30);
        EditLodgeContentPane.add(editBedsField);
        editBedsField.setBounds(120, 160, 205, 30);

        //---- bedsLabel ----
        editBedsLabel.setText("Beds");
        EditLodgeContentPane.add(editBedsLabel);
        editBedsLabel.setBounds(25, 160, 85, 30);

        //======== descriptionPanel ========
        {
            editDescriptionPanel.setViewportView(editDescriptionField);
        }
        EditLodgeContentPane.add(editDescriptionPanel);
        editDescriptionPanel.setBounds(120, 200, 205, 105);

        //---- descriptionLabel ----
        editDescriptionLabel.setText("Description");
        EditLodgeContentPane.add(editDescriptionLabel);
        editDescriptionLabel.setBounds(25, 195, 85, 30);

        //---- priceLabel ----
        editPriceLabel.setText("Price");
        EditLodgeContentPane.add(editPriceLabel);
        editPriceLabel.setBounds(25, 315, 85, 30);
        EditLodgeContentPane.add(editPriceField);
        editPriceField.setBounds(120, 315, 205, 30);

        //---- amenitiesLabel
        editAmenitiesLabel.setText("Amenities");
        EditLodgeContentPane.add(editAmenitiesLabel);
        editAmenitiesLabel.setBounds(25,360,85,30);

        //======== amenitiesPanel ========
        {
            editAmenitiesPanel.setLayout(new FlowLayout());
        }
        EditLodgeContentPane.add(editAmenitiesPanel);
        editAmenitiesPanel.setBounds(120, 360, 200, 75);

        for(Amenities amenity : Amenities.values())
            editAmenitiesPanel.add(new JCheckBox(amenity.label,false));

        editTitleField.addActionListener(e -> editConfirmButton());
        editSizeField.addActionListener(e -> editConfirmButton());
        editBedsField.addActionListener(e -> editConfirmButton());
        editPriceField.addActionListener(e -> editConfirmButton());

        //---- editImageButton ----
        editImageButton.setText("Image");
        EditLodgeContentPane.add(editImageButton);
        editImageButton.setBounds(25, 435, 75, 30);
        editImageButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String[] extensions = {"png", "jpg", "svg", "gif"};
                JFileChooser chooser = new JFileChooser();
                chooser.setFileFilter(new FileFilter() {
                    public boolean accept(File file) {
                        if (file.isDirectory()) {
                            return true;
                        } else {
                            String path = file.getAbsolutePath().toLowerCase();
                            for (String extension : extensions) {
                                if ((path.endsWith(extension) && (path.charAt(path.length()
                                        - extension.length() - 1)) == '.')) {
                                    return true;
                                }
                            }
                        }
                        return false;
                    }

                    @Override
                    public String getDescription() {
                        return "Image files";
                    }
                });
                int status = chooser.showOpenDialog(null);
                if (status == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    if (file != null) {
                        Path copied = Paths.get("src/Misc/Images/" + chooser.getSelectedFile().getName());
                        Path originalPath = Paths.get(chooser.getSelectedFile().getAbsolutePath());
                        try {
                            Files.copy(originalPath, copied, StandardCopyOption.REPLACE_EXISTING);
                            editImageIcon = new ImageIcon(chooser.getSelectedFile().getAbsolutePath());
                        } catch (IOException ex) {
                            editImageIcon = new ImageIcon("src/Misc/images/defaultLodgeImage.png");
                            ex.printStackTrace();
                        }
                    }
                }
            }
        });
        editImageIcon = new ImageIcon("src/Misc/images/defaultLodgeImage.png");

        //---- confirmButton ----
        editConfirmButton.setText("Confirm");
        EditLodgeContentPane.add(editConfirmButton);
        editConfirmButton.setBounds(245, 460, 75, 30);
        editConfirmButton.addActionListener(e -> editConfirmButton());

        //---- cancelButton ----
        editCancelButton.setText("Cancel");
        EditLodgeContentPane.add(editCancelButton);
        editCancelButton.setBounds(155, 460, 75, 30);

        EditLodgeContentPane.setPreferredSize(new Dimension(355, 500));
        editLodge.pack();
        editLodge.setLocationRelativeTo(editLodge.getOwner());
        editLodge.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                refresh();
            }
        });
        editLodge.setVisible(false);
        editLodge.setResizable(false);

        actionArea.add(ShowLodges);
        actionArea.add(Box.createRigidArea(new Dimension(0, 5)));

    }

    private JScrollPane ShowLodges;
    private JPanel Panel;
    private JLabel noteLabel;
    private JScrollPane lodgeTable;
    private JTable lodgeList;
    private JFrame currentLodge;
    private JButton image;
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
    private JButton deleteButton;
    private JButton editButton;
    private JLabel editTitleLabel;
    private JTextField editTitleField;
    private JLabel editSizeLabel;
    private JTextField editSizeField;
    private JLabel editNoteLabel;
    private JLabel editIdLabel;
    private JTextField editBedsField;
    private JLabel editBedsLabel;
    private JScrollPane editDescriptionPanel;
    private JTextPane editDescriptionField;
    private JLabel editDescriptionLabel;
    private JLabel editPriceLabel;
    private JTextField editPriceField;
    private JLabel editAmenitiesLabel;
    private JButton editConfirmButton;
    private JButton editCancelButton;
    private JFrame editLodge;
    private JPanel editAmenitiesPanel;
    private Lodge lodge;
    private JButton editImageButton;
    private ImageIcon editImageIcon;

    static class lodgeTableModel extends AbstractTableModel {
        String[] columnNames = {"ID",
                "Title",
                "Location",
                "Type",
                "Size",
                "Price"};

        Object[][] data = new Object[Storage.getLodges().size()][];
        int k = 0;
        public lodgeTableModel(Landlord parentUser) {
            for (Lodge lodge : Storage.getLodges()) {
                if (lodge.getLandlord().equals(parentUser)) {
                    data[k] = new String[]{lodge.getLodgeId(), lodge.getDetails().getTitle(),
                            lodge.getDetails().getLocation(), lodge.getType().toString(),
                            String.valueOf(lodge.getDetails().getSize()), "€ " + lodge.getDetails().getPrice()};
                    if (lodge.getType().equals(LodgeType.ROOM)) {
                        for (Lodge tmpLodge : Storage.getLodges())
                            if (tmpLodge instanceof Hotel hotel)
                                if (hotel.getRooms().get(lodge.getLodgeId()) != null) {
                                    data[k][2] = hotel.getDetails().getLocation();
                                    break;
                                }
                    } else if (lodge.getType().equals(LodgeType.HOTEL)) {
                        data[k][4] = data[k][5] = "-";
                    }
                    k++;
                }
            }
        }

        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return data.length;
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        public void setValueAt(Object value, int row, int col) {
            data[row][col] = value;
            fireTableCellUpdated(row, col);
        }
    }


    private void editConfirmButton() {
        if(editConfirmButton.isEnabled()) {
            int size = lodge.getDetails().getSize(), beds = lodge.getDetails().getSize();
            double price = lodge.getDetails().getPrice();
            try {
                size = editSizeField.isEnabled() ? (Integer.parseInt(editSizeField.getText())) : size;
                price = editPriceField.isEnabled() ? (Double.parseDouble(editPriceField.getText())) : price;
                beds = editBedsField.isEnabled() ? (Integer.parseInt(editBedsField.getText())) : beds;
            } catch (NumberFormatException exception) {
                exception.printStackTrace();
                JOptionPane.showMessageDialog(editLodge,"Invalid information! Please check again!","Error",JOptionPane.ERROR_MESSAGE);
            }
            if(JOptionPane.showConfirmDialog(editLodge, "Confirm changes on lodge #" + lodge.getLodgeId() + "?",
                    "Confirm changes",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                lodge.getDetails().setPrice(price);
                lodge.getDetails().setSize(size);
                lodge.getDetails().setBeds(beds);
                lodge.getDetails().setDescription(descriptionLabel.getText());

                for(Component c : editAmenitiesPanel.getComponents())
                    if(c instanceof JCheckBox box) {
                        if (box.isSelected()) {
                            lodge.addAmenity(Amenities.valueOfLabel(box.getText()));
                        } else {
                            lodge.removeAmenity(Amenities.valueOfLabel(box.getText()));
                        }
                    }

                if(!editImageIcon.toString().equals("src/Misc/images/defaultLodgeImage.png"))
                    lodge.getDetails().setImage(editImageIcon);
                currentLodge.dispose();
                editLodge.dispose();
                refresh();
            }
        }
    }

    private Image getScaledImage(Image srcImg) {
        BufferedImage resizedImg = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, 100, 100, null);
        g2.dispose();
        return resizedImg;
    }
}
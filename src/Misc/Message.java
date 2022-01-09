package Misc;

import Users.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


/**
 * This class represents a text-message object.
 * @author Christos Balaktsis
 */

public class Message implements Serializable {

    private String body;
    private User sender;
    private User recipient;
    private ArrayList<User> chatters;
    private final Instant timestamp;

    public Message(User sender, User recipient, String body) {
        this.sender = sender;
        this.recipient = recipient;
        this.body = body;
        this.chatters = new ArrayList<>();
        this.chatters.add(sender);
        this.chatters.add(recipient);
        this.timestamp = Instant.now();
    }

    /**
     * @return The User that send the message.
     */
    public User getSender() {
        return sender;
    }

    /**
     * Updates the User that send the message.
     * @param sender The new User that send the message.
     */
    public void setSender(User sender) {
        this.sender = sender;
    }

    /**
     * @return The User that receives the message.
     */
    public User getRecipient() {
        return recipient;
    }

    /**
     * Updates the User that receives the message.
     * @param recipient The new User that receives the message.
     */
    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    /**
     * @return The message text body.
     */
    public String getBody() {
        return body;
    }

    /**
     * Updates the message text body.
     * @param body The new text body.
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * @return The timestamp of the mailing.
     */
    public Instant getTimestamp() {
        return this.timestamp;
    }

    public JPanel receiveToJPanel() {
        JPanel messagePanel = new JPanel();
        messagePanel.setBackground(Color.white);

//        ImageIcon image = new ImageIcon("src/Misc/images/User_icon.png");
//        ImageIcon scaledImage = new ImageIcon(image.getImage().getScaledInstance(10, 10, Image.SCALE_AREA_AVERAGING));
//        JPanel imagePanel = new JPanel();
//        imagePanel.add(new JLabel(scaledImage));

        JPanel detailPanel = new JPanel();
        detailPanel.setLayout(new FlowLayout());
        JTextArea messagePane = new JTextArea();
        messagePane.setText(this.body);
        messagePane.setEditable(false);
        messagePane.setFont(UIManager.getFont("message"));
        messagePane.addFocusListener(new FocusListener() {
            @Override
            public void focusLost(FocusEvent e) {
                messagePane.setEditable(true);

            }
            @Override
            public void focusGained(FocusEvent e) {
                messagePane.setEditable(false);

            }
        });
        detailPanel.add(messagePane);
        JPanel datePanel = new JPanel();
        datePanel.setLayout(new BorderLayout());
        datePanel.add(new JLabel("sent by " + sender.getUsername() + ", at " + LocalDateTime.ofInstant(getTimestamp(), ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))),BorderLayout.WEST);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, detailPanel, datePanel);
        splitPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        splitPane.setDividerSize(1);
        messagePanel.add(splitPane);

        messagePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE,(int)messagePanel.getPreferredSize().getHeight()));

        return messagePanel;
    }

    public JPanel sendToPanel() {
        JPanel messagePanel = new JPanel();
        messagePanel.setBackground(Color.white);

//        ImageIcon image = new ImageIcon("src/Misc/images/User_icon.png");
//        ImageIcon scaledImage = new ImageIcon(image.getImage().getScaledInstance(10, 10, Image.SCALE_AREA_AVERAGING));
//        JPanel imagePanel = new JPanel();
//        imagePanel.add(new JLabel(scaledImage));

        JPanel detailPanel = new JPanel();
        detailPanel.setLayout(new FlowLayout());
        JTextArea messagePane = new JTextArea();
        messagePane.setText(this.body);
        messagePane.setEditable(false);
        messagePane.setFont(UIManager.getFont("message"));
        messagePane.addFocusListener(new FocusListener() {
            @Override
            public void focusLost(FocusEvent e) {
                messagePane.setEditable(true);

            }
            @Override
            public void focusGained(FocusEvent e) {
                messagePane.setEditable(false);

            }
        });
        detailPanel.add(messagePane);
        JPanel datePanel = new JPanel();
        datePanel.setLayout(new BorderLayout());
        datePanel.add(new JLabel("sent by you, at " + LocalDateTime.ofInstant(getTimestamp(), ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))),BorderLayout.WEST);


        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, detailPanel, datePanel);
        splitPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        splitPane.setDividerSize(1);
        messagePanel.add(splitPane);

        messagePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE,(int)messagePanel.getPreferredSize().getHeight()));

        return messagePanel;
    }

    public ArrayList<User> getChatters() {
        return this.chatters;
    }

}

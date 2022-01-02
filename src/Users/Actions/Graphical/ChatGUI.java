package Users.Actions.Graphical;

import Misc.Message;
import Misc.Storage;
import Users.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.*;

public class ChatGUI {

    public static JPanel getChatGUI(User parentUser){
        JPanel messageArea = new JPanel();
        JPanel contactArea = new JPanel();
        JLabel noteLabel = new JLabel();
        JComboBox<String> usersBox = new JComboBox<>();
        JPanel bottomArea = new JPanel();
        JButton sendButton = new JButton();
        JScrollPane centerPane = new JScrollPane();
        JPanel chatArea = new JPanel();
        JScrollPane newMessage = new JScrollPane();
        JTextArea textMessage = new JTextArea();

        messageArea.setLayout(new BorderLayout());

        //======== contactArea ========
        contactArea.setLayout(new BoxLayout(contactArea, BoxLayout.Y_AXIS));
        //---- noteLabel ----
        noteLabel.setText("Pick a contact to view chat");
        contactArea.add(noteLabel);
        usersBox.addItem("---");
        for(User user : Storage.getUsers())
            if(!user.equals(parentUser)) usersBox.addItem(user.getFullName() != null ? user.getFullName() : user.getUsername());
        usersBox.addItemListener(e -> {updateChat(usersBox, centerPane, chatArea, parentUser);});
        contactArea.add(usersBox);
        messageArea.add(contactArea, BorderLayout.NORTH);

        //======== chatArea ========
        chatArea.setLayout(new BoxLayout(chatArea, BoxLayout.Y_AXIS));
        AdjustSize.AdjustPanelSize(chatArea);
        centerPane.setViewportView(chatArea);
        messageArea.add(centerPane, BorderLayout.CENTER);

        //======== bottomArea ========
        bottomArea.setLayout(new BoxLayout(bottomArea, BoxLayout.X_AXIS));
        newMessage.setViewportView(textMessage);
        bottomArea.add(newMessage);
        //---- sendButton ----
        sendButton.setText("Ok");
        sendButton.addActionListener(e -> {
            if(textMessage.getText().length() > 0) {
                User user;
                String fullname = Objects.requireNonNull(usersBox.getSelectedItem()).toString();
                if((user = User.getUserFromFullName(Objects.requireNonNull(fullname))) != null)
                    parentUser.sendMessageTo(user,textMessage.getText());
                updateChat(usersBox, centerPane, chatArea, parentUser);
                textMessage.setText("");
            }
        });
        bottomArea.add(sendButton);

        messageArea.add(bottomArea, BorderLayout.SOUTH);
        messageArea.setVisible(true);

        return messageArea;
    }

    private static void updateChat(JComboBox<String> usersBox, JScrollPane centerPane, JPanel chatArea, User parentUser) {
        chatArea.removeAll();
        chatArea.revalidate();
        chatArea.repaint();
        chatArea.setLayout(new BoxLayout(chatArea, BoxLayout.Y_AXIS));
        centerPane.setViewportView(chatArea);

        ArrayList<Message> messageArrayList = new ArrayList<>();

        for(Message message : Storage.getMessages())
            if(message.getChatters().contains(parentUser) && message.getChatters().contains(User.getUserFromFullName(Objects.requireNonNull(usersBox.getSelectedItem()).toString())))
                messageArrayList.add(message);

        messageArrayList.sort((o1, o2) -> {
            if (o1.getTimestamp() == o2.getTimestamp())
                return 0;
            return o1.getTimestamp().compareTo(o2.getTimestamp()) > 0 ? 1 : -1;
        });

        for(Message message : messageArrayList) {
            if (message.getRecipient().equals(parentUser))
                chatArea.add(message.receiveToJPanel());
            else
                chatArea.add(message.sendToPanel());
        }
    }

}

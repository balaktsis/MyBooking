import Misc.Message;

import org.junit.Before;
import org.junit.Test;

import Users.*;

import java.time.Instant;
import java.util.ArrayList;

import static org.junit.Assert.*;


/**
 * This is a test class for User subclasses/child-classes functionalities.
 * @author Christos Balaktsis
 */

public class MessageTest {
    Landlord landlord;
    Customer customer;
    Message message1, message2;

    @Before
    public void initializeObjects() {
        landlord = new Landlord("landlord","123456");
        customer = new Customer("customer","123456");
        landlord.setApprovalStatus(true);
        customer.setApprovalStatus(true);
        message1 = new Message(landlord,customer,"This is message test 1");
        message2 = new Message(customer,landlord,"This is message test 2.");
    }

    @Test
    public void setGetMessageDetailsTest() {
        assertEquals(landlord,message1.getSender());
        assertEquals(customer,message1.getRecipient());

        message2.setSender(landlord);
        message2.setRecipient(customer);
        assertEquals(landlord,message2.getSender());
        assertEquals(customer,message2.getRecipient());

        assertEquals("This is a message test 1",message1.getBody());
        message1.setBody("Testing in progress");
        assertEquals("Testing in progress", message1.getBody());

        assertTrue(message1.getChatters().contains(landlord));
        assertTrue(message1.getChatters().contains(customer));

    }


}

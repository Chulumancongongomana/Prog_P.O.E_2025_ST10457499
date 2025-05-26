import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class MessageTest {

    @Test
    public void testMessageLengthSuccess() {
        Message msg = new Message("+278123456", "Hi thanks");
        assertTrue(msg.getContent().length() <= 250);
    }

    @Test
    public void testMessageLengthFailure() {
        String longMsg = "x".repeat(251);
        assertTrue(longMsg.length() > 250);
    }

    @Test
    public void testRecipientValidationSuccess() {
        Message msg = new Message("+278123456", "Hello");
        assertTrue(msg.checkRecipientCell());
    }

    @Test
    public void testRecipientValidationFailure() {
        Message msg = new Message("1234567890", "Hello");
        assertFalse(msg.checkRecipientCell());
    }

    @Test
    public void testMessageHash() {
        Message msg = new Message("+278123456", "Hi Thanks");
        assertNotNull(msg.createMessageHash());
    }
}

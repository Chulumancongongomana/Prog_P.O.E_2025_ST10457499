import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Message 
{
    private static int messageCounter = 0;
    private static JSONArray storedMessages = new JSONArray();

    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageContent;
    private String messageHash;

    public Message(String recipient, String messageContent) {
        this.messageID = generateMessageID();
        this.messageNumber = ++messageCounter;
        this.recipient = recipient;
        this.messageContent = messageContent;
        this.messageHash = createMessageHash();
    }

    private String generateMessageID() {
        Random rand = new Random();
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            id.append(rand.nextInt(10));
        }
        return id.toString();
    }

    public boolean checkMessageID() {
        return messageID.length() == 10;
    }

    public boolean checkRecipientCell() {
        return recipient.length() <= 10 && recipient.startsWith("+");
    }

    public String createMessageHash() {
        String[] words = messageContent.split("\\s+");
        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 1 ? words[words.length - 1] : "";
        return (messageID.substring(0, 2) + ":" + messageNumber + ":" + firstWord + lastWord).toUpperCase();
    }

    public String sendMessage() {
        return "Message successfully sent.";
    }

    public String disregardMessage() {
        return "Message disregarded.";
    }

    public String storeMessage() {
        JSONObject obj = new JSONObject();
        obj.put("messageID", messageID);
        obj.put("messageHash", messageHash);
        obj.put("recipient", recipient);
        obj.put("message", messageContent);

        storedMessages.add(obj);
        try (FileWriter file = new FileWriter("messages.json")) {
            file.write(storedMessages.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Message successfully stored.";
    }

    public String printMessage() {
        return "Message ID: " + messageID + "\n" +
               "Message Hash: " + messageHash + "\n" +
               "Recipient: " + recipient + "\n" +
               "Message: " + messageContent;
    }

    public static int getTotalMessages() {
        return messageCounter;
    }
}

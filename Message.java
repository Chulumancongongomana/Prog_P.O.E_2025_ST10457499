import java.util.Random;

public class Message 
{
    private String messageID;
    private String recipient;
    private String content;
    private String messageHash;
    private static int totalMessages = 0;

    public Message(String recipient, String content) {
        this.messageID = generateMessageID();
        this.recipient = recipient;
        this.content = content;
        this.messageHash = createMessageHash();
        totalMessages++;
    }

    private String generateMessageID() {
        Random rand = new Random();
        String id = "";
        for (int i = 0; i < 10; i++) {
            id += rand.nextInt(10);
        }
        return id;
    }

    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    public boolean checkRecipientCell() {
        return recipient.startsWith("+") && recipient.length() <= 10;
    }

    public String createMessageHash() {
        String[] words = content.split("\\s+");
        String firstWord = words.length >= 1 ? words[0].toUpperCase() : "";
        String lastWord = words.length >= 2 ? words[words.length - 1].toUpperCase() : "";
        return messageID.substring(0, 2) + ":" + totalMessages + ":" + firstWord + lastWord;
    }

    public String sendMessage() {
        return "Message successfully sent.";
    }

    public String disregardMessage() {
        return "Press 0 to delete message.";
    }

    public String storeMessage() {
        return "Message successfully stored.";
    }

    public String getMessageID() {
        return messageID;
    }

    public String getMessageHash() {
        return messageHash;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getContent() {
        return content;
    }

    public static int getTotalMessages() {
        return totalMessages;
    }
}


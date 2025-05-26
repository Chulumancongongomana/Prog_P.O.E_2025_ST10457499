import java.util.*;
import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class QuickChat {
    static Scanner scanner = new Scanner(System.in);
    static List<Message> messageList = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Welcome to QuickChat.");
        System.out.print("How many messages do you want to send? ");
        int numMessages = scanner.nextInt();
        scanner.nextLine();

        boolean isLoggedIn = loginUser();
        if (!isLoggedIn) {
            System.out.println("Login failed. Exiting.");
            return;
        }

        for (int i = 0; i < numMessages; i++) {
            System.out.println("\n--- Message #" + (i + 1) + " ---");
            System.out.print("Enter recipient number (max 10 chars, starts with '+'): ");
            String recipient = scanner.nextLine();

            System.out.print("Enter your message (max 250 chars): ");
            String content = scanner.nextLine();
            if (content.length() > 250) {
                System.out.println("Message exceeds 250 characters by " + (content.length() - 250) + ", please reduce size.");
                i--; continue;
            }

            Message msg = new Message(recipient, content);

            if (!msg.checkRecipientCell()) {
                System.out.println("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.");
                i--; continue;
            }

            System.out.println("\nSelect an option:\n1) Send Message\n2) Disregard Message\n3) Store Message");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    JOptionPane.showMessageDialog(null, formatMessageDetails(msg));
                    messageList.add(msg);
                    System.out.println(msg.sendMessage());
                    break;
                case 2:
                    System.out.println(msg.disregardMessage());
                    break;
                case 3:
                    storeMessageToJSON(msg);
                    System.out.println(msg.storeMessage());
                    break;
                default:
                    System.out.println("Invalid option. Message disregarded.");
            }
        }

        System.out.println("\nTotal messages sent: " + Message.getTotalMessages());
    }

    private static boolean loginUser() {
        System.out.print("Enter username: ");
        String user = scanner.nextLine();
        System.out.print("Enter password: ");
        String pass = scanner.nextLine();
        // Simulate login logic (hardcoded for now)
        return user.equals("admin") && pass.equals("password");
    }

    private static void storeMessageToJSON(Message msg) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter("messages.json", true)) {
            writer.write(gson.toJson(msg) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String formatMessageDetails(Message msg) {
        return "Message ID: " + msg.getMessageID() +
               "\nMessage Hash: " + msg.getMessageHash() +
               "\nRecipient: " + msg.getRecipient() +
               "\nMessage: " + msg.getContent();
    }
}


package contactsManager;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

public class ConatctsApp {
    public static Input input = new Input();

    public static String directory = "src/contactsManager/";
    public static String filename = "contacts";
    public static Path contactsPath = Paths.get(directory, filename);

    public static void main(String[] args) {

        while (true) {
            //TODO: Create welcome menu
            int userOption = mainMenu();

            if (userOption == 5) {
                break;
            }
            if (userOption == 1) {
                //TODO: Show all contacts
                showContacts();
            }
            if (userOption == 2) {
                //TODO: Add a contact
                getContactInfo();

            }
//            if (userOption == 3) {
//                //TODO: Search contact by name
//            }
//            if (userOption == 4) {
//                //TODO: Delete existing contact
//            }
        }
    }

    public static int mainMenu() {
        int userSelection = input.getInt("\nPlease make a selection:\n" +
                "1. View contacts.\n" +
                "2. Add a new contact.\n" +
                "3. Search a contact by name.\n" +
                "4. Delete an existing contact.\n" +
                "5. Exit.\n" +
                "Enter an option (1, 2, 3, 4 or 5):");
        if (userSelection > 0 && userSelection < 6) {
            return userSelection;
        } else {
            return mainMenu();
        }
    }

    public static void showContacts() {
        try {
            System.out.println();
            List<String> displayContacts = Files.readAllLines(contactsPath);
            System.out.println(displayContacts);
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getContactInfo() {
        //get contact name
        String newName = input.getString("Enter first and last name:");

        //get phone number
        String newNumber = input.getString("Enter a valid telephone number (including area code):");
// Create contact object
        Contact newContact = new Contact(newName, newNumber);
        addContact();

        //validate info
        if (!(newName.length() > 1 && newNumber.length() <= 7)) {
            System.out.println("Please try again.");
        }
    }

    public static void addContact() {
        try {
            Files.write(
                    contactsPath,
                    Arrays.asList("eggs"), // list with one item
                    StandardOpenOption.APPEND
            );
            showContacts();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

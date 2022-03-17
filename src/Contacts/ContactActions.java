package Contacts;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ContactActions {

    //checked and works
    public static void addContact(Contact contact)  {
        try {
            String directory = "src/Contacts/documents/";
            String filename = "contacts.txt";
            Path filepath = Paths.get(directory, filename);
            List<String> contactsList = List.of(contact.toString());
            Files.write(filepath, contactsList, StandardOpenOption.APPEND);
            System.out.println("User added");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error adding contact");
        }

    }
    //checked and works
    public static void getContactInfoFromUser() throws IOException {
        Input input = new Input();
        System.out.println("Enter the first name of the contact\n");
        try {
            String firstName = input.getString();
            firstName = capitalize(firstName);
            System.out.println("Enter the last name of the contact\n");
            String lastName = input.getString();
            lastName = capitalize(lastName);
            String name = firstName + " " + lastName;
            if (checkIfContactExists(name)) {
                System.out.println("Enter the phone number of the contact: (enter numbers only)\n");
                try {
                    String phoneNumber = input.getString();
                    phoneNumber = phoneNumber.replaceAll("\\D+","");
                    Contact contact = new Contact(name, phoneNumber);
                    addContact(contact);
                } catch (IllegalArgumentException p) {
                    System.out.println(p.getMessage());
                    getContactInfoFromUser();
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            getContactInfoFromUser();
        }
    }
    //checked and works
    public static void displayAllContacts() {
        try {
            String directory = "src/Contacts/documents/";
            String filename = "contacts.txt";
            Path filepath = Paths.get(directory, filename);
            List<String> displayContacts = Files.readAllLines(filepath);
            Collections.sort(displayContacts);
            System.out.println("   | Name                   | Phone number         |\n" +
                    "----------------------------------------------------");
            for (int i = 0; i < displayContacts.size(); i += 1) {
                System.out.println(String.format("%-3s", i + 1)  + displayContacts.get(i));
            }
        } catch  (IOException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error displaying contacts.txt");
        }
    }
    //checked and works
    public static void deleteContact() {
        try {
            String directory = "src/Contacts/documents/";
            String filename = "contacts.txt";
            Path filepath = Paths.get(directory, filename);
            List<String> contactsList = Files.readAllLines(filepath);
            Input input = new Input();
            System.out.println("Enter the name of the contact you want to delete");
            String name = input.getString();
            List<String> newList = new ArrayList<>();
            for (String contact : contactsList) {
                if (contact.toLowerCase().contains(name.toLowerCase())) {
                    newList.remove(contact);
                    System.out.println("Contact deleted");
                    break;
                }
                newList.add(contact);
            }
            Files.write(Paths.get(directory, filename), newList);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error deleting contact");
        }
    }
    //checked and works
    public static void searchContacts() {
        try {
            String directory = "src/Contacts/documents/";
            String filename = "contacts.txt";
            Path filepath = Paths.get(directory, filename);
            List<String> contactsList = Files.readAllLines(filepath);
            Input input = new Input();
            System.out.println("Search for a contact:");
            String name = input.getString();
            boolean finishedForLoop = true;
            for (String contact : contactsList) {
                if (contact.toLowerCase().contains(name.toLowerCase())) {
                    finishedForLoop = false;
                    System.out.println(contact);
//                    break;
                }
            }
            if(finishedForLoop) {
                System.out.println("Contact not found");
            }
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error finding contact");
        }

    }

    public static void editContact() throws IOException {
        String directory = "src/Contacts/documents/";
        String filename = "contacts.txt";
        Path filepath = Paths.get(directory, filename);
        List<String> contactsList = Files.readAllLines(filepath);
        Input input = new Input();
        System.out.println("Enter the name of the contact:");
        String name = input.getString();
        List<String> newList = new ArrayList<>();
        boolean finishedForLoop = true;
        for (String contact : contactsList) {
            if (contact.toLowerCase().contains(name.toLowerCase())) {
                finishedForLoop = false;
                System.out.println("Enter the correct first name of the contact\n");
                try {
                    String firstName = input.getString();
                    System.out.println("Enter the correct last name of the contact\n");
                    String lastName = input.getString();
                    name = firstName + " " + lastName;
                    System.out.println("Enter the correct phone number of the contact: (enter numbers only)\n");
                    try {
                        String phoneNumber = input.getString();
                        phoneNumber = phoneNumber.replaceAll("\\D+","");
                        Contact correctedContact = new Contact(name, phoneNumber);
                        newList.add(String.valueOf(correctedContact));
                        continue;
                    } catch (IllegalArgumentException p) {
                        System.out.println(p.getMessage());
                        editContact();
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    editContact();
                }
            }
            newList.add(contact);
        }
        Files.write(Paths.get(directory, filename), newList);
        if(finishedForLoop) {
            System.out.println("Contact not found");
        }
    }


    public static int contactMenu(){
        Input input = new Input();
        System.out.println("Welcome to your contacts.txt manager\n");
        System.out.println("1. View contacts.\n" +
                "2. Add a new contact.\n" +
                "3. Search a contact by name.\n" +
                "4. Delete an existing contact.\n" +
                "5. Edit an existing contact.\n" +
                "6. Exit.\n" +
                "Enter an option number (ex: 1, 2...):\n");
        return input.getInt(1,6);
    }

    public static boolean checkIfContactExists(String name){
        try {
            String directory = "src/Contacts/documents/";
            String filename = "contacts.txt";
            Path filepath = Paths.get(directory, filename);
            List<String> contactsList = Files.readAllLines(filepath);
            for (String contact : contactsList) {
                if (contact.toLowerCase().contains(name.toLowerCase())) {
                    System.out.println("This user already exists");
                    return false;
                }
            }
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error finding contact");
        }
        return true;
    }

    public static String capitalize(String str) throws IOException {
        if(str == null || str.isEmpty()) {
            System.out.println("Name input required");
            getContactInfoFromUser();
        }
        assert str != null;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

}
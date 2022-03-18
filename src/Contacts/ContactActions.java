package Contacts;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static Contacts.ContactsApp.contactNavigator;


public class ContactActions {

    //checked and works
    public static void addContact(Contact contact) {
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
    public static void getContactInfoFromUser() throws IOException, InterruptedException {
        Input input = new Input();
        System.out.print("\nEnter the first name of the contact\n> ");
        try {
            String firstName = input.getString();
            firstName = capitalize(firstName);
            System.out.print("\nEnter the last name of the contact\n> ");
            String lastName = input.getString();
            lastName = capitalize(lastName);
            String name = firstName + " " + lastName;
            if (checkIfContactExists(name)) {
                System.out.print("\nEnter the phone number of the contact: (enter numbers only)\n> ");
                try {
                    String phoneNumber = input.getString();
                    phoneNumber = phoneNumber.replaceAll("\\D+", "");
                    Contact contact = new Contact(name, phoneNumber);
                    addContact(contact);
                } catch (IllegalArgumentException p) {
                    System.out.println(p.getMessage());
                    contactNavigator();
                }
            }
        } catch (IllegalArgumentException | InterruptedException e) {
            System.out.println(e.getMessage());
            contactNavigator();
        }
    }

    //checked and works
    public static void displayContactsMenu() {
        try {
            String directory = "src/Contacts/documents/";
            String filename = "contacts.txt";
            Path filepath = Paths.get(directory, filename);
            List<String> displayContacts = Files.readAllLines(filepath);
            Input input = new Input();
            System.out.print("\n1. View contacts by first name.\n" +
                    "2. Display contacts by last name.\n" +
                    "Enter an option number:\n> ");
            int choice = input.getInt(1, 2);
            if (choice == 1) {
                displayNames(displayContacts);
            } else if (choice == 2) {
                lastNameFirst(displayContacts);
            }
        } catch (IOException ex) {
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
            System.out.print("\nEnter the name of the contact you want to delete\n> ");
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
            System.out.print("\nSearch for a contact:\n> ");
            String name = input.getString();
            boolean finishedForLoop = true;
            for (String contact : contactsList) {
                if (contact.toLowerCase().contains(name.toLowerCase())) {
                    finishedForLoop = false;
                    System.out.println(contact);
                    String[] splitStr = contact.split("\\|+");
                    String phoneNumber = splitStr[2].trim();
                    if(input.yesNo("\nWould you like to call this contact?\n> ")){
                        callContact(phoneNumber);
                    }
                    if(input.yesNo("\nWould you text message to call this contact?\n> ")){
                        messageContact(phoneNumber);
                    }
                }
            }
            if (finishedForLoop) {
                System.out.println("Contact not found");
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error finding contact");
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("Invalid Phone Number");
        }

    }

    public static void editContact() throws IOException {
        String directory = "src/Contacts/documents/";
        String filename = "contacts.txt";
        Path filepath = Paths.get(directory, filename);
        List<String> contactsList = Files.readAllLines(filepath);
        Input input = new Input();
        System.out.print("\nEnter the name of the contact:\n> ");
        String name = input.getString();
        List<String> newList = new ArrayList<>();
        boolean finishedForLoop = true;
        for (String contact : contactsList) {
            if (contact.toLowerCase().contains(name.toLowerCase())) {
                finishedForLoop = false;
                System.out.print("\nEnter the correct first name of the contact\n> ");
                try {
                    String firstName = input.getString();
                    System.out.print("\nEnter the correct last name of the contact\n> ");
                    String lastName = input.getString();
                    name = firstName + " " + lastName;
                    System.out.print("\nEnter the correct phone number of the contact: (enter numbers only)\n> ");
                    try {
                        String phoneNumber = input.getString();
                        phoneNumber = phoneNumber.replaceAll("\\D+", "");
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
        if (finishedForLoop) {
            System.out.println("Contact not found");
        }
    }


    public static int contactMenu() {
        Input input = new Input();
        System.out.println("Welcome to your contacts manager\n");
        System.out.print("\n1. View contacts.\n" +
                "2. Add a new contact.\n" +
                "3. Search a contact by name.\n" +
                "4. Delete an existing contact.\n" +
                "5. Edit an existing contact.\n" +
                "6. Make a call.\n" +
                "7. Send a text message.\n" +
                "8. Exit.\n" +
                "Enter an option number (ex: 1, 2...):\n> ");
        return input.getInt(1, 8);
    }

    public static boolean checkIfContactExists(String name) {
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
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error finding contact");
        }
        return true;
    }

    public static String capitalize(String str) throws IOException, InterruptedException {
        if (str == null || str.isEmpty()) {
            System.out.println("Name input required");
            getContactInfoFromUser();
        }
        assert str != null;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static void lastNameFirst(List<String> displayContacts) {
        List<String> revisedContacts = new ArrayList<>();
        for (String displayContact : displayContacts) {
            String[] splitStr = displayContact.split("\\|+");
            String[] secondSplit = splitStr[1].split("\\s+");
            String firstName = secondSplit[1];
            String lastName = secondSplit[secondSplit.length - 1];
            String name = lastName + ", " + firstName;
            name = String.format("%-22.22s", name);
            String lastNameFirst = "| " + name + " |" + splitStr[2] + "|";
            revisedContacts.add(lastNameFirst);
        }
        displayNames(revisedContacts);
    }

    public static void displayNames(List<String> displayContacts) {
        Collections.sort(displayContacts);
        System.out.println("   | Name                   | Phone number         |\n" +
                "----------------------------------------------------");
        for (int i = 0; i < displayContacts.size(); i += 1) {
            System.out.println(String.format("%-3s", i + 1) + displayContacts.get(i));
        }
        Input input = new Input();
        if (input.yesNo("\nWould you like to sort contacts in reverse alphabetical order?\n> ")) {
            Collections.reverse(displayContacts);
            System.out.println("   | Name                   | Phone number         |\n" +
                    "----------------------------------------------------");
            for (int i = 0; i < displayContacts.size(); i += 1) {
                System.out.println(String.format("%-3s", i + 1) + displayContacts.get(i));
            }
        }
    }

    public static void callContact(String phoneNumber) throws IOException, InterruptedException {
        String cmd = "open facetime://" + phoneNumber;
        macInterface(cmd);
    }
    public static void messageContact(String phoneNumber) throws IOException, InterruptedException {
        String cmd = "open imessage://" + phoneNumber;
        macInterface(cmd);
        }

    public static void macInterface(String cmd) throws InterruptedException, IOException {
        Runtime run = Runtime.getRuntime();
        Process pr = run.exec(cmd);
        pr.waitFor();
        BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line = "";
        while ((line = buf.readLine()) != null) {
            System.out.println(line);
        }
    }

}

package Contacts;



import java.io.IOException;
import static Contacts.ContactActions.*;


public class ContactsApp {
    public static void main(String[] args) throws IOException, InterruptedException {
        contactNavigator();
    }
    public static void contactNavigator() throws IOException, InterruptedException {
        Input input = new Input();
        int answer = contactMenu();
        while (answer != 8) {
            if (answer == 1) {
                displayContactsMenu();
                if (input.yesNo("\nWould you like to do anything else? Y/N\n> ")) {
                    answer = contactMenu();
                } else {
                    answer = 8;
                }
            } else if (answer == 2) {
                getContactInfoFromUser();
                if (input.yesNo("\nWould you like to do anything else? Y/N\n> ")) {
                    answer = contactMenu();
                } else {
                    answer = 8;
                }
            } else if (answer == 3) {
                searchContacts();
                if (input.yesNo("\nWould you like to do anything else? Y/N\n> ")) {
                    answer = contactMenu();
                } else {
                    answer = 8;
                }
            } else if (answer == 4) {
                deleteContact();
                if (input.yesNo("\nWould you like to do anything else? Y/N\n> ")) {
                    answer = contactMenu();
                } else {
                    answer = 8;
                }
            } else if (answer == 5) {
                editContact();
                if (input.yesNo("\nWould you like to do anything else? Y/N\n> ")) {
                    answer = contactMenu();
                } else {
                    answer = 8;
                }
            } else if (answer == 6) {
                System.out.print("\nEnter the phone number to call.\n> ");
                String userNumber = input.getString();
                callContact(userNumber);
                answer = contactMenu();
            }
            else if (answer == 7) {
                System.out.print("\nEnter the phone number to text message.\n> ");
                String userNumber = input.getString();
                messageContact(userNumber);
                answer = contactMenu();
            }
        }

    }
}

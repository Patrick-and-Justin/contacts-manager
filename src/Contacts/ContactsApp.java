package Contacts;



import java.io.IOException;
import java.net.URISyntaxException;

import static Contacts.ContactActions.*;


public class ContactsApp {
    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
        contactNavigator();
    }
    public static void contactNavigator() throws IOException, InterruptedException, URISyntaxException {
        Input input = new Input();
        int answer = contactMenu();
        while (answer != 7) {
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
                answer = contactMenu();
            } else if (answer == 4) {
                System.out.print("\nEnter the phone number to call.\n> ");
                String userNumber = input.getString();
                callContact(userNumber);
                answer = contactMenu();
            }
            else if (answer == 5) {
                System.out.print("\nEnter the phone number to text message.\n> ");
                String userNumber = input.getString();
                messageContact(userNumber);
                answer = contactMenu();
            }
            else if (answer == 6) {
                System.out.print("\nEnter the email address you want to mail.\n> ");
                String email = input.getString();
                sendEmail(email);
                answer = contactMenu();
            }
        }

    }
}

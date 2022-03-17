package Contacts;

import java.util.List;

public class ContactTest {
    public static void main(String[] args) {

    }

    public static void lastNameFirst(List<String> displayContacts){
        for (String displayContact : displayContacts) {
            String[] splitStr = displayContact.split("\\|+");
            String[] secondSplit = splitStr[1].split("\\s+");
            String firstName = secondSplit[0];
            String lastName = secondSplit[secondSplit.length - 1];
            String name = lastName + ", " + firstName;
            name = String.format("%-22.22s", name);
            String lastNameFirst = "|" + name + " | " + splitStr[2] + "|";
            System.out.println("   | Name                   | Phone number         |\n" +
                    "----------------------------------------------------");
            System.out.println(lastNameFirst);
            }
        }
    }


package Contacts;

import java.util.Objects;

public class Contact {
    private String name;

    @Override
    public String toString() {
        return "| " + String.format("%-22.22s", name) +
                " | " + String.format("%-21.21s",phoneNumber) + "|";
    }

    private String phoneNumber;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }



    public Contact(String name, String phoneNumber) {
        if (name.length() < 4) {
            throw new IllegalArgumentException("Contact cannot be created because name is too short.");
        }
        if (phoneNumber.length() < 7) {
            throw new IllegalArgumentException("Contact cannot be created because phone number is too short.");
        } else if (phoneNumber.length() == 7) {
            this.phoneNumber = phoneNumber.replaceFirst("(\\d{3})(\\d+)", "$1-$2");
        } else if (phoneNumber.length() == 10) {
            this.phoneNumber = phoneNumber.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "$1-$2-$3");
        } else if (phoneNumber.length() == 11) {
            this.phoneNumber = phoneNumber.replaceFirst("(\\d{1})(\\d{3})(\\d{3})(\\d+)", "$1-$2-$3-$4");
        } else if (phoneNumber.length() == 12) {
            this.phoneNumber = phoneNumber.replaceFirst("(\\d{2})(\\d{3})(\\d{3})(\\d+)", "$1-$2-$3-$4");
        } else if (phoneNumber.length() == 13) {
            this.phoneNumber = phoneNumber.replaceFirst("(\\d{3})(\\d{3})(\\d{3})(\\d+)", "$1-$2-$3-$4");
        } else if (phoneNumber.length() == 14) {
            this.phoneNumber = phoneNumber.replaceFirst("(\\d{1})(\\d{3})(\\d{3})(\\d{3})(\\d+)", "$1-$2-$3-$4-$5");
        } else if (phoneNumber.length() == 15) {
            this.phoneNumber = phoneNumber.replaceFirst("(\\d{2})(\\d{3})(\\d{3})(\\d{3})(\\d+)", "$1-$2-$3-$4-$5");
        } else if (phoneNumber.length() == 16) {
            this.phoneNumber = phoneNumber.replaceFirst("(\\d{2})(\\d{4})(\\d{3})(\\d{3})(\\d+)", "$1-$2-$3-$4-$5");
        } else {
            throw new IllegalArgumentException("Contact cannot be created because the entry is invalid.");
        }
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;
        Contact contact = (Contact) o;
        return Objects.equals(phoneNumber, contact.phoneNumber) && name.equals(contact.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phoneNumber);
    }
}
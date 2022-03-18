package Contacts;

import java.util.Objects;

public class Contact {
    private String name;
    private String phoneNumber;
    private String email;

    @Override
    public String toString() {
        return "| " + String.format("%-22.22s", name) +
                " | " + String.format("%-21.21s", phoneNumber) + "| " + String.format("%-30.30s", email) + "|";

    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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


    public Contact(String name, String phoneNumber, String email) {
        if (name.length() < 4) {
            throw new IllegalArgumentException("Contact cannot be created because name is too short.");
        }
        if (email.length() < 10) {
            throw new IllegalArgumentException("Contact cannot be created because email is too short.");
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
        this.email = email;
    }
}


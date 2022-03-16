package contactsManager;

import java.util.Scanner;

public class Input {
    private static Scanner scanner;

    public Input() {
        scanner = new Scanner(System.in);
    }

    public String getString(String prompt) {
        System.out.print(prompt + "\n> ");
        return scanner.nextLine();
    }

    public boolean yesNo(String prompt) {
        String reply = scanner.nextLine().toLowerCase();
        return reply.startsWith("y");
    }

    public int getInt(String prompt, int min, int max) {
        System.out.println(prompt);
        int userInt = scanner.nextInt();
        if (!(userInt >= min && userInt <= max)) {
            return getInt(prompt, min, max);
        }
        int myInt = userInt;
        scanner.nextLine();
        return myInt;
    }

    public int getInt(String prompt) {
        while (true) {
            try {
                String aString = getString(prompt);
                return Integer.valueOf(aString);
            } catch (NumberFormatException e) {
                System.out.println("Please try again.");
            }
        }
    }

    public double getDouble(String prompt, double min, double max) {
        System.out.println(prompt);
        double userDbl = scanner.nextDouble();
        if (!(userDbl >= 1 && userDbl <= 10)) {
            return getDouble(prompt, min, max);
        }
        double myDbl = userDbl;
        scanner.nextLine();
        return myDbl;
    }

    public double getDouble(String prompt) {
        while (true) {
            try {
                String aString = getString(prompt);
                return Double.valueOf(aString);
            } catch (NumberFormatException e) {
                System.out.print("\nPlease try again.\n");
                scanner.nextLine();
            }
        }
    }

    public void close() {
        scanner.close();
    }

    public static void main(String[] args) {

    }
}
package Contacts;

import java.util.Scanner;

public class Input {
    private Scanner scanner;

    public Input() {
        this.scanner = new Scanner(System.in);
    }

    public void close() {
        scanner.close();
    }


    public String getString() {
//        System.out.println("Please enter a string");
        return scanner.nextLine().trim();
    }

    public boolean yesNo(String prompt) {
        System.out.printf("%s", prompt);
        String response = getString().toLowerCase();
        return response.startsWith("y");
    }


    public int getInt(int min, int max) {
        String response = getString();
        try {
            while (Integer.parseInt(response) > max || Integer.parseInt(response) < min) {
                if (Integer.parseInt(response) > max) {
                    System.out.printf(response + " is greater than the maximum input of %d\n", max);
                    response = scanner.nextLine().trim();
                } else if (Integer.parseInt(response) < min) {
                    System.out.printf(response + " is less than the minimum input of %d\n", min);
                    response = scanner.nextLine().trim();
                }
            }
        } catch (NumberFormatException e) {
            System.out.println(response + " is not a valid input\n");
            response = scanner.nextLine().trim();
        }
        return Integer.parseInt(response);
    }

    public int getInt() {
//        System.out.print("Enter an integer:\n");
        String response = getString();
        try {
            Integer.parseInt(response);
        } catch (NumberFormatException e) {
            System.out.println(response + " is not a valid input\n");
            response = scanner.nextLine().trim();
        }
        return Integer.parseInt(response);
    }

    public double getDouble(double min, double max) {
        System.out.printf("Enter a double between %f and %f:\n", min, max);
        String response = getString();
        try {
            while (Double.parseDouble(response) > max || Double.parseDouble(response) < min) {
                if (Double.parseDouble(response) > max) {
                    System.out.printf(response + " is greater than the maximum input of %f\n", max);
                    response = scanner.nextLine().trim();
                } else if (Double.parseDouble(response) < min) {
                    System.out.printf(response + " is less than the minimum input of %f\n", min);
                    response = scanner.nextLine().trim();
                }
            }
        } catch (NumberFormatException e) {
            System.out.println(response + " is not a valid input\n");
            response = scanner.nextLine().trim();
        }
        return Double.parseDouble(response);
    }

    public double getDouble() {
        System.out.print("Enter a double:\n");
        String response = getString();
        try {
            Double.parseDouble(response);
        } catch (NumberFormatException e) {
            System.out.println(response + " is not a valid input\n");
            response = scanner.nextLine().trim();
        }
        return Double.parseDouble(response);
    }

    public int getBinary() {
        System.out.println("Enter binary number:\n");
        String response = getString();
        try {
            Integer.parseInt(response, 2);
        } catch (NumberFormatException e) {
            System.out.println(response + " is not a valid input\n");
            response = scanner.nextLine().trim();
        }
        return Integer.parseInt(response, 2);
    }

    public int getHex() {
        System.out.println("Enter a hexidecimal number:\n");
        String response = getString().toUpperCase();
        try {
            Integer.parseInt(response, 16);
        } catch (NumberFormatException e) {
            System.out.println(response + " is not a valid input\n");
            response = scanner.nextLine().trim();
        }
        return Integer.parseInt(response, 16);
    }
}
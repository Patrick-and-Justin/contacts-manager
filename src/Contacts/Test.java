package Contacts;


import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

public class Test {
    public static void main(String[] args) throws InterruptedException, IOException, URISyntaxException {
        sendEmail();
    }
    public static void sendEmail() throws URISyntaxException, IOException {
        Input input = new Input();
        System.out.println("Enter the mail address");
        String emailAddress = input.getString();
        Desktop desktop;
        if (Desktop.isDesktopSupported()
                && (desktop = Desktop.getDesktop()).isSupported(Desktop.Action.MAIL)) {
            URI mailto = new URI("mailto:" + emailAddress + "?subject=Look%20What%20This%20App%20Can%20Do");
            desktop.mail(mailto);
        } else {
            throw new RuntimeException("desktop doesn't support mailto");
        }

    }





    }





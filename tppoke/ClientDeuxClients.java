package tppoke;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientDeuxClients {

    public static void main(String[] args) {
        Socket socket = null;

        try {
            socket = new Socket("localhost", 1234);
            System.out.println("Connecté au serveur");

            Scanner in = new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Create a thread to read messages from the server
            Thread serverListener = new Thread(() -> {
                try {
                    while (in.hasNextLine()) {
                        String serverMessage = in.nextLine();
                        System.out.println(serverMessage);
                    }
                } catch (Exception e) {
                    System.err.println("Erreur de communication avec le serveur : " + e.getMessage());
                    System.exit(1);
                }
            });
            serverListener.start();

            // Read messages from the console and send them to the server
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                try {
                    String myMessage = scanner.nextLine();
                    out.println(myMessage);
                } catch (Exception e) {
                    System.err.println("Client fermé : " + e.getMessage());
                    socket.close();
                    System.exit(0);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null && !socket.isClosed()) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


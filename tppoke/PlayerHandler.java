package tppoke;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class PlayerHandler implements Runnable {
    private Socket socket;
    private Player player;

    public PlayerHandler(Socket socket, Player player) {
        this.socket = socket;
        this.player = player;
    }

    @Override
    public void run() {
        try (
            Scanner scanner = new Scanner(socket.getInputStream());
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)
        ) {
            // Receive and send messages
            while (true) {
                String receivedMessage = scanner.nextLine();
                System.out.println(player.getTrainer().getName() + " received: " + receivedMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        try (PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {
            writer.println(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

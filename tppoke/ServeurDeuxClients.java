package tppoke;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ServeurDeuxClients {

    private static List<ClientHandler> clients = new ArrayList<>();
    private static Dresseur player1Dresseur;
    private static Dresseur player2Dresseur;

    public static void main(String[] args) {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(1234);
            System.out.println("Le serveur est en attente de connexions...");

            // Connect the first player
            connectPlayer(serverSocket);

            // Wait for the first player to choose Pokémon
            while (player1Dresseur == null) {
                // Wait for the first player
            }

            // Connect the second player
            connectPlayer(serverSocket);

            // Wait for the second player to choose Pokémon
            while (player2Dresseur == null) {
                // Wait for the second player
            }

            // Notify both players that they can start the battle
            clients.get(0).sendMessage("Les deux joueurs sont connectés. Le combat commence !");
            clients.get(1).sendMessage("Les deux joueurs sont connectés. Le combat commence !");

            // Perform the battle
            String result = performCombat();

            // Notify players of the battle result
            clients.get(0).sendMessage(result);
            clients.get(1).sendMessage(result);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void connectPlayer(ServerSocket serverSocket) throws IOException {
        Socket clientSocket = serverSocket.accept();
        System.out.println("Client connecté");

        ClientHandler clientHandler = new ClientHandler(clientSocket);
        clients.add(clientHandler);

        Thread clientThread = new Thread(clientHandler);
        clientThread.start();
    }

    private static Dresseur assignPokemon(String playerName) {
        Dresseur dresseur = new Dresseur(playerName);
        Pokemon randomPokemon = PokemonLoader.getRandomPokemon();
        dresseur.capturePokemon(randomPokemon);
        return dresseur;
    }

    private static String performCombat() {
        Pokemon player1Pokemon = player1Dresseur.getPokemonList().get(0);
        Pokemon player2Pokemon = player2Dresseur.getPokemonList().get(0);

        // Perform the battle
        while (player1Pokemon.getHealthPoints() > 0 && player2Pokemon.getHealthPoints() > 0) {
            // Player 1 attacks Player 2
            player1Pokemon.attack(player2Pokemon);

            // Check if Player 2's Pokémon is knocked out
            if (player2Pokemon.getHealthPoints() <= 0) {
                return player1Dresseur.getName() + " gagne avec " + player1Pokemon.getName() + "!";
            }

            // Player 2 attacks Player 1
            player2Pokemon.attack(player1Pokemon);

            // Check if Player 1's Pokémon is knocked out
            if (player1Pokemon.getHealthPoints() <= 0) {
                return player2Dresseur.getName() + " gagne avec " + player2Pokemon.getName() + "!";
            }
        }

        // If the loop exits, it's a draw
        return "Match nul!";
    }

    static class ClientHandler implements Runnable {

        private Socket clientSocket;
        private PrintWriter out;
        private Scanner in;
        private String clientIdentifier;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new Scanner(clientSocket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                out.println("Veuillez saisir votre identifiant : ");
                clientIdentifier = in.nextLine();
                out.println("Bienvenue, " + clientIdentifier + "!");
                
                if (player1Dresseur == null) {
                    // Assign Pokémon to the first player
                    player1Dresseur = assignPokemon(clientIdentifier);
                } else {
                    // Assign Pokémon to the second player
                    player2Dresseur = assignPokemon(clientIdentifier);
                }

                while (true) {
                    String message = in.nextLine();
                    for (ClientHandler client : clients) {
                        if (client != this) {
                            client.out.println(clientIdentifier + " : " + message);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void sendMessage(String message) {
            out.println(message);
        }
    }
}


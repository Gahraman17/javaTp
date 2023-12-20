package tppoke;

public class Player {
    private ServeurDeuxClients.ClientHandler clientHandler;
    private Dresseur trainer;

    public Player(ServeurDeuxClients.ClientHandler clientHandler, Dresseur trainer) {
        this.clientHandler = clientHandler;
        this.trainer = trainer;
    }

    public ServeurDeuxClients.ClientHandler getClientHandler() {
        return clientHandler;
    }

    public Dresseur getTrainer() {
        return trainer;
    }

    public void sendMessage(String message) {
        clientHandler.sendMessage(message);
    }
}

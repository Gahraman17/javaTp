package tppoke;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game implements Runnable {
    private Player player1;
    private Player player2;
    private int currentPlayerIndex;

    private List<Pokemon> player1PokemonList;
    private List<Pokemon> player2PokemonList;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayerIndex = 0;

        this.player1PokemonList = player1.getTrainer().getPokemonList();
        this.player2PokemonList = player2.getTrainer().getPokemonList();
    }

    @Override
    public void run() {
        System.out.println("Game started!");

        while (!isGameOver()) {
            Player currentPlayer = getCurrentPlayer();
            Player opponentPlayer = getOpponentPlayer();

            // Display game state
            displayGameState(currentPlayer, opponentPlayer);

            // Player makes a move (for now, just choosing a Pokemon to attack with)
            Pokemon attackingPokemon = choosePokemonToAttack(currentPlayer);

            // Opponent chooses a Pokemon to defend with
            Pokemon defendingPokemon = choosePokemonToDefend(opponentPlayer);

            // Simulate combat on the server
            String combatResult = simulateCombat(attackingPokemon, defendingPokemon);

            // Broadcast combat result to both players
            player1.sendMessage(combatResult);
            player2.sendMessage(combatResult);

            // Switch to the next player for the next turn
            switchPlayer();
        }

        System.out.println("Game over!");
    }

    private boolean isGameOver() {
        // Implement a condition for game over (e.g., a certain number of turns)
        return false;
    }

    private void displayGameState(Player currentPlayer, Player opponentPlayer) {
        System.out.println("\n" + currentPlayer.getTrainer().getName() + "'s turn:");
        System.out.println("---------------");
        System.out.println("Player 1: " + player1.getTrainer().getName() + "'s Pokémon:");
        displayPokemonList(player1);

        System.out.println("\nPlayer 2: " + player2.getTrainer().getName() + "'s Pokémon:");
        displayPokemonList(player2);
        System.out.println("---------------");
    }

    private Pokemon choosePokemonToAttack(Player player) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(player.getTrainer().getName() + ", choose a Pokemon to attack with:");
        displayPokemonList(player);

        int choice = scanner.nextInt();
        return player.getTrainer().getPokemonList().get(choice - 1);
    }

    private Pokemon choosePokemonToDefend(Player player) {
        // For simplicity, opponent randomly chooses a Pokemon to defend with
        Random random = new Random();
        return player.getTrainer().getPokemonList().get(random.nextInt(player.getTrainer().getPokemonList().size()));
    }

    private String simulateCombat(Pokemon attacker, Pokemon defender) {
        // Simulate combat and return the result message
        return attacker.getName() + " attacks " + defender.getName() + "! Combat Result: " +
                (attacker.getCombatPoints() > defender.getCombatPoints() ? "Victory!" : "Defeat!");
    }

    private void displayPokemonList(Player player) {
        for (int i = 0; i < player.getTrainer().getPokemonList().size(); i++) {
            Pokemon pokemon = player.getTrainer().getPokemonList().get(i);
            System.out.println((i + 1) + ". " + pokemon.getName() + " - Type: " + pokemon.getType() +
                    " - HP: " + pokemon.getHealthPoints() + ", CP: " + pokemon.getCombatPoints() + ", Candies: " + pokemon.getCandies());
        }
    }

    private Player getCurrentPlayer() {
        return (currentPlayerIndex == 0) ? player1 : player2;
    }

    private Player getOpponentPlayer() {
        return (currentPlayerIndex == 0) ? player2 : player1;
    }

    private void switchPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % 2;
    }
}

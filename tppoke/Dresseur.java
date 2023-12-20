package tppoke;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Dresseur {
    private String name;
    private List<Pokemon> pokemonList;
    private int candies;

    public Dresseur(String name) {
        this.name = name;
        this.pokemonList = new ArrayList<>();
        this.candies = 0;
    }

    public String getName() {
        return name;
    }

    public List<Pokemon> getPokemonList() {
        return pokemonList;
    }
    public int getCandies() {
        return candies;
    }

    public void capturePokemon(Pokemon pokemon) {
        double captureChance = Math.random();  // Generates a random number between 0 and 1

        if (captureChance < 0.8) {  // 80% capture success rate, adjust as needed
            pokemonList.add(pokemon);
            candies++;  // Increment candies when capturing a new Pokemon
            System.out.println(name + " captured a " + pokemon.getName() + "!");
            System.out.println(name + " gained a candy for " + pokemon.getName() + ".");
        } else {
            System.out.println(name + " failed to capture " + pokemon.getName() + ". Try again!");
        }
    }



    public void upgradePokemon() {
        if (candies >= 5 && !pokemonList.isEmpty()) {
            System.out.println(name + " has enough candies to upgrade a Pokémon!");

            // Display the list of Pokémon
            System.out.println("Choose a Pokémon to upgrade:");
            for (int i = 0; i < pokemonList.size(); i++) {
                Pokemon pokemon = pokemonList.get(i);
                System.out.println((i + 1) + ". " + pokemon.getName() +
                        " - Type: " + pokemon.getType() +
                        " - HP: " + pokemon.getHealthPoints() +
                        ", CP: " + pokemon.getCombatPoints());
            }

            // Allow the trainer to choose a Pokémon by entering its number
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the number of the Pokémon you want to upgrade: ");
            int choice = scanner.nextInt();

            if (choice >= 1 && choice <= pokemonList.size()) {
                Pokemon selectedPokemon = pokemonList.get(choice - 1);

                if (selectedPokemon.getCandies() >= 5) {
                    selectedPokemon.evolve();
                    candies -= 5;  // Deduct candies after upgrade
                    System.out.println(name + " upgraded " + selectedPokemon.getName() + "!");
                } else {
                    System.out.println(name + " does not have enough candies to upgrade " + selectedPokemon.getName() + ".");
                }
            } else {
                System.out.println("Invalid choice. Please choose a Pokémon to upgrade.");
            }
        } else {
            System.out.println(name + " does not have enough candies or Pokémon to upgrade.");
        }
    }
    public void choosePokemon() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Veuillez saisir le nom de votre Pokémon : ");
        String pokemonName = scanner.nextLine();
        
        // Search for the chosen Pokemon in the list
        for (Pokemon pokemon : pokemonList) {
            if (pokemon.getName().equalsIgnoreCase(pokemonName)) {
                System.out.println(name + " a choisi " + pokemon.getName() + "!");
                return;
            }
        }

        System.out.println(name + " n'a pas pu choisir de Pokémon valide.");
    }
    
}
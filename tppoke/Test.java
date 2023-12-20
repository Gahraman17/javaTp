package tppoke;

public class Test {
    public static void main(String[] args) {
        // Create a trainer
        Dresseur ash = new Dresseur("Ash");

        // Get a random Pokémon from the file
        Pokemon randomPokemon = PokemonLoader.getRandomPokemon();
        Pokemon randomPokemon2 = PokemonLoader.getRandomPokemon();
        Pokemon randomPokemon3 = PokemonLoader.getRandomPokemon();
        Pokemon randomPokemon4 = PokemonLoader.getRandomPokemon();
        Pokemon randomPokemon5 = PokemonLoader.getRandomPokemon();
        Pokemon randomPokemon6 = PokemonLoader.getRandomPokemon();
        
        if (randomPokemon != null) {
            // Capture the random Pokémon
            ash.capturePokemon(randomPokemon);
            System.out.println(ash.getName() + " captured a " + randomPokemon.getName() + "!");

        } else {
            System.out.println("Error: Unable to load a random Pokémon from the file.");
        }

        if (randomPokemon2 != null) {
            // Capture the random Pokémon
            ash.capturePokemon(randomPokemon2);
            System.out.println(ash.getName() + " captured a " + randomPokemon2.getName() + "!");
          
        } else {
            System.out.println("Error: Unable to load a random Pokémon from the file.");
        }
        if (randomPokemon3 != null) {
            // Capture the random Pokémon
            ash.capturePokemon(randomPokemon3);
            System.out.println(ash.getName() + " captured a " + randomPokemon3.getName() + "!");
          
        } else {
            System.out.println("Error: Unable to load a random Pokémon from the file.");
        }
        if (randomPokemon4 != null) {
            // Capture the random Pokémon
            ash.capturePokemon(randomPokemon4);
            System.out.println(ash.getName() + " captured a " + randomPokemon4.getName() + "!");
          
        } else {
            System.out.println("Error: Unable to load a random Pokémon from the file.");
        }
        if (randomPokemon5 != null) {
            // Capture the random Pokémon
            ash.capturePokemon(randomPokemon5);
            System.out.println(ash.getName() + " captured a " + randomPokemon5.getName() + "!");
          
        } else {
            System.out.println("Error: Unable to load a random Pokémon from the file.");
        }
        if (randomPokemon6 != null) {
            // Capture the random Pokémon
            ash.capturePokemon(randomPokemon6);
            System.out.println(ash.getName() + " captured a " + randomPokemon6.getName() + "!");
          
        } else {
            System.out.println("Error: Unable to load a random Pokémon from the file.");
        }
        // Display trainer's Pokémon and candies
        System.out.println("\n" + ash.getName() + "'s Pokémon:");
        for (Pokemon pokemon : ash.getPokemonList()) {
            System.out.println(pokemon.getName() + " - Type: " + pokemon.getType() +
                    " - HP: " + pokemon.getHealthPoints() + ", CP: " + pokemon.getCombatPoints() + ", Candies: " + pokemon.getCandies());
        }

        System.out.println("\n" + ash.getName() + "'s Candies: " + ash.getCandies());

        // Upgrade a Pokémon
        ash.upgradePokemon();
    }
}

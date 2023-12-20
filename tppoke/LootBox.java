package tppoke;

import java.util.Random;

public class LootBox {
    private static final String[] POKEMON_NAMES = {"Bulbasaur", "Charmander", "Squirtle", "Pikachu"};
    private static final String[] POKEMON_TYPES = {"Grass", "Fire", "Water", "Electric"};

    private Random random;

    public LootBox() {
        this.random = new Random();
    }

    public Pokemon open() {
        String randomPokemonName = POKEMON_NAMES[random.nextInt(POKEMON_NAMES.length)];
        String correspondingType = getCorrespondingType(randomPokemonName);

        Pokemon basePokemon = Pokemon.createRandomPokemon(randomPokemonName, correspondingType);

        // Randomly decide if the Pokemon has an evolution
        if (random.nextBoolean()) {
            Pokemon evolution1 = Pokemon.createRandomPokemon("Evolution1", correspondingType);
            Pokemon evolution2 = Pokemon.createRandomPokemon("Evolution2", correspondingType);

            basePokemon.setEvolution(evolution1);
            evolution1.setEvolution(evolution2);
        }

        return basePokemon;
    }

    private String getCorrespondingType(String pokemonName) {
        // This method maps each Pokemon to its corresponding type
        // You can expand this method if needed
        switch (pokemonName) {
            case "Bulbasaur":
                return "Grass";
            case "Charmander":
                return "Fire";
            case "Squirtle":
                return "Water";
            case "Pikachu":
                return "Electric";
            default:
                return "Unknown";
        }
    }
}


package tppoke;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PokemonLoader {
	   private static final String POKEMON_DATA_FILE = "C:\\Users\\hp\\Desktop\\pokeman_jeu.json";
	    private static final Random random = new Random();

	    public static List<Pokemon> loadPokemon() {
	        try (FileReader reader = new FileReader(POKEMON_DATA_FILE)) {
	            Type listType = new TypeToken<List<Pokemon>>() {}.getType();
	            return new Gson().fromJson(reader, listType);
	        } catch (IOException e) {
	            e.printStackTrace();
	            System.err.println("Error loading Pokémon data from file.");
	            return Collections.emptyList();
	        }
	    }

	    public static Pokemon getRandomPokemon() {
	        List<Pokemon> allPokemon = loadPokemon();
	        if (allPokemon != null && !allPokemon.isEmpty()) {
	            int randomIndex = random.nextInt(allPokemon.size());
	            return allPokemon.get(randomIndex);
	        } else {
	            return null;
	        }
	    }
	}




  

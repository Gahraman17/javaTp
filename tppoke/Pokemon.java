package tppoke;

import java.util.Random;

public class Pokemon {
    private String name;
    private String type;
    private int healthPoints;
    private int combatPoints;
    private Pokemon evolution;
    private int candies;  // Added candies attribute

    public Pokemon(String name, String type, int healthPoints, int combatPoints, Pokemon evolution, int candies) {
        this.name = name;
        this.type = type;
        this.healthPoints = healthPoints;
        this.combatPoints = combatPoints;
        this.evolution = evolution;
        this.candies = candies;
    }

    public Pokemon() {
        // No-args
    }

    public String getName() {
        return name;
    }

    public int getCandies() {
        return candies;
    }

    public void setCandies(int candies) {
        this.candies = candies;
    }

    public String getType() {
        return type;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public int getCombatPoints() {
        return combatPoints;
    }

    public Pokemon getEvolution() {
        return evolution;
    }

    public void setEvolution(Pokemon evolution) {
        this.evolution = evolution;
    }

    public void evolve() {
        if (evolution != null && candies >= 5) {  // Require 5 candies for evolution
            System.out.println(name + " is evolving into " + evolution.getName() + "!");
            healthPoints += 10;  // Increase health points when evolving
            combatPoints += 5;   // Increase combat points when evolving
            candies -= 5;        // Deduct candies after evolution
            System.out.println("New HP: " + healthPoints + ", New CP: " + combatPoints);
        } else {
            System.out.println(name + " does not have enough candies to evolve.");
        }
    }

    public void useCandiesToEnhance() {
        if (evolution == null && candies > 0) {
            healthPoints += getRandomValue(1, 10);
            combatPoints += getRandomValue(1, 5);
            candies--;  // Deduct candies after enhancement
            System.out.println(name + " has been enhanced! New HP: " + healthPoints + ", New CP: " + combatPoints);
        } else {
            System.out.println(name + " cannot be enhanced further or does not have enough candies.");
        }
    }

    // Method to randomly assign PC and PV during creation
    private static int getRandomValue(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    public static Pokemon createRandomPokemon(String name, String type) {
        int randomHP = getRandomValue(50, 100);
        int randomCP = getRandomValue(10, 50);

        return new Pokemon(name, type, randomHP, randomCP, null, 0);
    }
    public void attack(Pokemon opponent) {
        int damage = calculateDamage();
        opponent.takeDamage(damage);
        System.out.println(name + " attaque " + opponent.getName() + " et inflige " + damage + " dégâts!");
    }

    private int calculateDamage() {
        // Simple damage calculation logic (you can adjust this)
        return combatPoints / 2;
    }

    private void takeDamage(int damage) {
        healthPoints -= damage;
        if (healthPoints < 0) {
            healthPoints = 0;
        }
    }
    @Override
    public String toString() {
        return name;
    }
}

package library;

import item.*;

/**
 * The Recipe class is responsible for representing the composition of ingredients to make a drink, which is the name and quantities of the ingredients.
 */
public class Recipe {
    private String name;
    private Item[] ingredients;

    /**
     * Constructor for a Recipe
     * @param name the name of the drink to be made as a string
     * @param ingredients An array of Item objects
     */
    public Recipe(String name, Item[] ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    /**
     * Getter function for the recipe name
     * @return the name of the recipe as a string
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter function for the ingredients used in the recipe
     * @return an array of Item objects
     */
    public Item[] getIngredients() {
        return this.ingredients;
    }
}

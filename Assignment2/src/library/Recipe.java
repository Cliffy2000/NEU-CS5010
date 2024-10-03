package library;

import item.*;

public class Recipe {
    private String name;
    private Item[] ingredients;

    public Recipe(String name, Item[] ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    public String getName() {
        return this.name;
    }

    public Item[] getIngredients() {
        return this.ingredients;
    }
}

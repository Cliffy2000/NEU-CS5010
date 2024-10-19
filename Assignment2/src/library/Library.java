package library;

/**
 * The Library class stores all the recipes that have been added to the brewery. It supports a query by name method to look up existing recipes.
 */
public class Library {
    private Recipe[] recipeList;
    private int recipeCount;

    public Library() {
        this.recipeList = new Recipe[4];
        this.recipeCount = 0;
    }

    /**
     * Adds a new recipe to the library. The recipe would include the name of the drink, as well as names and quantities of various ingredients.
     * @param recipe A Recipe object
     */
    public void addRecipe(Recipe recipe) {
        // Creates a larger array to store recipes if the current one is full
        if (this.recipeCount >= this.recipeList.length) {
            Recipe[] newList = new Recipe[this.recipeList.length * 2];
            // Manual array copy to avoid imports
            for (int i = 0; i < this.recipeList.length; i++) {
                newList[i] = this.recipeList[i];
            }
            this.recipeList = newList;
        }

        // Array indexing starts at 0, count starts at 1
        recipeList[recipeCount] = recipe;
        recipeCount += 1;
        System.out.printf("The recipe for %s has been added to the library.\n", recipe.getName());
    }

    /**
     * Looks up recipes by the drink name.
     * @param recipeName The name of the drink made in the recipe.
     * @return A Recipe object corresponding to the name of the drink, and null if no recipe is found.
     */
    public Recipe queryRecipe(String recipeName) {
        for (int i = 0; i < this.recipeCount; i++) {
            if (this.recipeList[i].getName().equals(recipeName)) {
                return this.recipeList[i];
            }
        }

        // Failed to find recipe with this name
        System.out.printf("Could not find the recipe for %s.\n", recipeName);
        return null;
    }
}

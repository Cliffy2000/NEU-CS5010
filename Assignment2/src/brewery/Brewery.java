package brewery;

import item.Item;
import production.*;
import inventory.*;
import library.*;

/**
 * The Brewery class is responsible for the overall flow of making drinks and managing the inventory and library. It also contains a scenario for a brewery with recipes and orders to be filled.
 *
 * @author Yichen (Cliff) Yang
 */
public class Brewery {
    private Production production;
    private Inventory inventory;
    private Library library;

    /**
     * Initializes a Brewery object with default ingredient values.
     * Sets up the inventory and library for production.
     */
    public Brewery() {
        this.production = new Production();
        this.inventory = new Inventory();
        this.library = new Library();

        this.inventory.updateIngredient("water", 100);
        this.inventory.updateIngredient("hops", 20);
        this.inventory.updateIngredient("malt", 20);
        this.inventory.updateIngredient("yeast", 15);
        this.inventory.updateIngredient("sugar", 15);
        this.inventory.updateIngredient("lemon", 15);
    }

    /**
     * Produces a drink based on its recipe by checking the ingredient inventory,
     * producing the drink, bottling it, and updating the drink inventory.
     *
     * @param drink The item representing the drink to be brewed, with its count indicating the quantity.
     */
    public void brew(Item drink) {
        Recipe recipe = this.library.queryRecipe(drink.getName());
        Item[] ingredientList = recipe.getIngredients();
        for (Item ingredient : ingredientList) {
            this.inventory.prepareIngredient(ingredient, drink.getCount());
        }

        System.out.print("\n");
        this.production.produce(drink);
        this.production.bottle();
        this.inventory.updateDrink(drink.getName(), drink.getCount());
        System.out.print("\n\n");
    }

    /**
     * The main method serves as the entry point for the Brewery application.
     * It sets up the brewery, adds recipes, and processes a batch order of drinks.
     *
     * @param args command line arguments (not used).
     */
    public static void main(String[] args) {
        Brewery brewery = new Brewery();
        System.out.print("Brewery Program start: \n\n");

        brewery.inventory.printIngredientInventory();

        Recipe recipe1 = new Recipe("IPA", new Item[] {
            new Item("water", 6),
            new Item("hops", 2),
            new Item("malt", 3),
            new Item("yeast", 2),
            new Item("sugar", 1)
        });
        brewery.library.addRecipe(recipe1);

        Recipe recipe2 = new Recipe("Pale Ale", new Item[] {
            new Item("water", 5),
            new Item("hops", 3),
            new Item("malt", 4),
            new Item("yeast", 2),
            new Item("sugar", 1)
        });
        brewery.library.addRecipe(recipe2);

        Recipe recipe3 = new Recipe("Lemonade", new Item[] {
            new Item("water", 5),
            new Item("lemon", 4),
            new Item("sugar", 4),
            new Item("mint", 1)
        });
        brewery.library.addRecipe(recipe3);
        System.out.print("\n\n");

        // An order of several batches of drinks
        Item[] order = new Item[] {
            new Item("IPA", 10),
            new Item("Pale Ale", 4),
            new Item("Lemonade", 3)
        };

        for (Item drink : order) {
            brewery.brew(drink);
        }

        brewery.inventory.printDrinkInventory();
    }
}

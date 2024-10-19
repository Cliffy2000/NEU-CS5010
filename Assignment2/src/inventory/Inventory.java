package inventory;

import item.*;

/**
 * The inventory class keeps track of both the available ingredients and the drinks that have been produced. The class contains methods of retrieving the information and changing it.
 */
public class Inventory {
    private Item[] drinkInventory;
    private int drinkTypeCount;
    private Item[] ingredientInventory;
    private int ingredientTypeCount;

    public Inventory() {
        this.drinkInventory = new Item[4];
        this.drinkTypeCount = 0;
        this.ingredientInventory = new Item[4];
        this.ingredientTypeCount = 0;
    }

    /**
     * Getter function to look up the quantity of a specific drink
     * @param drinkName the name of the drink as a String
     * @return an integer as the count
     */
    public int getDrinkCount(String drinkName) {
        for (int i = 0; i < this.drinkTypeCount; i++) {
            if (this.drinkInventory[i].getName().equals(drinkName)) {
                return this.drinkInventory[i].getCount();
            }
        }
        return 0;
    }

    /**
     * Updates the inventory by changing the quantity of a drink if it is already in the inventory, or adds is to the inventory before setting the count.
     * @param drinkName The name of the drink as a string
     * @param delta The change in quantity
     */
    public void updateDrink(String drinkName, int delta) {
        int index = -1;
        for (int i = 0; i < this.drinkTypeCount; i++) {
            if (this.drinkInventory[i].getName().equals(drinkName)) {
                index = i;
                break;
            }
        }

        // If the drink is found
        if (index != -1) {
            this.drinkInventory[index].setCount(this.drinkInventory[index].getCount() + delta);
        }
        // If the drink is not found
        else {
            if (this.drinkTypeCount >= this.drinkInventory.length) {
                Item[] newList = new Item[this.drinkInventory.length * 2];
                // Manual resize of array
                for (int i = 0; i < this.drinkInventory.length; i++) {
                    newList[i] = this.drinkInventory[i];
                }
                this.drinkInventory = newList;
            }
            this.drinkInventory[this.drinkTypeCount] = new Item(drinkName, delta);
            this.drinkTypeCount += 1;
        }
    }


    /**
     * Prints out the current drinks in the inventory as a table
     */
    public void printDrinkInventory() {
        if (this.drinkTypeCount == 0) {
            System.out.println("The drink inventory in empty.");
            return;
        }

        System.out.println("Drink Inventory: ");
        System.out.println("-------------------------");
        for (int i = 0; i < this.drinkTypeCount; i++) {
            System.out.printf("%-20s : %d\n", this.drinkInventory[i].getName(), this.drinkInventory[i].getCount());
        }
        System.out.println("-------------------------\n");
    }


    /**
     * Looks up the quantity of a specific ingredient in the inventory
     * @param ingredientName the name of the ingredient as a string
     * @return an integer representing the quantity of the ingredient
     */
    public int getIngredientCount(String ingredientName) {
        for (int i = 0; i < this.ingredientTypeCount; i++) {
            if (this.ingredientInventory[i].getName().equals(ingredientName)) {
                return this.ingredientInventory[i].getCount();
            }
        }
        return 0;
    }

    /**
     * This checks the inventory for the ingredients needed for a drink. It would make purchases if there isn't enough, before subtracting the needed amount from the inventory. This function takes into account the number of drinks that is made.
     * @param ingredient An Item that represents the ingredient, the count in this Item is the quantity required in the recipe.
     * @param multiple An integer that represents the multiplication factor on the ingredient quantities, this is usually the number of drinks to be made.
     */
    public void prepareIngredient(Item ingredient, int multiple) {
        int index = -1;
        // Find the ingredient in the inventory
        for (int i = 0; i < this.ingredientTypeCount; i++) {
            if (this.ingredientInventory[i].getName().equals(ingredient.getName())) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.printf("Ordering %d portions of %s.\n", ingredient.getCount() * multiple, ingredient.getName());
            updateIngredient(ingredient.getName(), ingredient.getCount() * multiple);
        }
        else {
            int currentCount = this.ingredientInventory[index].getCount();
            if (currentCount < ingredient.getCount() * multiple) {
                int difference = ingredient.getCount() * multiple - currentCount;
                System.out.printf("Ordering %d portions of %s.\n", difference, ingredient.getName());
                updateIngredient(ingredient.getName(), difference);
            }
        }

        System.out.printf("Using %d portions of %s\n", ingredient.getCount() * multiple, ingredient.getName());
        updateIngredient(ingredient.getName(), -ingredient.getCount() * multiple);     // negative to reduce count in inventory
    }

    /**
     * Updates the ingredient inventory, adding ingredients if there are not present in the inventory before changing the count
     * @param ingredientName the name of the ingredient as a string
     * @param delta the change in quantity as an integer
     */
    public void updateIngredient(String ingredientName, int delta) {
        int index = -1;
        for (int i = 0; i < this.ingredientTypeCount; i++) {
            if (this.ingredientInventory[i].getName().equals(ingredientName)) {
                index = i;
                break;
            }
        }

        // If the ingredient is found
        if (index != -1) {
            this.ingredientInventory[index].setCount(this.ingredientInventory[index].getCount() + delta);
        }
        // If the ingredient is not found
        else {
            if (this.ingredientTypeCount >= this.ingredientInventory.length) {
                Item[] newList = new Item[this.ingredientInventory.length * 2];
                // Manual resize of array
                for (int i = 0; i < this.ingredientInventory.length; i++) {
                    newList[i] = this.ingredientInventory[i];
                }
                this.ingredientInventory = newList;
            }
            this.ingredientInventory[this.ingredientTypeCount] = new Item(ingredientName, delta);
            this.ingredientTypeCount += 1;
        }
    }

    /**
     * Prints out the current ingredient inventory as a table
     */
    public void printIngredientInventory() {
        if (this.ingredientTypeCount == 0) {
            System.out.println("The ingredient inventory in empty.");
            return;
        }

        System.out.println("Ingredient Inventory: ");
        System.out.println("-------------------------");
        for (int i = 0; i < this.ingredientTypeCount; i++) {
            System.out.printf("%-20s : %d\n", this.ingredientInventory[i].getName(), this.ingredientInventory[i].getCount());
        }
        System.out.println("-------------------------\n");
    }
}

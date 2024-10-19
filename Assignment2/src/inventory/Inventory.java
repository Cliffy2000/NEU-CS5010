/**
 *
 *
 */


package inventory;

import item.*;

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

    public int getDrinkCount(String drinkName) {
        for (int i = 0; i < this.drinkTypeCount; i++) {
            if (this.drinkInventory[i].getName().equals(drinkName)) {
                return this.drinkInventory[i].getCount();
            }
        }
        return 0;
    }

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


    public int getIngredientCount(String ingredientName) {
        for (int i = 0; i < this.ingredientTypeCount; i++) {
            if (this.ingredientInventory[i].getName().equals(ingredientName)) {
                return this.ingredientInventory[i].getCount();
            }
        }
        return 0;
    }

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

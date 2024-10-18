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
        // The desired drink is not included in the inventory
        addDrinkType(drinkName, 0);
        return 0;
    }

    public void addDrinkType(String drinkName, int drinkCount) {
        if (this.drinkTypeCount >= this.drinkInventory.length) {
            Item[] newList = new Item[this.drinkInventory.length * 2];
            // Manual array copy to avoid imports
            for (int i = 0; i < this.drinkInventory.length; i++) {
                newList[i] = this.drinkInventory[i];
            }
            this.drinkInventory = newList;
        }

        this.drinkInventory[this.drinkTypeCount] = new Item(drinkName, drinkCount);
        this.drinkTypeCount += 1;
    }

    // This function only changes the value, any checks should be made before the function is used
    public void adjustDrinkCount(String drinkName, int delta) {
        int index = 0;
        // Find the drink in the inventory
        while (true) {
            if (this.drinkInventory[index].getName().equals(drinkName)) {
                break;
            }
            index += 1;
        }
        this.drinkInventory[index].setCount(this.drinkInventory[index].getCount() + delta);
    }

    public int getIngredientCount(String ingredientName) {
        for (int i = 0; i < this.ingredientTypeCount; i++) {
            if (this.ingredientInventory[i].getName().equals(ingredientName)) {
                return this.ingredientInventory[i].getCount();
            }
        }
        // The desired ingredient is not included in the inventory
        addIngredientType(ingredientName, 0);
        return 0;
    }

    public void addIngredientType(String ingredientName, int ingredientCount) {
        if (this.ingredientTypeCount >= this.ingredientInventory.length) {
            Item[] newList = new Item[this.ingredientInventory.length * 2];
            // Manual array copy to avoid imports
            for (int i = 0; i < this.ingredientInventory.length; i++) {
                newList[i] = this.ingredientInventory[i];
            }
            this.ingredientInventory = newList;
        }

        this.ingredientInventory[this.ingredientTypeCount] = new Item(ingredientName, ingredientCount);
        this.ingredientTypeCount += 1;
    }

    public void adjustIngredientCount(String ingredientName, int delta) {
        int index = 0;
        // Find the ingredient in the inventory
        while (true) {
            if (this.ingredientInventory[index].getName().equals(ingredientName)) {
                break;
            }
            index += 1;
        }
        this.ingredientInventory[index].setCount(this.ingredientInventory[index].getCount() + delta);
    }
}

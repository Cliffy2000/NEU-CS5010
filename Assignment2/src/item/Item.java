package item;

/**
 * The Item class is a simple yet crucial class that is used for both the ingredients and drinks in the brewery project. It contains the name and quantity of the item.
 */
public class Item {
    private String name;
    private int count;

    /**
     * Constructor method
     * @param name the String that is the name of the item
     * @param count the quantity of the item, this could be the amount of drinks in an order, the amount of ingredients either in an inventory or in a recipe
     */
    public Item(String name, int count) {
        this.name = name;
        this.count = count;
    }

    /**
     * Getter function for the name
     * @return the name as a String
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter function for the name
     * @param name the new name as a string
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter function for the count
     * @return an interger which is the count
     */
    public int getCount() {
        return this.count;
    }

    /**
     * Setter function for the count
     * @param count the new count as an integer
     */
    public void setCount(int count) {
        this.count = count;
    }
}

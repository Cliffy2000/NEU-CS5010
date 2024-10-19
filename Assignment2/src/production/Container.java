package production;

import item.*;

/**
 * The Container class represents a container in a brewery. It will be filled when producing a drink, dirty after emptying and back to clean after being cleaned. It also keeps track of what drink is in it, and the quantity of the drink.
 */
public class Container {
    private boolean empty = true;
    private boolean clean = true;
    private String content = "";
    private int quantity = 0;

    /**
     * Uses the container to create a drink, updating its state to reflect it.
     * @param drink The drink that would fill the container
     */
    public void useContainer(Item drink) {
        this.content = drink.getName();
        this.empty = false;
        this.quantity = drink.getCount();
    }

    /**
     * Empties the container and sets the container to not clean
     */
    public void emptyContainer() {
        this.content = "";
        this.quantity = 0;
        this.empty = false;
        this.clean = false;
    }

    /**
     * Cleans the container.
     */
    public void cleanContainer() {
        this.clean = true;
    }

    /**
     * Returns the name of the drink in the container.
     *
     * @return A string which is the name of the drink.
     */
    public String getContent() {
        return this.content;
    }
}

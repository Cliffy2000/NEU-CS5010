package production;

import item.*;

/**
 * The Production class handles the making of the drinks. The process is simplified with the containers as the program is not written for asynchronous behaviors and also only contains 1 container.
 */
public class Production {
    private Container container = new Container();

    /**
     * A simple function that produces a drink using the container.
     * @param drink An Item that represents the drink to be produced
     */
    public void produce(Item drink) {
        container.useContainer(drink);
        System.out.printf("%s has been produced.\n", drink.getName());
    }

    /**
     * A function for the bottling and cleaning process after producing a drink.
     */
    public void bottle() {
        String drink = container.getContent();
        container.emptyContainer();
        System.out.printf("%s has been bottled.\n", drink);
        container.cleanContainer();
        System.out.print("The container has been cleaned. \n");
    }
}

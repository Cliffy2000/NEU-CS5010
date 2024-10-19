package production;

import item.*;
import library.*;

public class Production {
    private Container container = new Container();

    public void produce(Item drink) {
        container.useContainer(drink);
        System.out.printf("%s has been produced.\n", drink.getName());
    }

    public void bottle() {
        String drink = container.getContent();
        container.emptyContainer();
        System.out.printf("%s has been bottled.\n", drink);
        container.cleanContainer();
        System.out.print("The container has been cleaned. \n");
    }
}
